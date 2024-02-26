package cis.javaholics.services;

import cis.javaholics.models.businesses.Businesses;
import cis.javaholics.models.comments.Comments;
import cis.javaholics.models.reviews.Reviews;
import cis.javaholics.models.users.Users;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import io.micrometer.common.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class ReviewsService {
    private Firestore firestore;

    public ReviewsService(){
        this.firestore = FirestoreClient.getFirestore();
    }

    @Nullable
    private Reviews documentSnapshotToTask(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        if (document.exists()) {
            Users createdBy = null;
            Businesses business = null;
            Rating rating = null;
            List<Comments> comments = new ArrayList<>();
            List<Likes> likes = new ArrayList<>();

            // Retrieve createdBy user details
            DocumentReference userRef = (DocumentReference) document.get("createdBy");
            if (userRef != null) {
                DocumentSnapshot userSnapshot = userRef.get().get();
                if (userSnapshot.exists()) {
                    createdBy = userSnapshot.toObject(Users.class);
                }
            }

            // Retrieve Business details
            DocumentReference businessRef = (DocumentReference) document.get("business");
            if (userRef != null) {
                DocumentSnapshot busSnapshot = businessRef.get().get();
                if (busSnapshot.exists()) {
                    business = busSnapshot.toObject(Businesses.class);
                }
            }

            // Retrieve createdBy rating
            DocumentReference ratingRef = (DocumentReference) document.get("rating");
            if (userRef != null) {
                DocumentSnapshot ratingSnapshot = ratingRef.get().get();
                if (ratingSnapshot.exists()) {
                    rating = ratingSnapshot.toObject(Ratings.class);
                }
            }

            //Retrieve likes
            List<DocumentReference> reviewLikes = (List<DocumentReference>) document.get("likes");
            for(DocumentReference reviewLike :reviewLikes)
            {
                DocumentSnapshot itemSnapshot = reviewLike.get().get();
                if(itemSnapshot.exists())
                {
                    likes.add(itemSnapshot.toObject(Likes.class));
                }
            }

            //Retrieve comments
            List<DocumentReference> reviewComments = (List<DocumentReference>) document.get("comments");
            for(DocumentReference reviewComment : reviewComments)
            {
                DocumentSnapshot itemSnapshot = reviewComment.get().get();
                if(itemSnapshot.exists())
                {
                    comments.add(itemSnapshot.toObject(Comments.class));
                }
            }


            return( new Reviews(document.getId(),
                    document.getString("type"),
                    document.getString("description"),
                    (List<String>)document.get("photos"),
                    document.getString("title"),
                    document.getTimestamp("postedAt"),
                    createdBy,
                    business,
                    rating,
                    likes,
                    comments
            ) );
        }
        return null;
    }

    @Nullable
    public Reviews getReviewById(String rPostId) throws ExecutionException, InterruptedException {
        DocumentReference reviewRef = firestore.collection("Reviews").document(rPostId);
        DocumentSnapshot documentSnapshot = reviewRef.get().get();
        if (documentSnapshot.exists()) {
            return documentSnapshot.toObject(Reviews.class);
        } else {
            return null;
        }
    }
    public List<Reviews> getReviewByUserId(String createdBy) throws ExecutionException, InterruptedException {
        Query query = firestore.collection("Reviews").whereEqualTo("createdBy", createdBy);
        return getReviewList(query);
    }

    public List<Reviews> getReviewByBusiness(String businessId) throws ExecutionException, InterruptedException {
        Query query = firestore.collection("Businesses").whereEqualTo("businessId", businessId);
        return getReviewList(query);
    }

    public List<Reviews> getAllReviews() throws ExecutionException, InterruptedException {
        CollectionReference reviewsCollection = firestore.collection("Reviews");
        return getReviewList(reviewsCollection);
    }


    @Nullable
    private List<Reviews> getReviewList(Query query) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        List<Reviews> reviewList = new ArrayList<>();

        for (DocumentSnapshot document : documentSnapshots) {
            reviewList.add(document.toObject(Reviews.class));
        }

        return reviewList;
    }

    public String createReview(Reviews review) throws ExecutionException, InterruptedException {
        CollectionReference reviewsCollection = firestore.collection("Reviews");
        ApiFuture<DocumentReference> future = reviewsCollection.add(review);
        DocumentReference docRef = future.get();
        return docRef.getId();
    }

    public WriteResult deleteReview(String rPostId) throws ExecutionException, InterruptedException {
        DocumentReference reviewRef = firestore.collection("Reviews").document(rPostId);
        return reviewRef.delete().get();
    }

}
