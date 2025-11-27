package com.project.mentorship.service.notification.mapper;

import com.project.mentorship.service.notification.api.dto.NotificationDto;
import com.project.mentorship.service.notification.domain.Notification;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class NotificationMapperTest {

    private final NotificationMapper mapper = new NotificationMapper();

    @Test
    void map_ShouldConvertDtoToDomain() {
        // given
        NotificationDto dto = new NotificationDto(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                "EMAIL",
                "PENDING",
                null,
                null
        );

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
        Notification notification = Notification.builder()
                .reservationId(UUID.randomUUID())
                .customerId(UUID.randomUUID())
                .build();

        // when
        NotificationDto dto = mapper.map(notification);

        // then
        assertNotNull(dto);
        assertEquals(notification.getReservationId().toString(), dto.reservationId());
        assertEquals(notification.getCustomerId().toString(), dto.customerId());
    }
}
