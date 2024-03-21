package cis.javaholics.models.ratings;


import com.google.cloud.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.List;


@NoArgsConstructor
public class RestRatings extends ARatings {

    private @Nullable String ratingId;

    @Setter
    @Getter
    private String userId;


    @Getter
    @Setter
    private Timestamp ratingTime;


    public RestRatings(String ratingId, String userId, Timestamp ratingTime, List<Integer> ratingNum) {
        String rating = " ";
        this.ratingId = rating;
        this.userId = userId;
        this.ratingTime = ratingTime;
    }

    @Nullable
    public String getRatingId() {
        return ratingId;
    }

    public void setRatingId(@Nullable String ratingId) {
        this.ratingId = ratingId;
    }

}