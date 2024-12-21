package com.lexuancong.demo.repository;

import com.lexuancong.demo.model.CustomerCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerCategoryRepository extends JpaRepository<CustomerCategory,Long> {
    List<CustomerCategory> findAllByCustomerId(String customerId);
}
