package com.boboibo.mytimestore.configuration;

import com.boboibo.mytimestore.service.AuthenticateService;
import com.boboibo.mytimestore.utils.JWTUtilsHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class CustomJwtFilter extends OncePerRequestFilter {

@Autowired
    JWTUtilsHelper jwtUtilsHelper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    if(getTokenFromHeader(request) != null && !jwtUtilsHelper.isTokenLoggedOut(getTokenFromHeader(request))) {
        if( jwtUtilsHelper.verifyToken(getTokenFromHeader(request))  ){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("","",new ArrayList<>());
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authenticationToken);
        }
    }
        filterChain.doFilter(request, response);
    }


    private String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return token;
    }



}
