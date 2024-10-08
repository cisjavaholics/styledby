package cis.javaholics.models.forumPosts;

import cis.javaholics.models.comments.Comments;
import cis.javaholics.models.likes.Likes;
import cis.javaholics.models.users.Users;
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

    public ForumPosts(@Nullable String fPostId, String topic, String description, String title, List<String> photos, Timestamp postedAt, Users postedBy, List<Likes> likes) {
        super(fPostId, topic, description, title, photos, postedAt);
        this.postedBy = postedBy;
        this.likes = likes;
    }

}
