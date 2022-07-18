package br.com.ridgue.argubankapi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TokenTO {
    private String token;
    private String type;
    private String expiration;
    private LocalDateTime createdAt;
    private LocalDateTime expirationAt;
}
