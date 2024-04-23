package cis.javaholics.models.comments;

import cis.javaholics.models.forumPosts.ForumPosts;
import cis.javaholics.models.users.Users;
import cis.javaholics.util.Utility;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestComments extends AComments {
    private DocumentReference senderId;
    private DocumentReference forumId;

    public RestComments(String commentId, String content, Timestamp time, String senderId, String forumId) {
        super(commentId, content, time);
        setSenderId(senderId);
        setforumId(forumId);
    }
    public void setSenderId(String senderId) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        this.senderId = Utility.retrieveDocumentReference("User", senderId);
    }
    public void setforumId(String forumId) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        this.forumId = Utility.retrieveDocumentReference("User", forumId);
    }


}
