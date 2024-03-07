package cis.javaholics.models.likes;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.text.ParseException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ALikes {
    @DocumentId
    private @Nullable String likeId;
    private Timestamp likedAt;

    public void setLikedAt(String likedAt) throws ParseException {
        this.likedAt = Timestamp.fromProto(Timestamps.parse(likedAt));
    }

}
