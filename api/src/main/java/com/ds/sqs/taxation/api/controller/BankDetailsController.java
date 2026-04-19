package com.ds.sqs.taxation.api.controller;

import com.ds.sqs.taxation.common.dto.BankDetailsDto;
import com.ds.sqs.taxation.common.service.BankDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bank-details")
@Tag(name = "Bank Details Management", description = "Operations for managing bank details records")
public class BankDetailsController {

    private final BankDetailsService service;

    @Operation(
            summary = "Retrieve bank details",
            description = "Returns a list of all bank details",
            operationId = "bankDetailsGetAll"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bank details retrieved successfully"),
    })
    @GetMapping
    public ResponseEntity<List<BankDetailsDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(
            summary = "Get bank details by ID",
            description = "Retrieve specific bank details using the ID",
            operationId = "bankDetailsGetById"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bank details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Bank details not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BankDetailsDto> getById(

            @Parameter(description = "Unique bank details identifier", example = "1")
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(
            summary = "Create bank details",
            description = "Creates a new bank details record",
            operationId = "bankDetailsCreate"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bank details created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid bank details data provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<BankDetailsDto> create(

            @RequestBody(description = "Bank details object to be created", required = true) BankDetailsDto dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(
            summary = "Update bank details",
            description = "Updates an existing bank details record using ID",
            operationId = "bankDetailsUpdate"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bank details updated successfully"),
            @ApiResponse(responseCode = "404", description = "Bank details not found"),
            @ApiResponse(responseCode = "400", description = "Invalid bank details data supplied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<BankDetailsDto> update(

            @Parameter(description = "Unique bank details identifier", example = "1")
            @PathVariable("id") Long id,

            @RequestBody(description = "Updated bank details object", required = true) BankDetailsDto dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(
            summary = "Delete bank details",
            description = "Deletes a bank details record using ID",
            operationId = "bankDetailsDelete"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Bank details deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Bank details not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(

            @Parameter(description = "Unique bank details identifier", example = "1")
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}