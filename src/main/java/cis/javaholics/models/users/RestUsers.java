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

    private List<DocumentReference> reviews;
    private List<DocumentReference> forums;
    private List<DocumentReference> saved;
    private int numReviews;
    private int numForums;
    private int numSaved;

    public RestUsers(String userId, String username, String email, List<String> roles, List<DocumentReference> reviews, List<DocumentReference> forums, List<DocumentReference> saved) {
        super(userId, username, email, roles);
        this.reviews = reviews;
        this.forums = forums;
        this.saved = saved;
    }
    // Setters and Getters for String parameters to perform Firestore queries

    public void setReviews(ArrayList<String> review) {

        this.reviews = new ArrayList<>();
        for(String tempRev: review) {
            this.saved.add(Utility.retrieveDocumentReference("Reviews", tempRev));
        }

    }
    public void setForums(ArrayList<String> forum) {

        this.forums = new ArrayList<>();
        for(String tempFor: forum) {
            this.saved.add(Utility.retrieveDocumentReference("Forums", tempFor));
        }

    }

    public void setSaved(ArrayList<String> saves) {

        this.saved = new ArrayList<>();
        for(String forum: saves) {
            this.saved.add(Utility.retrieveDocumentReference("Saves", forum));
        }

    }
}
