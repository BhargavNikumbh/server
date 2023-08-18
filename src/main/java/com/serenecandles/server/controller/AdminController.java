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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User createUser(@RequestBody User user) throws Exception {
//        Set<UserRole> userRoleSet = new HashSet<>();
//
//        Role role = new Role();
//        role.setRoleName("ADMIN");
////        role.setRoleId(1L);
//
//        UserRole userRole = new UserRole();
//        userRole.setUser(user);
//        userRole.setRole(role);
//
//        userRoleSet.add(userRole);
//
//        return this.userservice.createUser(user, userRoleSet);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Set<UserRole> userRoleSet = new HashSet<>();

        Role role = new Role();
        role.setRoleName("ADMIN");
        role.setRoleId(2L);

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        userRoleSet.add(userRole);

        return this.userservice.createUser(user, userRoleSet);
    }

}
