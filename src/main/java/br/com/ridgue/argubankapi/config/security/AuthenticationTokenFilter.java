package br.com.ridgue.argubankapi.config.security;

import br.com.ridgue.argubankapi.config.security.service.TokenService;
import br.com.ridgue.argubankapi.exception.InvalidTokenFormatException;
import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.gateway.database.entity.Client;
import br.com.ridgue.argubankapi.gateway.database.repository.ClientRepositoryFacade;
import br.com.ridgue.argubankapi.http.ws.url.URLMapping;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private ClientRepositoryFacade clientRepositoryFacade;

    public AuthenticationTokenFilter(TokenService tokenService, ClientRepositoryFacade clientRepositoryFacade) {
        this.tokenService = tokenService;
        this.clientRepositoryFacade = clientRepositoryFacade;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) {

        if (URLMapping.requireAuthentication(request.getRequestURI(), request.getMethod())){
            String token = validateToken(request);
            authenticateToken(token);
        }

        filterChain.doFilter(request, response);
    }

    private String validateToken(HttpServletRequest request) throws InvalidTokenFormatException {
        String authorization = request.getHeader("Authorization");

        if (authorization == null || authorization.isEmpty() || !authorization.startsWith("Bearer "))
            throw new InvalidTokenFormatException("Either the header is empty or it contains a invalid formatted token");

        String token = authorization.substring(7);
        tokenService.validateToken(token);

        return token;
    }

    private void authenticateToken(String token) throws InvalidTokenFormatException, ResourceNotFoundException {
        Long clientId = tokenService.getUserId(token);
        Client client = clientRepositoryFacade.findById(clientId);

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(client, null, client.getProfiles());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
