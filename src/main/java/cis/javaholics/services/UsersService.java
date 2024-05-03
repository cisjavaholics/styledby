package cis.javaholics.services;

import cis.javaholics.models.businesses.Businesses;
import cis.javaholics.models.forumPosts.ForumPosts;
import cis.javaholics.models.likes.Likes;
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
            Users user = new Users();
            user.setUserId(document.getId());
            user.setUsername(document.getString("username"));
            user.setEmail(document.getString("email"));
            List<Reviews> reviews = new ArrayList<>();
            List<Saves> saves = new ArrayList<>();
            List<Businesses> businesses = new ArrayList<>();


            //Retrieve businesses
            List<DocumentReference> userBusinesses = (List<DocumentReference>) document.get("businesses");
            if (userBusinesses != null) {
                for (DocumentReference userBusiness : userBusinesses) {
                    DocumentSnapshot itemSnapshot = userBusiness.get().get();
                    if (itemSnapshot.exists()) {
                        BusinessesService service = new BusinessesService();
                        Businesses business = service.documentSnapshotToBusiness(itemSnapshot);
                        businesses.add(business);
                    }
                }
                user.setBusinesses(businesses);
            }


            return user;
        }
        return null;
    }

    public List<Users> getAllUsers() throws InterruptedException, ExecutionException {
        CollectionReference userCollection = firestore.collection("User");
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
        DocumentReference userRef = firestore.collection("User").document(userId);
        Future<DocumentSnapshot> future = userRef.get();
        DocumentSnapshot documentSnapshot = future.get();
        return documentSnapshotToUser(documentSnapshot);
    }
    public String createUser(Users user) throws ExecutionException, InterruptedException {
        CollectionReference usersCollection = firestore.collection("User");
        ApiFuture<DocumentReference> future = usersCollection.add(user);
        DocumentReference docRef = future.get();
        return docRef.getId();
    }

    public WriteResult updateUser(String id, Map<String, Object> updateValues) throws ExecutionException, InterruptedException {

        String[] allowed = {"username", "email", "businesses", "reviews", "forums", "saved"};

        List<String> allowedFields = Arrays.asList(allowed);
        Map<String, Object> formattedValues = new HashMap<>();

        for(Map.Entry<String, Object> entry : updateValues.entrySet()) {
            String key = entry.getKey();
            if(allowedFields.contains(key)) {
                if(key != "forums")
                    formattedValues.put(key, entry.getValue());
                else {
                    ArrayList<DocumentReference> list = new ArrayList<>();
                    LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)entry.getValue();
                    ArrayList<HashMap<String, String>> refMap = (ArrayList<HashMap<String, String>>)(map.get("references"));
                    for(HashMap<String, String> ref :  refMap){
                        DocumentReference doc = firestore.collection("ForumPost").document(ref.get("id"));
                        list.add(doc);
                    }
                    formattedValues.put(key, list);
                }

            }
        }
        DocumentReference userDoc = firestore.collection("User").document(id);
        ApiFuture<WriteResult> result = userDoc.update(formattedValues);
        return result.get();
    }

    public WriteResult deleteUser(String id) throws ExecutionException, InterruptedException {
        DocumentReference userRef = firestore.collection("User").document(id);
        return userRef.delete().get();
    }



}
