package kz.rssession.notificationservice.sender;

import kz.rssession.commons.enums.PreferredContact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageSenderFactory {

    private final EmailSender emailSender;
    private final SmsSender smsSender;
    private final TelegramSender telegramSender;

    public MessageSender getSender(PreferredContact contact) {
        return switch (contact) {
            case EMAIL -> emailSender;
            case SMS -> smsSender;
            case TELEGRAM -> telegramSender;
        };
    }
}