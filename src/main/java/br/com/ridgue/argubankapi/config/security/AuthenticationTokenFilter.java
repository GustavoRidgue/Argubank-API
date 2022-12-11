package br.com.ridgue.argubankapi.config.security;

import br.com.ridgue.argubankapi.config.security.service.TokenService;
import br.com.ridgue.argubankapi.exception.InvalidTokenFormatException;
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

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final ClientRepositoryFacade clientRepositoryFacade;

    public AuthenticationTokenFilter(TokenService tokenService, ClientRepositoryFacade clientRepositoryFacade) {
        this.tokenService = tokenService;
        this.clientRepositoryFacade = clientRepositoryFacade;
    }

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) {

        if (URLMapping.requireAuthentication(request.getRequestURI(), request.getMethod())){
            String token = validateToken(request);
            authenticateToken(token);
        }

        filterChain.doFilter(request, response);
    }

    @SneakyThrows
    private String validateToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        if (authorization == null || authorization.isEmpty() || !authorization.startsWith("Bearer "))
            throw new InvalidTokenFormatException("Header Authorization is empty or it contains an invalid formatted token");

        String token = authorization.substring(7);
        tokenService.validateToken(token);

        return token;
    }

    @SneakyThrows
    private void authenticateToken(String token) {
        Long clientId = tokenService.getUserId(token);
        Client client = clientRepositoryFacade.findById(clientId);

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(client, null, client.getProfiles());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
