/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration;

/**
 *
 * @author Student
 */

import java.util.Scanner;

public class MessageReport {

    Scanner input = new Scanner(System.in);

    // a) Display sender and recipient of all sent messages
    public void displaySendersAndRecipients() {
        if (Message.sentMessages.isEmpty()) {
            System.out.println("No sent messages yet.");
            return;
        }
        System.out.println("\n--- Senders and Recipients ---");
        for (int i = 0; i < Message.sentMessages.size(); i++) {
            System.out.println("Recipient: " + Message.sentRecipients.get(i));
            System.out.println("Message: " + Message.sentMessages.get(i));
            System.out.println();
        }
    }

    // b) Display the longest sent message
    public void displayLongestMessage() {
        if (Message.sentMessages.isEmpty()) {
            System.out.println("No sent messages yet.");
            return;
        }
        String longest = Message.sentMessages.get(0);
        for (String msg : Message.sentMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        System.out.println("\n--- Longest Message ---");
        System.out.println(longest);
    }

    // c) Search for a message ID and display recipient + message
    public void searchByMessageID() {
        System.out.print("Enter Message ID to search: ");
        String searchID = input.nextLine();
        for (int i = 0; i < Message.messageIDs.size(); i++) {
            if (Message.messageIDs.get(i).equals(searchID)) {
                System.out.println("Found!");
                System.out.println("Hash: " + Message.messageHashes.get(i));
                // Recipient is in sent OR stored list, try both
                if (i < Message.sentRecipients.size()) {
                    System.out.println("Recipient: " + Message.sentRecipients.get(i));
                } else {
                    System.out.println("Recipient: " + Message.storedRecipients.get(i - Message.sentRecipients.size()));
                }
                return;
            }
        }
        System.out.println("Message ID not found.");
    }

    // d) Search for all messages sent to a particular recipient
    public void searchByRecipient() {
        System.out.print("Enter recipient to search: ");
        String searchRec = input.nextLine();
        boolean found = false;
        for (int i = 0; i < Message.sentRecipients.size(); i++) {
            if (Message.sentRecipients.get(i).equals(searchRec)) {
                System.out.println("Message: " + Message.sentMessages.get(i));
                found = true;
            }
        }
        for (int i = 0; i < Message.storedRecipients.size(); i++) {
            if (Message.storedRecipients.get(i).equals(searchRec)) {
                System.out.println("Stored Message: " + Message.storedMessages.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No messages found for that recipient.");
        }
    }

    // e) Delete a message using its hash
    public void deleteByHash() {
        System.out.print("Enter Message Hash to delete: ");
        String searchHash = input.nextLine();
        for (int i = 0; i < Message.messageHashes.size(); i++) {
            if (Message.messageHashes.get(i).equals(searchHash)) {
                Message.messageHashes.remove(i);
                Message.messageIDs.remove(i);
                // Also try remove from sent / stored
                if (i < Message.sentMessages.size()) {
                    Message.sentMessages.remove(i);
                    Message.sentRecipients.remove(i);
                }
                System.out.println("Message successfully deleted.");
                return;
            }
        }
        System.out.println("Hash not found.");
    }

    // f) Display a full report of all sent messages
    public void displayFullReport() {
        if (Message.sentMessages.isEmpty() && Message.storedMessages.isEmpty()) {
            System.out.println("No messages to report.");
            return;
        }
        System.out.println("\n--- Full Message Report ---");
        for (int i = 0; i < Message.sentMessages.size(); i++) {
            System.out.println("Hash: " + Message.messageHashes.get(i));
            System.out.println("Recipient: " + Message.sentRecipients.get(i));
            System.out.println("Message: " + Message.sentMessages.get(i));
            System.out.println();
        }
        System.out.println("Total messages sent: " + Message.totalMessages);
    }
}
