package com.johnsoncodelove.ecomapp.services.product;

import com.johnsoncodelove.ecomapp.dto.ProductRequest;
import com.johnsoncodelove.ecomapp.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    public ProductResponse createProduct(ProductRequest productRequest);

    public ProductResponse updateProduct(Long id, ProductRequest request);

    public List<ProductResponse> getAllProducts();
    public ProductResponse getProduct(Long id);

    void deleteProduct(Long id);

    public List<ProductResponse> searchProducts(String keyword);

}
