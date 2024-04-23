package cis.javaholics.models.saves;

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
public class RestSaves extends ASaves{

    private DocumentReference userId;
    private DocumentReference forumId;

    public RestSaves(String saveId, Timestamp savedAt, String userId, String forumId) {
        super(saveId, savedAt);
        setUserId(userId);
        setForumId(forumId);
    }
    public void setUserId(String userId) {
        this.userId = Utility.retrieveDocumentReference("User", userId);
    }

    public void setForumId(String forumId) {
        this.forumId = Utility.retrieveDocumentReference("ForumPost", forumId);
    }
}
