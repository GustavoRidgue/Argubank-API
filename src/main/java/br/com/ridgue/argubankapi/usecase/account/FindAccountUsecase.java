package br.com.ridgue.argubankapi.usecase.account;

import br.com.ridgue.argubankapi.domain.AccountTO;
import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.gateway.database.entity.Account;
import br.com.ridgue.argubankapi.gateway.database.repository.AccountRepositoryFacade;
import br.com.ridgue.argubankapi.http.domain.builder.AccountBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class FindAccountUsecase {
    private final AccountRepositoryFacade accountRepositoryFacade;
    private final AccountBuilder accountBuilder;

    public Page<AccountTO> findAll(Pageable pageable) {
        return accountRepositoryFacade.findAll(pageable).map(accountBuilder::build);
    }

    public AccountTO findById(Long id) {
        Account account = accountRepositoryFacade.findById(id);
        return accountBuilder.build(account);
    }

    public AccountTO findByClientId(Long clientId) {
        Account account = accountRepositoryFacade.findByClientId(clientId);
        return accountBuilder.build(account);
    }

    public AccountTO findByNumberAndDigit(String number, Integer digit) {
        Account account = accountRepositoryFacade.findByNumberAndDigit(number, digit);
        return accountBuilder.build(account);
    }
}
