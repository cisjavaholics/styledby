package cis.javaholics.services;


import cis.javaholics.models.businesses.Businesses;
import cis.javaholics.models.reviews.Reviews;
import cis.javaholics.models.users.Users;
import cis.javaholics.util.Utility;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import io.micrometer.common.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ExecutionException;

@Service
public class ReviewsService {
    private Firestore firestore;

    public ReviewsService(){
        this.firestore = FirestoreClient.getFirestore();
    }

    public Reviews documentSnapshotToReview(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        Users createdBy = null;
        Businesses business = null;
        if (document.exists()) {
            Reviews review = new Reviews();
            review.setRPostId(document.getId());
            review.setType(document.getString("type"));
            review.setRating(document.getLong("rating"));
            review.setDescription(document.getString("description"));
            review.setPhotos((List<String>)document.get("photos"));
            review.setCreatedAtS(document.getTimestamp("createdAt"));

            // Retrieve user details
            DocumentReference userRef = (DocumentReference) document.get("createdBy");
            if (userRef != null) {
                DocumentSnapshot userSnapshot = userRef.get().get();
                if (userSnapshot.exists()) {
                    UsersService service = new UsersService();
                    createdBy = service.documentSnapshotToUser(userSnapshot);
                    review.setCreatedBy(createdBy);
                }
            }



            return review;
        }
        return null;
    }

    @Nullable
    public Reviews getReviewById(String rPostId) throws ExecutionException, InterruptedException {
        DocumentReference reviewRef = firestore.collection("reviews").document(rPostId);
        ApiFuture<DocumentSnapshot> future = reviewRef.get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToReview(document);
    }

    @Nullable
    public List<Reviews> getReviewsByUserId(String userId) throws ExecutionException, InterruptedException {
        DocumentReference reviewRef = Utility.retrieveDocumentReference("reviews", userId);
        Query query = firestore.collection("reviews").whereEqualTo("createdBy", userId);
        return getReviewList(query);
    }

    @Nullable
    public List<Reviews> getReviewsByBusiness(String businessId) throws ExecutionException, InterruptedException {
        DocumentReference reviewRef = Utility.retrieveDocumentReference("reviews", businessId);
        Query query = firestore.collection("reviews").whereEqualTo("businessId", businessId);
        return getReviewList(query);
    }

    public List<Reviews> getAllReviews() throws ExecutionException, InterruptedException {
        CollectionReference reviewCollection = firestore.collection("reviews");
        ApiFuture<QuerySnapshot> future = reviewCollection.get();
        List<Reviews> reviewList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            Reviews review = documentSnapshotToReview(document);
            if (review != null) {
                reviewList.add(review);
            }
        }
        return reviewList;
    }


    @Nullable
    private List<Reviews> getReviewList(Query query) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        List<Reviews> reviewList = new ArrayList<>();

        for (DocumentSnapshot document : documentSnapshots) {
            Reviews review = documentSnapshotToReview(document);
            if (review != null) {
                reviewList.add(review);
            }
        }

        return reviewList;
    }

    public String createReview(Reviews review) throws ExecutionException, InterruptedException {
        CollectionReference reviewsCollection = firestore.collection("reviews");
        ApiFuture<DocumentReference> future = reviewsCollection.add(review);
        DocumentReference docRef = future.get();
        return docRef.getId();
    }

    public WriteResult deleteReview(String rPostId) throws ExecutionException, InterruptedException {
        DocumentReference reviewRef = firestore.collection("reviews").document(rPostId);
        return reviewRef.delete().get();
    }

}
