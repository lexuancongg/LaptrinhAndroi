package com.lexuancong.demo.model;

import com.lexuancong.demo.enums.Type;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "categoryType")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryType {
    @Id
    private String type;
    private String description;
    @OneToMany(mappedBy = "categoryType")
    List<Category> categories = new ArrayList<>();
}
