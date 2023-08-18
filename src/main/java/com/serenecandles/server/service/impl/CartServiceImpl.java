package com.serenecandles.server.service.impl;

import com.serenecandles.server.config.JwtAuthenticationFilter;
import com.serenecandles.server.model.Cart;
import com.serenecandles.server.model.Product;
import com.serenecandles.server.model.User;
import com.serenecandles.server.repo.CartRepository;
import com.serenecandles.server.repo.ProductRepository;
import com.serenecandles.server.repo.UserRepository;
import com.serenecandles.server.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;
    @Override
    public Cart addToCart(Long productId) {
        Product product = productRepository.findById(productId).get();
        String currentUser = JwtAuthenticationFilter.CURRENT_USER;
        User user=null;
        if(currentUser!=null){
            user = userRepository.findByUsername(currentUser);
        }

        List<Cart> carts = cartRepository.findByUser(user);
        List<Cart> filteredList = carts.stream().filter(x-> x.getProduct().getProductId() == productId).collect(Collectors.toList());

        if(filteredList.size()>0){
            return null;
        }

        if(product!=null && user!=null){
            Cart cart=new Cart(product, user);
            return cartRepository.save(cart);
        }
        return null;
    }

    @Override
    public List<Cart> getCartDetail() {
        String username = JwtAuthenticationFilter.CURRENT_USER;
        return cartRepository.findByUser(userRepository.findByUsername(username));
    }

    @Override
    public void deleteCartItem(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
