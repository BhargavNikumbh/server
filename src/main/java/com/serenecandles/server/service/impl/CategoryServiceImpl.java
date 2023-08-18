package com.serenecandles.server.service.impl;

import com.serenecandles.server.model.Category;
import com.serenecandles.server.model.Product;
import com.serenecandles.server.repo.CategoryRepository;
import com.serenecandles.server.repo.ProductRepository;
import com.serenecandles.server.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService  {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageServiceImpl imageService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) throws Exception {
        Category local = this.categoryRepository.findByCategoryname(category.getCategoryname());
        if(local != null){
            throw new Exception("Category Already Present");
        }else{
            local = this.categoryRepository.save(category);
        }
        return local;
    }

    @Override
    public List<Category> getAllCategories(int pageNumber, String searchKey) {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        if(searchKey.equals("")){
            List<Category> categories = new ArrayList<>();
            categoryRepository.findAll(pageable).forEach(category->categories.add(category));
            return categories;
        }else{
            return (List<Category>)categoryRepository.findByCategorynameContainingIgnoreCaseOrCategorydescriptionContainingIgnoreCase(searchKey, searchKey, pageable);
        }
    }

    @Override
    public boolean deleteCategoryDetails(Long categoryId) {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(product->products.add(product));
        for(Product product:products){
            if(product.getCategory().getCategoryId().equals(categoryId)){
                return false;
            }
        }
        this.categoryRepository.deleteById(categoryId);
        return true;
    }

    @Override
    public Category getCategoryByCategoryId( Long categoryId) {
        return this.categoryRepository.findById(categoryId).get();
    }

}
