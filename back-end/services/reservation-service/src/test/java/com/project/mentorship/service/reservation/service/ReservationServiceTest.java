package com.project.mentorship.service.reservation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.mentorship.service.reservation.domain.Reservation;
import com.project.mentorship.service.reservation.persistance.ReservationRepository;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ReservationServiceTest {

	@Mock
	private ReservationRepository reservationRepository;

	@InjectMocks
	private ReservationService reservationService;

	private Reservation reservation;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		reservation = new Reservation();
		reservation.setId(UUID.randomUUID());
	}

	@Test
	void create_ShouldSaveAndReturnReservation() {
		when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
		Reservation result = reservationService.create(reservation);
		assertNotNull(result);
		assertEquals(reservation.getId(), result.getId());
		verify(reservationRepository, times(1)).save(reservation);
	}
}
