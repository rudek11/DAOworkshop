package pl.coderslab;

import org.apache.commons.lang3.math.NumberUtils;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import java.util.Scanner;

public class Meny {
    public static String command;
    private static Scanner scanner = new Scanner(System.in);
    private static UserDao userDao = new UserDao();


    public static void showMenu(){
        System.out.println("List of accessible commands:");
        System.out.println("add\t\t( adds new user),");
        System.out.println("read\t( shows data of user with particular id)");
        System.out.println("update\t( updates data of user with particular id");
        System.out.println("delete\t( deletes user with particular id");
        System.out.println("list\t( lists all users in database)");
        System.out.println("exit\t( exits program)");
        command = scanner.nextLine();
    }

    private static User inputUser(User user){
        System.out.println("Input username:");
        user.setUserName(scanner.nextLine());
        System.out.println("Input email:");
        user.setEmail(scanner.nextLine());
        System.out.println("Input password:");
        user.setPassword(scanner.nextLine());
        return user;
    }

    public static void line(){
        System.out.println("-------------------------------------------------------------------------------------------------");
    }

    public static void add(){
        User user = new User("Alan", "r@interia.pl", "mamatata123");
        user = inputUser(user);
        user = userDao.create(user);
        if( user == null){
            System.out.println("Something went wrong");
            line();
        } else {
            System.out.println("Added successful.");
            line();
        }
    }

    public static User read(){
        System.out.println("Input id number:");
        String id = scanner.nextLine();
        if(NumberUtils.isParsable(id)) {
            User user = userDao.read(Integer.parseInt(id));
            if (user == null) {
                System.out.println("There is no user with this id.");
                line();
                return null;
            } else {
                System.out.println(user);
                line();
                return user;
            }
        } else {
            System.out.println("Wrong input!");
            line();
            return null;
        }

    }

    public static void update(){
        User user = read();
        if( user != null){
            user = inputUser(user);
            userDao.update(user);
            if( user.getId() == 0){
                System.out.println("Something went wrong");
                line();
            } else {
                System.out.println("Update successful.");
                line();
            }
        }
    }

    public static void list(){
        User[] users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        line();
    }

    public static void delete(){
        User user = read();
        if( user != null){
            System.out.println("Do you really want to delete this user? Input Y for confirmation");
            if( scanner.nextLine().equals("Y")){
                if(userDao.deleteWithConfirmation(user.getId())){
                    System.out.println("User deleted.");
                    line();
                } else {
                    System.out.println("Something went wrong!");
                    line();
                }
            } else {
                line();
            }
        }
    }

}
