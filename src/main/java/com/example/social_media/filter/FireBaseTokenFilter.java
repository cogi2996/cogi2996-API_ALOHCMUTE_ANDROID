//package com.example.social_media.filter;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthException;
//import com.google.firebase.auth.FirebaseToken;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.io.IOException;
//@Component
//@RequiredArgsConstructor
//public class FireBaseTokenFilter extends OncePerRequestFilter {
//    private final UserDetailsService userDetailsService;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
////Extracts token from header
//        String token = request.getHeader("Authorization");
//
//        //checks if token is there
//        if (token == null )
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Missing token!");
//
//        FirebaseToken decodedToken = null;
//        try {
//            //verifies token to firebase server
//            decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
//        } catch (FirebaseAuthException e) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Error! "+e.toString());
//        }
//        //if token is invalid
//        if (decodedToken==null){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid token!");
//        }
////        String uid= decodedToken.getUid();
//        String email = decodedToken.getEmail();
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
//        //set Authentication to SecurityContextHolder
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                userDetails,
//                null,
//                userDetails.getAuthorities()
//        );
//        SecurityContextHolder.getContext().setAuthentication(authToken);
//        filterChain.doFilter(request,response);
//    }
//}
