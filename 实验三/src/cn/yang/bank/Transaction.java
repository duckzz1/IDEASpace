package cn.yang.bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Transaction {
    private Account account;
    private ArrayList<Account> list = new ArrayList<>();

    enum Type {
        /**
         * W  取款
         * D  存款
         * T  转账
         */
        W, D, T
    }

    private int toId;

    /**
     * 交易类型
     */
    private Type t;

    //交易量
    private BigDecimal amount;

    //交易后的余额
    private BigDecimal balance;

    //描述信息
    private String description;

    public Transaction(Account account) {
        this.account = account;
    }

    public int getToId() {
        return toId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Type getT() {
        return t;
    }

    public void setT(Type t) {
        this.t = t;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public static void main(String[] args) {
//        Transaction tran = new Transaction(new Account("aa", 1, BigDecimal.valueOf(100)));
//        tran.start();
//    }

    public void start() {
        setType();
        operation();
    }

    //交易类型
    public void setType() {
        boolean sw = true;
        while (sw) {
            System.out.println("选择交易类型 ： ");
            System.out.format("D : 存款\nW : 取款\nT : 转账\n");
            Scanner scanner = new Scanner(System.in);
            String str = scanner.next();
            int isHere = "DWT".indexOf(str);
            if (isHere == -1) {
                System.out.println("输入错误，请重试！");

            } else {
                setT(Type.valueOf(str));
                sw = false;
            }
        }


    }

    public void operation() {
        boolean sw = true;

        while (sw) {
            switch (getT()) {
                case W: {
                    withdraw();
                    sw = false;
                    break;
                }
                case D: {
                    deposit();
                    sw = false;
                    break;
                }
                case T: {
                    transfer();
                    sw = false;
                    break;
                }
                default: {
                    System.out.println("请重新输入 ：");
                    break;
                }
            }
        }
    }

    //存款
    public void deposit() {
        boolean sw = true;

        while (sw) {
            setDescription("存款");
            System.out.println("请输入存款金额 ： ");
            Scanner scanner = new Scanner(System.in);
            BigDecimal db = new BigDecimal(scanner.next());
            if (db.doubleValue() <= 0) {
                System.out.println("输入错误， 请重新输入！");
            } else {
                sw = false;
                setAmount(db);
                BigDecimal temp = db.add(account.getMoney());
                setBalance(temp);
                System.out.println("交易成功！");
                showDetail();
            }
        }
    }

    //取款
    public void withdraw() {
        boolean sw = true;
        setDescription("取款");
        while (sw) {
            System.out.println("请输入取款金额 : ");
            Scanner scanner = new Scanner(System.in);
            BigDecimal db = new BigDecimal(scanner.next());
            int s = db.compareTo(getAccount().getMoney());
            if (s > 0) {
                System.out.println("请重新试 : ");
            } else {
                sw = false;
                setAmount(db);
                setBalance(account.getMoney().subtract(db));
                System.out.println("交易成功！");
                showDetail();
            }
        }
    }

    //转账
    public void transfer() {
        boolean sw = true;
        while (sw) {
            setDescription("转账");
            System.out.println("请输入对方账号 : ");
            Scanner scanner = new Scanner(System.in);
            setToId(scanner.nextInt());
            System.out.println("请输入转账金额 : ");
            setAmount(new BigDecimal(scanner.next()));
            int s = getAmount().compareTo(getAccount().getMoney());
            if (s > 0) {
                System.out.println("请重试 : ");
            } else {
                sw = false;
                setBalance(account.getMoney().subtract(getAmount()));
                System.out.println("交易成功！");
            }
        }
    }

    public void showDetail() {
        System.out.println("*************************");
        System.out.println("账户ID : " + getAccount().getId());
        System.out.println("账户姓名 : " + getAccount().getName());
        System.out.println("交易描述 ：" + getDescription());
        if (getT() == Type.T) {
            System.out.println("对方账户ID ：" + getToId());
        }
        System.out.println("交易金额 ：" + getAmount());
        System.out.println("您的余额 : " + getBalance());
        System.out.println("*************************");
    }

}
