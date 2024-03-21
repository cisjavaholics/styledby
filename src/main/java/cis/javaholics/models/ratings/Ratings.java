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
public class Ratings extends ARatings {
    private Users userId;

    public Ratings(@Nullable String ratingId, String content, Timestamp time, Users userId) {
        super(ratingId, content, time);
        this.userId = userId;
    }


}

