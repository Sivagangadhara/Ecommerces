package com.example.demo.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Entity.Product;
import com.example.demo.Payload.ProductDTO;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.ServiceInf.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	private final ProductRepository repo;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository repo, ModelMapper modelMapper) {
        this.repo = repo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        Product product = modelMapper.map(dto, Product.class);
        Product saved = repo.save(product);
        return modelMapper.map(saved, ProductDTO.class);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return repo.findAll()
                .stream()
                .map(p -> modelMapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product product = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        modelMapper.map(dto, product);
        Product updated = repo.save(product);
        return modelMapper.map(updated, ProductDTO.class);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        repo.deleteById(id);
    }
}
