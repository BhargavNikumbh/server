package com.serenecandles.server.service;

import com.serenecandles.server.model.Category;
import com.serenecandles.server.model.Product;

import java.util.List;

public interface CategoryService {
    //public Category createCategory(Category category, Set<CategoryImage> categoryImages) throws Exception;
    public Category createCategory(Category category) throws Exception;
    public List<Category> getAllCategories(int pageNumber, String searchKey);
    public boolean deleteCategoryDetails(Long categoryId);
    public Category getCategoryByCategoryId(Long categoryId);
}
