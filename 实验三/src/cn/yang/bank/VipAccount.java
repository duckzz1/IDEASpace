package cn.yang.bank;

import java.math.BigDecimal;

public class VipAccount extends Account {

    VipAccount() {

    }

    VipAccount(String name, int id, BigDecimal money) {
        super(name, id, money);
    }

    public void giveMoney() {
        super.setMoney(getMoney().add(new BigDecimal("10000")));
    }
}
