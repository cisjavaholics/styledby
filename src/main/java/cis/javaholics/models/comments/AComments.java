package cis.javaholics.models.comments;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class AComments {
   @DocumentId
   @Schema(example = "12345", description = "Unique ID of the comment")
    protected String commentId;
    @Schema(example = "Good Insight.", description = "Content of the comment")
    protected String content;
    @Schema(example = "January 25, 2024 at 6:56:59PM", description = "Date and time of comment")
    protected Timestamp time;

    public void setPostedAt(String time) throws ParseException {
        this.time = Timestamp.fromProto(Timestamps.parse(time));
    }
}
