package com.tads4.tads4.repositories;

import com.tads4.tads4.entities.Category;
import com.tads4.tads4.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category, Long> {
}
