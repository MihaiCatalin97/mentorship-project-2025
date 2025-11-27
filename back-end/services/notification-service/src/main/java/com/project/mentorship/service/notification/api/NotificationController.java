package com.project.mentorship.service.notification.api;

import com.project.mentorship.service.notification.api.dto.NotificationDto;
import com.project.mentorship.service.notification.mapper.NotificationMapper;
import com.project.mentorship.service.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;

    @PostMapping
    public ResponseEntity<NotificationDto> create(@RequestBody NotificationDto request) {
        var notification = notificationMapper.map(request);
        var createdNotification = notificationService.create(notification);
        var response = notificationMapper.map(createdNotification);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
