package br.com.ridgue.argubankapi.http.domain.response;

import br.com.ridgue.argubankapi.domain.CityTO;
import br.com.ridgue.argubankapi.domain.StateTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data()
@AllArgsConstructor
public class CityResponse extends DefaultResponse {
    private List<CityTO> cityTOS;

    public CityResponse(String status, List<String> messages) {
        super(status, messages);
    }
}
