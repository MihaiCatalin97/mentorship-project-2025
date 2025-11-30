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
		OffsetDateTime now = OffsetDateTime.now();

		ReservationDto dto = new ReservationDto(id, customerId, vehicleId, now, now, ReservationStatus.CONFIRMED, now,
				now);

		// When
		Reservation result = reservationMapper.map(dto);

		// Then
		assertNotNull(result);
		assertEquals(id, result.getId());
		assertEquals(customerId, result.getCustomerId());
		assertEquals(vehicleId, result.getVehicleId());
		assertEquals(ReservationStatus.CONFIRMED, result.getStatus());
		assertEquals(now, result.getStartTime());
		assertEquals(now, result.getEndTime());
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
	void map_ShouldHandleNullFields_WhenDtoHasNullValues() {
		// Given: DTO with all fields null to cover ternary branches
		ReservationDto dto = new ReservationDto(null, // id
				null, // customerId
				null, // vehicleId
				null, // startTime
				null, // endTime
				null, // status
				null, // createdAt
				null // updatedAt
		);

		// When
		Reservation result = reservationMapper.map(dto);

		// Then
		assertNotNull(result);
		assertNull(result.getId());
		assertNull(result.getCustomerId());
		assertNull(result.getVehicleId());
		assertNull(result.getStartTime());
		assertNull(result.getEndTime());
		assertNull(result.getStatus());
		assertNull(result.getCreatedAt());
		assertNull(result.getUpdatedAt());
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
		assertEquals(id, dto.id());
		assertEquals(customerId, dto.customerId());
		assertEquals(vehicleId, dto.vehicleId());
		assertEquals(ReservationStatus.CANCELLED, dto.status());
		assertEquals(now, dto.startTime());
		assertEquals(now, dto.endTime());
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

	@Test
	void map_ShouldHandleNullFields_WhenDomainHasNullValues() {
		// Given: domain object with all fields null to cover ternary branches
		Reservation reservation = Reservation.builder().id(null).customerId(null).vehicleId(null).startTime(null)
				.endTime(null).status(null).createdAt(null).updatedAt(null).build();

		// When
		ReservationDto dto = reservationMapper.map(reservation);

		// Then
		assertNotNull(dto);
		assertNull(dto.id());
		assertNull(dto.customerId());
		assertNull(dto.vehicleId());
		assertNull(dto.startTime());
		assertNull(dto.endTime());
		assertNull(dto.status());
		assertNull(dto.createdAt());
		assertNull(dto.updatedAt());
	}
}
