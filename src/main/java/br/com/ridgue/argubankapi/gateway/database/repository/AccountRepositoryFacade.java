package br.com.ridgue.argubankapi.gateway.database.repository;

import br.com.ridgue.argubankapi.gateway.database.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountRepositoryFacade {
    Page<Account> findAll(Pageable pageable);
    Account findById(Long id);
    Account findByNumberAndDigit(String number, Integer digit);
    Account findByClientId(Long clientId);
    void save(Account account);
    void delete(Long id);
}
