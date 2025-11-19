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
	void toDomain_ShouldMapAllFieldsCorrectly() {
		UUID id = UUID.randomUUID();
		UUID customerId = UUID.randomUUID();
		UUID vehicleId = UUID.randomUUID();
		String now = OffsetDateTime.now().toString();

		ReservationDto dto = ReservationDto.builder().id(id.toString()).customerId(customerId.toString())
				.vehicleId(vehicleId.toString()).startTime(now).endTime(now).status("CONFIRMED").createdAt(now)
				.updatedAt(now).build();

		Reservation result = reservationMapper.toDomain(dto);
		assertNotNull(result);
		assertEquals(id, result.getId());
		assertEquals(customerId, result.getCustomerId());
		assertEquals(vehicleId, result.getVehicleId());
		assertEquals(ReservationStatus.CONFIRMED, result.getStatus());
		assertEquals(OffsetDateTime.parse(now), result.getStartTime());
		assertEquals(OffsetDateTime.parse(now), result.getEndTime());
	}

	@Test
	void toDomain_ShouldReturnNull_WhenInputIsNull() {
		assertNull(reservationMapper.toDomain(null));
	}

	@Test
	void toDto_ShouldMapAllFieldsCorrectly() {
		// Arrange
		UUID id = UUID.randomUUID();
		UUID customerId = UUID.randomUUID();
		UUID vehicleId = UUID.randomUUID();
		OffsetDateTime now = OffsetDateTime.now();

		Reservation reservation = Reservation.builder().id(id).customerId(customerId).vehicleId(vehicleId)
				.startTime(now).endTime(now).status(ReservationStatus.CANCELLED).createdAt(now).updatedAt(now).build();

		ReservationDto dto = reservationMapper.toDto(reservation);

		assertNotNull(dto);
		assertEquals(id.toString(), dto.getId());
		assertEquals(customerId.toString(), dto.getCustomerId());
		assertEquals(vehicleId.toString(), dto.getVehicleId());
		assertEquals("CANCELLED", dto.getStatus());
		assertEquals(now.toString(), dto.getStartTime());
		assertEquals(now.toString(), dto.getEndTime());
	}

	@Test
	void toDto_ShouldReturnNull_WhenInputIsNull() {
		assertNull(reservationMapper.toDto(null));
	}
}
