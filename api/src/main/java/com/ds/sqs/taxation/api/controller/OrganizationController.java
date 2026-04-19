package com.ds.sqs.taxation.api.controller;

import com.ds.sqs.taxation.security.dto.OrganisationDto;
import com.ds.sqs.taxation.security.service.OrganisationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/organizations")
@Tag(name = "Organization Management", description = "APIs for managing organizations")
public class OrganizationController {

    private final OrganisationService service;


    @Operation(
            summary = "Retrieve all organizations",
            description = "Returns a list of all organizations",
            operationId = "organizationGetAll"
)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Organizations retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<OrganisationDto>> getAll() {

        return ResponseEntity.ok(service.findAll());
    }


    @Operation(
            summary = "Get organization by ID",
            description = "Retrieve organization details using the organization identifier",
            operationId = "organizationGetById"
)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Organization retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Organization not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrganisationDto> getById(

            @Parameter(description = "Unique organization identifier", example = "1")
            @PathVariable("id") Long id
    ) {

        return ResponseEntity.ok(service.getById(id));
    }


    @Operation(
            summary = "Create organization",
            description = "Creates a new organization",
            operationId = "organizationCreate"
)
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Organization created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid organization data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<OrganisationDto> create(
            @RequestBody OrganisationDto dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(dto));
    }


    @Operation(
            summary = "Update organization",
            description = "Updates an existing organization using the organization ID",
            operationId = "organizationUpdate"
)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Organization updated successfully"),
            @ApiResponse(responseCode = "404", description = "Organization not found"),
            @ApiResponse(responseCode = "400", description = "Invalid organization data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<OrganisationDto> update(

            @Parameter(description = "Unique organization identifier", example = "1")
            @PathVariable("id") Long id,

            @RequestBody OrganisationDto dto
    ) {

        return ResponseEntity.ok(service.update(id, dto));
    }


    @Operation(
            summary = "Delete organization",
            description = "Deletes an organization using the organization ID",
            operationId = "organizationDelete"
)
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Organization deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Organization not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(

            @Parameter(description = "Unique organization identifier", example = "1")
            @PathVariable("id") Long id
    ) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}