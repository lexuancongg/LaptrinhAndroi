package com.lexuancong.demo.repository;

import com.lexuancong.demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    @Query("SELECT t FROM Transaction t WHERE t.customerId = :customerId AND t.createdOn BETWEEN :startDate AND :endDate")
    List<Transaction> findTransactionsByCustomerIdAndDateRange(
            @Param("customerId") String customerId,
            @Param("startDate") ZonedDateTime startDate,
            @Param("endDate") ZonedDateTime endDate);

}
