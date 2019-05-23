package cn.yang.bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

class Operate {
    private ArrayList<Account> list = new ArrayList<>();

    public void startOperate() {


        System.out.println("1.新建账户");
        System.out.println("2.登录账户");
        System.out.println("3.退出");

        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        switch (s) {
            case 1: {
                list.add(newAccount());
                startOperate();
                break;
            }
            case 2: {
                loginAccount();
                break;
            }
            case 3: {
                System.out.println("退出...");
                System.exit(0);
                break;
            }
            default: {
                System.out.println("输入错误，请重试！");
                startOperate();
            }
        }

    }

    public Account getAccount(int index) {
        return list.get(index);
    }

    public int getIndex(int id) {
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            Account a = list.get(i);
            if (id == a.getId()) {
                index = i;
                break;
            }
        }
        return index;
    }

    public Account newAccount() {
        System.out.println("输入账户ID ：");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        System.out.println("输入姓名 ：");
        String s = scanner.next();
        System.out.println("输入余额 ：");
        String s1 = scanner.next();
        BigDecimal db = new BigDecimal(s1);

        Account account = new Account(s, id, db);

        return account;
    }

    public void loginAccount() {
        System.out.println("输入账户ID ：");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();

        int index = getIndex(id);

        if (index != -1) {
            System.out.println("登陆成功!");
            afterLogin(index);
        } else {
            System.out.println("登陆失败！");
            loginAccount();
        }
    }

    public void afterLogin(int index) {
        System.out.println("*************************");
        System.out.println("1.查看账户明细");
        System.out.println("2.交易");
        System.out.println("3.注销");
        System.out.println("*************************");

        Scanner scanner = new Scanner(System.in);
        int select = scanner.nextInt();

        switch (select) {
            case 1: {
                showTransactionDetail(index);
                break;
            }
            case 2: {
                trans(index);
                break;
            }
            case 3: {
                logout();
                break;
            }
            default: {
                System.out.println("输入错误，请重试！");
                afterLogin(index);
                break;
            }
        }
    }

    public void showTransactionDetail(int index) {
        Account account = getAccount(index);
        if (account.transactions.size() == 0) {
            account.showAccount();
            System.out.println("交易记录为空！");
            afterLogin(index);
        } else {
            account.showAccount();
            for (int i = 0; i < account.transactions.size(); i++) {
                account.transactions.get(i).showDetail();
            }
            afterLogin(index);
        }
    }

    public void trans(int index) {
        Account account = getAccount(index);
        Transaction tr = new Transaction(account);
        tr.start();
        list.get(index).setMoney(tr.getBalance());
        list.get(index).transactions.add(tr);

        if (tr.getT() == Transaction.Type.T) {
            int toIndex = getIndex(tr.getToId());
            Account account1 = list.get(toIndex);
            Transaction trs = new Transaction(account1);
            trs.setAmount(tr.getAmount());
            trs.setT(Transaction.Type.T);
            trs.setBalance(account1.getMoney().add(tr.getAmount()));
            trs.setToId(account.getId());
            trs.setDescription("入账");
            account1.setMoney(trs.getBalance());
            account1.transactions.add(trs);
        }

        afterLogin(index);
    }

    public void logout() {
        startOperate();
    }
}
