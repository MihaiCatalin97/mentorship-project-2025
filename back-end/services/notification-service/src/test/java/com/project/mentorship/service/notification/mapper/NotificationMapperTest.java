package com.project.mentorship.service.notification.mapper;

import static com.project.mentorship.contract.notification.model.NotificationDto.StatusEnum.PENDING;
import static com.project.mentorship.contract.notification.model.NotificationDto.TypeEnum.EMAIL;
import static com.project.mentorship.contract.notification.model.NotificationDto.TypeEnum.SMS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.project.mentorship.contract.notification.model.NotificationDto;
import com.project.mentorship.service.notification.domain.Notification;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class NotificationMapperTest {

	private final NotificationMapper mapper = new NotificationMapper();

	@Test
	void map_ShouldConvertDtoToDomain() {
		// given
		NotificationDto dto = new NotificationDto(UUID.randomUUID(), UUID.randomUUID(), EMAIL, PENDING);
		dto.setId(UUID.randomUUID());
		dto.setSentAt(null);
		dto.setCreatedAt(null);

		// when
		Notification result = mapper.map(dto);

		// then
		assertNotNull(result);
		assertEquals(dto.getReservationId(), result.getReservationId());
		assertEquals(dto.getCustomerId(), result.getCustomerId());
	}

	@Test
	void map_ShouldConvertDomainToDto() {
		// given
		Notification notification = Notification.builder().reservationId(UUID.randomUUID())
				.customerId(UUID.randomUUID()).build();
		notification.setStatus(PENDING);
		notification.setType(SMS);
		notification.setId(null);
		notification.setSentAt(null);
		notification.setCreatedAt(null);

		// when
		NotificationDto dto = mapper.map(notification);

		// then
		assertNotNull(dto);
		assertEquals(notification.getReservationId(), dto.getReservationId());
		assertEquals(notification.getCustomerId(), dto.getCustomerId());
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
