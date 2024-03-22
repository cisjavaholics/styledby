package cis.javaholics.models.reviews;

import cis.javaholics.util.Utility;
import com.google.cloud.firestore.DocumentReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestReviews extends AReviews {
    private DocumentReference createdBy;
    private DocumentReference business;
    private DocumentReference rating;

    public void setCreatedBy(String createdBy) {
        this.createdBy = Utility.retrieveDocumentReference("User", createdBy);
    }
    public void setBusiness(String business) {
        this.business = Utility.retrieveDocumentReference("Business", business);
    }

    public void setRating(String rating) {
        this.rating = Utility.retrieveDocumentReference("Rating", rating);
    }


}
