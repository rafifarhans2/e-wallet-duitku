package com.enigma.duitku.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "m_transaction")
@Builder(toBuilder = true)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "local_date", nullable = false)
    private LocalDateTime localDate;

    @Column(name = "wallet_id", length = 50)
    private String walletId;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false, length = 30)
    private String description;

    @Column(nullable = false, length = 50)
    private String receiver;

    @Column(nullable = false, length = 50)
    private String type;
}
