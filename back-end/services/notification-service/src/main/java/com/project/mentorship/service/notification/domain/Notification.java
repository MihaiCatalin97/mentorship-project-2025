package com.project.mentorship.service.notification.domain;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Notification {
	private UUID id;
	private UUID reservationId;
	private UUID customerId;
	private NotificationType type;
	private NotificationStatus status;
	private OffsetDateTime sentAt;
	private OffsetDateTime createdAt;
}
