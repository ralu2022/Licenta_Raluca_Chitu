/**
 * Service class for managing products on the Produse page. This class interacts with the ProductRepository
 * to perform CRUD operations of create, read, update and delete.
 * */



package com.example.Luana_Nature.service;


import com.example.Luana_Nature.model.Product;
import com.example.Luana_Nature.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public void addProduct(String name, int price, int stock, String description, String category, String imageUrl){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        product.setDescription(description);
        product.setCategory(category);
        product.setImageUrl(imageUrl);
        productRepository.save(product);
    }

}
