package com.budak.entity.repository;

import com.budak.entity.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Samet BUDAK
 * @since
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Product findByType(String type);

    List<Product> findByDescriptionAndCategory(String description, String category);

    List<Product> findByCategoryAndNameIn(String category, List<String> name);


}
