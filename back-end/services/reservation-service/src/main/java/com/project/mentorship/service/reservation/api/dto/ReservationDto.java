package com.project.mentorship.service.reservation.api.dto;

public record ReservationDto(
        String id, String customerId,
        String vehicleId, String startTime,
        String endTime, String status,
        String createdAt, String updatedAt
) {}
