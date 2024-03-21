package cis.javaholics.controllers;


import cis.javaholics.models.likes.Likes;
import cis.javaholics.services.LikesService;
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
@RequestMapping("api/likes/")
@Tag(name = "Likes", description = "Operations related to like management")
public class LikesController {
    private final LikesService likesService;

    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    @Operation(summary = "Get like by id")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Like found"),
            @ApiResponse(responseCode = "204", description = "No likes Found"),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve likes..",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })
    @GetMapping("/like_id/{like_id}")
    public ResponseEntity<ApiResponseFormat<Likes>> getLikeById(@PathVariable(name = "like_id") String likeId){
        try{
            Likes like = likesService.getLikeById(likeId);
            if (like != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Like found.",like, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "No likes found.",null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving like", null, e.getMessage()));
        }
    }

    @Operation(summary = "Get all likes on a review")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Like found"),
            @ApiResponse(responseCode = "204", description = "No likes Found"),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve likes..",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })
    @GetMapping("review_id/{review_id}")
    public ResponseEntity<ApiResponseFormat<Likes>> getAllLikesOnPost(@PathVariable (name = "review_id") String reviewId) {
        try {
            List<Likes> likes = likesService.getAllLikesOnPost(reviewId);
            if (likes != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Like found.",likes, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "No likes found.",null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving like", null, e.getMessage()));
        }
    }
    @Operation(summary = "Add like")
    public ResponseEntity<ApiResponseFormat<String>> addLike(@RequestBody Likes like) {
        try {
            String likeId = likesService.addLike(like);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseFormat<>(true, "Like successfully added.",likeId, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error adding like.", null, e));
        }
    }

    @Operation(summary = "Delete like")
    public ResponseEntity<ApiResponseFormat<Void>> deleteLike(@PathVariable (name = "like_id") String likeId) {
        try {
            likesService.deleteLike(likeId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ApiResponseFormat<>(true, "Like successfully deleted.",null, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error deleting like.",null, e));
        }
    }
}
