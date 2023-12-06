package com.tads4.tads4.controllers;

import com.tads4.tads4.dto.OrderDTO;
import com.tads4.tads4.dto.OrderItemDTO;
import com.tads4.tads4.entities.OrderItem;
import com.tads4.tads4.service.OrderItemService;
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
@RequestMapping(value = "/OrderItem")

public class OrderItemController {

    @Autowired
    private OrderItemService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderItemDTO> findById(@PathVariable Long id) {
        OrderItemDTO dto = service.findById(id);
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
    public ResponseEntity<Page<OrderItemDTO>> findAll(@PathVariable Pageable pageable) {
        Page<OrderItemDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);

    }

    @PostMapping
    public ResponseEntity<OrderItemDTO> insert(@Valid @RequestBody OrderItemDTO dto) {
        /*dto = service.insert(dto)
        return  dto;*/
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OrderItemDTO> updated(@PathVariable Long id, @Valid @RequestBody OrderItemDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
