package com.project.mentorship.service.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    private String id;
    private String customerId;
    private String vehicleId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
