package com.project.mentorship.service.reservation.persistance;

import com.project.mentorship.lib.pattern.Repository.BaseRepository;
import com.project.mentorship.service.reservation.domain.Reservation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepository implements BaseRepository<Reservation> {
	private final List<Reservation> reservations = new ArrayList<>();

	@Override
	public Reservation save(Reservation reservation) {
		reservations.add(reservation);
		return reservation;
	}

}
