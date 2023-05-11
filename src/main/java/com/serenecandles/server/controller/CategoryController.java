package com.serenecandles.server.controller;

import com.serenecandles.server.model.Category;
import com.serenecandles.server.repo.ImageRepository;
import com.serenecandles.server.service.impl.CategoryServiceImpl;
import com.serenecandles.server.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.serenecandles.server.model.Image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin-user")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    public CategoryServiceImpl categoryServiceImpl;
    @Autowired
    private ImageRepository imageRepository;

    @PostMapping(value = {"/category"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addCategory(@RequestPart("category") Category category,
                                         @RequestPart("imageFile") ArrayList<MultipartFile> file) throws Exception {
        try{

            Set<Image> images = uploadImage(file);
            category.setCategoryImages(images);
            //imageRepository.deleteNullImages();
            Category local = this.categoryServiceImpl.createCategory(category);
            return ResponseEntity.status(HttpStatus.OK).body(local);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

    public Set<Image> uploadImage(ArrayList<MultipartFile> multipartfiles) throws IOException {
        Set<Image> images = new HashSet<>();
        for(MultipartFile file : multipartfiles){
            Image image = new Image(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            images.add(image);
        }
        return images;
    }

    @GetMapping("/category")
    public List<Category> getAllCategories() {
        return categoryServiceImpl.getAllCategories();
    }

    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<?> deleteCategoryDetails(@PathVariable("categoryId") Long categoryId){
        boolean isDeleted = this.categoryServiceImpl.deleteCategoryDetails(categoryId);
        if(isDeleted)
            return ResponseEntity.status(HttpStatus.OK).body("Category Deleted Successfully");
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Please Delete associated products with Category Id="+categoryId+" first");
    }

    @GetMapping("/category/{categoryId}")
    public Category getCategoryById(@PathVariable("categoryId") Long categoryId){
        return this.categoryServiceImpl.getCategoryByCategoryId(categoryId);
    }

}
