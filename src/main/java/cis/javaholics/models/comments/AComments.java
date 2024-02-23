package cis.javaholics.models.comments;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
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
    private String commentId;
    private String content;
    private Timestamp time;

    public void setPostedAt(String time) throws ParseException {
        this.time = Timestamp.fromProto(Timestamps.parse(time));
    }
}
