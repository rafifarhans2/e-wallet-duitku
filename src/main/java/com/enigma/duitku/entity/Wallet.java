package com.enigma.duitku.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "m_wallet")
@Builder(toBuilder = true)
public class Wallet {

    @Id
    @GenericGenerator(strategy = "uuid2", name= "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="wallet_id")
    private String id;

    @Column(nullable = false)
    private Double balance;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Transaction> listOfTransactions = new ArrayList<>();
}
