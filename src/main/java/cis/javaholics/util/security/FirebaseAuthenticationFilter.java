package cis.javaholics.util.security;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class FirebaseAuthenticationFilter extends OncePerRequestFilter {


    private AuthenticationManager authenticationManager;
    private FirebaseAuthenticationFailureHandler failureHandler;

    public FirebaseAuthenticationFilter( AuthenticationManager authenticationManager, FirebaseAuthenticationFailureHandler failureHandler) {

        this.authenticationManager = authenticationManager;
        this.failureHandler = failureHandler;

    }

    private String extractAuthTokenFromRequest(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        if (StringUtils.hasText(authToken) && authToken.startsWith("Bearer ")) {
            return authToken.substring(7);
        }
        return null;
    }


    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void setAuthenticationFailureHandler(FirebaseAuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        try {
            String authToken = extractAuthTokenFromRequest(request);

            if (StringUtils.hasText(authToken)) {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(authToken);
                String uid = decodedToken.getUid();
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("USER"));
                Authentication authentication = new UsernamePasswordAuthenticationToken(uid, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (AuthenticationException e) {
            failureHandler.onAuthenticationFailure(request, response, e);
            return;
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }

        filterChain.doFilter( request, response);
    }
}