package cis.javaholics.models.ratings;


import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class ARatings {
    @DocumentId
    private @Nullable String ratingId;
    private String userId;
    private Timestamp ratingTime;
    private List<Integer> ratingNum;

    public ARatings(String ratingId, String content) {
    }
}
