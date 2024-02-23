package cis.javaholics.models.reviewPosts;

import cis.javaholics.models.comments.Comments;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.firebase.database.annotations.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ReviewPosts extends AReviewPosts{
    private @Nullable List<Likes> likes;
    private @Nullable List<Comments> comments;

    public ReviewPosts(String rPostId, String topic, String description, List<String> photos, String title, Timestamp postedAt, List<Likes> likes, List<Comments> comments) {
        super(rPostId, topic, description, photos, title, postedAt);
        this.likes = likes;
        this.comments = comments;
    }

    public ReviewPosts(List<Likes> likes, List<Comments> comments) {
        this.likes = likes;
        this.comments = comments;
    }
}
