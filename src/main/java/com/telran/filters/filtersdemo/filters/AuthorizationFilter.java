package com.telran.filters.filtersdemo.filters;

import com.telran.filters.filtersdemo.entity.UserSession;
import com.telran.filters.filtersdemo.exception.AuthenticationException;
import com.telran.filters.filtersdemo.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class AuthorizationFilter implements Filter {

    @Autowired
    private UserSessionRepository userSessionRepository;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getRequestURI().startsWith("/users/login")) {
            chain.doFilter(request, response);
            return;
        }

        String sessionId = httpRequest.getHeader("telran-authorization");

        UserSession userSession = userSessionRepository.findBySessionId(sessionId);

        if (userSession == null) {
            httpResponse.sendError(401);
            throw new AuthenticationException("Session does not exist");
        }

        if (!userSession.isValid()) {
            httpResponse.sendError(401);
            throw new AuthenticationException("Session is not valid");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
