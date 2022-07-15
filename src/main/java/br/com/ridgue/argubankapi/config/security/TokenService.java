package br.com.ridgue.argubankapi.config.security;

import br.com.ridgue.argubankapi.gateway.database.entity.Client;
import br.com.ridgue.argubankapi.gateway.database.repository.ClientRepositoryFacade;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${argubank.jwt.expiration}")
    private String expirationMilliseconds;

    @Value("${argubank.jwt.secret}")
    private String secret;

    public String generateToken(Authentication auth) {
        Client principal = (Client) auth.getPrincipal();

        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(expirationMilliseconds));

        return Jwts.builder()
                .setIssuer("Argubank API")
                .setSubject(principal.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
