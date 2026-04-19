package com.ds.sqs.taxation.api.controller;

import com.ds.sqs.taxation.api.service.CountryService;
import com.ds.sqs.taxation.common.dto.CountryDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/countries")
@Tag(name = "Country Management", description = "APIs for retrieving country information")
@Log
public class CountryController {

    private final CountryService service;


    @Operation(
            summary = "Retrieve all countries",
            description = "Returns a list of all countries available in the system",
            operationId = "countryGetAll"
)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Countries retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<CountryDto>> getAll() {

        return ResponseEntity.ok(service.findAll());
    }


    @Operation(
            summary = "Get country by ID",
            description = "Retrieve a specific country using its unique identifier",
            operationId = "countryGetById"
)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Country retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Country not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getById(

            @Parameter(description = "Unique country identifier", example = "1")
            @PathVariable("id") Long id
    ) {

        log.info("Country lookup requested for id=" + id);

        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}