package cis.javaholics.services;


import cis.javaholics.models.businesses.Businesses;
import cis.javaholics.models.ratings.Ratings;
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
        Ratings rating = null;
        if (document.exists()) {
            // Retrieve createdBy user details
            DocumentReference userRef = (DocumentReference) document.get("createdBy");
            if (userRef != null) {
                DocumentSnapshot userSnapshot = userRef.get().get();
                if (userSnapshot.exists()) {
                    UsersService service = new UsersService();
                    createdBy = service.documentSnapshotToUser(userSnapshot);
                }
            }

            // Retrieve Business details
            DocumentReference businessRef = (DocumentReference) document.get("business");
            if (userRef != null) {
                DocumentSnapshot busSnapshot = businessRef.get().get();
                if (busSnapshot.exists()) {
                    BusinessesService service = new BusinessesService();
                    business = service.documentSnapshotToBusiness(busSnapshot);
                }
            }

            // Retrieve createdBy rating
            DocumentReference ratingRef = (DocumentReference) document.get("rating");
            if (userRef != null) {
                DocumentSnapshot ratingSnapshot = ratingRef.get().get();
                if (ratingSnapshot.exists()) {
                    RatingsService service = new RatingsService();
                    rating = service.documentSnapshotToRating(ratingSnapshot);
                }
            }

            return( new Reviews(document.getId(),
                    document.getString("type"),
                    document.getString("description"),
                    (List<String>)document.get("photos"),
                    document.getTimestamp("createdAt"),
                    createdBy,
                    business,
                    rating
            ) );
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
