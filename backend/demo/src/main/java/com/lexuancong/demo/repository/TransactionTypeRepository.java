package com.lexuancong.demo.repository;

import com.lexuancong.demo.model.Transaction;
import com.lexuancong.demo.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface TransactionTypeRepository extends JpaRepository<TransactionType,String> {

}
