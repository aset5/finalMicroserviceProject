package kz.rssession.notificationservice.controller;

import kz.rssession.commons.event.NotificationEvent;
import kz.rssession.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/notifications")
@RequiredArgsConstructor
public class NotificationInternalController {

    private final NotificationService notificationService;

    @PostMapping
    public void receive(@RequestBody NotificationEvent event) {
        notificationService.process(event);
    }
}