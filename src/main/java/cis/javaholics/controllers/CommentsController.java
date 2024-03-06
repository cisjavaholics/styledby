package cis.javaholics.controllers;

import cis.javaholics.models.comments.Comments;
import cis.javaholics.models.reviews.Reviews;
import cis.javaholics.services.CommentsService;
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

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/comments/")
@Tag(name = "Comments", description = "Operations related to comment management")
public class CommentsController {
    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @Operation(summary = "Get comment by id")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Comment found"),
            @ApiResponse(responseCode = "204", description = "No comments Found"),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve comments..",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })
    @GetMapping("/comment_id/{comment_id}")
    public ResponseEntity<ApiResponseFormat<Comments>> getCommentbyId(@PathVariable (name = "comment_id") String commentId){
        try{
            Comments comment = commentsService.getCommentById(commentId);
            if (comment != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Comment found.",comment, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "No comments found.",null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving comment", null, e.getMessage()));
        }
    }

    @Operation(summary = "Get all comments on a forum post")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Comments found"),
            @ApiResponse(responseCode = "204", description = "No comments Found"),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve commets..",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseFormat.class)))
    })
    @GetMapping("forum_id/{{forum_id}")
    public ResponseEntity<ApiResponseFormat<Comments>> getAllCommentsOnPost(@PathVariable (name = "forum_id") String forumId) {
        try {
            List<Comments> comments = commentsService.getAllCommentsOnPost(forumId);
            if (comments != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Comments found.",comments, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "No comments found.",null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving comments", null, e.getMessage()));
        }
    }
    @Operation(summary = "Add comment")
    public ResponseEntity<ApiResponseFormat<String>> addComment(@RequestBody Comments comment) {
        try {
            String commentId = commentsService.addComment(comment);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseFormat<>(true, "Comment successfully added.",commentId, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error adding comment.", null, e));
        }
    }

    @Operation(summary = "Delete comment")
    public ResponseEntity<ApiResponseFormat<Void>> deleteComment(@PathVariable (name = "comment_id") String commentId) {
        try {
            commentsService.deleteComment(commentId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ApiResponseFormat<>(true, "Comment successfully deleted.",null, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error deleting comment.",null, e));
        }
    }

}
