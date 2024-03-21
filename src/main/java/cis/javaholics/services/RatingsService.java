package cis.javaholics.services;


import cis.javaholics.models.forumPosts.ForumPosts;
import cis.javaholics.models.ratings.Ratings;
import cis.javaholics.models.users.Users;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import io.micrometer.common.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
@Service
public class RatingsService {
    private final Firestore firestore;

    public RatingsService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    @Nullable
    public Ratings documentSnapshotToRating(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        if (document.exists()) {
            Users senderId = null;
            ForumPosts forumId = null;

            // Retrieve senderId user details
            DocumentReference userRef = (DocumentReference) document.get("userId");
            if (userRef != null) {
                DocumentSnapshot userSnapshot = userRef.get().get();
                if (userSnapshot.exists()) {
                    senderId = userSnapshot.toObject(Users.class);
                }
            }

            // Retrieve forum post details
            DocumentReference forumRef = (DocumentReference) document.get("fPostId");
            if (forumRef != null) {
                DocumentSnapshot forumSnapshot = forumRef.get().get();
                if (forumSnapshot.exists()) {
                    forumId = forumSnapshot.toObject(ForumPosts.class);
                }
            }

            return (new Ratings(document.getId(),
                    document.getString("content"),
                    document.getTimestamp("time"),
                    senderId
            ));
        }
        return null;
    }

    @Nullable
    public Ratings getRatingById(String ratingId) throws ExecutionException, InterruptedException {
        CollectionReference ratingsCollection = firestore.collection("Rating");
        ApiFuture<DocumentSnapshot> future = ratingsCollection.document(ratingId).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToRating(document);
    }

    @Nullable
    public List<Ratings> getAllRatingsOnPost(String forumId) throws ExecutionException, InterruptedException {
        Query query = firestore.collection("Rating").whereEqualTo("forumId", forumId);
        return getRatingList(query);
    }

    private List<Ratings> getRatingList(Query query) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        List<Ratings> ratingList = new ArrayList<>();

        for (DocumentSnapshot document : documentSnapshots) {
            ratingList.add(documentSnapshotToRating(document));
        }
        return ratingList;
    }

    public String addRating(Ratings rating) throws ExecutionException, InterruptedException {
        CollectionReference ratingsCollection = firestore.collection("Rating");
        ApiFuture<DocumentReference> future = ratingsCollection.add(rating);
        DocumentReference docRef = future.get();
        return docRef.getId();
    }

    public WriteResult deleteRating(String ratingId) throws ExecutionException, InterruptedException {
        DocumentReference ratingRef = firestore.collection("Rating").document(ratingId);
        return ratingRef.delete().get();
    }

}
