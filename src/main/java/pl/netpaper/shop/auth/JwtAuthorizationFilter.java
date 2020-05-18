package pl.netpaper.shop.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        Claims claims = Jwts.parser().setSigningKey("authorization").parseClaimsJws(token.replace("Bearer ", "")).getBody();
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        String authorities = claims.get("authorities", String.class);
        if (authorities != null) {
            simpleGrantedAuthorities = Arrays.stream(authorities.split(",")) //tworzenie z tablicy stringow stream
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
        String email = claims.getSubject();
        if (email != null){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, null, simpleGrantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            chain.doFilter(request, response);
        }else{
            response.setStatus(401);
        }

    }
}
