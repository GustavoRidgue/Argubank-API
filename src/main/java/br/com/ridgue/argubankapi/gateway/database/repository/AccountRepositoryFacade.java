package br.com.ridgue.argubankapi.gateway.database.repository;

import br.com.ridgue.argubankapi.gateway.database.entity.Account;

import java.util.List;

public interface AccountRepositoryFacade {
    List<Account> findAll();
    Account findById(Long id);
    Account findByNumberAndDigit(String number, Integer digit);
    Account findByClientId(Long clientId);
    void save(Account account);
    void delete(Long id);
}
