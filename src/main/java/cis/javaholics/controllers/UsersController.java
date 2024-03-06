package cis.javaholics.controllers;

import cis.javaholics.models.users.Users;
import cis.javaholics.services.UsersService;
import cis.javaholics.util.ApiResponseFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Operations related to user management")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    @Operation(summary = "Get a list of all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found."),
            @ApiResponse(responseCode = "204", description = "No users found."),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve users.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
    })
    public ResponseEntity<ApiResponseFormat<List<Users>>> getAllUsers() {
        try {
            List<Users> userList = usersService.getAllUsers();
            if (userList.isEmpty())
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponseFormat<>(true, "No users found.", null, null));
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Users retrieved successfully", userList, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving users", null, e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found."),
            @ApiResponse(responseCode = "204", description = "No user found."),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve user.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
    })
    public ResponseEntity<ApiResponseFormat<Users>> getUserById(@PathVariable(name="id") String userId) {
        try {
            Users user = usersService.getUserByUserId(userId);
            if (user != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "User retrieved successfully", user, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "User not found", null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving user", null, e.getMessage()));
        }
    }
}

