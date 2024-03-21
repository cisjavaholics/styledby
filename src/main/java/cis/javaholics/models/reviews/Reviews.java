package cis.javaholics.models.reviews;


import cis.javaholics.models.businesses.Businesses;
import cis.javaholics.models.ratings.Ratings;
import cis.javaholics.models.users.Users;
import com.google.cloud.Timestamp;
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

    public Reviews(String rPostId, String type, String description, @jakarta.annotation.Nullable List<String> photos, Timestamp createdAt, Users createdBy, Businesses business, Ratings rating) {
        super(rPostId, type, description, photos, createdAt);
        this.createdBy = createdBy;
        this.business = business;
        this.rating = rating;
    }

    public Reviews(Users createdBy, Businesses business, Ratings rating) {
        this.createdBy = createdBy;
        this.business = business;
        this.rating = rating;
    }
}
