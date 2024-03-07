package cis.javaholics.models.forumPosts;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.text.ParseException;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AForumPosts {
    @DocumentId
    private @Nullable String fPostId;
    private String topic;
    private String description;
    private String title;
    private List<String> photos;
    private Timestamp postedAt;

    public void setPostedAt(String postedAt) throws ParseException {
        this.postedAt = Timestamp.fromProto(Timestamps.parse(postedAt));
    }
}
