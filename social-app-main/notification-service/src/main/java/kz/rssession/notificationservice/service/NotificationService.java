package kz.rssession.notificationservice.service;

import kz.rssession.commons.event.NotificationEvent;

public interface NotificationService {
    void process(NotificationEvent event);
}