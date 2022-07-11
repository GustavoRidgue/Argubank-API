package br.com.ridgue.argubankapi.http.ws;

import br.com.ridgue.argubankapi.domain.AccountTO;
import br.com.ridgue.argubankapi.exception.AlreadyHasAccountException;
import br.com.ridgue.argubankapi.exception.NotOldEnoughException;
import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.gateway.database.entity.Account;
import br.com.ridgue.argubankapi.http.domain.response.AccountResponse;
import br.com.ridgue.argubankapi.usecase.account.AlterAccountUseCase;
import br.com.ridgue.argubankapi.usecase.account.FindAccountUsecase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;

import static br.com.ridgue.argubankapi.http.ws.url.BaseWS.INTERNAL_MESSAGE_ERROR;
import static br.com.ridgue.argubankapi.http.ws.url.URLMapping.*;

@RestController
@RequestMapping(ROOT_API_PATH)
@AllArgsConstructor
@Slf4j
public class AccountWS {
    private final FindAccountUsecase findAccountUsecase;
    private final AlterAccountUseCase alterAccountUseCase;

    @GetMapping(ROOT_API_WS_FIND_ALL_ACCOUNT)
    public ResponseEntity<AccountResponse> findAll() {
        return ResponseEntity.ok(new AccountResponse(findAccountUsecase.findAll()));
    }

    @GetMapping(ROOT_API_WS_FIND_ACCOUNT_BY_ID)
    public ResponseEntity<AccountResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new AccountResponse(Collections.singletonList(findAccountUsecase.findById(id))));
    }

    @GetMapping(ROOT_API_WS_FIND_ACCOUNT_BY_NUMBER_AND_DIGIT)
    public ResponseEntity<AccountResponse> findByNumberAndDigit(@PathVariable("number") String number,
                                                                @PathVariable("digit") Integer digit) {
        return ResponseEntity.ok(new AccountResponse(Collections.singletonList(findAccountUsecase.findByNumberAndDigit(number, digit))));
    }

    @GetMapping(ROOT_API_WS_FIND_ACCOUNT_BY_CLIENT_ID)
    public ResponseEntity<AccountResponse> findByClientId(@PathVariable("clientId") Long clientId) {
        return ResponseEntity.ok(new AccountResponse(Collections.singletonList(findAccountUsecase.findByClientId(clientId))));
    }

    @PostMapping(ROOT_API_WS_CREATE_ACCOUNT)
    public ResponseEntity<AccountResponse> create(@PathVariable("clientId") Long clientId, UriComponentsBuilder uriComponentsBuilder) {
        try {
            AccountTO accountTO = alterAccountUseCase.create(clientId);
            URI uri = uriComponentsBuilder.path(ROOT_API_PATH + ROOT_API_WS_FIND_CLIENT_BY_NAME).buildAndExpand(accountTO.getId()).toUri();
            return ResponseEntity.created(uri).body(new AccountResponse(Collections.singletonList(accountTO)));
        } catch (AlreadyHasAccountException | NotOldEnoughException | ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping(ROOT_API_WS_CREATE_ACCOUNT)
    public ResponseEntity<AccountResponse> activate(@PathVariable("clientId") Long clientId) {
        try {
            AccountTO accountTO = alterAccountUseCase.activate(clientId);
            return ResponseEntity.ok(new AccountResponse(Collections.singletonList(accountTO)));
        } catch (AlreadyHasAccountException | ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
