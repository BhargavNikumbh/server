package com.serenecandles.server.controller;

import com.serenecandles.server.model.Cart;
import com.serenecandles.server.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService cartService;

    @PreAuthorize("hasAuthority('NORMAL')")
    @GetMapping({"/addToCart/{productId}"})
    public Cart addToCart(@PathVariable("productId") Long productId){
        return cartService.addToCart(productId);
    }

    @PreAuthorize("hasAuthority('NORMAL')")
    @GetMapping("/getCartDetails")
    public List<Cart> getCartDetails(){
        return cartService.getCartDetail();
    }

    @PreAuthorize("hasAuthority('NORMAL')")
    @DeleteMapping("/deleteCartItem/{cartId}")
    public void deleteCartItem(@PathVariable(name = "cartId") Long cartId){
        cartService.deleteCartItem(cartId);
    }

}
