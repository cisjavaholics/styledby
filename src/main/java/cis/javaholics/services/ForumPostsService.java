package cis.javaholics.services;


import cis.javaholics.models.likes.Likes;
import cis.javaholics.models.comments.Comments;
import cis.javaholics.models.forumPosts.ForumPosts;
import cis.javaholics.models.reviews.Reviews;
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

@Service
public class ForumPostsService {

    private Firestore firestore;

    public ForumPostsService(){
        this.firestore = FirestoreClient.getFirestore();
    }

    @Nullable
    public ForumPosts documentSnapshotToForum(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        if (document.exists()) {
            Users postedBy = null;
            List<Likes> likes = null;
            List<Comments> comments = null;

            // Retrieve postedBy user details
            DocumentReference userRef = (DocumentReference) document.get("postedBy");
            if (userRef != null) {
                DocumentSnapshot userSnapshot = userRef.get().get();
                if (userSnapshot.exists()) {
                    UsersService service = new UsersService();
                    postedBy = service.documentSnapshotToUser(userSnapshot);
                }
            }

            // Retrieve Likes details
            List<DocumentReference> likeList = (List<DocumentReference>) document.get("likes");
            for(DocumentReference likeRef : likeList)
            {
                DocumentSnapshot likeSnapshot = likeRef.get().get();
                if(likeSnapshot.exists())
                {
                    LikesService service = new LikesService();
                    Likes like = service.documentSnapshotToLike(likeSnapshot);
                    likes.add(like);
                }
            }

            // Retrieve comments details
            List<DocumentReference> commentList = (List<DocumentReference>) document.get("comments");
            for(DocumentReference commentRef : commentList)
            {
                DocumentSnapshot commentSnapshot = commentRef.get().get();
                if(commentSnapshot.exists())
                {
                    CommentsService service = new CommentsService();
                    Comments comment = service.documentSnapshotToComment(commentSnapshot);
                    comments.add(comment);
                }
            }

            return( new ForumPosts(document.getId(),
                    document.getString("topic"),
                    document.getString("description"),
                    document.getString("title"),
                    (List<String>)document.get("photos"),
                    document.getTimestamp("postedAt"),
                    postedBy,
                    likes,
                    comments
            ) );
        }
        return null;
    }

    @Nullable
    public ForumPosts getForumPostById(String fPostId) throws ExecutionException, InterruptedException {
        CollectionReference forumPostsCollection = firestore.collection("fPostId");
        ApiFuture<DocumentSnapshot> future = forumPostsCollection.document(fPostId).get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToForum(document);
    }

    @Nullable
    public List<ForumPosts> getForumPostsByUserId(String userId) throws ExecutionException, InterruptedException {
        DocumentReference forumPostRef = Utility.retrieveDocumentReference("Reviews", userId);
        Query query = firestore.collection("ForumPosts").whereEqualTo("postedBy", userId);
        return getForumPostList(query);
    }


    public List<ForumPosts> getAllForumPosts() throws ExecutionException, InterruptedException {
        CollectionReference forumPostCollection = firestore.collection("ForumPosts");
        ApiFuture<QuerySnapshot> future = forumPostCollection.get();
        List<ForumPosts> forumPostList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            ForumPosts forumPost = documentSnapshotToForum(document);
            if (forumPost != null) {
                forumPostList.add(forumPost);
            }
        }
        return forumPostList;
    }


    @Nullable
    private List<ForumPosts> getForumPostList(Query query) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        List<ForumPosts> forumPostList = new ArrayList<>();

        for (DocumentSnapshot document : documentSnapshots) {
            forumPostList.add(document.toObject(ForumPosts.class));
        }

        return forumPostList;
    }

    public String createForumPost(ForumPosts forumPost) throws ExecutionException, InterruptedException {
        CollectionReference forumPostsCollection = firestore.collection("ForumPosts");
        ApiFuture<DocumentReference> future = forumPostsCollection.add(forumPost);
        DocumentReference docRef = future.get();
        return docRef.getId();
    }

    public WriteResult deleteForumPost(String fPostId) throws ExecutionException, InterruptedException {
        DocumentReference forumPostRef = firestore.collection("ForumPosts").document(fPostId);
        return forumPostRef.delete().get();
    }
}
