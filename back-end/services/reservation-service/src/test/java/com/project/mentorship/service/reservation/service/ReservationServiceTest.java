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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

	@Mock
	private ReservationRepository reservationRepository;

	@InjectMocks
	private ReservationService reservationService;

	private final Reservation reservation = new Reservation().setId(UUID.randomUUID());

	@Test
	void create_ShouldSaveAndReturnReservation() {
		// Given
		when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

		// When
		Reservation result = reservationService.create(reservation);

		// Then
		assertNotNull(result);
		assertEquals(reservation.getId(), result.getId());
		verify(reservationRepository, times(1)).save(reservation);
	}
}
