package cis.javaholics.models.mentions;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import cis.javaholics.util.Utility;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RestMentions extends AMentions {

    private DocumentReference mentionedBy;
    private DocumentReference forumId;
    private DocumentReference mentionedBus;

    public RestMentions(String mentionId, Timestamp mentionedAt, String mentionedBy, String mentionedBus, String forumId) {
        super(mentionId, mentionedAt);
        setMentionedBy(mentionedBy);
        setForumId(forumId);
        setMentionedBus(mentionedBus);
    }
    // Setters and Getters for String parameters to perform Firestore queries
    public void setMentionedBy(String mentionedBy) {
        this.mentionedBy = Utility.retrieveDocumentReference("Users", mentionedBy);
    }

    public void setForumId(String forumId) {
        this.forumId = Utility.retrieveDocumentReference("ForumPost", forumId);
    }

    public void setMentionedBus(String mentionedBus) {
        this.mentionedBus = Utility.retrieveDocumentReference("Business", mentionedBus);
    }
}
