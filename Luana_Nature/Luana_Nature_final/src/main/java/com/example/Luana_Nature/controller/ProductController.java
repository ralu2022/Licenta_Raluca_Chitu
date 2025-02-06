/*
 * Controller used to handle CRUD (create, read, update, delete) operations upon Product entity.
 *
 *
 * */



package com.example.Luana_Nature.controller;

import com.example.Luana_Nature.model.Product;
import com.example.Luana_Nature.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor


public class ProductController {

    private final ProductService productService;

    @GetMapping("/storeproducts")
    public String AllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "storeproducts";
    }

    @GetMapping("/store")
    public String listAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "store";
    }

    @GetMapping("/storeadmin")
    public String listAllProductsAdmin(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "storeadmin";
    }



    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String name,
                             @RequestParam int price,
                             @RequestParam int stock,
                             @RequestParam String description,
                             @RequestParam String category,
                             @RequestParam String imageUrl) {
        productService.addProduct(name, price, stock, description, category, imageUrl);
        return "redirect:/products/products";
    }

}
