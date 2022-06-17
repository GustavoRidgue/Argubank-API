package br.com.ridgue.argubankapi.gateway.database.repository.impl;

import br.com.ridgue.argubankapi.gateway.database.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByClientId(Long clientId);

    Account findByNumberAndDigit(String number, Integer digit);
}
