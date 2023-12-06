package com.tads4.tads4.service;

import com.tads4.tads4.dto.PaymentDTO;
import com.tads4.tads4.entities.Payment;
import com.tads4.tads4.repositories.PaymentRepository;
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
public class PaymentService {
    @Autowired
    private PaymentRepository repository;

    @Transactional(readOnly = true)
    public PaymentDTO findById(Long id) {
        /*Optional<Product> result = repository.findById(id);
        Product product = result.get();
        ProductDTO dto = new ProductDTO(product);
        return dto;*/
        Payment payment = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new PaymentDTO(new Payment());
    }

    @Transactional(readOnly = true)
    public Page<PaymentDTO> findAll(Pageable pageable) {
        /*Optional<Product> result = repository.findById(id);
        Product product = result.get();
        ProductDTO dto = new ProductDTO(product);
        return dto;*/
        Page<Payment> result = repository.findAll(pageable);
        return result.map(x -> new PaymentDTO(x));
    }

    @Transactional
    public PaymentDTO insert(PaymentDTO dto) {
        Payment entity = new Payment();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new PaymentDTO(entity);
    }



    /*@Transactional
    public ProductDTO update (Long id, ProductDTO dto) {
        Product entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        return new ProductDTO(entity);
    }*/
    @Transactional
    public PaymentDTO update(Long id, PaymentDTO dto) {

        try{
            Payment entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            return new PaymentDTO(entity);
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

    private void copyDtoToEntity(PaymentDTO dto, Payment entity) {
        entity.setId(dto.getId());
        entity.setOrder(dto.getOrder());
        entity.setMoment(dto.getMoment());


    }
}
