package br.com.ridgue.argubankapi.http.ws;

import br.com.ridgue.argubankapi.config.security.service.TokenService;
import br.com.ridgue.argubankapi.http.domain.request.AuthRequest;
import br.com.ridgue.argubankapi.http.domain.response.TokenResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.Collections;

import static br.com.ridgue.argubankapi.http.ws.url.URLMapping.ROOT_API_PATH;
import static br.com.ridgue.argubankapi.http.ws.url.URLMapping.ROOT_API_WS_AUTH;

@RestController
@RequestMapping(ROOT_API_PATH)
@AllArgsConstructor
@Slf4j
public class AuthenticationWS {

    @Autowired
    private final AuthenticationManager authManager;

    @Autowired
    private final TokenService tokenService;

    @PostMapping(ROOT_API_WS_AUTH)
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody AuthRequest request) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication auth = authManager.authenticate(login);

        return ResponseEntity.ok(new TokenResponse(Collections.singletonList(tokenService.generateToken(auth))));
    }
}
