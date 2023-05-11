package com.serenecandles.server.service.impl;

import com.serenecandles.server.model.Product;
import com.serenecandles.server.repo.ProductImageRepository;
import com.serenecandles.server.repo.ProductRepository;
import com.serenecandles.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product createProduct(Product product) throws Exception {
        Product local = this.productRepository.findByProductname(product.getProductname());
        if(local!=null){
            throw new Exception("Product already present");
        }else{
            this.productRepository.save(product);
        }
        return product;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(product->products.add(product));
        return products;
    }

    @Override
    public void deleteProduct(Long productId) {
        this.productRepository.deleteById(productId);
    }

    @Override
    public Product getProductDetailsById(Long productId) {
        return this.productRepository.findById(productId).get();
    }
}
