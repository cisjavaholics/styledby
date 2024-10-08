package cis.javaholics.models.businesses;

import cis.javaholics.models.reviews.Reviews;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data //creates setters and getters automatically
@AllArgsConstructor //creates constructor with all values automatically
@NoArgsConstructor //creates no argument constructor automatically
public class Businesses {
    @DocumentId
    private @Nullable String businessId;
    private String name;
    private String category;
    private long rating;
    private List<Reviews> reviews;
}
