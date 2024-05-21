package com.sp.controller;

import com.sp.model.dto.AuthDTO;
import com.sp.utils.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AuthController {
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {
        String sessionToken = CookieUtil.getCookieValue(request, "Token");

        if (sessionToken == null) {


            AuthDTO authDTO = new AuthDTO();
            model.addAttribute("authDTO", authDTO);
            return "login";

        }
        return "redirect:/addCard";

    }

    @RequestMapping(value = {"/auth"}, method = RequestMethod.POST)
    public String auth(Model model, AuthDTO authDTO, HttpServletResponse response) {
        if (authDTO.getSurname().equals("admin") && authDTO.getPassword().equals("admin")) {

            String token = authDTO.getSurname(); // Vous pouvez générer un vrai token ici

            Cookie cookie = new Cookie("Token", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(false);
            cookie.setPath("/");
            cookie.setMaxAge(7 * 24 * 60 * 60);

            // Ajouter le cookie à la réponse
            response.addCookie(cookie);

            return "redirect:/addCard";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout(Model model, HttpServletResponse response) {

        Cookie cookie = new Cookie("Token", null);
        cookie.setMaxAge(0);

        // Ajouter le cookie à la réponse
        response.addCookie(cookie);
        return "redirect:/login";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String register(Model model) {
        AuthDTO authDTO = new AuthDTO();
        model.addAttribute("authDTO", authDTO);
        return "redirect:/index";
    }


}