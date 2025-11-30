package com.project.mentorship.service.notification.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.project.mentorship.service.notification.api.dto.NotificationDto;
import com.project.mentorship.service.notification.domain.Notification;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class NotificationMapperTest {

	private final NotificationMapper mapper = new NotificationMapper();

	@Test
	void map_ShouldConvertDtoToDomain() {
		// given
		NotificationDto dto = new NotificationDto(UUID.randomUUID(), UUID.randomUUID().toString(),
				UUID.randomUUID().toString(), "EMAIL", "PENDING", null, null);

		// when
		Notification result = mapper.map(dto);

		// then
		assertNotNull(result);
		assertEquals(dto.reservationId(), result.getReservationId().toString());
		assertEquals(dto.customerId(), result.getCustomerId().toString());
	}

	@Test
	void map_ShouldConvertDomainToDto() {
		// given
		Notification notification = Notification.builder().reservationId(UUID.randomUUID())
				.customerId(UUID.randomUUID()).build();

		// when
		NotificationDto dto = mapper.map(notification);

		// then
		assertNotNull(dto);
		assertEquals(notification.getReservationId().toString(), dto.reservationId());
		assertEquals(notification.getCustomerId().toString(), dto.customerId());
	}

	@Test
	void map_ShouldReturnNull_WhenDtoIsNull() {
		// when
		var result = mapper.map((NotificationDto) null);

		// then
		assertNull(result);
	}

	@Test
	void map_ShouldReturnNull_WhenNotificationIsNull() {
		// when
		var result = mapper.map((Notification) null);

		// then
		assertNull(result);
	}
}
