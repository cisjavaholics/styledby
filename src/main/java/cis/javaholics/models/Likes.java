package cis.javaholics.models;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;


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
