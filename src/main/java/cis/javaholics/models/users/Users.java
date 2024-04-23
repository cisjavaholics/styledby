package cis.javaholics.models.users;

import cis.javaholics.models.businesses.Businesses;
import cis.javaholics.models.forumPosts.ForumPosts;
import cis.javaholics.models.reviews.Reviews;
import cis.javaholics.models.saves.Saves;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data //creates setters and getters automatically
@NoArgsConstructor //creates no argument constructor automatically
public class Users extends AUsers {
    private List<Reviews> reviews;
    private List<Saves> saved;
    private List<Businesses> businesses;

    public Users(String userId, String username, String email, List<Reviews> reviews, List<Saves> saved, List <Businesses> business) {
        super(userId, username, email);
        this.reviews = reviews;
        this.saved = saved;
        this.businesses = businesses;
    }
}
