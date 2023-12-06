package com.tads4.tads4.controllers;

import com.tads4.tads4.dto.CategoryDTO;
import com.tads4.tads4.dto.OrderDTO;
import com.tads4.tads4.service.CategoryService;
import com.tads4.tads4.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
@RestController
@RequestMapping(value = "/Order")

public class OrderController {


        @Autowired
        private OrderService service;

        @GetMapping(value = "/{id}")
        public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
            OrderDTO dto = service.findById(id);
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
        public ResponseEntity<Page<OrderDTO>> findAll(@PathVariable Pageable pageable) {
            Page<OrderDTO> dto = service.findAll(pageable);
            return ResponseEntity.ok(dto);

        }

        @PostMapping
        public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderDTO dto) {
        /*dto = service.insert(dto)
        return  dto;*/
            dto = service.insert(dto);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
            return ResponseEntity.created(uri).body(dto);
        }

        @PutMapping(value = "/{id}")
        public ResponseEntity<OrderDTO> updated(@PathVariable Long id, @Valid @RequestBody OrderDTO dto) {
            dto = service.update(id, dto);
            return ResponseEntity.ok(dto);

        }

        @DeleteMapping(value = "/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }

    }


