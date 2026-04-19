package com.ds.sqs.taxation.api.controller;

import com.ds.sqs.taxation.api.service.ProvinceService;
import com.ds.sqs.taxation.common.dto.ProvinceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/provinces")
public class ProvinceController {

    private final ProvinceService provinceService;

    @GetMapping()
    public ResponseEntity<List<ProvinceDto>> getAll() {
        return ResponseEntity.ok(provinceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvinceDto> getById(@PathVariable("id") Long id) {
        return provinceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<ProvinceDto>> findByCountry(
            @PathVariable("countryId") Long countryId
    ) {
        return ResponseEntity.ok(provinceService.findByCountry(countryId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        provinceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
