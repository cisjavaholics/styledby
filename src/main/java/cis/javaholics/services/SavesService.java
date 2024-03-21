package cis.javaholics.services;

import cis.javaholics.models.businesses.Businesses;
import cis.javaholics.models.forumPosts.ForumPosts;
import cis.javaholics.models.mentions.Mentions;
import cis.javaholics.models.saves.Saves;
import cis.javaholics.models.users.Users;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import io.micrometer.common.lang.Nullable;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
@Service
public class SavesService {
    private final Firestore firestore;

    public SavesService() {
        this.firestore = FirestoreClient.getFirestore();
    }
    public Saves documentSnapshotToSave(DocumentSnapshot document) throws ExecutionException, InterruptedException {
        Users userId = null;
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

            DocumentReference forumRef = (DocumentReference) document.get("fPostId");
            if (forumRef != null) {
                DocumentSnapshot forumSnapshot = forumRef.get().get();
                if (forumSnapshot.exists()) {
                    ForumPostsService service = new ForumPostsService();
                    forumId = service.documentSnapshotToForum(forumSnapshot);
                }
            }


            return (new Saves(document.getId(),
                    document.getTimestamp("savedAt"),
                    userId,
                    forumId
            ));
        }
        return null;
    }

    @Nullable
    public Saves getSaveById(String saveId) throws ExecutionException, InterruptedException {
        DocumentReference saveRef = firestore.collection("saves").document(saveId);
        ApiFuture<DocumentSnapshot> future = saveRef.get();
        DocumentSnapshot document = future.get();
        return documentSnapshotToSave(document);
    }
}
