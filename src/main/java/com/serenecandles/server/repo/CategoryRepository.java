package com.serenecandles.server.repo;

import com.serenecandles.server.model.Category;
import com.serenecandles.server.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Category findByCategoryname(String categoryname);
//    @Query(nativeQuery = true, value = "select * from product where category_category_id= ?")
//    List<Product> getProductsWithCategoryId();
}
