package com.example.bt_nhom;

public class Biendongsodu {
    private int thang;
    private  int money;

    public int getThang() {
        return thang;
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

    public Biendongsodu(int thang , int money){
        this.money = money;
        this.thang = thang;
    }

}
