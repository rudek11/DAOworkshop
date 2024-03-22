package pl.coderslab;

public class Main {


    public static void main(String[] args) {
        boolean isWhileRunning = true;
        while (isWhileRunning) {
            Meny.showMenu();
            switch (Meny.command) {
                case "add":
                    Meny.add();
                    break;
                case "read":
                    Meny.read();
                    break;
                case "update":
                    Meny.update();
                    break;
                case "list":
                    Meny.list();
                    break;
                case "delete":
                    Meny.delete();
                    break;
                case "exit":
                    isWhileRunning = false;
                    break;
                default:
                    System.out.println("Wrong command!");
                    Meny.line();
                    break;


            }
        }
    }
}