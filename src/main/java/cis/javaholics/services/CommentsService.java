package cis.javaholics.services;


import cis.javaholics.models.comments.Comments;
import cis.javaholics.models.forumPosts.ForumPosts;
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
public class CommentsService {

    private Firestore firestore;

    public CommentsService(){
        this.firestore = FirestoreClient.getFirestore();
    }
    @Nullable
    private Comments documentSnapshotToComment(DocumentSnapshot document) throws ExecutionException, InterruptedException {
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

            return( new Comments(document.getId(),
                    document.getString("content"),
                    document.getTimestamp("time"),
                    senderId,
                    forumId
            ) );
        }
        return null;
    }

    @Nullable
    public Comments getCommentById(String commentId) throws ExecutionException, InterruptedException {
        CollectionReference commentsCollection = firestore.collection("commentId");
        ApiFuture<DocumentSnapshot> future = commentsCollection.document(commentId).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToComment(document);
    }

    @Nullable
    public List<Comments> getAllCommentsOnPost(String forumId) throws ExecutionException, InterruptedException {
        DocumentReference commentRef = Utility.retrieveDocumentReference("Comments", forumId);
        Query query = firestore.collection("Comments").whereEqualTo("forumId", forumId);
        return getCommentList(query);
    }

    private List<Comments> getCommentList(Query query) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        List<Comments> commentList = new ArrayList<>();

        for (DocumentSnapshot document : documentSnapshots) {
            commentList.add(documentSnapshotToComment(document));
        }
        return commentList;
    }

    public String addComment(Comments comment) throws ExecutionException, InterruptedException {
        CollectionReference commentsCollection = firestore.collection("Comments");
        ApiFuture<DocumentReference> future = commentsCollection.add(comment);
        DocumentReference docRef = future.get();
        return docRef.getId();
    }

    public WriteResult deleteComment(String commentId) throws ExecutionException, InterruptedException {
        DocumentReference commentRef = firestore.collection("Comments").document(commentId);
        return commentRef.delete().get();
    }
}
