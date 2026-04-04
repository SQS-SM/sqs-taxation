package com.ds.sqs.taxation.api.controller;

import com.ds.sqs.taxation.api.service.ProvinceService;
import com.ds.sqs.taxation.common.dto.ProvinceDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/provinces")
@Tag(name = "Province Management", description = "APIs for retrieving and managing provinces")
public class ProvinceController {

    private final ProvinceService provinceService;


    @Operation(
            summary = "Retrieve all provinces",
            description = "Returns a list of all provinces available in the system"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Provinces retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<ProvinceDto>> getAll() {

        return ResponseEntity.ok(provinceService.findAll());
    }


    @Operation(
            summary = "Get province by ID",
            description = "Retrieve a province using its unique identifier"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Province retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Province not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProvinceDto> getById(

            @Parameter(description = "Unique province identifier", example = "1")
            @PathVariable("id") Long id
    ) {

        return provinceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Retrieve provinces by country",
            description = "Returns all provinces belonging to a specific country"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Provinces retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Country not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<ProvinceDto>> findByCountry(

            @Parameter(description = "Country identifier used to filter provinces", example = "27")
            @PathVariable("countryId") Long countryId
    ) {

        return ResponseEntity.ok(provinceService.findByCountry(countryId));
    }


    @Operation(
            summary = "Delete province",
            description = "Deletes a province using the province identifier"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Province deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Province not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(

            @Parameter(description = "Unique province identifier", example = "1")
            @PathVariable("id") Long id
    ) {

        provinceService.delete(id);

        return ResponseEntity.noContent().build();
    }
}