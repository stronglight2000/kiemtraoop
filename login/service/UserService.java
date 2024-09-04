package login.service;

import login.entities.User;
import login.view.Menu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    boolean isValid;
    boolean duplicateEmail;
    boolean duplicateUserName;

    public User registerAccount(Scanner scanner, ArrayList<User> users) {

        String username;
        String email;
        String password;
        System.out.println("===============ĐĂNG KÝ==================");
        do {
            System.out.println("Mời bạn nhập vào username:");
            username = scanner.nextLine();
            duplicateUserName = findUserName(users, username);
            if (duplicateUserName) {
                System.out.println("Username đã tồn tại, mời bạn nhập lại:");
            }
        }
        while (duplicateUserName);
        do {

            System.out.println("Mời bạn nhập vào email:");
            email = scanner.nextLine();
            isValid = checkEmail(email);
            duplicateEmail = findUserEmail(users, email);
            if (!isValid || duplicateEmail) {
                System.out.println("Email không hợp lệ hoặc đã tồn tại, mời bạn nhập lại");
            }
        } while (!isValid || duplicateEmail);
        do {
            System.out.println("Mời bạn nhập vào password:(password dài từ 7 đến 15 ký tự, chứa ít nhất 1 ký tự in hoa, 1 ký tự đặc biệt)");
            password = scanner.nextLine();
            isValid = checkPassword(password);
            if (!isValid) {
                System.out.println("Password không hợp lệ, mời bạn nhập lại");
            }
        } while (!isValid);
        System.out.println("Tạo tài khoản thành công");
        User user = new User(username, email, password);
        users.add(user);
        return user;


    }

    public boolean checkEmail(String email) {
        String validEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(validEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

    public boolean checkPassword(String password) {
        String validPassword = "^(?=.*[A-Z])(?=.*[.,-_;])(?=\\S+$).{7,15}$";
        Pattern pattern = Pattern.compile(validPassword);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean findUserEmail(ArrayList<User> users, String email) {
        for (User user : users) {
            if (email.equals(user.getEmail())) {
                return true;
            }
        }
        return false;

    }

    public boolean findUserName(ArrayList<User> users, String username) {
        for (User user : users) {
            if (username.equals(user.getUsername())) {
                return true;
            }
        }
        return false;

    }

    public boolean findPassWord(ArrayList<User> users, String username, String password) {
        for (User user : users) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return true;
            }
        }
        return false;

    }

    public void changePassWordWhenForget(Scanner scanner, ArrayList<User> users) {
        System.out.println("Mời bạn nhập vào email");
        String email = scanner.nextLine();
        isValid = findUserEmail(users, email);

        if (isValid) {
            for (User user : users) {
                System.out.println("Mời bạn nhập vào mật khẩu mới");
                String password = scanner.nextLine();
                user.setPassword(password);
                System.out.println("Mật khẩu đã được thay đổi.");
            }
            Menu menu = new Menu();
            menu.selectMenuPassWordWrong(scanner, users);

        } else {
            System.out.println("Chưa tồn tại tài khoản");
            Menu menu = new Menu();
            menu.selectRegisterMenu(scanner, users);
        }
    }


    public User logIn(Scanner scanner, ArrayList<User> users) {
        String username;
        String password;
//      boolean isValid;
        System.out.println("===============ĐĂNG NHẬP================");

        do {
            System.out.println("Mời bạn nhập vào username:");
            username = scanner.nextLine();
            isValid = findUserName(users, username);
            if (!isValid) {
                System.out.println("Bạn đã nhập sai tài khoản, mời bạn nhập lại:");
            }
        } while (!isValid);
        System.out.println("Mời bạn nhập vào password:");
        password = scanner.nextLine();
        isValid = findPassWord(users, username, password);
        Menu menu = new Menu();
        if (!isValid) {
            menu.selectMenuPassWordWrong(scanner, users);
        }
        else{
            System.out.println("Chào mừng" +" "+ username);
            menu.selectMenuSuccessful(scanner, users);
        }

        User user = new User(username, password);
        return user;
    }

    public void changeUserNameWhenLogIn(ArrayList<User> users, Scanner scanner) {
        User existedUser = findUser(users);
        if (existedUser != null) {
            System.out.println("Nhập username mới mà bạn muốn đổi");
            String newUserName = scanner.nextLine();
            existedUser.setUsername(newUserName);
            System.out.println("Đổi username thành công");
        }
    }

    public void changePassWordWhenLogIn(ArrayList<User> users, Scanner scanner) {
        User existedUser = findUser(users);
        if (existedUser != null) {
            System.out.println("Nhập password mới mà bạn muốn đổi");
            String newPassWord = scanner.nextLine();
            existedUser.setPassword(newPassWord);
            System.out.println("Đổi password thành công");
        }
    }

    public void changeEmailWhenLogIn(ArrayList<User> users, Scanner scanner) {
        User existedUser = findUser(users);
        if (existedUser != null) {
            System.out.println("Nhập email mới mà bạn muốn đổi");
            String newEmail = scanner.nextLine();
            existedUser.setEmail(newEmail);
            System.out.println("Đổi email thành công");
        }
    }


    public User findUser(ArrayList<User> users) {
        for (User user : users) {
            if (true) {
                return user;
            }
        }
        return null;
    }


}
