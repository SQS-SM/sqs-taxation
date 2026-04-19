package com.ds.sqs.taxation.api.controller;

import com.ds.sqs.taxation.api.service.CountryService;
import com.ds.sqs.taxation.common.dto.CountryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import net.logstash.logback.argument.StructuredArguments;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/countries")
@Log
public class CountryController {

    private final CountryService service;

    @GetMapping()
    public ResponseEntity<List<CountryDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getById(@PathVariable("id") Long id) {
        log.info("Employee payroll processed"
        );
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
