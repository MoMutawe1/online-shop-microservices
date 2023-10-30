package com.onlineshop.ProductService.service;

import com.onlineshop.ProductService.model.ProductRequest;
import com.onlineshop.ProductService.model.ProductResponse;

import java.util.List;

public interface ProductService {

    String save(ProductRequest productRequest);
    ProductResponse getProductByProductId(String productId);
    List<ProductResponse> getAllProducts();
    void reduceQuantity(String productId, long quantity);
}
