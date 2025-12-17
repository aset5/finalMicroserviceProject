package kz.rssession.commons.event;

import kz.rssession.commons.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationEvent {

    private UUID recipientUserId;   // кому
    private UUID senderUserId;      // от кого
    private NotificationType type;

    private UUID targetId;          // postId / commentId
    private LocalDateTime createdAt;
}