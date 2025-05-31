package org.liquidice.exmall.message.controller;

import lombok.RequiredArgsConstructor;
import org.liquidice.exmall.framework.result.Result;
import org.liquidice.exmall.framework.result.Results;
import org.liquidice.exmall.message.dto.req.MessageReqDTO;
import org.liquidice.exmall.message.dto.resp.MessageRespDTO;
import org.liquidice.exmall.message.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    /**
     * 根据用户id查询消息
     */
    @GetMapping("/api/message/v1/message/{uid}")
    public Result<List<MessageRespDTO>> getMessageById(@PathVariable("uid") Long uid) {
        return Results.success(messageService.getMessageById(uid));
    }

    /**
     * 发送消息
     */
    @PostMapping("/api/message/v1/message")
    public Result<Void> sendMessage(@RequestBody MessageReqDTO message) {
        messageService.sendMessage(message);
        return Results.success();
    }

    /**
     * 删除消息
     */
    @DeleteMapping("/api/message/v1/message")
    public Result<Void> deleteMessage(@RequestParam("id") Long id) {
        messageService.deleteMessage(id);
        return Results.success();
    }
}
