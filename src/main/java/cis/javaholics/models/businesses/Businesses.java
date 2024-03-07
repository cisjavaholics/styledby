package cis.javaholics.models.businesses;

import cis.javaholics.models.mentions.Mentions;
import cis.javaholics.models.reviews.Reviews;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data //creates setters and getters automatically
@NoArgsConstructor //creates no argument constructor automatically
public class Businesses extends ABusinesses {
    private int rating;
    private List<Reviews> reviews;
    private List<Mentions> mentions;
    private int numReviews;
    private int numMentions;
    public Businesses(String businessId, String name,String category, int rating, List<Reviews> reviews, List<Mentions> mentions, int numReviews, int numMentions) {
        super(businessId, name, category);
        this.rating = rating;
        this.reviews = reviews;
        this.mentions = mentions;
        this.numReviews = numReviews;
        this.numMentions = numMentions;
    }
}