package com.tomer.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestURI = request.getRequestURI();
		if (requestURI.equals("/user")) {
			HttpSession session = request.getSession();
            Boolean isAuthenticated = (Boolean) session.getAttribute("authenticated");
            if (isAuthenticated == null || !isAuthenticated) {
                response.sendRedirect("/");
                return false;
            }
		}
		return true;
	}
}
