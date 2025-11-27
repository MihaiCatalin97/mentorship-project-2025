package com.project.mentorship.service.notification.persistance;

import com.project.mentorship.lib.pattern.BaseRepository;
import com.project.mentorship.service.notification.domain.Notification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NotificationRepository implements BaseRepository<Notification> {
    private final List<Notification> notifications= new ArrayList<>();

    @Override
    public Notification save(Notification notification) {
        notifications.add(notification);
        return notification;
    }
}
