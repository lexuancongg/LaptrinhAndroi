package com.example.bai1.viewmodel;

public class CategoryAtYearAmountVm {
    private String categoryName;
    private Float amount;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public CategoryAtYearAmountVm(String categoryName, Float amount) {
        this.categoryName = categoryName;
        this.amount = amount;
    }
}
