package cis.javaholics.models.comments;

import cis.javaholics.util.Utility;
import com.google.cloud.firestore.DocumentReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestComments extends AComments {
    private DocumentReference senderId;
    private DocumentReference receiverId;

    public void setSenderId(String senderId) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        this.senderId = Utility.retrieveDocumentReference("Users", senderId);
    }
    public void setReceiverId(String receiverId) {
        // Perform Firebase Firestore query to retrieve DocumentReference for createBy
        this.receiverId = Utility.retrieveDocumentReference("Users", receiverId);
    }


}
