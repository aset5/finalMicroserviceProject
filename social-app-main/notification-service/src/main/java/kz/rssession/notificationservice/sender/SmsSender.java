package kz.rssession.notificationservice.sender;

import kz.rssession.commons.dto.user.UserContactDto;
import org.springframework.stereotype.Component;

@Component
public class SmsSender implements MessageSender {

    @Override
    public void send(UserContactDto contact, String message) {
        System.out.println("SMS → " + contact.getPhoneNumber() + ": " + message);
    }
}