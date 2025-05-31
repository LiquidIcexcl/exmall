package org.liquidice.exmall.logistics.controller;

import lombok.RequiredArgsConstructor;
import org.liquidice.exmall.logistics.service.LogisticsService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LogisticsController {

    private final LogisticsService logisticsService;
}
