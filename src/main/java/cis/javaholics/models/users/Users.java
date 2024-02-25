package cis.javaholics.models.users;

import cis.javaholics.models.reviews.Reviews;
import cis.javaholics.models.saves.Saves;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data //creates setters and getters automatically
@NoArgsConstructor //creates no argument constructor automatically
public class Users extends AUsers {
    private List<Reviews> reviews;
    private List<ForumPosts> forums;
    private List<Saves> saved;
    private int numReviews;
    private int numForums;
    private int numSaved;
    public Users(String userId, String username, String email, List<String> roles, List<Reviews> reviews, List<ForumPosts> forums, List<Saves> saved, int numReviews, int numForums, int numSaved) {
        super(userId, username, email, roles);
        this.reviews = reviews;
        this.forums = forums;
        this.saved = saved;
        this.numReviews = numReviews;
        this.numForums = numForums;
        this.numSaved = numSaved;

    }
}
