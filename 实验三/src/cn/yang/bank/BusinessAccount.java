package cn.yang.bank;

import java.math.BigDecimal;
import java.util.Scanner;

public class BusinessAccount extends Account {
    private BigDecimal loanMoney;

    BusinessAccount() {

    }

    BusinessAccount(String name, int id, BigDecimal money) {
        super(name, id, money);
    }

    public void loan() {
        System.out.println("贷款金额 :");
        Scanner scanner = new Scanner(System.in);
        this.loanMoney = new BigDecimal(scanner.next());
    }

    public void repayLoan() {
        System.out.println("还款金额 : ");
        Scanner scanner = new Scanner(System.in);
        this.loanMoney = loanMoney.subtract(new BigDecimal(scanner.next()));
    }
}
