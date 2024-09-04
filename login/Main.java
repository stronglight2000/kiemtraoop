package login;

import login.entities.User;
import login.service.UserService;
import login.view.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        menu.selectRegisterMenu(scanner,users);
        /*String choice;
        do{*/
            /*UserService userService = new UserService();
            User user = userService.registerAccount(scanner, users);
            users.add(user);
            System.out.println("Tạo tài khoản thành công");*/
           /* System.out.println("Bạn có muốn tiếp tục không (Y/N)");
            choice = scanner.nextLine();
        }while(choice.equalsIgnoreCase("Y"));*/


       /* System.out.println(users);*/

        /*Menu menu = new Menu();
        menu.registerMenu();
*/



    }

}
