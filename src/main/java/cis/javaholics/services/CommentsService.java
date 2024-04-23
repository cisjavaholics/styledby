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
import java.util.concurrent.Future;

@Service
public class CommentsService {

    private Firestore firestore;

    public CommentsService(){
        this.firestore = FirestoreClient.getFirestore();
    }
    @Nullable
    public Comments documentSnapshotToComment(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        Users user = null;
        ForumPosts forumPost = null;
        if (document.exists()) {
            // Retrieve senderId user details
            DocumentReference userRef = (DocumentReference) document.get("senderId");
            if (userRef != null) {
                DocumentSnapshot userSnapshot = userRef.get().get();
                if (userSnapshot.exists()) {
                    UsersService service = new UsersService();
                    user = service.documentSnapshotToUser(userSnapshot);
                }
            }

            // Retrieve forum post details
            DocumentReference forumRef = (DocumentReference) document.get("forumId");
            if (forumRef != null) {
                DocumentSnapshot forumSnapshot = forumRef.get().get();
                if (forumSnapshot.exists()) {
                    ForumPostsService service = new ForumPostsService();
                    forumPost = service.documentSnapshotToForum(forumSnapshot);
                }
            }

            return (new Comments(document.getId(),
                    document.getString("content"),
                    document.getTimestamp("time"),
                    user,
                    forumPost
            ));
        }
            return null;
    }

    public List<Comments> getAllComments() throws InterruptedException, ExecutionException {
        CollectionReference commentCollection = firestore.collection("Comment");
        Future<QuerySnapshot> future = commentCollection.get();
        QuerySnapshot documents = future.get();

        List<Comments> commentList = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            Comments comment = documentSnapshotToComment(document);
            if (comment != null) {
                commentList.add(comment);
            }
        }
        return commentList;
    }
    @Nullable
    public Comments getCommentById(String commentId) throws ExecutionException, InterruptedException {
        DocumentReference jobRef = firestore.collection("Comment").document(commentId);
        ApiFuture<DocumentSnapshot> future = jobRef.get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToComment(document);
    }

    @Nullable
    public List<Comments> getAllCommentsOnPost(String forumId) throws ExecutionException, InterruptedException {
        DocumentReference commentRef = Utility.retrieveDocumentReference("Comment", forumId);
        Query query = firestore.collection("Comment").whereEqualTo("forumId", forumId);
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
        CollectionReference commentsCollection = firestore.collection("Comment");
        ApiFuture<DocumentReference> future = commentsCollection.add(comment);
        DocumentReference docRef = future.get();
        return docRef.getId();
    }

    public WriteResult deleteComment(String commentId) throws ExecutionException, InterruptedException {
        DocumentReference commentRef = firestore.collection("Comment").document(commentId);
        return commentRef.delete().get();
    }
}
