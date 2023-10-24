package com.onlineshop.ProductService.service;

import com.onlineshop.ProductService.entity.ProductEntity;
import com.onlineshop.ProductService.exception.ProductServiceCustomException;
import com.onlineshop.ProductService.model.ProductRequest;
import com.onlineshop.ProductService.model.ProductResponse;
import com.onlineshop.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Override
    public String save(ProductRequest productRequest) {
        log.info("Adding Product: " + productRequest);
        ProductEntity productEntity =
                ProductEntity.builder()
                        .productName(productRequest.getProductName())
                        .quantity(productRequest.getQuantity())
                        .price(productRequest.getPrice())
                        .build();

        productEntity.setId(UUID.randomUUID().toString());
        productRepository.save(productEntity);

        log.info("Product with Id: " +productEntity.getId()+ ", has been created");
        return productEntity.getId();
    }

    @Override
    public ProductResponse getProductByProductId(String productId) {
        log.info("Get the product for productId: {}", productId);

        String errorMsg = "Product with Id: " + productId + ", not found.";
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new ProductServiceCustomException(errorMsg, "PRODUCT_NOT_FOUND."));

        /* ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product, productResponse); */
        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();

        return productResponse;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<ProductEntity> productEntity = productRepository.findAll();
        List<ProductResponse> products = productEntity.stream().map(product -> {
                ProductResponse productResponse = new ProductResponse();
                BeanUtils.copyProperties(product, productResponse);
                return productResponse;
        }).collect(Collectors.toList());
        return products;
    }
}

