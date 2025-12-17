package kz.rssession.commons.client;

import kz.rssession.commons.event.NotificationEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "notification-service",
        path = "/internal/notifications"
)
public interface NotificationClient {

    @PostMapping
    void send(@RequestBody NotificationEvent event);
}