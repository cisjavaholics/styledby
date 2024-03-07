package cis.javaholics.models.businesses;

import com.google.cloud.firestore.DocumentReference;
import cis.javaholics.util.Utility;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RestBusinesses extends ABusinesses {

    private int rating;
    private List<DocumentReference> reviews;
    private List<DocumentReference> mentions;
    private int numReviews;
    private int numMentions;

    public RestBusinesses(String businessId, String name, String category, int rating, List<DocumentReference> reviews, List<DocumentReference> mentions, int numReviews, int numMentions) {
        super(businessId, name, category);
        this.rating = rating;
        this.reviews = reviews;
        this.mentions = mentions;
        this.numReviews = numReviews;
        this.numMentions = numMentions;
    }
    // Setters and Getters for String parameters to perform Firestore queries
    public void setReviews(ArrayList<String> review) {
        this.reviews = new ArrayList<>();
        for (String tempRev : review) {
            this.reviews.add(Utility.retrieveDocumentReference("Reviews", tempRev));
        }
    }

    public void setMentions(ArrayList<String> forum) {
        this.mentions = new ArrayList<>();
        for (String tempFor : forum) {
            this.mentions.add(Utility.retrieveDocumentReference("Forums", tempFor));
        }
    }
}
