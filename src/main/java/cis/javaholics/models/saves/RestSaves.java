package cis.javaholics.models.saves;

import cis.javaholics.util.Utility;
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

    public void setUserId(String userId) {
        this.userId = Utility.retrieveDocumentReference("Users", userId);
    }

    public void setForumId(String forumId) {
        this.forumId = Utility.retrieveDocumentReference("ForumPosts", forumId);
    }
}
