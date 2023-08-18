package com.serenecandles.server.repo;

import com.serenecandles.server.model.Cart;
import com.serenecandles.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    public List<Cart> findByUser(User user);

}
