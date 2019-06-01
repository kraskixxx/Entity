package com.budak.entity.controller;

import com.budak.entity.model.Product;
import com.budak.entity.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Samet BUDAK
 * @since
 */
@RestController
@RequestMapping(path = "/api/products/")
public class ProductRestController {

    private ProductRepository productRepository;

    @Autowired
    public void productRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Optional<Product> getProduct(@PathVariable(name = "id") String id){
        return productRepository.findById(id);
    }
}
