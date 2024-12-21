package com.lexuancong.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "customer_category")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String customerId ;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
