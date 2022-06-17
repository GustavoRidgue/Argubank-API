package br.com.ridgue.argubankapi.gateway.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @Column(nullable = false)
    private String bank;

    @Column(nullable = false)
    private Integer agency;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private Integer digit;

    @Column(nullable = false)
    private BigDecimal amount;

    @OneToMany(mappedBy = "account")
    private List<Card> cards;

    @Column(nullable = false)
    private boolean active;
}
