package cis.javaholics.models;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ratings {
    @DocumentId
    private @Nullable String ratingId;
    private String userId;
    private Timestamp ratingTime;
    private List<Integer> ratingNum;


}
