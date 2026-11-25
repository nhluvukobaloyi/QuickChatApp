package quickchatapp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PC
 */


import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.file.*;

public class MessageManager {

    public ArrayList<String> sentMessages = new ArrayList<>();
    public ArrayList<String> disregardedMessages = new ArrayList<>();
    public ArrayList<String> storedMessages = new ArrayList<>();
    public ArrayList<String> messageHashes = new ArrayList<>();
    public ArrayList<String> messageIDs = new ArrayList<>();

    public ArrayList<String> allMessages = new ArrayList<>();
    public ArrayList<String> allRecipients = new ArrayList<>();
    public ArrayList<String> allSenders = new ArrayList<>();
    public ArrayList<String> allFlags = new ArrayList<>();

    public MessageManager() {
        populateArrays();
    }

    public void populateArrays() {
        sentMessages.clear();
        disregardedMessages.clear();
        storedMessages.clear();
        messageHashes.clear();
        messageIDs.clear();
        allMessages.clear();
        allRecipients.clear();
        allSenders.clear();
        allFlags.clear();

        
    
        addMessage("00:1:DIDCAKE", "00:1:DIDCAKE", "+27834557896", "+27838884567", "Did you get the cake?", "Sent");
        addMessage("01:2:WHEREONTIME", "01:2:WHEREONTIME", "+27834557896", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
        addMessage("02:3:YOHOOOGATE", "02:3:YOHOOOGATE", "+27834557896", "+27834484567", "Yohoooo, I am at your gate.", "Disregard");
        addMessage("03:4:ITTIME", "03:4:ITTIME", "+27834557896", "+27838884567", "It is dinner time!", "Sent");
        addMessage("04:5:OKYOU", "04:5:OKYOU", "+27834557896", "+27838884567", "Ok, I am leaving without you.", "Stored");
    }

    private void addMessage(String id, String hash, String sender, String recipient, String message, String flag) {
        messageIDs.add(id);
        messageHashes.add(hash);
        allMessages.add(message);
        allSenders.add(sender);
        allRecipients.add(recipient);
        allFlags.add(flag);

        if (flag.equalsIgnoreCase("Sent")) {
            sentMessages.add(message);
        } else if (flag.equalsIgnoreCase("Disregard")) {
            disregardedMessages.add(message);
        } else if (flag.equalsIgnoreCase("Stored")) {
            storedMessages.add(message);
        }
    }

  
    public String openSpecificMessage(Scanner scanner) {
        ArrayList<Integer> sentIndexes = new ArrayList<>();
        for (int i = 0; i < allMessages.size(); i++) {
            if (allFlags.get(i).equalsIgnoreCase("Sent")) {
                sentIndexes.add(i);
            }
        }
        if (sentIndexes.isEmpty()) return "No sent messages to display.";

        System.out.println("-- Sent Messages --");
        for (int j = 0; j < sentIndexes.size(); j++) {
            int i = sentIndexes.get(j);
            System.out.println((j + 1) + ") To: " + allRecipients.get(i) + " | Hash: " + messageHashes.get(i));
        }
        System.out.print("Which message would you like to select? Enter number (1-" + sentIndexes.size() + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice < 1 || choice > sentIndexes.size()) return "Invalid message number.";

        int index = sentIndexes.get(choice - 1);
        return "- Message " + choice + ""
                + "Message ID: " + messageIDs.get(index) + "_"
                + "Message Hash: " + messageHashes.get(index) + "_"
                + "Sender: " + allSenders.get(index) + "\n"
                + "Recipient: " + allRecipients.get(index) + "_"
                + "Message: " + allMessages.get(index) + "_"
                + "Flag: " + allFlags.get(index);
    }

  
    public String displaySenderAndRecipient() {
        String result = "";
        for (int i = 0; i < allMessages.size(); i++) {
            if (allFlags.get(i).equalsIgnoreCase("Stored")) {
                result += "Sender: " + allSenders.get(i) + " Recipient: " + allRecipients.get(i) + "\n";
            }
        }
        if (result.isEmpty()) return "No stored messages found.";
        return result.trim();
    }

   
    public String displayLongestMessage() {
        String longest = "";
        for (String msg : allMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        return longest;
    }

    public String searchByMessageID(String searchID) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(searchID)) {
                return "Message: " + allMessages.get(i)
                     + "\nSender: " + allSenders.get(i)
                     + "\nRecipient: " + allRecipients.get(i);
            }
        }
        return "Message ID not found.";
    }

    public String searchByRecipient(String recipient) {
        String result = "";
        for (int i = 0; i < allRecipients.size(); i++) {
            if (allRecipients.get(i).equals(recipient)) {
                result += allMessages.get(i) + "_";
            }
        }
        if (result.isEmpty()) return "No messages found for this recipient.";
        return result.trim();
    }


    public String deleteByHash(String hash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equals(hash)) {
                String deletedMsg = allMessages.get(i);
                messageHashes.remove(i);
                messageIDs.remove(i);
                allMessages.remove(i);
                allRecipients.remove(i);
                allSenders.remove(i);
                allFlags.remove(i);
                sentMessages.remove(deletedMsg);
                storedMessages.remove(deletedMsg);
                disregardedMessages.remove(deletedMsg);
                return "Message: \"" + deletedMsg + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

  
    public String displayReport() {
        String report = "=== SENT MESSAGES REPORT ===";
        for (int i = 0; i < allMessages.size(); i++) {
            if (allFlags.get(i).equalsIgnoreCase("Sent")) {
                report += "Message Hash: " + messageHashes.get(i) + "_";
                report += "Recipient: " + allRecipients.get(i) + "_";
                report += "Message: " + allMessages.get(i) + "_";
            }
        }
        return report;
    }

   
    public String readStoredMessagesFromJSON(String filePath) {
        try {
            String content = Files.readString(Path.of(filePath));
            if (content.trim().isEmpty()) return "JSON file is empty.";
            return "Stored Messages from JSON:\n" + content;
        } catch (IOException e) {
            return "Could not read JSON file: " + e.getMessage();
        }
    }

    public String storeMessagesToJSON(String filePath) {
        try {
            StringBuilder json = new StringBuilder("[_");
            for (int i = 0; i < allMessages.size(); i++) {
                if (allFlags.get(i).equalsIgnoreCase("Stored")) {
                    json.append("  {\"id\":\"").append(messageIDs.get(i))
                        .append("\", \"hash\":\"").append(messageHashes.get(i))
                        .append("\", \"sender\":\"").append(allSenders.get(i))
                        .append("\", \"recipient\":\"").append(allRecipients.get(i))
                        .append("\", \"message\":\"").append(allMessages.get(i).replace("\"", "'"))
                        .append("\", \"flag\":\"").append(allFlags.get(i)).append("\"}");
                    if (i < allMessages.size() - 1) json.append(",");
                    json.append("\n");
                }
            }
            json.append("]");
            Files.writeString(Path.of(filePath), json.toString());
            return "Stored messages successfully written to " + filePath;
        } catch (IOException e) {
            return "Could not write JSON file: " + e.getMessage();
        }
    }
}
