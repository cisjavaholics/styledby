package cis.javaholics.models.saves;

import cis.javaholics.models.users.Users;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Schema(description = "Represents a save on a forum post")
public class Saves extends ASaves{
    @Schema(example = "12345", description = "Unique ID of user that saved the forum post")
    private Users userId;
    @Schema(example = "12345", description = "Unique ID of the forum post that was saved")
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
