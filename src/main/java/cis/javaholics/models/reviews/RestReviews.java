package cis.javaholics.models.reviews;

import cis.javaholics.models.businesses.Businesses;
import cis.javaholics.models.users.Users;
import cis.javaholics.util.Utility;
import com.google.cloud.Timestamp;
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

    public RestReviews(String rPostId, String type, long rating, String description, @jakarta.annotation.Nullable List<String> photos, Timestamp createdAt, String createdBy, String business) {
        super(rPostId, type, rating, description, photos, createdAt);
        setCreatedBy(createdBy);
        setBusiness(business);
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = Utility.retrieveDocumentReference("User", createdBy);
    }
    public void setBusiness(String business) {
        this.business = Utility.retrieveDocumentReference("Business", business);
    }



}
