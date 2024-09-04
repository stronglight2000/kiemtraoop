package login.view;

import jdk.swing.interop.SwingInterOpUtils;
import login.entities.User;
import login.service.UserService;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    UserService userService = new UserService();
    int choice;
    private void registerMenu(){
        System.out.println("1-Đăng nhập");
        System.out.println("2-Đăng ký");
    }
    public void selectRegisterMenu(Scanner scanner, ArrayList<User> users){
        registerMenu();
        choice = Integer.parseInt(scanner.nextLine());
        switch (choice){
            case 1:
                userService.logIn(scanner,users);
                break;
            case 2:
                userService.registerAccount(scanner,users);
                userService.logIn(scanner,users);
                break;
        }
    }
    private void displayMenuPassWordWrong(){
        System.out.println("1. Đăng nhập lại");
        System.out.println("2. Quên mật khẩu");
    }
    public void selectMenuPassWordWrong(Scanner scanner, ArrayList<User> users){
        displayMenuPassWordWrong();
        choice = Integer.parseInt(scanner.nextLine());
        switch (choice){
            case 1:
                userService.logIn(scanner,users);
                break;
            case 2:
                userService.changePassWord(scanner,users);
                break;
        }

    }
    private void displayMenuSuccessful(){
        System.out.println("Mời bạn chọn các dịch vụ sau");
        System.out.println("1. Thay đổi username");
        System.out.println("2. Thay đổi email");
        System.out.println("3. Thay đổi mật khẩu");
        System.out.println("4. Đăng xuất");
        System.out.println("0. Thoát chương trình");

    }
    public void selectMenuSuccessful(Scanner scanner,ArrayList<User> users ){
        while(true){
            displayMenuSuccessful();
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    userService.changeUserName(users,scanner);
                    break;
                case 2:
                    userService.changeEmail(users,scanner);
                    break;
                case 3:
                    userService.changePassWord(users,scanner);
                    break;
                case 4:
                    selectRegisterMenu(scanner,users);
                    break;
                case 0:
                    System.exit(0);
                case 5:
                    System.out.println(users);
                    break;

            }
        }



    }




}
