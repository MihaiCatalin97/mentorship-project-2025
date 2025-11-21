package com.project.mentorship.service.reservation.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.project.mentorship.service.reservation.api.dto.ReservationDto;
import com.project.mentorship.service.reservation.domain.Reservation;
import com.project.mentorship.service.reservation.domain.ReservationStatus;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class ReservationMapperTest {

	private final ReservationMapper reservationMapper = new ReservationMapper();

	@Test
	void map_ShouldMapAllFieldsCorrectly_FromDtoToDomain() {
		// Given
		UUID id = UUID.randomUUID();
		UUID customerId = UUID.randomUUID();
		UUID vehicleId = UUID.randomUUID();
		String now = OffsetDateTime.now().toString();

		ReservationDto dto = new ReservationDto(id.toString(), customerId.toString(), vehicleId.toString(), now, now,
				"CONFIRMED", now, now);

		// When
		Reservation result = reservationMapper.map(dto);

		// Then
		assertNotNull(result);
		assertEquals(id, result.getId());
		assertEquals(customerId, result.getCustomerId());
		assertEquals(vehicleId, result.getVehicleId());
		assertEquals(ReservationStatus.CONFIRMED, result.getStatus());
		assertEquals(OffsetDateTime.parse(now), result.getStartTime());
		assertEquals(OffsetDateTime.parse(now), result.getEndTime());
	}

	@Test
	void map_ShouldReturnNull_WhenDtoIsNull() {
		// Given
		ReservationDto dto = null;

		// When
		Reservation result = reservationMapper.map(dto);

		// Then
		assertNull(result);
	}

	@Test
	void map_ShouldMapAllFieldsCorrectly_FromDomainToDto() {
		// Given
		UUID id = UUID.randomUUID();
		UUID customerId = UUID.randomUUID();
		UUID vehicleId = UUID.randomUUID();
		OffsetDateTime now = OffsetDateTime.now();

		Reservation reservation = Reservation.builder().id(id).customerId(customerId).vehicleId(vehicleId)
				.startTime(now).endTime(now).status(ReservationStatus.CANCELLED).createdAt(now).updatedAt(now).build();

		// When
		ReservationDto dto = reservationMapper.map(reservation);

		// Then
		assertNotNull(dto);
		assertEquals(id.toString(), dto.id());
		assertEquals(customerId.toString(), dto.customerId());
		assertEquals(vehicleId.toString(), dto.vehicleId());
		assertEquals("CANCELLED", dto.status());
		assertEquals(now.toString(), dto.startTime());
		assertEquals(now.toString(), dto.endTime());
	}

	@Test
	void map_ShouldReturnNull_WhenDomainIsNull() {
		// Given
		Reservation reservation = null;

		// When
		ReservationDto dto = reservationMapper.map(reservation);

		// Then
		assertNull(dto);
	}
}
