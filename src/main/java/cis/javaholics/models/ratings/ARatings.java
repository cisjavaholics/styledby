package cis.javaholics.models.ratings;


import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.text.ParseException;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class ARatings {
    @DocumentId
    private @Nullable String ratingId;
    private String content;
    private Timestamp time;

    public void setTime(String time) throws ParseException {
        this.time = Timestamp.fromProto(Timestamps.parse(time));
    }
}
