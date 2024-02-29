package cis.javaholics.models;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.text.ParseException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Likes {
    @DocumentId
    private @Nullable String likeId;
    private String senderId;
    private String receiverId;
    private Timestamp time;

}
