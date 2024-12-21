package com.lexuancong.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "transaction")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends ParentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String customerId;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "transactionType_Id")
    private  TransactionType transactionType;
    private String description;

}
