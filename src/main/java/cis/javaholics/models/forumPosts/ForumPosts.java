package cis.javaholics.models.forumPosts;

import cis.javaholics.models.Likes;
import cis.javaholics.models.comments.Comments;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@NoArgsConstructor
public class ForumPosts extends AForumPosts {
    private List<Likes> likes;
    private List<Comments> comments;

    public ForumPosts(@Nullable String fPostId, String topic, String description, String title, List<String> photos, Timestamp postedAt, List<Likes> likes, List<Comments> comments) {
        super(fPostId, topic, description, title, photos, postedAt);
        this.likes = likes;
        this.comments = comments;
    }
}
