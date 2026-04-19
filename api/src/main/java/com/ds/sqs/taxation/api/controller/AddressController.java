package com.ds.sqs.taxation.api.controller;

import com.ds.sqs.taxation.common.dto.AddressDto;
import com.ds.sqs.taxation.common.service.AddressService;
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
@RequestMapping("/api/v1/addresses")
@Tag(name = "Address Management", description = "Operations for managing address records")
public class AddressController {

    private final AddressService service;

    @Operation(
            summary = "Retrieve addresses",
            description = "Returns a list of all addresses",
            operationId = "addressGetAll"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Addresses retrieved successfully"),
    })
    @GetMapping
    public ResponseEntity<List<AddressDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(
            summary = "Get address by ID",
            description = "Retrieve a specific address using the ID",
            operationId = "addressGetById"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getById(

            @Parameter(description = "Unique address identifier", example = "1")
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(
            summary = "Create address",
            description = "Creates a new address record",
            operationId = "addressCreate"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Address created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid address data provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<AddressDto> create(
            @RequestBody(description = "Address object to be created", required = true) AddressDto dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(
            summary = "Update address",
            description = "Updates an existing address record using ID",
            operationId = "addressUpdate"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found"),
            @ApiResponse(responseCode = "400", description = "Invalid address data supplied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> update(
            @Parameter(description = "Unique address identifier", example = "1")
            @PathVariable("id") Long id,
            @RequestBody(description = "Updated address object", required = true) AddressDto dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(
            summary = "Delete address",
            description = "Deletes an address record using ID",
            operationId = "addressDelete"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Address deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(

            @Parameter(description = "Unique address identifier", example = "1")
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}