package com.project.mentorship.service.reservation.api;

import com.project.mentorship.contract.reservation.api.ReservationsApiDelegate;
import com.project.mentorship.contract.reservation.model.ReservationDto;
import com.project.mentorship.service.reservation.domain.Reservation;
import com.project.mentorship.service.reservation.mapper.ReservationMapper;
import com.project.mentorship.service.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationApiDelegateImpl implements ReservationsApiDelegate {

	private final ReservationService reservationService;
	private final ReservationMapper reservationMapper;

	@Override
	public ResponseEntity<ReservationDto> createReservation(ReservationDto request) {
		Reservation reservation = reservationMapper.map(request);
		Reservation createdReservation = reservationService.create(reservation);
		ReservationDto response = reservationMapper.map(createdReservation);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
