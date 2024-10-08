package cis.javaholics.controllers;

import cis.javaholics.models.reviews.Reviews;
import cis.javaholics.services.ReviewsService;
import cis.javaholics.util.ApiResponseFormat;
import cis.javaholics.util.Utility;
import com.google.cloud.firestore.WriteResult;
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
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/reviews")
@Tag(name = "Reviews", description = "Operations related to review management")
public class ReviewsController {
    private final ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @Operation(summary = "Get a review by ID")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Review found"),
            @ApiResponse(responseCode = "204", description = "No Review Found"),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve reviews..",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })
    @GetMapping("rPostId/{rPostId}")
    public ResponseEntity<ApiResponseFormat<Object>> getReviewById(@PathVariable (name = "rPostId")String rPostId) {
        try {
            Reviews review = reviewsService.getReviewById(rPostId);
            if (review != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Review found.",review, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "No Reviews found.",null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving user", null, e.getMessage()));
        }
    }

    @Operation(summary = "Get a review by user")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Review found"),
            @ApiResponse(responseCode = "204", description = "No Review Found"),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve reviews..",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })
    @GetMapping("/user_id/{user_id}")
    public ResponseEntity<ApiResponseFormat<Reviews>> getReviewsByUserId(@PathVariable (name = "user_id") String createdBy) {
        try {
            List<Reviews> review = reviewsService.getReviewsByUserId(createdBy);
            if (review != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Review found.",review, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "No Reviews found.",null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving user", null, e.getMessage()));
        }
    }
    @Operation(summary = "Get a review by business")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Review found"),
            @ApiResponse(responseCode = "204", description = "No Review Found"),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve reviews..",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })
    @GetMapping("/business_id/{business_id}")
    public ResponseEntity<ApiResponseFormat<Reviews>> getReviewsByBusiness(@PathVariable (name = "business_id")String businessId) {
        try {
            List<Reviews> review = reviewsService.getReviewsByBusiness(businessId);
            if (review != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Review found.",review, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "No Reviews found.",null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving review", null, e.getMessage()));
        }
    }

    @Operation(summary = "Get all reviews")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Reviews found"),
            @ApiResponse(responseCode = "204", description = "No Reviews Found"),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve reviews..",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })
    @GetMapping("/")
    public ResponseEntity<ApiResponseFormat<Reviews>> getAllReviews() {
        try {
            List<Reviews> reviewList = reviewsService.getAllReviews();
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Review successfully retrieved.",reviewList, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving reviews",null, e));
        }
    }

    @Operation(summary = "Create a review")
    @PostMapping("/create")
    public ResponseEntity<ApiResponseFormat<String>> createReview(@RequestBody Reviews review) {
        try {
            String reviewId = reviewsService.createReview(review);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseFormat<>(true, "Review successfully created.",reviewId, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponseFormat<>(false, "Error creating review.", null, e));
        }
    }

    @PutMapping(path="/{rPostId}", produces = Utility.DEFAULT_MEDIA_TYPE, consumes =  Utility.DEFAULT_MEDIA_TYPE)
    public ResponseEntity<ApiResponseFormat<WriteResult>> updateReview(@PathVariable(name="rPostId") String id, @RequestBody Map<String,Object> updateValues){
        try {
            WriteResult result = reviewsService.updateReview(id, updateValues);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponseFormat<>(true, "Review successfully updated.", result, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error updating review.", null, e));
        }

    }

    @Operation(summary = "Delete a review")
    @DeleteMapping(path = "/{rPostId}")
    public ResponseEntity<ApiResponseFormat<Void>> deleteReview(@PathVariable (name = "rPostId") String rPostId) {
        try {
            reviewsService.deleteReview(rPostId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ApiResponseFormat<>(true, "Review successfully deleted.",null, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error deleting review.",null, e));
        }
    }


}
