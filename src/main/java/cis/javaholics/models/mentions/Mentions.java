package cis.javaholics.models.mentions;

import cis.javaholics.models.businesses.Businesses;
import cis.javaholics.models.forumPosts.ForumPosts;
import cis.javaholics.models.users.Users;
import cis.javaholics.models.reviews.Reviews;

import com.google.cloud.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data //creates setters and getters automatically
@NoArgsConstructor //creates no argument constructor automatically
public class Mentions extends AMentions {
    private Users userId;
    private List<Users> mentionedUsers;
    private List<Businesses> mentionedBus;
    private ForumPosts forumId;
    public Mentions(String mentionId, Timestamp mentionedAt, Users userId, List<Users> mentionedUsers, List<Businesses> mentionedBus, ForumPosts forumId) {
        super(mentionId, mentionedAt);
        this.userId = userId;
        this.mentionedUsers = mentionedUsers;
        this.mentionedBus = mentionedBus;
        this.forumId = forumId;
    }
}