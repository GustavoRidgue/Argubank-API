package br.com.ridgue.argubankapi.http.domain.response;

import br.com.ridgue.argubankapi.domain.AccountTO;
import br.com.ridgue.argubankapi.domain.ClientTO;
import br.com.ridgue.argubankapi.gateway.database.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data()
@AllArgsConstructor
public class AccountResponse extends DefaultResponse {
    private List<AccountTO> accountTOS;

    public AccountResponse(String status, List<String> messages) {
        super(status, messages);
    }
}
