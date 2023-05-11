package com.serenecandles.server.controller;

import com.serenecandles.server.config.JwtUtil;
import com.serenecandles.server.model.JwtRequest;
import com.serenecandles.server.model.JwtResponse;
import com.serenecandles.server.model.User;
import com.serenecandles.server.service.impl.UserDetailsSericeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsSericeImpl userDetailsSerice;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try{
            this.authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        }
        catch(UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("User not found");
        }
        ////////////user is authenticated
        UserDetails userDetails = this.userDetailsSerice.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }


    private void authenticate(String username, String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch(DisabledException e){
            throw new Exception("User is disabled");
        }catch(BadCredentialsException e){
            throw new Exception("Invalid Credetials");
        }
    }

    //returns details of current user
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        return (User)this.userDetailsSerice.loadUserByUsername(principal.getName());
    }

}
