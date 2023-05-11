package com.serenecandles.server.repo;

import com.serenecandles.server.model.Category;
import com.serenecandles.server.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public Product findByProductname(String productName);
    public Product findByCategory(Category category);
}
