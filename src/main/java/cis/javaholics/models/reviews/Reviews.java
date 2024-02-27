package cis.javaholics.models.reviews;

import cis.javaholics.models.businesses.Businesses;
import cis.javaholics.models.comments.Comments;
import cis.javaholics.models.users.Users;
import com.google.cloud.Timestamp;
import com.google.firebase.database.annotations.Nullable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Schema(description = "Represents a review")
public class Reviews extends AReviews {
    @Schema(example = "12345", description = "Unique ID of the user who created review")
    private Users createdBy;
    @Schema(example = "12345", description = "Unique ID of business review is created about")
    private Businesses business;
    @Schema(example = "12345", description = "Unique ID of the review")
    private Ratings rating;
    @Schema(example = "12345,12345,12345", description = "List of IDs of likes on post")
    private @Nullable List<Likes> likes;
    @Schema(example = "12345,12345,12345,12345", description = "List of IDs of comments on post")
    private @Nullable List<Comments> comments;

    public Reviews(String rPostId, String type, String description, @jakarta.annotation.Nullable List<String> photos, Timestamp createdAt, Users createdBy, Businesses business, Ratings rating, List<Likes> likes, List<Comments> comments) {
        super(rPostId, type, description, photos, createdAt);
        this.createdBy = createdBy;
        this.business = business;
        this.rating = rating;
        this.likes = likes;
        this.comments = comments;
    }

    public Reviews(Users createdBy, Businesses business, Ratings rating, List<Likes> likes, List<Comments> comments) {
        this.createdBy = createdBy;
        this.business = business;
        this.rating = rating;
        this.likes = likes;
        this.comments = comments;
    }
}
