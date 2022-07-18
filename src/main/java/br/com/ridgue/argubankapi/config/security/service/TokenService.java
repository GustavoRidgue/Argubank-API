package br.com.ridgue.argubankapi.config.security.service;

import br.com.ridgue.argubankapi.domain.TokenTO;
import br.com.ridgue.argubankapi.exception.InvalidTokenFormatException;
import br.com.ridgue.argubankapi.exception.TokenNotAuthenticatedException;
import br.com.ridgue.argubankapi.gateway.database.entity.Client;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
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

        return getTokenTO(token, expirationDate);
    }

    public void validateToken(String token) throws TokenNotAuthenticatedException {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
        } catch (Exception e) {
            throw new TokenNotAuthenticatedException("This token is not authenticated");
        }
    }

    public Long getUserId(String token) throws InvalidTokenFormatException {
        try {
            return Long.parseLong(Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject());

        } catch (Exception e) {
            throw new InvalidTokenFormatException("Either the header is empty or it contains a invalid formatted token");
        }
    }

    private TokenTO getTokenTO(String token, Date expirationDate) {
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
