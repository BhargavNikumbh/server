package com.serenecandles.server.service;

import com.serenecandles.server.model.Cart;
import com.serenecandles.server.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CartService {
    Cart addToCart(Long productId);
    List<Cart> getCartDetail();
    void deleteCartItem(Long cartId);
}
