package com.sp.service;

import com.sp.model.dto.AuthDTO;
import com.sp.model.dto.UserDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

/**
 * This interface defines the operations that can be performed for authentication.
 */
public interface AuthService {

    /**
     * Authenticates a user.
     *
     * @param authDTO the AuthDTO object containing the authentication details of the user.
     * @return a Cookie object containing the session details if the authentication was successful.
     */
    Cookie authenticate(AuthDTO authDTO);

    /**
     * Logs out the currently logged in user.
     *
     * @return a Cookie object containing the session details after the logout.
     */
    Cookie logout();

    /**
     * Fetches the currently logged in user.
     *
     * @param request the HttpServletRequest object containing the request details.
     * @return a UserDTO object containing the details of the logged in user.
     */
    UserDTO getLoggedUser(HttpServletRequest request);

    /**
     * Registers a new user.
     *
     * @param authDTO the AuthDTO object containing the registration details of the user.
     * @return a Cookie object containing the session details if the registration was successful.
     */
    Cookie register(AuthDTO authDTO);
}
