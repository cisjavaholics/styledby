package cis.javaholics.models.likes;

//import cis.javaholics.models.users.Users;
import cis.javaholics.util.Utility;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
public class RestLikes extends ALikes{

    private DocumentReference senderId;
    private DocumentReference postId;

    public RestLikes(@Nullable String likeId, Timestamp likedAt, String senderId, String postId) {
        super(likeId, likedAt);
        setSenderId(senderId);
        setPostId(postId);
    }

    public void setSenderId(String senderId) {
        this.senderId = Utility.retrieveDocumentReference("User", senderId);
    }
    public void setPostId(String postId) {
        this.postId = Utility.retrieveDocumentReference("reviews", postId);
    }
}
