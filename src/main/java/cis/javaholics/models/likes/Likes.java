package cis.javaholics.models.likes;

import cis.javaholics.models.reviews.Reviews;
import cis.javaholics.models.users.Users;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;


@Data
@NoArgsConstructor
public class Likes extends ALikes {

    private Users senderId;
    private Reviews postId;

    public Likes(@Nullable String likeId, Timestamp likedAt, Users senderId, Reviews postId) {
        super(likeId, likedAt);
        this.senderId = senderId;
        this.postId = postId;
    }

}
