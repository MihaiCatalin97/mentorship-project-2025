package com.project.mentorship.service.reservation.service;

import com.project.mentorship.lib.pattern.Repository.BaseService;
import com.project.mentorship.service.reservation.domain.Reservation;
import com.project.mentorship.service.reservation.persistance.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService implements BaseService<Reservation> {

	private final ReservationRepository reservationRepository;

	@Override
	public Reservation create(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

}
