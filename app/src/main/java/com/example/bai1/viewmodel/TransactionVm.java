package com.example.bai1.viewmodel;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class TransactionVm {
    private Long id;
    private String categoryName;
    private String typeTransaction;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long selectedCategoryId;
    private String  note;
    private BigDecimal amount;
    private String dateTime;

    public Long getSelectedCategoryId() {
        return selectedCategoryId;
    }

    public void setSelectedCategoryId(Long selectedCategoryId) {
        this.selectedCategoryId = selectedCategoryId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    public static TransactionVm fromTransactionGetVm(TransactionGetVm transactionGetVm) {
        TransactionVm transactionVm = new TransactionVm();
        transactionVm.setId(transactionGetVm.getId());
        transactionVm.setCategoryName(transactionGetVm.getCategoryName());
        transactionVm.setNote(transactionGetVm.getNote());
        transactionVm.setAmount(BigDecimal.valueOf(transactionGetVm.getAmount())); // Chuyển Float sang BigDecimal
        transactionVm.setDateTime(transactionGetVm.getDateTime());

        // Chuyển đổi selectedCategoryId nếu cần
        // transactionVm.setSelectedCategoryId(...); // Nếu có, bạn cần xử lý thêm logic cho selectedCategoryId

        return transactionVm;
    }
}
