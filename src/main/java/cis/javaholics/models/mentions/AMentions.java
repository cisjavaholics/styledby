package cis.javaholics.models.mentions;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.util.List;

@Data //creates setters and getters automatically
@AllArgsConstructor //creates constructor with all values automatically
@NoArgsConstructor //creates no argument constructor automatically
public abstract class AMentions {
    @DocumentId
    private @Nullable String mentionId;
    private Timestamp mentionedAt;
    public void setMentionedAt(String mentionedAt) throws ParseException {
        this.mentionedAt = Timestamp.fromProto(Timestamps.parse(mentionedAt));
    }
}
