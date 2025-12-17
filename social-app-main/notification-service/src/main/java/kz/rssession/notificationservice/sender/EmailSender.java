package kz.rssession.notificationservice.sender;

import kz.rssession.commons.dto.user.UserContactDto;
import org.springframework.stereotype.Component;

@Component
public class EmailSender implements MessageSender {

    @Override
    public void send(UserContactDto contact, String message) {
        System.out.println("EMAIL → " + contact.getEmail() + ": " + message);
    }
}