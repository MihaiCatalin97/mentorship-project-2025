package com.project.mentorship.service.reservation.persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.project.mentorship.service.reservation.domain.Reservation;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReservationRepositoryTest {

	private ReservationRepository reservationRepository;

	@BeforeEach
	void setUp() {
		reservationRepository = new ReservationRepository();
	}

	@Test
	void save_ShouldAddReservationToList() {

		Reservation reservation = new Reservation();
		reservation.setId(UUID.randomUUID());
		Reservation result = reservationRepository.save(reservation);
		assertNotNull(result);
		assertEquals(reservation, result, "Obiectul returnat trebuie să fie același cu cel salvat");
	}
}
