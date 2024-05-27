package com.sp.controller;

import com.sp.model.dto.UserDTO;
import com.sp.service.AuthService;
import com.sp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController  {

    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(this.userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable String id) {
        return this.userService.getById((long) Integer.parseInt(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getProfile(HttpServletRequest request) {
        return ResponseEntity.ok(this.authService.getLoggedUser(request));
    }

    @GetMapping("/wallet/{id}/test/{amount}")
    public ResponseEntity<Boolean> testWallet(@PathVariable String id, @PathVariable String amount) {
        return ResponseEntity.ok(this.userService.testWallet((long) Integer.parseInt(id), Integer.parseInt(amount)));
    }

    @PostMapping("/wallet/{id}/add/{amount}")
    public ResponseEntity<Boolean> addWallet(@PathVariable String id, @PathVariable String amount) {
        return ResponseEntity.ok(this.userService.addWallet((long) Integer.parseInt(id), Integer.parseInt(amount)));
    }

    @PostMapping("/wallet/{id}/sub/{amount}")
    public ResponseEntity<Boolean> subWallet(@PathVariable String id, @PathVariable String amount) {
        return ResponseEntity.ok(this.userService.subWallet((long) Integer.parseInt(id), Integer.parseInt(amount)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.userService.delete((long) Integer.parseInt(id));
        return ResponseEntity.noContent().build();
    }
}
