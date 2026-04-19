package com.ds.sqs.taxation.api.controller;

import com.ds.sqs.taxation.security.adapter.RolePersistencePort;
import com.ds.sqs.taxation.security.dto.RoleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
@Tag(name = "Roles", description = "Role management and lookup")
public class RoleController {

    private final RolePersistencePort rolePort;

    @Operation(summary = "List roles", operationId = "roleGetAll")
    @GetMapping
    public ResponseEntity<List<RoleDto>> getAll() {
        return ResponseEntity.ok(rolePort.findAll());
    }

    @Operation(summary = "Get role by id", operationId = "roleGetById")
    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getById(@PathVariable("id") Long id) {
        return rolePort.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}