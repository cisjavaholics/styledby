package cis.javaholics.controllers;

import cis.javaholics.models.businesses.Businesses;
import cis.javaholics.services.BusinessesService;
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
@RequestMapping("/api/business")
@Tag(name = "Businesses", description = "Operations related to business management")

public class BusinessesController {
    private final BusinessesService businessesService;

    public BusinessesController(BusinessesService businessesService) {
        this.businessesService = businessesService;
    }

    @GetMapping("/")
    @Operation(summary = "Get a list of all businesses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Businesses found."),
            @ApiResponse(responseCode = "204", description = "No businesses found."),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve businesses.", content = @Content(mediaType = "business/json", schema = @Schema(implementation = ApiResponse.class))),
    })
    public ResponseEntity<ApiResponseFormat<List<Businesses>>> getAllBusinesses() {
        try {
            List<Businesses> businessList = businessesService.getAllBusinesses();
            if (businessList.isEmpty())
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponseFormat<>(true, "No businesses found.", null, null));
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Businesses retrieved successfully", businessList, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving businesses", null, e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an business by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Business found."),
            @ApiResponse(responseCode = "204", description = "No business found."),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve business.", content = @Content(mediaType = "business/json", schema = @Schema(implementation = ApiResponse.class))),
    })
    public ResponseEntity<ApiResponseFormat<Businesses>> getBusinessById(@PathVariable(name="id") String businessId) {
        try {
            Businesses business = businessesService.getBusinessByBusinessId(businessId);
            if (business != null) {
                return ResponseEntity.ok(new ApiResponseFormat<>(true, "Business retrieved successfully", business, null));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseFormat<>(false, "Business not found", null, null));
            }
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving business", null, e.getMessage()));
        }
    }

    @GetMapping("/users/{user_id}")
    @Operation(summary = "Get a list of all businesses by user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Businesses found."),
            @ApiResponse(responseCode = "204", description = "No businesses found."),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve businesses.", content = @Content(mediaType = "business/json", schema = @Schema(implementation = ApiResponse.class))),
    })
    public ResponseEntity<ApiResponseFormat<List<Businesses>>> getBusinessByUserId(@PathVariable(name="user_id") String userId) {
        try {
            List<Businesses> businesses = businessesService.getBusinessesByUserId(userId);
            if (businesses.isEmpty())
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponseFormat<>(true, "No users found.", null, null));
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Users retrieved successfully", businesses, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving users", null, e.getMessage()));
        }
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Get a list of all businesses by category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Businesses found."),
            @ApiResponse(responseCode = "204", description = "No businesses found."),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve businesses.", content = @Content(mediaType = "business/json", schema = @Schema(implementation = ApiResponse.class))),
    })
    public ResponseEntity<ApiResponseFormat<List<Businesses>>> getBusinessByCategory(@PathVariable(name="category") String category) {
        try {
            List<Businesses> businesses = businessesService.getBusinessesByCategory(category);
            if (businesses.isEmpty())
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponseFormat<>(true, "No users found.", null, null));
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Businesses retrieved successfully", businesses, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving users", null, e.getMessage()));
        }
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get a list of all businesses by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Businesses found."),
            @ApiResponse(responseCode = "204", description = "No businesses found."),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve businesses.", content = @Content(mediaType = "business/json", schema = @Schema(implementation = ApiResponse.class))),
    })
    public ResponseEntity<ApiResponseFormat<List<Businesses>>> getBusinessByName(@PathVariable(name="name") String name) {
        try {
            List<Businesses> businesses = businessesService.getBusinessesByName(name);
            if (businesses.isEmpty())
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponseFormat<>(true, "No users found.", null, null));
            return ResponseEntity.ok(new ApiResponseFormat<>(true, "Businesses retrieved successfully", businesses, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error retrieving users", null, e.getMessage()));
        }
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponseFormat<String>> addBusiness(@RequestBody Businesses business) {
        try{
            String id = businessesService.createBusiness(business);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseFormat<>(true, "Business successfully created.", id, null));
        }
        catch(ExecutionException | InterruptedException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error creating Business.", null, e));
        }
    }

    @PutMapping(path="/{bus_id}", produces = Utility.DEFAULT_MEDIA_TYPE, consumes =  Utility.DEFAULT_MEDIA_TYPE)
    public ResponseEntity<ApiResponseFormat<WriteResult>> updateBusiness(@PathVariable(name="bus_id") String id, @RequestBody Map<String,Object> updateValues){
        try {
            WriteResult result = businessesService.updateBusiness(id, updateValues);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponseFormat<>(true, "Business successfully updated.", result, null));
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseFormat<>(false, "Error updating Business.", null, e));
        }

    }

}

