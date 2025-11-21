package com.project.mentorship.service.reservation.api;

import com.project.mentorship.service.reservation.api.dto.ReservationDto;
import com.project.mentorship.service.reservation.mapper.ReservationMapper;
import com.project.mentorship.service.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;
	private final ReservationMapper reservationMapper;

	@PostMapping
	public ResponseEntity<ReservationDto> create(@RequestBody ReservationDto request) {
		var reservation = reservationMapper.map(request);
		var createdReservation = reservationService.create(reservation);
		var response = reservationMapper.map(createdReservation);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
