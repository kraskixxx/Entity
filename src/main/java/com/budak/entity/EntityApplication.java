package com.budak.entity;

import com.budak.entity.model.Product;
import com.budak.entity.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EntityApplication implements CommandLineRunner {

    private ProductRepository productRepository;

    private Logger LOG = LoggerFactory.getLogger(EntityApplication.class);

    // Setter Injection ile kullanımı daha güvenli ve Pivotal tarafından da tavsiye ediliyor.
    @Autowired
    public void productRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(EntityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Product productOne = new Product();
        productOne.setName("Tester Product");
        productOne.setDescription("This is a test product");
        productOne.setCategory("TEST");
        productOne.setType("GENERAL");
        productOne.setPrice(0.0);

        productRepository.save(productOne);
        
        Product productTwo = new Product();
        productTwo.setName("Another Tester Product");
        productTwo.setDescription("This is a tester product");
        productTwo.setCategory("TEST");
        productTwo.setType("CUSTOM");
        productTwo.setPrice(15.0);

        productRepository.save(productTwo);


        Product productThree = new Product();
        productThree.setName("Tester Product");
        productThree.setDescription("description");
        productThree.setCategory("TEST");
        productThree.setType("SPECIFIC");
        productThree.setPrice(19.0);

        productRepository.save(productThree);

//        List<Product> productList = productRepository.findAll();
//        for (Product product : productList) {
//            LOG.info("Products found: " + product.toString());
//        }

//        Product resultProduct = productRepository.findByType("CUSTOM");
//
//        LOG.info("GENERAL type of product found" + resultProduct.toString());

        List<Product> productList = productRepository.findByDescriptionAndCategory("This is a tester product", "TEST");

        for (Product product : productList) {
            LOG.info("Matching results are: " + product.toString());
        }

        List<String> nameList = new ArrayList<>();
        nameList.add("Tester Product");
        nameList.add("Another Tester Product");
        List<Product> resultProducts = productRepository.findByCategoryAndNameIn("TEST", nameList);

        for (Product resultProduct : resultProducts) {
            LOG.info("Products By Name : " + resultProduct.toString());
        }


    }
}
