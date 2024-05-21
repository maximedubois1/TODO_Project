package com.sp.controller;

import com.sp.model.dto.AuthDTO;
import com.sp.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public ResponseEntity<String> auth(Model model, @RequestBody AuthDTO authDTO, HttpServletResponse response) {
        Cookie cookie = authService.authenticate(authDTO);
        if (cookie != null) {
            response.addCookie(cookie);
            return ResponseEntity.ok("Login successful");
        }
        //login fail
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public ResponseEntity<String> logout(Model model, HttpServletResponse response) {
        Cookie cookie = authService.logout();
        // Ajouter le cookie à la réponse
        response.addCookie(cookie);
        return ResponseEntity.ok("Logout successful");
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public ResponseEntity<String> register(Model model, @RequestBody AuthDTO authDTO, HttpServletResponse response) {
        Cookie cookie = authService.register(authDTO);
        if (cookie == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful");
        }
        response.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed");
    }


}