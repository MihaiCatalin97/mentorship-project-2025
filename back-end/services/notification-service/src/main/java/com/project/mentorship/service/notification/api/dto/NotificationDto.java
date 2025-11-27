package com.project.mentorship.service.notification.api.dto;

public record NotificationDto(
        String id,
        String reservationId,
        String customerId,
        String type,
        String status,
        String sentAt,
        String createdAt)
{}
