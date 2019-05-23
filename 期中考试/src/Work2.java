import java.util.Scanner;

public class Work2 {
    public static void main(String[] args){
        DetectPassword pwd = new DetectPassword();
        System.out.println("请输入密码 ：");
        Scanner scanner = new Scanner(System.in);

        pwd.setPassword(scanner.nextLine());

        System.out.println(pwd.detectPassword(pwd.getPassword()));
    }
}

class DetectPassword {
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public  static String detectPassword(String password) {
        String regex1 = "[0-9A-Za-z]{8,}";
        String regex2 = "(.*\\d.*\\d.*\\d.*)";

        if (password.matches(regex1) && password.matches(regex2)) {
            return "Vaild Password";
        }else {
            return "Invaild Password";
        }

    }
}