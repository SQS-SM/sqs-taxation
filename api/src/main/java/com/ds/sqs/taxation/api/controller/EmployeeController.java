package com.ds.sqs.taxation.api.controller;

import com.ds.sqs.taxation.employee.dto.EmployeeDto;
import com.ds.sqs.taxation.employee.service.EmployeeCrudService;
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
@RequestMapping("/api/v1/employees")
@Tag(name = "Employee Management", description = "Operations for managing employee records")
public class EmployeeController {

    private final EmployeeCrudService service;

    @Operation(
            summary = "Retrieve employees",
            description = "Returns a list of employees optionally filtered by companyId and organizationId",
            operationId = "employeeGetAll"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees retrieved successfully"),
    })
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll(

            @Parameter(description = "Filter employees by company ID", example = "1001")
            @RequestParam(value = "companyId", required = false) Long companyId,

            @Parameter(description = "Filter employees by organization ID", example = "200")
            @RequestParam(value = "organizationId", required = false) Long organizationId
    ) {
        return ResponseEntity.ok(service.findByFilters(companyId, organizationId));
    }


    @Operation(
            summary = "Get employee by ID",
            description = "Retrieve a specific employee using the employee ID",
            operationId = "employeeGetById"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(

            @Parameter(description = "Unique employee identifier", example = "1")
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.getById(id));
    }


    @Operation(
            summary = "Create employee",
            description = "Creates a new employee record",
            operationId = "employeeCreate"
)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid employee data provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<EmployeeDto> create(

            @RequestBody(description = "Employee object to be created", required = true)
            @org.springframework.web.bind.annotation.RequestBody EmployeeDto dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }


    @Operation(
            summary = "Update employee",
            description = "Updates an existing employee record using employee ID",
            operationId = "employeeUpdate"
)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "400", description = "Invalid employee data supplied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(

            @Parameter(description = "Unique employee identifier", example = "1")
            @PathVariable("id") Long id,

            @RequestBody(description = "Updated employee object", required = true)
            @org.springframework.web.bind.annotation.RequestBody EmployeeDto dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }


    @Operation(
            summary = "Delete employee",
            description = "Deletes an employee record using employee ID",
            operationId = "employeeDelete"
)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(

            @Parameter(description = "Unique employee identifier", example = "1")
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
