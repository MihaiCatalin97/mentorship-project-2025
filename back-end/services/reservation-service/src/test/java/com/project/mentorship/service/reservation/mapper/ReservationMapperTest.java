package com.project.mentorship.service.reservation.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.project.mentorship.contract.reservation.model.ReservationDto;
import com.project.mentorship.service.reservation.domain.Reservation;
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

		ReservationDto dto = new ReservationDto(customerId, vehicleId, now, now, ReservationDto.StatusEnum.CONFIRMED);
		dto.setId(id);
		dto.setCreatedAt(now);
		dto.setUpdatedAt(now);

		// When
		Reservation result = reservationMapper.map(dto);

		// Then
		assertNotNull(result);
		assertEquals(id, result.getId());
		assertEquals(customerId, result.getCustomerId());
		assertEquals(vehicleId, result.getVehicleId());
		assertEquals(ReservationDto.StatusEnum.CONFIRMED, result.getStatus());
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
		ReservationDto dto = new ReservationDto(null, // customerId
				null, // vehicleId
				null, // startTime
				null, // endTime
				null // status
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
				.startTime(now).endTime(now).status(ReservationDto.StatusEnum.CANCELLED).createdAt(now).updatedAt(now)
				.build();

		// When
		ReservationDto dto = reservationMapper.map(reservation);

		// Then
		assertNotNull(dto);
		assertEquals(id, dto.getId());
		assertEquals(customerId, dto.getCustomerId());
		assertEquals(vehicleId, dto.getVehicleId());
		assertEquals(ReservationDto.StatusEnum.CANCELLED, dto.getStatus());
		assertEquals(now, dto.getStartTime());
		assertEquals(now, dto.getEndTime());
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
		assertNull(dto.getId());
		assertNull(dto.getCustomerId());
		assertNull(dto.getVehicleId());
		assertNull(dto.getStartTime());
		assertNull(dto.getEndTime());
		assertNull(dto.getStatus());
		assertNull(dto.getCreatedAt());
		assertNull(dto.getUpdatedAt());
	}
}
