package com.ds.sqs.taxation.api.controller;

import com.ds.sqs.taxation.employee.dto.EmployeeDto;
import com.ds.sqs.taxation.employee.service.EmployeeCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeCrudService service;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll(
            @RequestParam(value = "companyId", required = false) Long companyId,
            @RequestParam(value = "organizationId", required = false) Long organizationId
    ) {
        return ResponseEntity.ok(service.findByFilters(companyId, organizationId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(
            @PathVariable("id") Long id,
            @RequestBody EmployeeDto dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
