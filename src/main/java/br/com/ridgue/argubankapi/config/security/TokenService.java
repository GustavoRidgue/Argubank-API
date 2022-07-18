package br.com.ridgue.argubankapi.config.security;

import br.com.ridgue.argubankapi.domain.TokenTO;
import br.com.ridgue.argubankapi.gateway.database.entity.Client;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TokenService {

    @Value("${argubank.jwt.expiration}")
    private String expirationMilliseconds;

    @Value("${argubank.jwt.secret}")
    private String secret;

    public TokenTO generateToken(Authentication auth) {
        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(expirationMilliseconds));

        Client principal = (Client) auth.getPrincipal();
        String token = buildToken(principal, today, expirationDate);

        return getTokenTO(token, today, expirationDate);
    }

    private TokenTO getTokenTO(String token, Date today, Date expirationDate) {
        TokenTO to = new TokenTO();
        to.setToken(token);
        to.setType("Bearer");
        to.setExpiration(expirationMilliseconds);
        to.setCreatedAt(LocalDateTime.now());
        to.setExpirationAt(expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        return to;
    }

    private String buildToken(Client client, Date today, Date expirationDate) {
        return Jwts.builder()
                .setIssuer("Argubank API")
                .setSubject(client.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
