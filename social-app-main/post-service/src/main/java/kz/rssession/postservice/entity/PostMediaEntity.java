package kz.rssession.postservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "post_media")
@IdClass(PostMediaId.class)
public class PostMediaEntity {

    @Id
    @Column(name = "post_id")
    private UUID postId;

    @Id
    @Column(name = "file_id")
    private UUID fileId;
}
