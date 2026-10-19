/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quickchatapp;
import java.util.Scanner;
/**
 *
 * @author PC
 */
public class Messaging {
    
    public static void main(String[] args) {
        Scanner K = new Scanner(System.in);

        System.out.println("Welcome to QuickChat.");
        
        System.out.print("How many messages do you want to send? ");
        int totalMessages = K.nextInt();
        K.nextLine();
        int sent = 0;

        int choice = 0;
        while (choice != 3) {
            System.out.println("\n--- MENU ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Enter your choice: ");
            choice = K.nextInt();
            K.nextLine();

            if (choice == 1) {
                if (sent >= totalMessages) {
                    System.out.println("You have reached the limit of " + totalMessages + " messages.");
                } else {
                    System.out.print("Enter recipient (+27...): ");
                    String recipient = K.nextLine();
                    System.out.print("Enter message: ");
                    String text = K.nextLine();
                    
                    // Simple validation
                    if (!recipient.startsWith("+27")) {
                        System.out.println("Invalid recipient - must start with +27");
                    } else if (text.length() > 250) {
                        System.out.println("Message too long - max 250 chars");
                    } else {
                        System.out.println("Message sent to " + recipient);
                        sent++;
                    }
                }

            } else if (choice == 2) {
                System.out.println("Coming Soon.");

            } else if (choice == 3) {
                System.out.println("Quitting. Total sent: " + sent);
                break;

            } else {
                System.out.println("Invalid - enter 1,2 or 3");
            }
        }
        K.close();
    }

}
