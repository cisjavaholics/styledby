package cis.javaholics.models.reviews;

import cis.javaholics.models.businesses.Businesses;
import cis.javaholics.models.comments.Comments;
import cis.javaholics.models.users.Users;
import com.google.cloud.Timestamp;
import com.google.firebase.database.annotations.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Reviews extends AReviews {
    private Users createdBy;
    private Businesses business;
    private Rating rating;
    private @Nullable List<Likes> likes;
    private @Nullable List<Comments> comments;

    public Reviews(String rPostId, String type, String description, @jakarta.annotation.Nullable List<String> photos, String title, Timestamp postedAt, Users createdBy, Businesses business, Rating rating, List<Likes> likes, List<Comments> comments) {
        super(rPostId, type, description, photos, title, postedAt);
        this.createdBy = createdBy;
        this.business = business;
        this.rating = rating;
        this.likes = likes;
        this.comments = comments;
    }

    public Reviews(Users createdBy, Businesses business, Rating rating, List<Likes> likes, List<Comments> comments) {
        this.createdBy = createdBy;
        this.business = business;
        this.rating = rating;
        this.likes = likes;
        this.comments = comments;
    }
}
