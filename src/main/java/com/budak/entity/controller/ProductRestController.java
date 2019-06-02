package com.budak.entity.controller;

import com.budak.entity.model.Product;
import com.budak.entity.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Optional;

/**
 * @author Samet BUDAK
 * @since
 */
@RestController
@RequestMapping(path = "/api/products/")
public class ProductRestController {

    private ProductRepository productRepository;

    private Logger logger = LoggerFactory.getLogger(ProductRestController.class);

    @Autowired
    public void productRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Optional<Product> getProduct(@PathVariable(name = "id") String id){
        return productRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product saveProduct(@RequestBody Product productPosted){
        return productRepository.save(productPosted);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@RequestBody Product product, @PathVariable(name = "id") String id){
        Optional<Product> productById = productRepository.findById(id);
        if(productById != null){
            Product productToUpdate = productById.get();
            productToUpdate.setName(product.getName());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setType(product.getType());
            productToUpdate.setCategory(product.getCategory());
            productToUpdate.setDescription(product.getDescription());
            return productRepository.save(productToUpdate);
        } else {
            logger.info("aramış olduğunuz ürün bulunamadı");
            return product;
        }
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable("id") String id){
        Optional<Product> productToDelete = productRepository.findById(id);

        if(productToDelete != null){
            productRepository.delete(productToDelete.get());
        }
    }
}
