package com.generation.mondolibri.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        Object roleAttr = session.getAttribute("role");
        if(roleAttr == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "jsp/error.jsp");
            return;
        }
        String role = roleAttr.toString();
        if (!("Admin").equals(role)) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "jsp/error.jsp");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
