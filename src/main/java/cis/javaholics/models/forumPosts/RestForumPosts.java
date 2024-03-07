package cis.javaholics.models.forumPosts;

import cis.javaholics.util.Utility;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RestForumPosts extends AForumPosts {
    private DocumentReference postedBy;
    private List<DocumentReference> likes;
    private List<DocumentReference> comments;

    public RestForumPosts(@Nullable String fPostId, String topic, String description, String title, List<String> photos, Timestamp postedAt, DocumentReference postedBy, List<DocumentReference> likes, List<DocumentReference> comments) {
        super(fPostId, topic, description, title, photos, postedAt);
        this.postedBy = postedBy;
        this.likes = likes;
        this.comments = comments;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = Utility.retrieveDocumentReference("Users", postedBy);
    }

    public void setLikes(ArrayList<String> likes) {

        this.likes = new ArrayList<>();
        for(String like : likes) {
            this.likes.add(Utility.retrieveDocumentReference("Likes", like));
        }
    }

    public void setComments(ArrayList<String> comments) {

        this.comments = new ArrayList<>();
        for(String comment : comments) {
            this.comments.add(Utility.retrieveDocumentReference("Comments", comment));
        }
    }


}
