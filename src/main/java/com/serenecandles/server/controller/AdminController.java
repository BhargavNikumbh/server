package com.serenecandles.server.controller;

import com.serenecandles.server.model.*;
import com.serenecandles.server.repo.CategoryRepository;
import com.serenecandles.server.repo.ImageRepository;
import com.serenecandles.server.service.UserService;
import com.serenecandles.server.service.impl.CategoryServiceImpl;
import com.serenecandles.server.service.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/admin-user")
@CrossOrigin("*")
public class AdminController {

    @Autowired UserService userservice;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    @Autowired
    private ImageServiceImpl imageServiceImpl;

    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
        Set<UserRole> userRoleSet = new HashSet<>();

        Role role = new Role();
        role.setRoleName("ADMIN");
        role.setRoleId(1L);

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        userRoleSet.add(userRole);

        return this.userservice.createUser(user, userRoleSet);
    }

    //@PostMapping(value = "/category", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //public ResponseEntity<?> addProduct(@RequestBody Category category, @RequestParam("images") ArrayList<MultipartFile> images) throws Exception{
//    public ResponseEntity<?> addProduct(
//            @RequestPart(value = "images") ArrayList<MultipartFile> images,
//            @RequestPart(value = "value") Category category
//    ) throws Exception {
//        Set<CategoryImage> categoryImages = new HashSet<>();
//
//        CategoryImage categoryImage = new CategoryImage();
//
//        for (MultipartFile image : images) {
//            String imageName = this.imageServiceImpl.uploadImage(image);
//            Optional<Image> img = this.imageRepository.findByFilename(imageName);
//            categoryImage.setCategory(category);
//            categoryImage.setImage( image);
//        }
//
//        categoryImages.add(categoryImage);
        //Category cat = this.categoryServiceImpl.createCategory(category,images);
        //return ResponseEntity.status(HttpStatus.OK).body(cat);
  //  }

}
