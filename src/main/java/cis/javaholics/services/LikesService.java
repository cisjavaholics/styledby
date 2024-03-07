package cis.javaholics.services;


import cis.javaholics.models.likes.Likes;
import cis.javaholics.models.reviews.Reviews;
import cis.javaholics.models.users.Users;
import cis.javaholics.util.Utility;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LikesService {
    private Firestore firestore;

    public LikesService(){
        this.firestore = FirestoreClient.getFirestore();
    }
    @Nullable
    private Likes documentSnapshotToLike(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        if (document.exists()) {
            Users senderId = null;
            Reviews postId = null;

            // Retrieve senderId user details
            DocumentReference userRef = (DocumentReference) document.get("userId");
            if (userRef != null) {
                DocumentSnapshot userSnapshot = userRef.get().get();
                if (userSnapshot.exists()) {
                    senderId = userSnapshot.toObject(Users.class);
                }
            }

            // Retrieve post details
            DocumentReference postRef = (DocumentReference) document.get("rPostId");
            if (postRef != null) {
                DocumentSnapshot postSnapshot = postRef.get().get();
                if (postSnapshot.exists()) {
                    postId = postSnapshot.toObject(Reviews.class);
                }
            }

            return( new Likes(document.getId(),
                    document.getTimestamp("likedAt"),
                    senderId,
                    postId
            ) );
        }
        return null;
    }

    @Nullable
    public Likes getLikeById(String likeId) throws ExecutionException, InterruptedException {
        CollectionReference likesCollection = firestore.collection("likeId");
        ApiFuture<DocumentSnapshot> future = likesCollection.document(likeId).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToLike(document);
    }

    @Nullable
    public List<Likes> getAllLikesOnPost(String postId) throws ExecutionException, InterruptedException {
        DocumentReference likeRef = Utility.retrieveDocumentReference("Likes", postId);
        Query query = firestore.collection("Likes").whereEqualTo("postId", postId);
        return getLikeList(query);
    }

    private List<Likes> getLikeList(Query query) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        List<Likes> likeList = new ArrayList<>();

        for (DocumentSnapshot document : documentSnapshots) {
            likeList.add(documentSnapshotToLike(document));
        }
        return likeList;
    }

    public String addLike(Likes like) throws ExecutionException, InterruptedException {
        CollectionReference likesCollection = firestore.collection("Likes");
        ApiFuture<DocumentReference> future = likesCollection.add(like);
        DocumentReference docRef = future.get();
        return docRef.getId();
    }

    public WriteResult deleteLike(String likeId) throws ExecutionException, InterruptedException {
        DocumentReference likeRef = firestore.collection("Likes").document(likeId);
        return likeRef.delete().get();
    }
}
