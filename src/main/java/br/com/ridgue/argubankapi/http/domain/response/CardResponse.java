package br.com.ridgue.argubankapi.http.domain.response;

import br.com.ridgue.argubankapi.domain.AccountTO;
import br.com.ridgue.argubankapi.domain.CardTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data()
@AllArgsConstructor
public class CardResponse extends DefaultResponse {
    private List<CardTO> cardTOS;

    public CardResponse(String status, String messages) {
        super(status, messages);
    }

    public CardResponse(Integer page, Long size, List<CardTO> tos) {
        super(page, size);
        this.cardTOS = tos;
    }
}
