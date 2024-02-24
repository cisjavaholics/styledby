package cis.javaholics.models.users;

import lombok.Data;
import lombok.NoArgsConstructor;
import cis.javaholics.models.Reviews;
import cis.javaholics.models.Forums;
import cis.javaholics.models.Saves;


import java.util.List;

@Data //creates setters and getters automatically
@NoArgsConstructor //creates no argument constructor automatically
public class Users extends AUsers {
    private List<Reviews> reviews;
    private List<Forums> forums;
    private List<Saves> saved;
    private int numReviews;
    private int numForums;
    private int numSaved;
    public Users(String userId, String username, String email, List<String> roles, List<Reviews> reviews, List<Forums> forums, List<Saves> saved, int numReviews, int numForums, int numSaved) {
        super(userId, username, email, roles);
        this.reviews = reviews;
        this.forums = forums;
        this.saved = saved;
        this.numReviews = numReviews;
        this.numForums = numForums;
        this.numSaved = numSaved;

    }
}
