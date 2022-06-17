package br.com.ridgue.argubankapi.domain;

import br.com.ridgue.argubankapi.gateway.database.entity.Card;
import br.com.ridgue.argubankapi.gateway.database.entity.Client;
import br.com.ridgue.argubankapi.gateway.database.entity.embadded.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class AccountTO {
    private Long id;
    private String bank;
    private Integer agency;
    private String number;
    private Integer digit;
    private BigDecimal amount;
    private List<Card> cards;
    private Client client;
    private boolean active;
}
