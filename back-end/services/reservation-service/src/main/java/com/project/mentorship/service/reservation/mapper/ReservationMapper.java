package com.project.mentorship.service.reservation.mapper;

import com.project.mentorship.contract.reservation.model.ReservationDto;
import com.project.mentorship.service.reservation.domain.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

	public Reservation map(ReservationDto dto) {
		if (dto == null) {
			return null;
		}

		return Reservation.builder().id(dto.getId() != null ? dto.getId() : null)
				.customerId(dto.getCustomerId() != null ? dto.getCustomerId() : null)
				.vehicleId(dto.getVehicleId() != null ? dto.getVehicleId() : null)
				.startTime(dto.getStartTime() != null ? dto.getStartTime() : null)
				.endTime(dto.getEndTime() != null ? dto.getEndTime() : null)
				.status(dto.getStatus() != null ? dto.getStatus() : null)
				.createdAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : null)
				.updatedAt(dto.getUpdatedAt() != null ? dto.getUpdatedAt() : null).build();
	}

	public ReservationDto map(Reservation reservation) {
		if (reservation == null) {
			return null;
		}
		ReservationDto reservationDto = new ReservationDto();

		reservationDto.setId(reservation.getId());
		reservationDto.setCustomerId(reservation.getCustomerId());
		reservationDto.setVehicleId(reservation.getVehicleId());
		reservationDto.setStartTime(reservation.getStartTime());
		reservationDto.setEndTime(reservation.getEndTime());
		reservationDto.setStatus(reservation.getStatus());
		reservationDto.setCreatedAt(reservation.getCreatedAt());
		reservationDto.setUpdatedAt(reservation.getUpdatedAt());

		return reservationDto;

	}
}
