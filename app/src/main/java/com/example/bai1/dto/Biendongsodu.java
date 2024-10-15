package com.example.bai1.dto;

public class Biendongsodu {
    private int thang;
    private int money;
    private int ngay;

    public int getThang() {
        return thang;
    }

    public int getNgay() {
        return ngay;
    }

    public void setNgay(int ngay) {
        this.ngay = ngay;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    // Constructor với tham số tháng và tiền
    public Biendongsodu(int thang, int money) {
        this.money = money;
        this.thang = thang;
    }

    // Constructor với tham số ngày và tiền, thay đổi tham số để tránh trùng
    public Biendongsodu(int ngay, int money, boolean isNgay) {
        this.money = money;
        this.ngay = ngay;
    }
}
