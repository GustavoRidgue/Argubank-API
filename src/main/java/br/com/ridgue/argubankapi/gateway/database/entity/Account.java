package br.com.ridgue.argubankapi.gateway.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id")
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

    @OneToMany
    @JoinColumn(name = "account")
    private List<Card> cards;

    @Column(nullable = false)
    private boolean active;

    @CreationTimestamp
    private LocalDateTime createDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;
}
