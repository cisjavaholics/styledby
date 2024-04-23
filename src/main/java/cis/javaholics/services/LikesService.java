package cis.javaholics.services;


import cis.javaholics.models.likes.Likes;
import cis.javaholics.models.reviews.Reviews;
//import cis.javaholics.models.users.Users;
import cis.javaholics.models.users.Users;
import cis.javaholics.util.Utility;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class LikesService {
    private Firestore firestore;

    public LikesService(){
        this.firestore = FirestoreClient.getFirestore();
    }
    @Nullable
    public Likes documentSnapshotToLike(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        Users senderId = null;
        Reviews postId = null;
        if (document.exists()) {
            Likes like = new Likes();
            like.setLikeId(document.getId());
            // error: java.lang.ClassCastException: class java.lang.String cannot be cast to class com.google.cloud.Timestamp (java.lang.String is in module java.base of loader 'bootstrap'; com.google.cloud.Timestamp is in unnamed module of loader 'app')
            //like.setLikedAt(document.getTimestamp("likedAt"));
            // Retrieve senderId user details
            DocumentReference userRef = (DocumentReference) document.get("userId");
            if (userRef != null) {
                DocumentSnapshot userSnapshot = userRef.get().get();
                if (userSnapshot.exists()) {
                    UsersService service = new UsersService();
                    senderId = service.documentSnapshotToUser(userSnapshot);
                }
                like.setSenderId(senderId);
            }

            // Retrieve post details
            DocumentReference postRef = (DocumentReference) document.get("rPostId");
            if (postRef != null) {
                DocumentSnapshot postSnapshot = postRef.get().get();
                if (postSnapshot.exists()) {
                    ReviewsService service = new ReviewsService();
                    postId = service.documentSnapshotToReview(postSnapshot);
                }
                like.setPostId(postId);
            }

            return like;


        }
        return null;
    }

    public List<Likes> getAllLikes() throws InterruptedException, ExecutionException {
        CollectionReference likeCollection = firestore.collection("Likes");
        Future<QuerySnapshot> future = likeCollection.get();
        QuerySnapshot documents = future.get();

        List<Likes> likesList = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            Likes like = documentSnapshotToLike(document);
            if (like != null) {
                likesList.add(like);
            }
        }
        return likesList;
    }

    @Nullable
    public Likes getLikeById(String likeId) throws ExecutionException, InterruptedException {
        DocumentReference likeRef = firestore.collection("Likes").document(likeId);
        ApiFuture<DocumentSnapshot> future = likeRef.get();
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
        DocumentReference likeRef = firestore.collection("Like").document(likeId);
        return likeRef.delete().get();
    }
}
