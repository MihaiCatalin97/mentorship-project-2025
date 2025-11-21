package com.project.mentorship.service.reservation.service;

import com.project.mentorship.lib.pattern.BaseService;
import com.project.mentorship.service.reservation.domain.Reservation;
import com.project.mentorship.service.reservation.persistance.ReservationRepository;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService implements BaseService<Reservation> {

	private final ReservationRepository reservationRepository;

	@Override
	public Reservation create(Reservation reservation) {

		reservation.setId(UUID.randomUUID());
		reservation.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC));
		reservation.setUpdatedAt(null);

		return reservationRepository.save(reservation);
	}
}
