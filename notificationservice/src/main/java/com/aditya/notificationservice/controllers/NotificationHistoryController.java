package com.aditya.notificationservice.controllers;

import com.aditya.notificationservice.dtos.NotificationHistoryResponseDto;
import com.aditya.notificationservice.services.NotificationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class NotificationHistoryController {
    //@Autowired
    NotificationHistoryService notificationHistoryService;

    public NotificationHistoryController(NotificationHistoryService notificationHistoryService) {
        this.notificationHistoryService = notificationHistoryService;
    }

    @GetMapping("/history")
    public ResponseEntity<List<NotificationHistoryResponseDto>> getNotificationHistory()
    {
        return new ResponseEntity<>(notificationHistoryService.getNotificationHistory(), HttpStatus.OK);
    }
}
