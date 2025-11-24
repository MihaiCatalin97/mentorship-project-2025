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
		// Given
		Reservation reservation = new Reservation();
		reservation.setId(UUID.randomUUID());

		// When
		Reservation result = reservationRepository.save(reservation);

		// Then
		assertNotNull(result);
		assertEquals(reservation, result, "The saved reservation should be the same as the input reservation");
	}
}
