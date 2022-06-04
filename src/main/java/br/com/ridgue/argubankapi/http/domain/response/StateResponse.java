package br.com.ridgue.argubankapi.http.domain.response;

import br.com.ridgue.argubankapi.domain.StateTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data()
@AllArgsConstructor
public class StateResponse extends DefaultResponse {
    private List<StateTO> stateTOS;

    public StateResponse(String status, List<String> messages) {
        super(status, messages);
    }
}
