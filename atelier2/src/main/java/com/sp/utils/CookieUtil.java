package com.sp.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookieUtil {

    /**
     * Get the value of a cookie by its name.
     * @param request the HttpServletRequest object containing the request details.
     * @param cookieName the name of the cookie.
     * @return the value of the cookie if it exists, null otherwise.
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null; // Retourne null si le cookie n'existe pas
    }
}
