package com.rho.cli.api.infra.security;

import com.rho.cli.api.domain.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    private String getAuthorizationHeaderToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null  ) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var TokenJWT  = getAuthorizationHeaderToken(request);
        if(TokenJWT != null ) {
            var userName = tokenService.getSubject(TokenJWT);
            if (userName != null) {
                var user = userRepository.findByLogin(userName);
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
