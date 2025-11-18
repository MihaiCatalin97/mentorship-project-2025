package com.project.mentorship.service.reservation.mapper;

import com.project.mentorship.service.reservation.api.dto.ReservationDto;
import com.project.mentorship.service.reservation.domain.Reservation;
import com.project.mentorship.service.reservation.domain.ReservationStatus;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

	public Reservation toDomain(ReservationDto dto) {
		if (dto == null) {
			return null;
		}

		return Reservation.builder().id(dto.getId() != null ? UUID.fromString(dto.getId()) : null)
				.customerId(dto.getCustomerId() != null ? UUID.fromString(dto.getCustomerId()) : null)
				.vehicleId(dto.getVehicleId() != null ? UUID.fromString(dto.getVehicleId()) : null)
				.startTime(dto.getStartTime() != null ? OffsetDateTime.parse(dto.getStartTime()) : null)
				.endTime(dto.getEndTime() != null ? OffsetDateTime.parse(dto.getEndTime()) : null)
				.status(dto.getStatus() != null ? ReservationStatus.valueOf(dto.getStatus()) : null)
				.createdAt(dto.getCreatedAt() != null ? OffsetDateTime.parse(dto.getCreatedAt()) : null)
				.updatedAt(dto.getUpdatedAt() != null ? OffsetDateTime.parse(dto.getUpdatedAt()) : null).build();
	}

	public ReservationDto toDto(Reservation reservation) {
		if (reservation == null) {
			return null;
		}

		return ReservationDto.builder().id(reservation.getId() != null ? reservation.getId().toString() : null)
				.customerId(reservation.getCustomerId() != null ? reservation.getCustomerId().toString() : null)
				.vehicleId(reservation.getVehicleId() != null ? reservation.getVehicleId().toString() : null)
				.startTime(reservation.getStartTime() != null ? reservation.getStartTime().toString() : null)
				.endTime(reservation.getEndTime() != null ? reservation.getEndTime().toString() : null)
				.status(reservation.getStatus() != null ? reservation.getStatus().name() : null)
				.createdAt(reservation.getCreatedAt() != null ? reservation.getCreatedAt().toString() : null)
				.updatedAt(reservation.getUpdatedAt() != null ? reservation.getUpdatedAt().toString() : null).build();
	}
}
