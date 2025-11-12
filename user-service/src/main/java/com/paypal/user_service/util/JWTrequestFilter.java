package com.paypal.user_service.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JWTrequestFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    public JWTrequestFilter(JWTUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        final String authorizationHeader = request.getHeader("Authorization");
//        String username = null;
//        String jwt = null;
//
//        // for Authenticating user.
//        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
//            jwt = authorizationHeader.substring(7);
//            try{
//                username = jwtUtil.extractUsername(jwt);
//            }catch (Exception e){
//                //log
//            }
//        }
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            //username = null not authenticated
//            if (jwtUtil.validateToken(jwt, username)) {
//                UsernamePasswordAuthenticationToken authToken =
//                        new UsernamePasswordAuthenticationToken(username, null, null);
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//
//        }
//
//        // For checking role based authorization and then authenticating role.
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            jwt = authorizationHeader.substring(7);
//            if (jwt == null || jwt.isBlank()) {
//                chain.doFilter(request, response);
//                return; // skip processing if token empty
//            }
//            try {
//                username = jwtUtil.extractUsername(jwt);
//                // only extract role if JWT is valid and present
//                String role = jwtUtil.extractRole(jwt);
//                // use role for authorities as needed
//                UsernamePasswordAuthenticationToken authToken =
//                        new UsernamePasswordAuthenticationToken(
//                                username,
//                                null,
//                                List.of(new SimpleGrantedAuthority(role))
//                        );
//
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//
//
//                chain.doFilter(request, response);
//            } catch (Exception e) {
//                // log error if you want
//                System.out.println("❌ Improper Authentication Or Authorization" +e.getMessage());
//            }
//        } else {
//            chain.doFilter(request, response);
//            return;
//        }
//
//
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        String jwt = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            if (!jwt.isBlank()) {
                try {
                    username = jwtUtil.extractUsername(jwt);
                    if (jwtUtil.validateToken(jwt, username)) {
                        String role = jwtUtil.extractRole(jwt);
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(
                                        username,
                                        null,
                                        List.of(new SimpleGrantedAuthority(role))
                                );
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token");
                    return; // stop processing
                }
            }
        }

        // Continue the filter chain (always call at the end)
        chain.doFilter(request, response);


    }

}
