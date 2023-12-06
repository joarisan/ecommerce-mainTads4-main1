package com.tads4.tads4.controllers;


import com.tads4.tads4.dto.ProductDTO;
import com.tads4.tads4.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);

    }

   /* @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            ProductDTO dto = service.findById(id);
            return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException e) {
            CustomError err = new CustomError(Instant.now(), 404, e.getMessage(), "caminho");
            return ResponseEntity.status(404).body(err);
        }
    }*/

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(@PathVariable Pageable pageable) {
        Page<ProductDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);

    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
        /*dto = service.insert(dto)
        return  dto;*/
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> updated(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}