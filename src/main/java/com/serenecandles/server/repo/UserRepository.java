package com.serenecandles.server.repo;

import com.serenecandles.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository  extends JpaRepository<User,Long> {
    public User findByUsername(String username);
}
