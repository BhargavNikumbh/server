package com.serenecandles.server.controller;

import com.serenecandles.server.model.Role;
import com.serenecandles.server.model.User;
import com.serenecandles.server.model.UserRole;
import com.serenecandles.server.service.UserService;
import com.serenecandles.server.util.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OtpService otpService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    //creating user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        //encoding password with BCryptPassword encoder
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Set<UserRole> userRoleSet = new HashSet<>();

        Role role = new Role();
        role.setRoleName("NORMAL");
        role.setRoleId(2L);

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        userRoleSet.add(userRole);

        return this.userService.createUser(user, userRoleSet);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String userName){
        return this.userService.getUser(userName);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);
    }

}
