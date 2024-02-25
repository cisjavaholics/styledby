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
    private List<DocumentReference> likes;
    private List<DocumentReference> comments;

    public void setMentionedBy(String createdBy) {
        this.createdBy = Utility.retrieveDocumentReference("Users", createdBy);
    }
    public void setLikes(ArrayList<String> likes) {

        this.likes = new ArrayList<>();
        for(String like : likes) {
            this.likes.add(Utility.retrieveDocumentReference("Likes", like));
        }
    }
    public void setComments(ArrayList<String> comments) {

        this.comments = new ArrayList<>();
        for(String comment : comments) {
            this.comments.add(Utility.retrieveDocumentReference("Comments", comment));
        }
    }
}
