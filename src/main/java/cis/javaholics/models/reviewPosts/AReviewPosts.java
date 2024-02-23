package cis.javaholics.models.reviewPosts;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class AReviewPosts {

    @DocumentId
    private String rPostId;
    private String topic;
    private String description;
    private @Nullable List<String> photos;
    private String title;
    private Timestamp postedAt;

    public void setPostedAt(String postedAt) throws ParseException {
        this.postedAt = Timestamp.fromProto(Timestamps.parse(postedAt));
    }
}
