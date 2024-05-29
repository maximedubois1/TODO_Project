package com.sp.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.sp.model.UserEntity;
import com.sp.model.dto.AuthDTO;
import com.sp.model.dto.JwtResponseDTO;
import com.sp.model.dto.UserDTO;
import com.sp.repository.UserRepository;
import com.sp.service.AuthService;
import com.sp.service.CardService;
import com.sp.service.UserService;
import com.sp.utils.CookieUtil;
import com.sp.utils.security.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final CardService cardService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserService userService, JwtService jwtService, UserRepository userRepository, CardService cardService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.cardService = cardService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public JwtResponseDTO authenticate(AuthDTO authDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword()));
        if (authentication.isAuthenticated())
            return new JwtResponseDTO.Builder().withAccessToken(jwtService.generateToken(authDTO.getUsername())).build();
        else
            return null;
    }

    private boolean checkCredential(AuthDTO authDTO) {
        UserEntity user = userRepository.findBySurname(authDTO.getSurname());
        return user != null && authDTO.getSurname().equals(user.getSurname()) &&
                BCrypt.verifyer().verify(authDTO.getPassword().toCharArray(), user.getPassword()).verified;
    }

    @Override
    public Cookie logout() {
        Cookie cookie = CookieUtil.createCookie("auth_jwt", null);
        cookie.setMaxAge(0);
        return cookie;
    }

    @Override
    public UserDTO getLoggedUser(HttpServletRequest request) {
        String jwtToken = CookieUtil.getCookieValue(request, "auth_jwt");
        String username = jwtService.extractUsername(jwtToken);
        if (jwtToken == null) {
            return null;
        } else {
            return userService.getBySurname(username);
        }
    }

    @Override
    public JwtResponseDTO register(AuthDTO authDTO) {
        if (userRepository.findBySurname(authDTO.getSurname()) != null) {
            return null;
        }
        System.out.println(authDTO);
        System.out.println(authDTO.getPassword());
        System.out.println(authDTO.getSurname());
        UserEntity newUser = new UserEntity();
        newUser.setSurname(authDTO.getSurname());
        newUser.setName(authDTO.getSurname());
        newUser.setPassword(BCrypt.withDefaults().hashToString(12, authDTO.getPassword().toCharArray()));
        newUser.setWallet(0);
        newUser = userRepository.save(newUser);
        cardService.generateFiveCardsForUser(newUser);
        return authenticate(authDTO);
    }
}
