package br.com.ridgue.argubankapi.http.domain.response;

import br.com.ridgue.argubankapi.domain.TokenTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data()
@AllArgsConstructor
public class TokenResponse extends DefaultResponse {
    private List<TokenTO> tokenTOS;

    public TokenResponse(String status, String messages) {
        super(status, messages);
    }

    public TokenResponse(Integer page, Long size, List<TokenTO> tos) {
        super(page, size);
        this.tokenTOS = tos;
    }
}
