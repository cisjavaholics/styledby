package cis.javaholics.services;

import cis.javaholics.models.businesses.Businesses;
import cis.javaholics.models.mentions.Mentions;
import cis.javaholics.models.reviews.Reviews;
import cis.javaholics.models.users.Users;
import cis.javaholics.util.Utility;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import io.micrometer.common.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class BusinessesService {
    private Firestore firestore;

    public BusinessesService() {
        this.firestore = FirestoreClient.getFirestore();
    }

    public Businesses documentSnapshotToBusiness(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        if (document.exists()) {
            int rating = 0;
            List<Reviews> reviews = new ArrayList<>();
            List<Mentions> mentions = new ArrayList<>();
            int numReviews = 0;
            int numMentions = 0;


            //Retrieve reviews
            List<DocumentReference> businessReviews = (List<DocumentReference>) document.get("reviews");
            if (businessReviews != null) {
                for (DocumentReference businessReview : businessReviews) {
                    DocumentSnapshot itemSnapshot = businessReview.get().get();
                    if (itemSnapshot.exists()) {
                        ReviewsService service = new ReviewsService();
                        Reviews review = service.documentSnapshotToReview(itemSnapshot);
                        reviews.add(review);
                    }
                }
            }

            //Retrieve mentions
            List<DocumentReference> businessMentions = (List<DocumentReference>) document.get("mentions");
            if (businessMentions != null) {
                for (DocumentReference businessMention : businessMentions) {
                    DocumentSnapshot itemSnapshot = businessMention.get().get();
                    if (itemSnapshot.exists()) {
                        MentionsService service = new MentionsService();
                        Mentions mention = service.documentSnapshotToMention(itemSnapshot);
                        mentions.add(mention);
                    }
                }
            }

            return (new Businesses(document.getId(),
                    document.getString("name"),
                    document.getString("category"),
                    rating,
                    reviews,
                    mentions
            ));
        }
        return null;
    }
    public List<Businesses> getAllBusinesses() throws InterruptedException, ExecutionException {
        CollectionReference businessCollection = firestore.collection("Business");
        Future<QuerySnapshot> future = businessCollection.get();
        QuerySnapshot documents = future.get();

        List<Businesses> businessesList = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
            Businesses business = documentSnapshotToBusiness(document);
            if (business != null) {
                businessesList.add(business);
            }
        }
        return businessesList;
    }
    public Businesses getBusinessByBusinessId(String businessId) throws InterruptedException, ExecutionException {
        DocumentReference businessRef = firestore.collection("Business").document(businessId);
        Future<DocumentSnapshot> future = businessRef.get();
        DocumentSnapshot documentSnapshot = future.get();
        return documentSnapshotToBusiness(documentSnapshot);
    }

    private void processBusinesses( QuerySnapshot queryDocumentSnapshots, List<Businesses> businesses) throws ExecutionException, InterruptedException {
        for(QueryDocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
            Businesses business = documentSnapshotToBusiness(document);
            businesses.add(business);
        }
    }

    public List<Businesses> getBusinessesByUserId(String userId) throws InterruptedException, ExecutionException {
        List<Businesses> businesses= new ArrayList<>();
        ApiFuture<QuerySnapshot> appFuture = firestore.collection("Business")
                .whereEqualTo("userId", Utility.retrieveDocumentReference("User", userId)).get();
        processBusinesses(appFuture.get(), businesses);
        return businesses;
    }

    public List<Businesses> getBusinessesByCategory(String category) throws InterruptedException, ExecutionException {
        List<Businesses> businesses= new ArrayList<>();
        ApiFuture<QuerySnapshot> appFuture = firestore.collection("Business")
                .whereEqualTo("category", category).get();
        processBusinesses(appFuture.get(), businesses);
        return businesses;
    }

    public List<Businesses> getBusinessesByName(String name) throws InterruptedException, ExecutionException {
        List<Businesses> businesses= new ArrayList<>();
        ApiFuture<QuerySnapshot> appFuture = firestore.collection("Business")
                .whereEqualTo("name", name).get();
        processBusinesses(appFuture.get(), businesses);
        return businesses;
    }

    public String createBusiness(Businesses business) throws ExecutionException, InterruptedException {
        CollectionReference businessesCollection = firestore.collection("Business");
        ApiFuture<DocumentReference> future = businessesCollection.add(business);
        DocumentReference docRef = future.get();
        return docRef.getId();
    }

    public WriteResult updateBusiness(String id, Map<String, Object> updateValues) throws ExecutionException, InterruptedException {

        String[] allowed = {"category", "email", "businesses", "reviews", "forumPosts", "mentions", "rating"};

        List<String> allowedFields = Arrays.asList(allowed);
        Map<String, Object> formattedValues = new HashMap<>();

        for(Map.Entry<String, Object> entry : updateValues.entrySet()) {
            String key = entry.getKey();
            if(allowedFields.contains(key)) {
                formattedValues.put(key, entry.getValue());

            }
        }
        DocumentReference busDoc = firestore.collection("Business").document(id);
        ApiFuture<WriteResult> result = busDoc.update(formattedValues);
        return result.get();
    }



}
