/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quickchatapp;

import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Messaging {

  
    private ArrayList<String> sentIDs = new ArrayList<>();
    private ArrayList<String> sentHashes = new ArrayList<>();
    private ArrayList<String> sentRecipients = new ArrayList<>();
    private ArrayList<String> sentTexts = new ArrayList<>();

    public void runMessaging() {
        Scanner K = new Scanner(System.in);
        MessageManager messagemanager = new MessageManager();

        System.out.println("Welcome to QuickChat.");

        System.out.print("How many messages do you want to send? ");
        int totalMessages = K.nextInt();
        K.nextLine();
        int sent = 0;

        int choice = 0;
        while (choice != 3) {
            System.out.println("--- MENU ---");
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

                    if (!recipient.startsWith("+27")) {
                        System.out.println("Invalid recipient - must start with +27");
                    } else if (text.length() > 250) {
                        System.out.println("Message too long - max 250 chars");
                    } else {
                        String messageID = StoreMessageID();
                        String messageHash = createMessageHash(messageID, sent, text);

                        // Save in memory + file
                        sentIDs.add(messageID);
                        sentHashes.add(messageHash);
                        sentRecipients.add(recipient);
                        sentTexts.add(text);
                        saveToJson(messageID, messageHash, recipient, text);

                        System.out.println("Message ID: " + messageID);
                        System.out.println("Message Hash: " + messageHash);
                        System.out.println("Message sent to " + recipient);
                        sent++;
                    }
                }

            } else if (choice == 2) {
               
                showOneSentMessage(K);
                System.out.println("Total messages sent: " + sent + " / " + totalMessages);

            } else if (choice == 3) {
                System.out.println("Total messages sent: " + sent);
                break;

            } else {
                System.out.println("Invalid - enter 1,2 or 3");
            }
        }
        K.close();
    }

    public static String StoreMessageID() {
        Random rand = new Random();
        String id = "";
        for (int i = 0; i < 10; i++) {
            id += rand.nextInt(10);
        }
        return id;
    }

    public static String createMessageHash(String id, int count, String message) {
        String[] words = message.trim().split("\\s+");
        String first = words[0];
        String last = words[words.length - 1];
        return (id.substring(0, 2) + ":" + count + ":" + first + last).toUpperCase();
    }

    public static void saveToJson(String id, String hash, String recipient, String message) {
        try {
            FileWriter writer = new FileWriter("storedMessages.json", true);
            writer.write(id + "|" + hash + "|" + recipient + "|" + message + "\n");
            writer.close();
            System.out.println("Saved to file");
        } catch (Exception e) {
            System.out.println("Error saving file");
        }
    }


    public void showOneSentMessage(Scanner K) {
        if (sentTexts.isEmpty()) {
            System.out.println("No messages sent yet.");
            return;
        }

        System.out.println("\n--- Recently Sent Messages ---");
        for (int i = 0; i < sentTexts.size(); i++) {
            System.out.println((i + 1) + ") To: " + sentRecipients.get(i) + " | Hash: " + sentHashes.get(i));
        }

        System.out.print("Which message would you like to select amongst the messages you sent? Enter number (1-" + sentTexts.size() + "): ");
        int pick = K.nextInt();
        K.nextLine();

        if (pick < 1 || pick > sentTexts.size()) {
            System.out.println("Invalid message number.");
            return;
        }

        int index = pick - 1;
        System.out.println("\n--- Message " + pick + "_");
        System.out.println("Message ID: " + sentIDs.get(index));
        System.out.println("Message Hash: " + sentHashes.get(index));
        System.out.println("Recipient: " + sentRecipients.get(index));
        System.out.println("Message: " + sentTexts.get(index));
    }
}
