package cis.javaholics.models.mentions;

import cis.javaholics.models.businesses.Businesses;
import cis.javaholics.models.users.Users;
import cis.javaholics.models.forums.Forums;
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
    private Forums forumId;
    public Mentions(String mentionId, Timestamp mentionedAt, Users userId, List<Users> mentionedsers, List<Businesses> mentionedBus, Forums forumId) {
        super(mentionId, mentionedAt);
        this.userId = userId;
        this.mentionedUsers = mentionedsers;
        this.mentionedBus = mentionedBus;
        this.forumId = forumId;
    }
}