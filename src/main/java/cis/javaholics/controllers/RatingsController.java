package cis.javaholics.controllers;


import cis.javaholics.util.ApiResponseFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/ratings/")
@Tag(name = "Ratings", description = "Operations related to business management")


public class RatingController<Rating> {
    private RatingService ratingService = null;

    public RatingController(RatingService RatingsService) {
        this.ratingService = ratingService;
    }

    @Operation(summary = "Get rating by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rating found"),
            @ApiResponse(responseCode = "204", description = "No ratings Found"),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve ratings..",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })
    @GetMapping("/rating_id/{rating_id}")
    public ResponseEntity<ApiResponseFormat<Object>> getRatingById(@PathVariable(name = "rating_id") String ratingId) {
        try {
            Rating rating = ratingService.getRatingById(ratingId);
            if (rating != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Rating found.", rating, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "No ratings found.", null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving rating", null, e.getMessage()));
        }
    }

    @Operation(summary = "Get all ratings on a forum post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ratings found"),
            @ApiResponse(responseCode = "204", description = "No ratings Found"),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve ratings..",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })
    @GetMapping("forum_id/{forum_id}")
    public ResponseEntity<ApiResponseFormat<Object>> getAllRatingsOnPost(@PathVariable(name = "forum_id") String forumId) {
        try {
            List<Rating> ratings = ratingService.getAllRatingsOnPost(forumId);
            if (ratings != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Ratings found.", ratings, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "No ratings found.", null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving ratings", null, e.getMessage()));
        }
    }

    @Operation(summary = "Add rating")
    public ResponseEntity<ApiResponseFormat<String>> addRating(@RequestBody Rating rating) {
        try {
            String ratingId = ratingService.addRating(rating);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseFormat<>(true, "Rating successfully added.", ratingId, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error adding rating.", null, e));
        }
    }

    @Operation(summary = "Delete rating")
    public ResponseEntity<ApiResponseFormat<Void>> deleteRating(@PathVariable(name = "rating_id") String ratingId) {
        try {
            ratingService.deleteRating(ratingId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ApiResponseFormat<>(true, "Rating successfully deleted.", null, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error deleting rating.", null, e));
        }
    }

}