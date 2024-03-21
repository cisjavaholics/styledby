package cis.javaholics.models.ratings;

import cis.javaholics.models.forumPosts.ForumPosts;
import cis.javaholics.models.users.Users;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class Ratings extends ARatings {

    @DocumentId
    private @Nullable String ratingId;
    private String userId;
    private Timestamp ratingTime;
    private List<Integer> ratingNum;
    private String content;


    public Ratings(String ratingId, String content, Timestamp time, Users senderId, ForumPosts forumId) {
        super(ratingId, content);
        String rating = " ";
        this.ratingId = rating;
        this.userId = userId;
        this.content = content;
    }
}

