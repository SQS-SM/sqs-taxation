package com.ds.sqs.taxation.api.controller;

import com.ds.sqs.taxation.security.dto.CompanyDto;
import com.ds.sqs.taxation.security.service.CompanyService;

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
@RequestMapping("/api/v1/companies")
@Tag(name = "Company Management", description = "APIs for managing companies")
public class CompanyController {

    private final CompanyService service;


    @Operation(
            summary = "Retrieve companies",
            description = "Returns all companies or filters companies by organizationId",
            operationId = "companyGetAll"
)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Companies retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid filter parameter"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAll(

            @Parameter(description = "Filter companies by organization ID", example = "100")
            @RequestParam(value = "organizationId", required = false) Long organizationId
    ) {

        if (organizationId != null) {
            return ResponseEntity.ok(service.findByOrganisationId(organizationId));
        }

        return ResponseEntity.ok(service.findAll());
    }


    @Operation(
            summary = "Get company by ID",
            description = "Retrieve company details using the company identifier",
            operationId = "companyGetById"
)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Company retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Company not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getById(

            @Parameter(description = "Unique company identifier", example = "1")
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.getById(id));
    }


    @Operation(
            summary = "Create company",
            description = "Creates a new company record",
            operationId = "companyCreate"
)
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Company created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid company data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<CompanyDto> create(@RequestBody CompanyDto dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(dto));
    }


    @Operation(
            summary = "Update company",
            description = "Updates an existing company using the company ID",
            operationId = "companyUpdate"
)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Company updated successfully"),
            @ApiResponse(responseCode = "404", description = "Company not found"),
            @ApiResponse(responseCode = "400", description = "Invalid company data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> update(

            @Parameter(description = "Unique company identifier", example = "1")
            @PathVariable("id") Long id,

            @RequestBody CompanyDto dto
    ) {

        return ResponseEntity.ok(service.update(id, dto));
    }


    @Operation(
            summary = "Delete company",
            description = "Deletes a company using the company ID",
            operationId = "companyDelete"
)
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Company deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Company not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(

            @Parameter(description = "Unique company identifier", example = "1")
            @PathVariable("id") Long id
    ) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}