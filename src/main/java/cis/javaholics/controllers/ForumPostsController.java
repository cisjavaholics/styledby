package cis.javaholics.controllers;

import cis.javaholics.models.forumPosts.ForumPosts;
import cis.javaholics.services.ForumPostsService;
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
@RequestMapping("/api/forumPosts")
@Tag(name = "ForumPosts", description = "Operations related to forum post management")
public class ForumPostsController {
    private final ForumPostsService forumPostsService;

    public ForumPostsController(ForumPostsService forumPostsService) {
        this.forumPostsService = forumPostsService;
    }

    @Operation(summary = "Get a forum post by ID")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Forum Post found"),
            @ApiResponse(responseCode = "204", description = "No Forum Post Found"),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve forum posts..",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })
    @GetMapping("fPostId/{fPostId}")
    public ResponseEntity<ApiResponseFormat<Object>> getForumPostById(@PathVariable(name = "fPostId")String fPostId) {
        try {
            ForumPosts forumPost = forumPostsService.getForumPostById(fPostId);
            if (forumPost != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Forum Post found.",forumPost, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "No Forum Posts found.",null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving user", null, e.getMessage()));
        }
    }

    @Operation(summary = "Get a forum post by user")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Forum Post found"),
            @ApiResponse(responseCode = "204", description = "No Forum Post Found"),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve forum posts..",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })
    @GetMapping("/user_id/{user_id}")
    public ResponseEntity<ApiResponseFormat<ForumPosts>> getForumPostsByUserId(@PathVariable (name = "user_id") String postedBy) {
        try {
            List<ForumPosts> forumPost = forumPostsService.getForumPostsByUserId(postedBy);
            if (forumPost != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Forum Post found.",forumPost, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "No Forum Posts found.",null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving user", null, e.getMessage()));
        }
    }

    @Operation(summary = "Get all forum posts")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Forum Posts found"),
            @ApiResponse(responseCode = "204", description = "No Forum Posts Found"),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve forum posts..",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })
    @GetMapping("/")
    public ResponseEntity<ApiResponseFormat<ForumPosts>> getAllForumPosts() {
        try {
            List<ForumPosts> forumPostList = forumPostsService.getAllForumPosts();
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Forum Post successfully retrieved.",forumPostList, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving users",null, e));
        }
    }

    @Operation(summary = "Create a forum post")
    public ResponseEntity<ApiResponseFormat<String>> createForumPost(@RequestBody ForumPosts forumPost) {
        try {
            String forumPostId = forumPostsService.createForumPost(forumPost);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseFormat<>(true, "Forum Post successfully created.",forumPostId, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error creating forum post.", null, e));
        }
    }

    @Operation(summary = "Delete a forum post")
    public ResponseEntity<ApiResponseFormat<Void>> deleteForumPost(@PathVariable (name = "fPostId") String fPostId) {
        try {
            forumPostsService.deleteForumPost(fPostId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ApiResponseFormat<>(true, "Forum Post successfully deleted.",null, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error deleting forum post.",null, e));
        }
    }
}
