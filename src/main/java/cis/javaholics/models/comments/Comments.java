package cis.javaholics.models.comments;

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
    private Users receiverId;

    public Comments(String commentId, String content, Timestamp time, Users senderId, Users receiverId) {
        super(commentId, content, time);
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public Comments(Users senderId, Users receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}

