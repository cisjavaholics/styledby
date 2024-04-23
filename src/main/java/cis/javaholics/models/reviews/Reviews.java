package cis.javaholics.models.reviews;


import cis.javaholics.models.businesses.Businesses;
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

    public Reviews(String rPostId, String type, long rating, String description, @jakarta.annotation.Nullable List<String> photos, Timestamp createdAt, Users createdBy, Businesses business) {
        super(rPostId, type, rating, description, photos, createdAt);
        this.createdBy = createdBy;
        this.business = business;
    }

    public Reviews(Users createdBy, Businesses business) {
        this.createdBy = createdBy;
        this.business = business;
    }
}
