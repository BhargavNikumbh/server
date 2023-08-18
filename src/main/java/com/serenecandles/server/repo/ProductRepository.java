package com.serenecandles.server.repo;

import com.serenecandles.server.model.Category;
import com.serenecandles.server.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public Product findByProductname(String productName);
    public List<Product> findByCategory(Optional<Category> category);
    public List<Product> findByProductnameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
            String key1, String key2, Pageable pageable
    );
}
