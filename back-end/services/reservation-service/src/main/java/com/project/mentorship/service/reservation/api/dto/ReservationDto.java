package com.project.mentorship.service.reservation.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDto {
	private String id;
	private String customerId;
	private String vehicleId;
	private String startTime;
	private String endTime;
	private String status;
	private String createdAt;
	private String updatedAt;
}
