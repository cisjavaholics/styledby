package cis.javaholics.models.forumPosts;

<<<<<<< Updated upstream
import cis.javaholics.models.Likes;
=======
import cis.javaholics.models.likes.Likes;
import cis.javaholics.models.comments.Comments;
import cis.javaholics.models.users.Users;
>>>>>>> Stashed changes
import com.google.cloud.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@NoArgsConstructor
public class ForumPosts extends AForumPosts {
    private Users postedBy;
    private List<Likes> likes;
    private List<Comments> comments;

    public ForumPosts(@Nullable String fPostId, String topic, String description, String title, List<String> photos, Timestamp postedAt, Users postedBy, List<Likes> likes, List<Comments> comments) {
        super(fPostId, topic, description, title, photos, postedAt);
        this.postedBy = postedBy;
        this.likes = likes;
        this.comments = comments;
    }

}
