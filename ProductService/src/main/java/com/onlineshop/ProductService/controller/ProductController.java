package com.onlineshop.ProductService.controller;

import com.onlineshop.ProductService.model.ProductRequest;
import com.onlineshop.ProductService.model.ProductResponse;
import com.onlineshop.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductByProductId(@PathVariable String productId){
        ProductResponse productResponse = productService.getProductByProductId(productId);
        return new ResponseEntity(productResponse ,HttpStatus.OK);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> productResponse = productService.getAllProducts();
        return new ResponseEntity(productResponse ,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductRequest productRequest){
        String ProductId = productService.save(productRequest);
        String message = "Product with Id: " + ProductId + ", has been added.";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
