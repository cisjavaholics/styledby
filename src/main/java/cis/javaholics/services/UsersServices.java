package cis.javaholics.services;

import cis.javaholics.models.comments.Comments;
import cis.javaholics.models.reviews.Reviews;
import cis.javaholics.models.saves.Saves;
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
public class UsersService {
    private Firestore firestore;

    public UsersService(){
        this.firestore = FirestoreClient.getFirestore();
    }

    @Nullable
    private Users documentSnapshotToTask(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        if (document.exists()) {
            // set every parameter to null if document exists

            List<Reviews> reviews = new ArrayList<>();
            List<Forums> forums = new ArrayList<>();
            List<Saves> saves = new ArrayList<>();
            int numReviews = 0;
            int numForums = 0;
            int numSaved = 0;


            //Retrieve reviews
            List<DocumentReference> userReviews = (List<DocumentReference>) document.get("Reviews");
            for(DocumentReference userReview :userReviews)
            {
                DocumentSnapshot itemSnapshot = userReview.get().get();
                if(itemSnapshot.exists())
                {
                    reviews.add(itemSnapshot.toObject(Reviews.class));
                }
            }

            //Retrieve forums
            List<DocumentReference> userForums = (List<DocumentReference>) document.get("Forums");
            for(DocumentReference userForum : userForums)
            {
                DocumentSnapshot itemSnapshot = userForum.get().get();
                if(itemSnapshot.exists())
                {
                    forums.add(itemSnapshot.toObject(Forums.class));
                }
            }

            //Retrieve saves
            List<DocumentReference> userSaves = (List<DocumentReference>) document.get("Saves");
            for(DocumentReference userSave : userSaves)
            {
                DocumentSnapshot itemSnapshot = userSave.get().get();
                if(itemSnapshot.exists())
                {
                    saves.add(itemSnapshot.toObject(Saves.class));
                }
            }

            return( new Users(document.getId(),
                    document.getString("username"),
                    document.getString("email"),
                    document.getString("roles"),
                    reviews,
                    forums,
                    saves,
                    numReviews,
                    numForums,
                    numSaved
            ) );
        }
        return null;
    }


    // get list of all reviews
    @Nullable
    private List<Reviews> getReviewsList(Query query) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        List<Reviews> reviewList = new ArrayList<>();

        for (DocumentSnapshot document : documentSnapshots) {
            reviewList.add(document.toObject(Reviews.class));
        }

        return reviewList;
    }
    // get list of all reviews
    @Nullable
    private List<Forums> getForumsList(Query query) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        List<Forums> forumList = new ArrayList<>();

        for (DocumentSnapshot document : documentSnapshots) {
            forumList.add(document.toObject(Forums.class));
        }

        return forumList;
    }

    @Nullable
    private List<Comments> getCommentsList(Query query) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> documentSnapshots = future.get().getDocuments();
        List<Comments> commentList = new ArrayList<>();

        for (DocumentSnapshot document : documentSnapshots) {
            commentList.add(document.toObject(Comments.class));
        }
        return commentList;
    }

}
