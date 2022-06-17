package br.com.ridgue.argubankapi.http.domain.builder;

import br.com.ridgue.argubankapi.domain.AccountTO;
import br.com.ridgue.argubankapi.gateway.database.entity.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AccountBuilder {
    public AccountTO build (Account account) {
        if (account == null) return null;

        AccountTO to = new AccountTO();
        to.setId(account.getId());
        to.setNumber(account.getNumber());
        to.setDigit(account.getDigit());
        to.setBank(account.getBank());
        to.setAgency(account.getAgency());
        to.setAmount(account.getAmount());
        to.setCards(account.getCards());
        to.setClient(account.getClient());
        to.setActive(account.isActive());
        return to;
    }

    public List<AccountTO> build(List<Account> accounts) {
        if (accounts == null)
            return new ArrayList<>();

        return accounts.stream().map(this::build).collect(Collectors.toList());
    }
}
