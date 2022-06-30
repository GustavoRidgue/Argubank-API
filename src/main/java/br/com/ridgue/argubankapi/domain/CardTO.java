package br.com.ridgue.argubankapi.domain;

import br.com.ridgue.argubankapi.gateway.database.entity.enums.CardLevel;
import br.com.ridgue.argubankapi.gateway.database.entity.enums.CardType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardTO {
    private Long id;
    private Long accountId;
    private String number;
    private CardType type;
    private CardLevel level;
    private boolean active;
}
