package com.tads4.tads4.service;

import com.tads4.tads4.dto.CategoryDTO;
import com.tads4.tads4.dto.ProductDTO;
import com.tads4.tads4.entities.Category;
import com.tads4.tads4.entities.Product;
import com.tads4.tads4.repositories.CategoryRepository;
import com.tads4.tads4.repositories.ProductRepository;
import com.tads4.tads4.service.exceptions.DatabaseException;
import com.tads4.tads4.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public CategoryDTO findById(Long id) {
        /*Optional<Product> result = repository.findById(id);
        Category category = result.get();
        CategoryDTO dto = new CategoryDTO(category);
        return dto;*/
        Category category = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Recusro não encontrado"));
        return new CategoryDTO(category);
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAll(Pageable pageable) {
        /*Optional<Category> result = repository.findById(id);
        Category category = result.get();
        CategoryDTO dto = new CategoryDTO(category);
        return dto;*/
        Page<Category> result = repository.findAll(pageable);
        return result.map(x-> new CategoryDTO(x));
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        copyDtoToEntity(dto, entity);
        entity =repository.save(entity);
        return new CategoryDTO(entity);
    }

    /*@Transactional
    public ProductDTO update (Long id, ProductDTO dto) {
        Product entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        return new ProductDTO(entity);
    }*/
    @Transactional
    public CategoryDTO update (Long id, CategoryDTO dto) {

        try {
            Category entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            return new CategoryDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

        @Transactional (propagation = Propagation.SUPPORTS)
        @Modifying
        public void delete(Long id) {
            try{
                repository.deleteById(id);
            } catch(EmptyResultDataAccessException e){
                throw new ResourceNotFoundException("Recurso não encontrado");
            } catch(DataIntegrityViolationException e){
                throw new DatabaseException("Falha de integrigadade referencial");
            }

        }

        private void copyDtoToEntity(CategoryDTO dto, Category entity) {
            entity.setId(dto.getId());
            entity.setName(dto.getName());


        }

}
