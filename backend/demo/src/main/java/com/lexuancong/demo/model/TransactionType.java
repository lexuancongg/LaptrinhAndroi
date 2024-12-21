package com.lexuancong.demo.model;

import com.lexuancong.demo.enums.Type;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "transaction_type")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionType {
    @Id
    private String type;

}
