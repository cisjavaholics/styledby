package cis.javaholics.models.users;
import com.google.cloud.firestore.DocumentReference;
import cis.javaholics.util.Utility;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class RestUsers extends AUsers {

    private ArrayList<DocumentReference> reviews;
    private ArrayList<DocumentReference> saved;
    private ArrayList<DocumentReference> businesses;

    public RestUsers(String userId, String username, String email, ArrayList<String> reviews, ArrayList<String> saved, ArrayList<String> businesses) {
        super(userId, username, email);
        setReviews(reviews);
        setSaved(saved);
        setBusinesses(businesses);
    }


// Setters and Getters for String parameters to perform Firestore queries

    public void setReviews(ArrayList<String> review) {

        this.reviews = new ArrayList<>();
        for(String tempRev: review) {
            this.saved.add(Utility.retrieveDocumentReference("reviews", tempRev));
        }

    }

    public void setSaved(ArrayList<String> saves) {

        this.saved = new ArrayList<>();
        for(String forum: saves) {
            this.saved.add(Utility.retrieveDocumentReference("Saves", forum));
        }

    }

    public void setBusinesses(ArrayList<String> businesses) {

        this.businesses = new ArrayList<>();
        for(String business: businesses) {
            this.businesses.add(Utility.retrieveDocumentReference("Businesses", business));
        }
    }
}
