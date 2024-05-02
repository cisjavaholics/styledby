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

import java.util.*;
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
            ForumPosts forum = new ForumPosts();
            forum.setFPostId(document.getId());
            forum.setTopic(document.getString("topic"));
            forum.setDescription(document.getString("description"));
            forum.setTitle(document.getString("title"));
            forum.setPhotos((List<String>)document.get("photos"));
            forum.setPostedAtS(document.getTimestamp("postedAt"));
            Users postedBy = null;
            List<Likes> likes = new ArrayList<>();
            List<Comments> comments = new ArrayList<>();

            // Retrieve postedBy user details
            DocumentReference userRef = (DocumentReference) document.get("postedBy");
            if (userRef != null) {
                DocumentSnapshot userSnapshot = userRef.get().get();
                if (userSnapshot.exists()) {
                    UsersService service = new UsersService();
                    postedBy = service.documentSnapshotToUser(userSnapshot);
                    forum.setPostedBy(postedBy);
                }
            }


            // Retrieve Likes details
            List<DocumentReference> likeList = (List<DocumentReference>) document.get("likes");
            if (likeList != null) {
                for(DocumentReference likeRef : likeList) {
                    DocumentSnapshot likeSnapshot = likeRef.get().get();
                    if (likeSnapshot.exists()) {
                        LikesService service = new LikesService();
                        Likes like = service.documentSnapshotToLike(likeSnapshot);
                        likes.add(like);
                    }
                }
                forum.setLikes(likes);
            }


            return forum;
        }
        return null;
    }

    @Nullable
    public ForumPosts getForumPostById(String fPostId) throws ExecutionException, InterruptedException {
        DocumentReference forumRef = firestore.collection("ForumPost").document(fPostId);
        ApiFuture<DocumentSnapshot> future = forumRef.get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToForum(document);
    }

    @Nullable
    public List<ForumPosts> getForumPostsByUserId(String userId) throws ExecutionException, InterruptedException {
        DocumentReference forumPostRef = Utility.retrieveDocumentReference("Reviews", userId);
        Query query = firestore.collection("ForumPost").whereEqualTo("postedBy", userId);
        return getForumPostList(query);
    }


    public List<ForumPosts> getAllForumPosts() throws ExecutionException, InterruptedException {
        CollectionReference forumPostCollection = firestore.collection("ForumPost");
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
        CollectionReference forumPostsCollection = firestore.collection("ForumPost");
        ApiFuture<DocumentReference> future = forumPostsCollection.add(forumPost);
        DocumentReference docRef = future.get();
        return docRef.getId();
    }

    public WriteResult updateForumPost(String id, Map<String, Object> updateValues) throws ExecutionException, InterruptedException {

        String[] allowed = {"topic", "description", "title"};

        List<String> allowedFields = Arrays.asList(allowed);
        Map<String, Object> formattedValues = new HashMap<>();

        for(Map.Entry<String, Object> entry : updateValues.entrySet()) {
            String key = entry.getKey();
            if(allowedFields.contains(key)) {
                formattedValues.put(key, entry.getValue());

            }
        }
        DocumentReference forumDoc = firestore.collection("ForumPost").document(id);
        ApiFuture<WriteResult> result = forumDoc.update(formattedValues);
        return result.get();
    }
    public WriteResult deleteForumPost(String id) throws ExecutionException, InterruptedException {
        DocumentReference forumRef = firestore.collection("ForumPost").document(id);
        return forumRef.delete().get();
    }
}
