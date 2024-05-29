package com.sp.controller;

import com.sp.model.dto.AuthDTO;
import com.sp.model.dto.JwtResponseDTO;
import com.sp.model.dto.UserDTO;
import com.sp.service.AuthService;
import com.sp.utils.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> auth(@RequestBody AuthDTO authDTO, HttpServletResponse response) {
        JwtResponseDTO jwt = authService.authenticate(authDTO);
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }

        String token = jwt.getAccessToken();
        Cookie jwtCookie = CookieUtil.createCookie("auth_jwt", token);
        response.addCookie(jwtCookie);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body("Login successful");
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public ResponseEntity<String> logout(HttpServletResponse response) {
        Cookie cookie = authService.logout();
        response.addCookie(cookie);
        System.out.println(cookie.getName() + " " + cookie.getValue() + " " + cookie.getMaxAge());
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Logout successful");
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public ResponseEntity<Long> register(@RequestBody AuthDTO authDTO, HttpServletResponse response) {
        UserDTO userDTO = authService.register(authDTO);

        if (userDTO == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(-1L);
        }

        return ResponseEntity.ok().body(userDTO.getId());
    }

    @RequestMapping(value = {"/is-connected"}, method = RequestMethod.POST)
    public ResponseEntity<Long> isConnected(HttpServletRequest request) {
        UserDTO user = authService.getLoggedUser(request);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(-1L);
        }
        return ResponseEntity.ok().body(user.getId());
    }
}
