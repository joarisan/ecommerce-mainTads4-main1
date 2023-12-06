package com.tads4.tads4.service;

import com.tads4.tads4.dto.OrderDTO;
import com.tads4.tads4.dto.ProductDTO;
import com.tads4.tads4.entities.Order;
import com.tads4.tads4.entities.Product;
import com.tads4.tads4.repositories.OrderRepository;
import com.tads4.tads4.repositories.ProductRepository;
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
public class OrderService {
    @Autowired
    private OrderRepository repository;


    public OrderDTO findById(Long id) {
        /*Optional<Product> result = repository.findById(id);
        Product product = result.get();
        ProductDTO dto = new ProductDTO(product);
        return dto;*/
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new OrderDTO(order);
    }

    @Transactional(readOnly = true)
    public Page<OrderDTO> findAll(Pageable pageable) {
        /*Optional<Product> result = repository.findById(id);
        Product product = result.get();
        ProductDTO dto = new ProductDTO(product);
        return dto;*/
        Page<Order> result = repository.findAll(pageable);
        return result.map(x -> new OrderDTO(x));
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order entity = new Order();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new OrderDTO(entity);
    }



    /*@Transactional
    public ProductDTO update (Long id, ProductDTO dto) {
        Product entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        return new ProductDTO(entity);
    }*/
    @Transactional
    public OrderDTO update(Long id, OrderDTO dto) {

        try{
            Order entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            return new OrderDTO(entity);
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

    private void copyDtoToEntity(OrderDTO dto, Order entity) {
        entity.setId(dto.getId());
        entity.setMoment(dto.getMoment());
        entity.setClient(dto.getClient());
        entity.setPayment(dto.getPayment());
        entity.setSatus(dto.getStatus());

    }

}
