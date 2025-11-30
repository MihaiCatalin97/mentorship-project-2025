package com.project.mentorship.service.notification.persistance;

import com.project.mentorship.lib.pattern.BaseRepository;
import com.project.mentorship.service.notification.domain.Notification;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepository implements BaseRepository<Notification> {
	private final List<Notification> notifications = new ArrayList<>();

	@Override
	public Notification save(Notification notification) {
		notifications.add(notification);
		return notification;
	}
}
