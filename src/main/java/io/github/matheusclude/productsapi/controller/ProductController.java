package io.github.matheusclude.productsapi.controller;

import io.github.matheusclude.productsapi.model.Product;
import io.github.matheusclude.productsapi.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public Product save(@RequestBody Product product){
        System.out.println("Product: "+ product);
        var id = UUID.randomUUID().toString();
        product.setId(id);
        productRepository.save(product);
        return product;
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable("id")  String id){
        return productRepository.findById(id).orElse(null);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") String id){
        productRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public void updateById(@PathVariable("id") String id, @RequestBody Product product){
        product.setId(id);
        productRepository.save(product);

    }

    @GetMapping
    public List<Product>  find(@RequestParam("name") String name){
        return productRepository.findByName(name);
    }
}
