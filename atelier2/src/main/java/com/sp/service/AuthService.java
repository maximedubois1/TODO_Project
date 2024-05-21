package com.sp.service;

import com.sp.model.dto.AuthDTO;
import com.sp.model.dto.UserDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    public Cookie authenticate(AuthDTO authDTO);
    public Cookie logout();
    public UserDTO getLoggedUser(HttpServletRequest request);
    public Cookie register(AuthDTO authDTO);
}
