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
    private ArrayList<DocumentReference> likes;

    public RestForumPosts(@Nullable String fPostId, String topic, String description, String title, List<String> photos, Timestamp postedAt, String postedBy, ArrayList<String> likes) {
        super(fPostId, topic, description, title, photos, postedAt);
        setPostedBy(postedBy);
        setLikes(likes);
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = Utility.retrieveDocumentReference("User", postedBy);
    }

    public void setLikes(ArrayList<String> likes) {

        this.likes = new ArrayList<>();
        for(String like : likes) {
            this.likes.add(Utility.retrieveDocumentReference("Likes", like));
        }
    }




}
