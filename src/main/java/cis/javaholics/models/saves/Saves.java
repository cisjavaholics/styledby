package cis.javaholics.models.saves;

import cis.javaholics.models.users.Users;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Saves extends ASaves{
    private Users userId;
    private ForumPosts forumId;

    public Saves(String saveId, Timestamp savedAt, Users userId, ForumPosts forumId) {
        super(saveId, savedAt);
        this.userId = userId;
        this.forumId = forumId;
    }

    public Saves(Users userId, ForumPosts forumId) {
        this.userId = userId;
        this.forumId = forumId;
    }
}
