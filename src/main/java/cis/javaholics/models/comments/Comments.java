package cis.javaholics.models.comments;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Comments extends AComments{
    private Users senderId;
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

