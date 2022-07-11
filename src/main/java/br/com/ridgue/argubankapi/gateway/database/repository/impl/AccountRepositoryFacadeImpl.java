package br.com.ridgue.argubankapi.gateway.database.repository.impl;

import br.com.ridgue.argubankapi.gateway.database.entity.Account;
import br.com.ridgue.argubankapi.gateway.database.repository.AccountRepositoryFacade;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountRepositoryFacadeImpl implements AccountRepositoryFacade {
    private final AccountRepository accountRepository;

    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
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
