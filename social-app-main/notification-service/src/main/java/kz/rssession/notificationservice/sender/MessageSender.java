package kz.rssession.notificationservice.sender;

import kz.rssession.commons.dto.user.UserContactDto;

public interface MessageSender {
    void send(UserContactDto contact, String message);
}