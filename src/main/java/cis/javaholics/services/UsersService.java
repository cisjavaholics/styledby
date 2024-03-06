package cis.javaholics.services;

import cis.javaholics.models.businesses.Businesses;
import cis.javaholics.models.forumPosts.ForumPosts;
import cis.javaholics.models.reviews.Reviews;
import cis.javaholics.models.saves.Saves;
import cis.javaholics.models.users.Users;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import io.micrometer.common.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class UsersService {
    private Firestore firestore;

    public UsersService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    @Nullable
    private Users documentSnapshotToUser(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        if (document.exists()) {
            List<Reviews> reviews = new ArrayList<>();
            List<ForumPosts> forums = new ArrayList<>();
            List<Saves> saves = new ArrayList<>();
            List<Businesses> businesses = new ArrayList<>();
            int numReviews = 0;
            int numForums = 0;
            int numSaved = 0;


            //Retrieve reviews
            List<DocumentReference> userReviews = (List<DocumentReference>) document.get("Reviews");
            for (DocumentReference userReview : userReviews) {
                DocumentSnapshot itemSnapshot = userReview.get().get();
                if (itemSnapshot.exists()) {
                    reviews.add(itemSnapshot.toObject(Reviews.class));
                }
            }

            //Retrieve forums
            List<DocumentReference> userForums = (List<DocumentReference>) document.get("Forums");
            for (DocumentReference userForum : userForums) {
                DocumentSnapshot itemSnapshot = userForum.get().get();
                if (itemSnapshot.exists()) {
                    forums.add(itemSnapshot.toObject(ForumPosts.class));
                }
            }

            //Retrieve saves
            List<DocumentReference> userSaves = (List<DocumentReference>) document.get("Saves");
            for (DocumentReference userSave : userSaves) {
                DocumentSnapshot itemSnapshot = userSave.get().get();
                if (itemSnapshot.exists()) {
                    saves.add(itemSnapshot.toObject(Saves.class));
                }
            }

            //Retrieve businesses
            List<DocumentReference> userBusinesses = (List<DocumentReference>) document.get("Businesses");
            for (DocumentReference userBusiness : userBusinesses) {
                DocumentSnapshot itemSnapshot = userBusiness.get().get();
                if (itemSnapshot.exists()) {
                    businesses.add(itemSnapshot.toObject(Businesses.class));
                }
            }

            return (new Users(document.getId(),
                    document.getString("username"),
                    document.getString("email"),
                    reviews,
                    forums,
                    saves,
                    businesses,
                    numReviews,
                    numForums,
                    numSaved
            ));
        }
        return null;
    }
    public List<Users> getAllUsers() throws InterruptedException, ExecutionException {
        CollectionReference userCollection = firestore.collection("Users");
        Future<QuerySnapshot> future = userCollection.get();
        QuerySnapshot documents = future.get();

        List<Users> userList = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            Users user = documentSnapshotToUser(document);
            if (user != null) {
                userList.add(user);
            }
        }
        return userList;
    }
    public Users getUserByUserId(String userId) throws InterruptedException, ExecutionException {
        DocumentReference userRef = firestore.collection("Users").document(userId);
        Future<DocumentSnapshot> future = userRef.get();
        DocumentSnapshot documentSnapshot = future.get();
        return documentSnapshotToUser(documentSnapshot);
    }


}
