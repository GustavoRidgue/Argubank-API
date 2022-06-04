package br.com.ridgue.argubankapi.gateway.database.entity.embadded;

import br.com.ridgue.argubankapi.gateway.database.entity.City;
import br.com.ridgue.argubankapi.gateway.database.entity.State;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Address implements Serializable {
    @Column(nullable = false)
    private String cep;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(nullable = false)
    private String neighborhood;

    private String complement;

    @ManyToOne
    private City city;
}
