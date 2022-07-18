package br.com.ridgue.argubankapi.config.security;

import br.com.ridgue.argubankapi.exception.InvalidTokenFormatException;
import lombok.SneakyThrows;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationTokenFilter extends OncePerRequestFilter {
    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) {

        String token = validateToken(request);
        System.out.println(token);

        filterChain.doFilter(request, response);
    }

    private String validateToken(HttpServletRequest request) throws InvalidTokenFormatException {
        String authorization = request.getHeader("Authorization");

        if (authorization == null || authorization.isEmpty() || !authorization.startsWith("Bearer "))
            throw new InvalidTokenFormatException("Either the header is empty or it contains a invalid formatted token");

        return authorization.substring(7);
    }
}
