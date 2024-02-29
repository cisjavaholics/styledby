package cis.javaholics.controllers;

import cis.javaholics.models.reviews.Reviews;
import cis.javaholics.services.ReviewsService;
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
    @GetMapping("/{rPostId}")
    public ResponseEntity<ApiResponseFormat<Object>> getReviewById(@PathVariable String rPostId) {
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
    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponseFormat<Reviews>> getReviewByUserId(@PathVariable String createdBy) {
        try {
            List<Reviews> review = reviewsService.getReviewByUserId(createdBy);
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
    @GetMapping("/businesses/{businessId}")
    public ResponseEntity<ApiResponseFormat<Reviews>> getReviewByBusiness(@PathVariable String businessId) {
        try {
            List<Reviews> review = reviewsService.getReviewByBusiness(businessId);
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
                    .body(new ApiResponseFormat<>(false, "Error Retreiving users",null, e));
        }
    }

    @Operation(summary = "Create a review")
    @PostMapping("/")
    public ResponseEntity<ApiResponseFormat<String>> addReview(@RequestBody Reviews review) {
        try {
            String reviewId = reviewsService.createReview(review);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseFormat<>(true, "Review successfully created.",reviewId, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponseFormat<>(false, "Error creating review.", null, e));
        }
    }

    @Operation(summary = "Delete a review")
    @DeleteMapping("/{rPostId}")
    public ResponseEntity<ApiResponseFormat<Void>> deleteReview(@PathVariable String rPostId) {
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
