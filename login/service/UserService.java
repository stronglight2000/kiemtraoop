package login.service;

import login.entities.User;
import login.utils.Utils;
import login.view.Menu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    boolean isValid;
    User duplicateEmail;
    User duplicateUserName;
    User duplicatePassWord;

    public User registerAccount(Scanner scanner, ArrayList<User> users) {

        String username;
        String email;
        String password;
        Utils utils = new Utils();
        System.out.println("===============ĐĂNG KÝ==================");
        do {
            System.out.println("Mời bạn nhập vào username:");
            username = scanner.nextLine();
            duplicateUserName = findUserName(users, username);
            if (duplicateUserName != null) {
                System.out.println("Username đã tồn tại, mời bạn nhập lại:");
            }
        }
        while (duplicateUserName !=null);
        do {

            System.out.println("Mời bạn nhập vào email:");
            email = scanner.nextLine();
            isValid = utils.checkEmail(email);
            duplicateEmail = findUserEmail(users, email);
            if (!isValid || duplicateEmail != null) {
                System.out.println("Email không hợp lệ hoặc đã tồn tại, mời bạn nhập lại");
            }
        } while (!isValid || duplicateEmail != null);
        do {
            System.out.println("Mời bạn nhập vào password:(password dài từ 7 đến 15 ký tự, chứa ít nhất 1 ký tự in hoa, 1 ký tự đặc biệt)");
            password = scanner.nextLine();
            isValid = utils.checkPassword(password);
            if (!isValid) {
                System.out.println("Password không hợp lệ, mời bạn nhập lại");
            }
        } while (!isValid);
        System.out.println("Tạo tài khoản thành công");
        User user = new User(username, email, password);
        users.add(user);
        return user;


    }




    public User findUserEmail(ArrayList<User> users, String email) {
        for (User user : users) {
            if (email.equals(user.getEmail())) {
                return user;
            }
        }
        return null;

    }

    public User findUserName(ArrayList<User> users, String username) {
        for (User user : users) {
            if (username.equals(user.getUsername())) {
                return user;
            }
        }
        return null;

    }

    public User findPassWord(ArrayList<User> users, String username, String password) {
        for (User user : users) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;

    }

    public void changePassWordWhenForget(Scanner scanner, ArrayList<User> users) {
        System.out.println("Mời bạn nhập vào email");
        String email = scanner.nextLine();
        duplicateEmail = findUserEmail(users, email);

        if (duplicateEmail != null) {
                System.out.println("Mời bạn nhập vào mật khẩu mới");
                String password = scanner.nextLine();
                duplicateEmail.setPassword(password);
                System.out.println("Mật khẩu đã được thay đổi.");
                Menu menu = new Menu();
                menu.selectMenuPassWordWrong(scanner, users);
            }


         else {
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
                System.out.println("Mời bạn nhập vào user name:");
                username = scanner.nextLine();
                System.out.println("Mời bạn nhập vào password:");
                password = scanner.nextLine();
                duplicateUserName=findUserName(users,username);
                duplicatePassWord = findPassWord(users, username, password);
                Menu menu = new Menu();
                if (duplicatePassWord!=null && duplicateUserName !=null ) {
                    System.out.println("Chào mừng" + " " + username);
                    menu.selectMenuSuccessful(scanner, users);
                } else if(duplicatePassWord==null && duplicateUserName !=null) {
                    menu.selectMenuPassWordWrong(scanner,users);
                }else{
                    System.out.println("Tài khoản đã sai,mời bạn nhập lại ");
                }
            }while (duplicateUserName == null);



       /* do {
            System.out.println("Mời bạn nhập vào username:");
            username = scanner.nextLine();
            duplicateUserName = findUserName(users, username);
            if (duplicateUserName == null) {
                System.out.println("Bạn đã nhập sai tài khoản, mời bạn nhập lại:");
            }
        } while (duplicateUserName == null);
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
        }*/

        User user = new User(username, password);
        return user;
    }

    public void changeUserNameWhenLogIn(ArrayList<User> users, Scanner scanner) {
        System.out.println("Mời bạn nhập username để xác minh");
        String username = scanner.nextLine();
        User existedUser = findUserName(users,username);
        if (existedUser != null) {
            System.out.println("Nhập username mới mà bạn muốn đổi");
            String newUserName = scanner.nextLine();
            existedUser.setUsername(newUserName);
            System.out.println("Đổi username thành công");
        }
    }

    public void changePassWordWhenLogIn(ArrayList<User> users, Scanner scanner) {
        System.out.println("Mời bạn nhập lại tài khoản để xác minh");
        String username = scanner.nextLine();
        System.out.println("Mời bạn nhập password để xác minh");
        String password = scanner.nextLine();
        User existedPassWord = findPassWord(users,username,password);
        if (existedPassWord != null) {
            System.out.println("Nhập password mới mà bạn muốn đổi");
            String newPassWord = scanner.nextLine();
            existedPassWord.setPassword(newPassWord);
            System.out.println("Đổi password thành công");
        }
    }

    public void changeEmailWhenLogIn(ArrayList<User> users, Scanner scanner) {
        System.out.println("Mời bạn nhập email để xác minh");
        String email = scanner.nextLine();
        User existedEmail = findUserEmail(users,email);
        if (existedEmail != null) {
            System.out.println("Nhập email mới mà bạn muốn đổi");
            String newEmail = scanner.nextLine();
            existedEmail.setEmail(newEmail);
            System.out.println("Đổi email thành công");
        }
    }





}
