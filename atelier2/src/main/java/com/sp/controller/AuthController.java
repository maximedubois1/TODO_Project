package com.sp.controller;

import com.sp.model.dto.AuthDTO;
import com.sp.model.dto.UserDTO;
import com.sp.service.AuthService;
import com.sp.utils.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AuthController {

    @Autowired
    AuthService authService;

    private String goToLogin(Model model) {

        return "redirect:/login";
    }

    private String goToSuccess() {
        return "redirect:/addCard";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {
        String sessionToken = CookieUtil.getCookieValue(request, "Token");

        if (sessionToken == null) {
            AuthDTO authDTO = new AuthDTO();
            model.addAttribute("authDTO", authDTO);
            return "login";
        }
        UserDTO userDTO = authService.getLoggedUser(request);
        if (userDTO != null) {
            System.out.println(userDTO.getSurname() + " is logged in");
            return goToSuccess();
        }
        return goToLogin(model);
    }

    @RequestMapping(value = {"/auth"}, method = RequestMethod.POST)
    public String auth(Model model, AuthDTO authDTO, HttpServletResponse response) {
        Cookie cookie = authService.authenticate(authDTO);
        if (cookie != null) {
            response.addCookie(cookie);
            return goToSuccess();
        }
        //login fail
        return goToLogin(model);
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout(Model model, HttpServletResponse response) {
        Cookie cookie = authService.logout();
        // Ajouter le cookie à la réponse
        response.addCookie(cookie);
        return goToLogin(model);
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String register(Model model, AuthDTO authDTO, HttpServletResponse response) {
        Cookie cookie = authService.register(authDTO);
        if (cookie == null) {
            return goToLogin(model);
        }
        response.addCookie(cookie);
        return goToSuccess();
    }


}