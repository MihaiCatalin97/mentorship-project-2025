package com.project.mentorship.service.reservation.mapper;

import com.project.mentorship.service.reservation.api.dto.ReservationDto;
import com.project.mentorship.service.reservation.domain.Reservation;
import com.project.mentorship.service.reservation.domain.ReservationStatus;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

	public Reservation map(ReservationDto dto) {
		if (dto == null) {
			return null;
		}

		return Reservation.builder().id(dto.id() != null ?
                        UUID.fromString(dto.id()) : null)
				.customerId(dto.customerId() != null ?
                        UUID.fromString(dto.customerId()) : null)
				.vehicleId(dto.vehicleId() != null ?
                        UUID.fromString(dto.vehicleId()) : null)
				.startTime(dto.startTime() != null ?
                        OffsetDateTime.parse(dto.startTime()) : null)
				.endTime(dto.endTime() != null ?
                        OffsetDateTime.parse(dto.endTime()) : null)
				.status(dto.status() != null ?
                        ReservationStatus.valueOf(dto.status()) : null)
				.createdAt(dto.createdAt() != null ?
                        OffsetDateTime.parse(dto.createdAt()) : null)
				.updatedAt(dto.updatedAt() != null ?
                        OffsetDateTime.parse(dto.updatedAt()) : null).build();
	}

	public ReservationDto map(Reservation reservation) {
		if (reservation == null) {
			return null;
		}

		return new ReservationDto(reservation.getId() != null ?
                reservation.getId().toString() : null,
				reservation.getCustomerId() != null ?
                        reservation.getCustomerId().toString() : null,
				reservation.getVehicleId() != null ?
                        reservation.getVehicleId().toString() : null,
				reservation.getStartTime() != null ?
                        reservation.getStartTime().toString() : null,
				reservation.getEndTime() != null ?
                        reservation.getEndTime().toString() : null,
				reservation.getStatus() != null ?
                        reservation.getStatus().name() : null,
				reservation.getCreatedAt() != null ?
                        reservation.getCreatedAt().toString() : null,
				reservation.getUpdatedAt() != null ?
                        reservation.getUpdatedAt().toString() : null);
	}
}
