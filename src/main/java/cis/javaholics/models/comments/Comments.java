package cis.javaholics.models.comments;

import cis.javaholics.models.forumPosts.ForumPosts;
import cis.javaholics.models.users.Users;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Schema(description = "Represents a comment on a forum post or review")
public class Comments extends AComments{
    @Schema(example = "12345", description = "Unique ID of the sender")
    private Users senderId;
    @Schema(example = "12345", description = "Unique ID of the receiver")
    private ForumPosts forumId;

    public Comments(String commentId, String content, Timestamp time, Users senderId, ForumPosts forumId) {
        super(commentId, content, time);
        this.senderId = senderId;
        this.forumId = forumId;
    }

    public Comments(Users senderId, ForumPosts forumId) {
        this.senderId = senderId;
        this.forumId = forumId;
    }
}

