package cis.javaholics.models.ratings;


import cis.javaholics.util.Utility;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.List;

@Setter
@NoArgsConstructor
public class RestRatings extends ARatings {
    private DocumentReference userId;

    public void setUserId(String userId) {
        this.userId = Utility.retrieveDocumentReference("Users", userId);
    }
}