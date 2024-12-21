package com.example.bai1.viewmodel;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class TransactionPostVm {
    private Long selectedCategoryId;
    private String  note;
    private BigDecimal amount;
    private String dateTime;
    private String type;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



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
}
