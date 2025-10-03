package com.example.demo.ServiceInf;

import java.util.List;

import com.example.demo.Payload.ProductDTO;

public interface ProductService {
	ProductDTO createProduct(ProductDTO dto);
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProducts();
    ProductDTO updateProduct(Long id, ProductDTO dto);
    void deleteProduct(Long id);
}
