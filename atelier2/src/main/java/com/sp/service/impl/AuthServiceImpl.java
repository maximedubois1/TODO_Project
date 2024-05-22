package com.sp.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.sp.model.UserEntity;
import com.sp.model.dto.AuthDTO;
import com.sp.model.dto.UserDTO;
import com.sp.repository.UserRepository;
import com.sp.service.AuthService;
import com.sp.service.CardService;
import com.sp.service.UserService;
import com.sp.utils.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CardService cardService;

    @Override
    public Cookie authenticate(AuthDTO authDTO) {

        if (checkCredential(authDTO)) {
            String token = authDTO.getSurname();

            Cookie cookie = new Cookie("Token", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(false);
            cookie.setPath("/");
            cookie.setMaxAge(6 * 24 * 60 * 60);
            return cookie;
        }
        return null;
    }

    private boolean checkCredential(AuthDTO authDTO) {
        UserEntity user = userRepository.findBySurname(authDTO.getSurname());
        return user != null && authDTO.getSurname().equals(user.getSurname()) &&
                BCrypt.verifyer().verify(authDTO.getPassword().toCharArray(), user.getPassword()).verified;
    }

    @Override
    public Cookie logout() {
        Cookie cookie = new Cookie("Token", null);
        cookie.setMaxAge(0);
        return cookie;
    }

    @Override
    public UserDTO getLoggedUser(HttpServletRequest request) {
        String sessionToken = CookieUtil.getCookieValue(request, "Token");
        System.out.println("sessionToken: " + sessionToken);
        if (sessionToken == null) {
            return null;
        } else {
            return userService.getBySurname(sessionToken);
        }
    }

    @Override
    public Cookie register(AuthDTO authDTO) {
        if (userRepository.findBySurname(authDTO.getSurname()) != null) {
            return null;
        }
        UserEntity newUser = new UserEntity();
        newUser.setSurname(authDTO.getSurname());
        newUser.setName(authDTO.getSurname());
        newUser.setPassword(BCrypt.withDefaults().hashToString(12, authDTO.getPassword().toCharArray()));
        newUser.setWallet(0);
        newUser = userRepository.save(newUser);
        cardService.generateFiveCardsForUser(newUser.getId());
        return authenticate(authDTO);
    }
}
