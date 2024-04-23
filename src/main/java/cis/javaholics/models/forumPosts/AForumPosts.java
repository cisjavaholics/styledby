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
    protected @Nullable String fPostId;
    protected String topic;
    protected String description;
    protected String title;
    protected List<String> photos;
    protected Timestamp postedAt;

    public void setPostedAt(String postedAt) throws ParseException {
        this.postedAt = Timestamp.fromProto(Timestamps.parse(postedAt));
    }

    public void setPostedAtS(Timestamp postedAt) {
        this.postedAt = postedAt;
    }
}
