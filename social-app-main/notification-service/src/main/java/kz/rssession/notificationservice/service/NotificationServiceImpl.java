package kz.rssession.notificationservice.service;

import kz.rssession.commons.client.UserClient;
import kz.rssession.commons.dto.user.UserContactDto;
import kz.rssession.commons.event.NotificationEvent;
import kz.rssession.notificationservice.sender.MessageSender;
import kz.rssession.notificationservice.sender.MessageSenderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final UserClient userClient;
    private final MessageSenderFactory senderFactory;

    @Override
    public void process(NotificationEvent event) {

        UserContactDto contact =
                userClient.getUserContactById(event.getRecipientUserId());

        MessageSender sender =
                senderFactory.getSender(contact.getPreferredContact());

        String message = buildMessage(event);

        sender.send(contact, message);
    }

    private String buildMessage(NotificationEvent event) {
        return switch (event.getType()) {
            case POST_LIKED -> "Ваш пост получил новый лайк 👍";
            case COMMENT_ADDED -> "Новый комментарий к вашему посту 💬";
        };
    }
}