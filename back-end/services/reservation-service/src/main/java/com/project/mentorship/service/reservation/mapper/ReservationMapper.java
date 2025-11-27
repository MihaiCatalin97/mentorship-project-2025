package com.project.mentorship.service.reservation.mapper;

import com.project.mentorship.service.reservation.api.dto.ReservationDto;
import com.project.mentorship.service.reservation.domain.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

	public Reservation map(ReservationDto dto) {
		if (dto == null) {
			return null;
		}

		return Reservation.builder().id(dto.id() != null ? dto.id() : null)
				.customerId(dto.customerId() != null ? dto.customerId() : null)
				.vehicleId(dto.vehicleId() != null ? dto.vehicleId() : null)
				.startTime(dto.startTime() != null ? dto.startTime() : null)
				.endTime(dto.endTime() != null ? dto.endTime() : null)
				.status(dto.status() != null ? dto.status() : null)
				.createdAt(dto.createdAt() != null ? dto.createdAt() : null)
				.updatedAt(dto.updatedAt() != null ? dto.updatedAt() : null).build();
	}

	public ReservationDto map(Reservation reservation) {
		if (reservation == null) {
			return null;
		}

		return new ReservationDto(reservation.getId() != null ? reservation.getId() : null,
				reservation.getCustomerId() != null ? reservation.getCustomerId() : null,
				reservation.getVehicleId() != null ? reservation.getVehicleId() : null,
				reservation.getStartTime() != null ? reservation.getStartTime() : null,
				reservation.getEndTime() != null ? reservation.getEndTime() : null,
				reservation.getStatus() != null ? reservation.getStatus() : null,
				reservation.getCreatedAt() != null ? reservation.getCreatedAt() : null,
				reservation.getUpdatedAt() != null ? reservation.getUpdatedAt() : null);
	}
}
