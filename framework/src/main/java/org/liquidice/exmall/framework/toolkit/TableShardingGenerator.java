package org.liquidice.exmall.framework.toolkit;

public class TableShardingGenerator {

    /**
     * 生成分表SQL语句
     * @param baseSql 基础SQL语句
     * @param tableCount 分表数量
     * @return 生成的分表SQL语句列表
     */
    public static String[] generateShardedTableSql(String baseSql, int tableCount) {
        if (baseSql == null || baseSql.trim().isEmpty()) {
            throw new IllegalArgumentException("基础SQL语句不能为空");
        }
        if (tableCount <= 0) {
            throw new IllegalArgumentException("分表数量必须大于0");
        }

        String[] result = new String[tableCount];
        String tableName = extractTableName(baseSql);

        for (int i = 0; i < tableCount; i++) {
            String shardedTableName = tableName + "_" + i;
            result[i] = replaceTableName(baseSql, tableName, shardedTableName);
        }

        return result;
    }

    /**
     * 从SQL语句中提取表名
     */
    private static String extractTableName(String sql) {
        String lowerCaseSql = sql.toLowerCase();
        int tableIndex = lowerCaseSql.indexOf("table");
        if (tableIndex == -1) {
            throw new IllegalArgumentException("SQL语句中未找到TABLE关键字");
        }

        int nameStartIndex = findTableNameStart(sql, tableIndex);
        int nameEndIndex = findTableNameEnd(sql, nameStartIndex);

        return sql.substring(nameStartIndex, nameEndIndex);
    }

    private static int findTableNameStart(String sql, int tableIndex) {
        int nameStartIndex = tableIndex + 5;
        while (nameStartIndex < sql.length() && Character.isWhitespace(sql.charAt(nameStartIndex))) {
            nameStartIndex++;
        }
        return nameStartIndex;
    }

    private static int findTableNameEnd(String sql, int nameStartIndex) {
        int nameEndIndex = nameStartIndex;
        while (nameEndIndex < sql.length()) {
            char c = sql.charAt(nameEndIndex);
            if (Character.isWhitespace(c) || c == '(' || c == ';' || c == '.') {
                break;
            }
            nameEndIndex++;
        }
        return nameEndIndex;
    }

    /**
     * 替换SQL语句中的表名
     */
    private static String replaceTableName(String sql, String originalTableName, String newTableName) {
        String pattern = "\\b" + originalTableName + "\\b";
        return sql.replaceAll(pattern, newTableName);
    }

    public static void main(String[] args) {
        // 使用三引号字符串简化多行SQL的定义
        String baseSql = """
                create table t_user
                (
                    uid           bigint auto_increment comment '用户ID'
                        primary key,
                    username      varchar(256) null comment '用户名称',
                    password      varchar(512) null comment '用户密码',
                    phone         varchar(128) null comment '用户手机号',
                    email         varchar(512) null comment '用户邮箱',
                    avatar_url    varchar(512) null comment '头像图片地址',
                    is_merchant    tinyint(1)   null comment '是否为商家标识 0：非 1：是',
                    shop_id       bigint       null,
                    deletion_time bigint       null comment '注销时间戳',
                    create_time   datetime     null comment '创建时间',
                    update_time   datetime     null comment '修改时间',
                    del_flag      tinyint(1)   null comment '删除标识 0：未删除 1：已删除',
                    constraint idx_unique_username
                        unique (username)
                );
            """;

        int tableCount = 16;

        // 生成SQL并存储在变量中
        String[] shardedSqls = generateShardedTableSql(baseSql, tableCount);

        // 这里可以将shardedSqls保存到文件或进行其他处理
        // 以下为示例：将生成的SQL存储到一个字符串变量中
        StringBuilder allSqls = new StringBuilder();
        for (String sql : shardedSqls) {
            allSqls.append(sql).append("\n\n");
            // 输出
            System.out.println(sql);
        }

        // 现在allSqls变量包含了所有分表SQL，可以进行手动维护
        System.out.println("已生成" + tableCount + "个分表SQL语句");
    }
}