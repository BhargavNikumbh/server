package com.serenecandles.server.service.impl;

import com.serenecandles.server.config.JwtAuthenticationFilter;
import com.serenecandles.server.model.Cart;
import com.serenecandles.server.model.Product;
import com.serenecandles.server.model.User;
import com.serenecandles.server.repo.*;
import com.serenecandles.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;

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
    public List<Product> getProducts(int pageNumber, String searchKey) {
        Pageable pagable = PageRequest.of(pageNumber,10);
        if(searchKey.equals("")){
            List<Product> products = new ArrayList<>();
            productRepository.findAll(pagable).forEach(products::add);
            return products;
        }else{
            return (List<Product>)productRepository.findByProductnameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchKey, searchKey, pagable);
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        this.productRepository.deleteById(productId);
    }

    @Override
    public Product getProductDetailsById(Long productId) {
        return this.productRepository.findById(productId).get();
    }

    @Override
    public List<Product> getProductDetails(boolean isSingleProductCheckout, Long productId) {
        if(isSingleProductCheckout && productId!=0){
            //we are going to buy single product
            List<Product> list = new ArrayList<>();
            Product product = productRepository.findById(productId).get();
            list.add(product);
            return list;
        }else{
            //we are going through cart
            String username = JwtAuthenticationFilter.CURRENT_USER;
            User user = userRepository.findByUsername(username);
            List<Cart> carts = cartRepository.findByUser(user);
            return carts.stream().map(x-> x.getProduct()).collect(Collectors.toList());
        }
    }

    @Override
    public List<Product> getProductByCategoryId(Long categoryId) {
            return (List<Product>) productRepository.findByCategory(categoryRepository.findById(categoryId));
    }
}
