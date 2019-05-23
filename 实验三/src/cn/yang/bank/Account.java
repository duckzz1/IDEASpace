package cn.yang.bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String name;
    private int id;
    private BigDecimal money = new BigDecimal("0");
    List<Transaction> transactions = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    Account() {

    }

    Account(String name, int id) {
        this.name = name;
        this.id = id;
    }

    Account(String name, int id, BigDecimal money) {
        this.name = name;
        this.id = id;
        this.money = money;
    }

    public void showAccount() {
        System.out.println("*************************");
        System.out.println("账户ID : " + this.id);
        System.out.println("账户姓名 : " + this.name);
        System.out.println("账户余额 : " + this.money);
        System.out.println("*************************");
    }

}
