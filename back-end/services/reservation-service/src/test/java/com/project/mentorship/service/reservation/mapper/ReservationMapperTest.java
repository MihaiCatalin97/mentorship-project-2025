package com.project.mentorship.service.reservation.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.project.mentorship.service.reservation.api.dto.ReservationDto;
import com.project.mentorship.service.reservation.domain.Reservation;
import com.project.mentorship.service.reservation.domain.ReservationStatus;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReservationMapperTest {

	private ReservationMapper reservationMapper;

	@BeforeEach
	void setUp() {
		reservationMapper = new ReservationMapper();
	}

	@Test
	void toDomain_ShouldMapOffsetDateTimeFields() {
		ReservationDto dto = ReservationDto.builder().id(UUID.randomUUID().toString())
				.customerId(UUID.randomUUID().toString()).vehicleId(UUID.randomUUID().toString())
				.startTime("2024-06-01T10:15:30+01:00").endTime("2024-06-01T12:15:30+01:00").status("CONFIRMED")
				.createdAt("2024-06-01T09:00:00+01:00").updatedAt("2024-06-01T09:30:00+01:00").build();

		Reservation result = reservationMapper.toDomain(dto);

		assertThat(result.getStartTime()).isEqualTo(OffsetDateTime.parse(dto.getStartTime()));
		assertThat(result.getEndTime()).isEqualTo(OffsetDateTime.parse(dto.getEndTime()));
		assertThat(result.getCreatedAt()).isEqualTo(OffsetDateTime.parse(dto.getCreatedAt()));
		assertThat(result.getUpdatedAt()).isEqualTo(OffsetDateTime.parse(dto.getUpdatedAt()));
	}

	@Test
	void toDto_ShouldMapOffsetDateTimeFields() {
		Reservation reservation = Reservation.builder().id(UUID.randomUUID()).customerId(UUID.randomUUID())
				.vehicleId(UUID.randomUUID()).startTime(OffsetDateTime.parse("2024-06-01T10:15:30+01:00"))
				.endTime(OffsetDateTime.parse("2024-06-01T12:15:30+01:00")).status(ReservationStatus.CONFIRMED)
				.createdAt(OffsetDateTime.parse("2024-06-01T09:00:00+01:00"))
				.updatedAt(OffsetDateTime.parse("2024-06-01T09:30:00+01:00")).build();

		ReservationDto dto = reservationMapper.toDto(reservation);

		assertThat(dto.getStartTime()).isEqualTo(reservation.getStartTime().toString());
		assertThat(dto.getEndTime()).isEqualTo(reservation.getEndTime().toString());
		assertThat(dto.getCreatedAt()).isEqualTo(reservation.getCreatedAt().toString());
		assertThat(dto.getUpdatedAt()).isEqualTo(reservation.getUpdatedAt().toString());
	}

}
