package cis.javaholics.services;

import cis.javaholics.models.businesses.Businesses;
import cis.javaholics.models.forumPosts.ForumPosts;
import cis.javaholics.models.reviews.Reviews;
import cis.javaholics.models.saves.Saves;
import cis.javaholics.models.users.Users;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import io.micrometer.common.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class UsersService {
    private Firestore firestore;

    public UsersService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    @Nullable
    public Users documentSnapshotToUser(DocumentSnapshot document) throws ExecutionException, InterruptedException {
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
            if(userReviews != null) {
                for (DocumentReference userReview : userReviews) {
                    DocumentSnapshot itemSnapshot = userReview.get().get();
                    if (itemSnapshot.exists()) {
                        ReviewsService service = new ReviewsService();
                        Reviews review = service.documentSnapshotToReview(itemSnapshot);
                        reviews.add(review);
                    }
                }
            }

            //Retrieve forums
            List<DocumentReference> userForums = (List<DocumentReference>) document.get("Forums");
            if(userForums != null) {
                for (DocumentReference userForum : userForums) {
                    DocumentSnapshot itemSnapshot = userForum.get().get();
                    if (itemSnapshot.exists()) {
                        ForumPostsService service = new ForumPostsService();
                        ForumPosts forumPost = service.documentSnapshotToForumPost(itemSnapshot);
                        forums.add(forumPost);
                    }
                }
            }

            //Retrieve saves
            List<DocumentReference> userSaves = (List<DocumentReference>) document.get("Saves");
            if(userSaves != null) {
                for (DocumentReference userSave : userSaves) {
                    DocumentSnapshot itemSnapshot = userSave.get().get();
                    if (itemSnapshot.exists()) {

                    }
                }
            }

            //Retrieve businesses
            List<DocumentReference> userBusinesses = (List<DocumentReference>) document.get("Businesses");
            if(userBusinesses != null) {
                for (DocumentReference userBusiness : userBusinesses) {
                    DocumentSnapshot itemSnapshot = userBusiness.get().get();
                    if (itemSnapshot.exists()) {
                        BusinessesService service = new BusinessesService();
                        Businesses business = service.documentSnapshotToBusiness(itemSnapshot);
                        businesses.add(business);
                    }
                }
            }

            return (new Users(document.getId(),
                    document.getString("username"),
                    document.getString("email"),
                    reviews,
                    forums,
                    saves,
                    businesses
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
    public String createUser(Users user) throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = firestore.collection("Users");
        ApiFuture<DocumentReference> future = usersCollection.add(user);
        DocumentReference docRef = future.get();
        return docRef.getId();
    }

    public WriteResult updateUser(String id, Map<String, Object> updateValues) throws ExecutionException, InterruptedException {

        String[] allowed = {"username", "email", "businesses", "reviews", "forumPosts", "saved"};

        List<String> allowedFields = Arrays.asList(allowed);
        Map<String, Object> formattedValues = new HashMap<>();

        for(Map.Entry<String, Object> entry : updateValues.entrySet()) {
            String key = entry.getKey();
            if(allowedFields.contains(key)) {
                formattedValues.put(key, entry.getValue());

            }
        }
        DocumentReference userDoc = firestore.collection("Users").document(id);
        ApiFuture<WriteResult> result = userDoc.update(formattedValues);
        return result.get();
    }

}
