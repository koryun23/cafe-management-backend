package com.cafe.service.impl.product;

import com.cafe.entity.product.Product;
import com.cafe.repository.ProductRepository;
import com.cafe.service.core.product.ProductCreationParams;
import com.cafe.service.core.product.ProductService;
import com.cafe.service.core.product.ProductUpdateParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public Product create(ProductCreationParams params) {
        Assert.notNull(params, "Product Creation Params should not be null");
        LOGGER.info("Creating a new product according to the product creation params - {}", params);
        Product product = productRepository.save(new Product(
                params.getProductName(), params.getPrice(), params.getAmount())
        );
        LOGGER.info("Successfully created a new product according to the product creation params - {}", params);
        return product;
    }

    @Transactional(readOnly = true)
    @Override
    public Product getById(Long id) {
        Assert.notNull(id, "Product id should not be null");
        LOGGER.info("Retrieving a product having an id of {}", id);
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        LOGGER.info("Successfully retrieved a product having an id of {}, result - {}", id, product);
        return product;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        Assert.notNull(id, "Product id should not be null");
        LOGGER.info("Retrieving an optional of a product having an id of {}", id);
        Optional<Product> byId = productRepository.findById(id);
        LOGGER.info("Successfully retrieved an optional of a product having an id of {}, result - {}", id, byId);
        return byId;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findByName(String name) {
        Assert.notNull(name, "Product name should not be null");
        LOGGER.info("Retrieving an optional of a product named as '{}'", name);
        Optional<Product> productOptional = productRepository.findByProductName(name);
        LOGGER.info("Successfully retrieved an optional of a product named as '{}', result - {}", name, productOptional);
        return productOptional;
    }

    @Transactional(readOnly = true)
    @Override
    public Integer getAmountByProductName(String name) {
        Assert.notNull(name, "Product name should not be null");
        Assert.notNull(name, "Product name should not be empty");
        LOGGER.info("Retrieving amount of the product named as '{}'", name);
        Product product = productRepository.findByProductName(name).orElseThrow(() -> new ProductNotFoundException(name));
        Integer amount = product.getAmount();
        LOGGER.info("Successfully retrieved the amount of a product named as '{}', result - {}", name, amount);
        return amount;
    }

    @Transactional(readOnly = true)
    @Override
    public Integer getAmountByProductId(Long id) {
        Assert.notNull(id, "Product id should not be null");
        LOGGER.info("Retrieving amount of the product having an id of {}", id);
        Integer amount = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id)).getAmount();
        LOGGER.info("Successfully retrieved the amount of the product having an id of {}, result - {}", id, amount);
        return amount;
    }

    @Override
    public Product updateProduct(ProductUpdateParams params) {
        Assert.notNull(params, "Product update params should not be null");
        LOGGER.info("Updating a product according to the product update params - {}", params);
        Product product = new Product(
                params.getName(),
                params.getPrice(),
                params.getAmount()
        );
        product.setId(params.getId());

        Product savedProduct = productRepository.save(product);
        LOGGER.info("Successfully updated a product according to the product update params - {}, result - {}", params, savedProduct);
        return savedProduct;
    }
}
