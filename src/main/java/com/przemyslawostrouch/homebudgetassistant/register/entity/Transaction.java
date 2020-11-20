package com.przemyslawostrouch.homebudgetassistant.register.entity;

import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Register fromRegister;
    @OneToOne
    private Register toRegister;
    @Embedded
    private TransferValue transferValue;
    private LocalDateTime transactionDateTime;
}

