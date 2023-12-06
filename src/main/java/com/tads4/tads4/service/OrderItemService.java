package com.tads4.tads4.service;

import com.tads4.tads4.dto.OrderItemDTO;
import com.tads4.tads4.entities.OrderItem;
import com.tads4.tads4.repositories.OrderItemRepository;
import com.tads4.tads4.service.exceptions.DatabaseException;
import com.tads4.tads4.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository repository;


    public OrderItemDTO findById(Long id) {
        /*Optional<Product> result = repository.findById(id);
        Product product = result.get();
        ProductDTO dto = new ProductDTO(product);
        return dto;*/
        OrderItem orderItem = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new OrderItemDTO(new OrderItem());
    }

    @Transactional(readOnly = true)
    public Page<OrderItemDTO> findAll(Pageable pageable) {
        /*Optional<Product> result = repository.findById(id);
        Product product = result.get();
        ProductDTO dto = new ProductDTO(product);
        return dto;*/
        Page<OrderItem> result = repository.findAll(pageable);
        return result.map(x -> new OrderItemDTO(x));
    }

    @Transactional
    public OrderItemDTO insert(OrderItemDTO dto) {
        OrderItem entity = new OrderItem();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new OrderItemDTO(entity);
    }



    /*@Transactional
    public ProductDTO update (Long id, ProductDTO dto) {
        Product entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        return new ProductDTO(entity);
    }*/
    @Transactional
    public OrderItemDTO update(Long id, OrderItemDTO dto) {

        try{
            OrderItem entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            return new OrderItemDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }

    }

    /*@Transactional
    public void delete(Long id) {
         repository.deleteById(id);
      }*/

    @Transactional (propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        try{
            repository.deleteById(id);
        } catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        } catch(DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integrigadade referencial");
        }

    }

    private void copyDtoToEntity(OrderItemDTO dto, OrderItem entity) {
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());


    }

}
