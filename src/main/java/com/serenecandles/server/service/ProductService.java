package com.serenecandles.server.service;

import com.serenecandles.server.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Product createProduct(Product product) throws Exception;
    public List<Product> getProducts(int pageNumber, String serachKey);
    public void deleteProduct(Long productId);
    public Product getProductDetailsById(Long productId);
    public List<Product> getProductDetails(boolean isSingleProductCheckout, Long productId);

    public List<Product> getProductByCategoryId(Long categoryId);

}
