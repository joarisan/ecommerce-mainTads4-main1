package com.tads4.tads4.dto;

import com.tads4.tads4.entities.Category;
import com.tads4.tads4.entities.Order;
import com.tads4.tads4.entities.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//@NotNull
//@NotEmpty

public class CategoryDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;



    public CategoryDTO() {

    }

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public CategoryDTO(Category entity) {
       id = id;
       name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
