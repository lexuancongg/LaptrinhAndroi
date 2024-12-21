package com.lexuancong.demo.viewmodel;

import com.lexuancong.demo.model.Transaction;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record TransactionVm(Long id,Long selectedCategoryId, String note, BigDecimal amount, String dateTime,String type) {
    public static TransactionVm fromModel(Transaction transaction,String  dateTime){
        return new TransactionVm(transaction.getId(),transaction.getCategoryId().getId(),transaction.getDescription(),transaction.getAmount(),dateTime,transaction.getTransactionType().getType());
    }
}
