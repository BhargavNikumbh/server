package com.serenecandles.server.service;

import com.serenecandles.server.model.User;
import com.serenecandles.server.model.UserRole;

import java.util.Set;

public interface UserService {
    //creating User
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;
    public User getUser(String username);

    //delete user by id
    public void deleteUser(Long userId);
}
