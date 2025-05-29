package org.liquidice.exmall.order.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * 雪花算法分布式唯一 ID 生成器
 * 整合 Spring 注解，支持配置文件参数
 */
//@Component
public class SnowFlakeIdGenerator {
    // 起始时间戳 (2020-01-01 00:00:00)
    private final Long startEpoch = 1577836800000L;

    // 各部分占用的位数
    private final Long datacenterIdBits = 5L;
    private final Long machineIdBits = 5L;
    private final Long sequenceBits = 12L;

    // 各部分的最大值
    private final Long maxDatacenterId = -1L ^ (-1L << datacenterIdBits); // 31
    private final Long maxMachineId = -1L ^ (-1L << machineIdBits);       // 31
    private final Long maxSequence = -1L ^ (-1L << sequenceBits);          // 4095

    // 各部分向左的位移
    private final Long machineIdShift = sequenceBits;
    private final Long datacenterIdShift = sequenceBits + machineIdBits;
    private final Long timestampShift = sequenceBits + machineIdBits + datacenterIdBits;

    private final Long datacenterId;
    private final Long machineId;
    private Long sequence = 0L;
    private Long lastTimestamp = -1L;

    // 构造函数注入参数
    public SnowFlakeIdGenerator(
            @Value("${snowflake.datacenter-id:1}") Long datacenterId,
            @Value("${snowflake.machine-id:1}") Long machineId
    ) {
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException("Datacenter ID must be between 0 and " + maxDatacenterId);
        }
        if (machineId > maxMachineId || machineId < 0) {
            throw new IllegalArgumentException("Machine ID must be between 0 and " + maxMachineId);
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    /**
     * 生成下一个唯一 ID
     */
    public synchronized Long nextId() {
        Long currentTimestamp = System.currentTimeMillis();

        // 处理时钟回拨
        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id for " +
                    (lastTimestamp - currentTimestamp) + " milliseconds");
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0) {
                currentTimestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        // 生成最终 ID
        return ((currentTimestamp - startEpoch) << timestampShift) |
                (datacenterId << datacenterIdShift) |
                (machineId << machineIdShift) |
                sequence;
    }

    private Long waitNextMillis(Long lastTimestamp) {
        Long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
