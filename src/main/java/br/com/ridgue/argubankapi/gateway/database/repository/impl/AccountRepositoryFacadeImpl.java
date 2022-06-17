package br.com.ridgue.argubankapi.gateway.database.repository.impl;

import br.com.ridgue.argubankapi.exception.ResourceNotFoundException;
import br.com.ridgue.argubankapi.gateway.database.entity.Account;
import br.com.ridgue.argubankapi.gateway.database.entity.Client;
import br.com.ridgue.argubankapi.gateway.database.repository.AccountRepositoryFacade;
import br.com.ridgue.argubankapi.gateway.database.repository.ClientRepositoryFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AccountRepositoryFacadeImpl implements AccountRepositoryFacade {
    private final AccountRepository accountRepository;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account findByNumberAndDigit(String number, Integer digit) {
        return accountRepository.findByNumberAndDigit(number, digit);
    }

    public Account findByClientId(Long clientId) {
        return accountRepository.findByClientId(clientId);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
