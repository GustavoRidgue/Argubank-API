package br.com.ridgue.argubankapi.domain;

import br.com.ridgue.argubankapi.gateway.database.entity.enums.Region;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StateTO {
    private Long id;
    private String name;
    private Region region;
}
