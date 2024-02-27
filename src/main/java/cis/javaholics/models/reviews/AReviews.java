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
    private String rPostId;
    @Schema(example = "Hair", description = "Type of service that review is made on")
    private String type;
    @Schema(example = "Great work.", description = "Description of review")
    private String description;
    @Schema(example = "images/image.jpeg", description = "List of photos of service (file paths)")
    private @Nullable List<String> photos;
    @Schema(example = "January 25, 2024 at 6:56:59PM", description = "Time that review was created")
    private Timestamp createdAt;

    public void setCreatedAt(String createdAt) throws ParseException {
        this.createdAt = Timestamp.fromProto(Timestamps.parse(createdAt));
    }
}
