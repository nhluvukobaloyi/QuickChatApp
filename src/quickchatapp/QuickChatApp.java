package quickchatapp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

   import java.util.Scanner;
/**
 *
 * @author PC
 */


    /**
     * @param args the command line arguments
     */




public class QuickChatApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Login logic = new Login();

        System.out.println("=== REGISTRATION ===");
        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter last name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter username: ");
        String userName = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        System.out.print("Enter cell (+27...): ");
        String cell = sc.nextLine();

        String status = logic.registerUser(userName, password, cell, firstName, lastName);
        System.out.println(status);

        if (status.toLowerCase().contains("successfully")) {
            System.out.println("=== LOGIN ===");
            System.out.print("Enter username: ");
            String inUser = sc.nextLine();
            System.out.print("Enter password: ");
            String inPass = sc.nextLine();

            boolean ok = logic.loginUser(inUser, inPass);
            System.out.println("Welcome to QuickChatApp");

           
            if (ok) {
                System.out.println("Login successful, starting QuickChat");
                Messaging m = new Messaging();
               
            } else {
                System.out.println("Login failed, cannot open QuickChat.");
            }

        } else {
            System.out.println("Registration failed, please fix and run again.");
        }
        
        sc.close();
    }
}