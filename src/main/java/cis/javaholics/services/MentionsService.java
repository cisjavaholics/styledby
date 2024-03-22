package cis.javaholics.services;

import cis.javaholics.models.businesses.Businesses;
import cis.javaholics.models.forumPosts.ForumPosts;
import cis.javaholics.models.mentions.Mentions;
import cis.javaholics.models.ratings.Ratings;
import cis.javaholics.models.reviews.Reviews;
import cis.javaholics.models.users.Users;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import io.micrometer.common.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
@Service
public class MentionsService {
    private final Firestore firestore;

    public MentionsService() {
        this.firestore = FirestoreClient.getFirestore();
    }
    public Mentions documentSnapshotToMention(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        Users userId = null;
        List<Users> mentionedUsers = new ArrayList<>();
        List<Businesses> mentionedBus = new ArrayList<>();
        ForumPosts forumId = null;

        if (document.exists()) {
            // Retrieve createdBy user details
            DocumentReference userRef = (DocumentReference) document.get("userId");
            if (userRef != null) {
                DocumentSnapshot userSnapshot = userRef.get().get();
                if (userSnapshot.exists()) {
                    UsersService service = new UsersService();
                    userId = service.documentSnapshotToUser(userSnapshot);
                }
            }

            // Retrieve Mentioned Users details
            List<DocumentReference> usersMentioned = (List<DocumentReference>) document.get("mentionedUsers");
            if (usersMentioned != null) {
                for (DocumentReference userMentioned : usersMentioned) {
                    DocumentSnapshot itemSnapshot = userMentioned.get().get();
                    if (itemSnapshot.exists()) {
                        UsersService service = new UsersService();
                        Users user = service.documentSnapshotToUser(itemSnapshot);
                        mentionedUsers.add(user);
                    }
                }
            }

            // Retrieve Business details
            List<DocumentReference> mentionedBusinesses = (List<DocumentReference>) document.get("mentionedBus");
            if (mentionedBusinesses != null) {
                for (DocumentReference mentionedBusiness : mentionedBusinesses) {
                    DocumentSnapshot itemSnapshot = mentionedBusiness.get().get();
                    if (itemSnapshot.exists()) {
                        BusinessesService service = new BusinessesService();
                        Businesses business = service.documentSnapshotToBusiness(itemSnapshot);
                        mentionedBus.add(business);
                    }
                }
            }


        // Retrieve forumId
        DocumentReference forumRef = (DocumentReference) document.get("forumId");
        if (forumRef != null) {
            DocumentSnapshot forumSnapshot = forumRef.get().get();
            if (forumSnapshot.exists()) {
                ForumPostsService service = new ForumPostsService();
                forumId = service.documentSnapshotToForum(forumSnapshot);
            }
        }

        return (new Mentions(document.getId(),
                document.getTimestamp("mentionAt"),
                userId,
                mentionedUsers,
                mentionedBus,
                forumId
        ));
        }
        return null;
    }

    @Nullable
    public Mentions getMentionById(String mentionId) throws ExecutionException, InterruptedException {
        DocumentReference mentionRef = firestore.collection("mentions").document(mentionId);
        ApiFuture<DocumentSnapshot> future = mentionRef.get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToMention(document);
    }



}


