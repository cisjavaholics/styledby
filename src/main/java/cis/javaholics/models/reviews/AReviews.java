package cis.javaholics.models.reviews;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class AReviews {
    @DocumentId
    @Schema(example = "12345", description = "Unique ID of the review")
    protected String rPostId;
    @Schema(example = "Hair", description = "Type of service that review is made on")
    protected String type;
    @Schema(example = "4", description = "Number 1-5 representing users rating of service")
    protected long rating;
    @Schema(example = "Great work.", description = "Description of review")
    protected String description;
    @Schema(example = "images/image.jpeg", description = "List of photos of service (file paths)")
    protected @Nullable List<String> photos;
    @Schema(example = "January 25, 2024 at 6:56:59PM", description = "Time that review was created")
    protected Timestamp createdAt;

    public void setCreatedAt(String createdAt) throws ParseException {
        this.createdAt = Timestamp.fromProto(Timestamps.parse(createdAt));
    }

    public void setCreatedAtS(Timestamp createdAt) {

        this.createdAt = createdAt;
    }
}
