package cis.javaholics.controllers;

import cis.javaholics.models.businesses.Businesses;
import cis.javaholics.services.BusinessesService;
import cis.javaholics.util.ApiResponseFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/api/bus")
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

    @GetMapping("/user/{id}")
    @Operation(summary = "Get a list of all businesses by user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Businesses found."),
            @ApiResponse(responseCode = "204", description = "No businesses found."),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve businesses.", content = @Content(mediaType = "business/json", schema = @Schema(implementation = ApiResponse.class))),
    })
    public ResponseEntity<ApiResponseFormat<List<Businesses>>> getBusinessByUserId(@PathVariable(name="id") String userId) {
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


}

