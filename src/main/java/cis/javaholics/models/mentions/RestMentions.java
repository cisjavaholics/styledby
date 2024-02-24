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
    private List<DocumentReference> mentionedUsers;
    private List<DocumentReference> mentionedBus;

    public RestMentions(String mentionId, Timestamp mentionedAt, DocumentReference mentionedBy, List<DocumentReference> mentionedsers, List<DocumentReference> mentionedBus, DocumentReference forumId) {
        super(mentionId, mentionedAt);
        this.mentionedBy = mentionedBy;
        this.forumId = forumId;
        this.mentionedUsers = mentionedsers;
        this.mentionedBus = mentionedBus;
    }
    // Setters and Getters for String parameters to perform Firestore queries
    public void setMentionedBy(String mentionedBy) {
        this.mentionedBy = Utility.retrieveDocumentReference("Users", mentionedBy);
    }

    public void setForumId(String forumId) {
        this.forumId = Utility.retrieveDocumentReference("Forums", forumId);
    }
    public void setMentionedUsers(ArrayList<String> mentionedUsers) {
        this.mentionedUsers = new ArrayList<>();
        for (String user : mentionedUsers) {
            this.mentionedUsers.add(Utility.retrieveDocumentReference("Users", user));
        }
    }

    public void setMentionedBus(ArrayList<String> mentionedBus) {
        this.mentionedBus = new ArrayList<>();
        for (String business : mentionedBus) {
            this.mentionedBus.add(Utility.retrieveDocumentReference("Businesses", business));
        }
    }
}
