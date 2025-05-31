package org.liquidice.exmall.message.controller;

import lombok.RequiredArgsConstructor;
import org.liquidice.exmall.message.service.MessageService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
}
