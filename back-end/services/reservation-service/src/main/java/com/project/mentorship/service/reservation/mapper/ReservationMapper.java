package com.project.mentorship.service.reservation.mapper;

import com.project.mentorship.contract.reservation.model.ReservationDto;
import com.project.mentorship.service.reservation.domain.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

	public Reservation map(ReservationDto reservationDto) {
		if (reservationDto == null) {
			return null;
		}

		return Reservation.builder().id(reservationDto.getId() != null ? reservationDto.getId() : null)
				.customerId(reservationDto.getCustomerId() != null ? reservationDto.getCustomerId() : null)
				.vehicleId(reservationDto.getVehicleId() != null ? reservationDto.getVehicleId() : null)
				.startTime(reservationDto.getStartTime() != null ? reservationDto.getStartTime() : null)
				.endTime(reservationDto.getEndTime() != null ? reservationDto.getEndTime() : null)
				.status(reservationDto.getStatus() != null ? reservationDto.getStatus() : null)
				.createdAt(reservationDto.getCreatedAt() != null ? reservationDto.getCreatedAt() : null)
				.updatedAt(reservationDto.getUpdatedAt() != null ? reservationDto.getUpdatedAt() : null).build();
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
