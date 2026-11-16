/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration;
/**
 *Message class for Quickchat
 * Handles message creation, validation, hashing & storing
 * @author Student
 */

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public final class Message {
    private final String messageID;
    private final int numMessages;
    private final String recipient;
    private final String messageText;
    private final String messageHash;
    public static int totalMessages = 0;
    public static List<String> allMessages = new ArrayList<>();
    public static List<String> storedJson = new ArrayList<>(); 
    // PART 3 - you already have these, we keep them
    public static List<String> sentMessages = new ArrayList<>();
    public static List<String> disregardedMessages = new ArrayList<>();
    public static List<String> storedMessages = new ArrayList<>();
    public static List<String> messageHashes = new ArrayList<>();
    public static List<String> messageIDs = new ArrayList<>();
    public static List<String> sentRecipients = new ArrayList<>();
    public static List<String> storedRecipients = new ArrayList<>();
    
    public Message(String recipient, String messageText, int num) {
        this.messageID = String.valueOf((long)(Math.random() * 9000000000L) + 1000000000L);
        this.recipient = recipient;
        this.messageText = messageText;
        this.numMessages = num;
        this.messageHash = createMessageHash();
    }
    // PART 3 - for tests, keep this, Main does NOT use it so it can't break Main
    public Message(String messageID, String recipient, String messageText, int num) {
        this.messageID = messageID;
        this.recipient = recipient;
        this.messageText = messageText;
        this.numMessages = num;
        this.messageHash = createMessageHash();
    }
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }
    public String checkRecipientCell() {
        if (recipient.length() <= 12 && recipient.contains("+")) {
            return "Cell phone number successfully captured";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    public String checkMessageLength() {
        if (messageText.length() > 250) {
            return "Please enter a message of less than 250 characters.";
        } else {
            return "Message sent";
        }
    }
    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String first = words[0].replaceAll("[^A-Za-z0-9]", "");
        String last = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "");
        String firstTwo = messageID.substring(0, 2);
        return firstTwo + ":" + numMessages + ":" + first.toUpperCase() + last.toUpperCase();
    }
    // SAME method name, SAME int, ONLY added the 3 lines to fill Part 3 lists
    public String SentMessage(int option) {
        if (option == 1) {
            totalMessages++;
            allMessages.add(printMessages());
            // PART 3 - fill lists so Report can use them
            sentMessages.add(messageText);
            messageHashes.add(messageHash);
            messageIDs.add(messageID);
            sentRecipients.add(recipient);
            return "Message successfully sent";
        } else if (option == 2) {
            disregardedMessages.add(messageText);
            return "Press 0 to delete the message";
        } else if (option == 3) {
            storeMessage();
            // PART 3 - fill lists
            storedMessages.add(messageText);
            storedRecipients.add(recipient);
            messageHashes.add(messageHash);
            messageIDs.add(messageID);
            return "Message successfully stored";
        }
        return "Invalid";
    }
    public String printMessages() {
        if (allMessages.isEmpty()) {
        return "MessageID: " + messageID + "\nMessageHash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + messageText;
    }
    StringBuilder sb = new StringBuilder();
    for (String m : allMessages) {
        sb.append(m).append("\n\n");
    }
    return sb.toString();
    }
    public int returnTotalMessagess() {
        return totalMessages;
    }
    public void storeMessage() {
        String json = "{\"messageID\":\"" + messageID + "\",\"messageHash\":\"" + messageHash + "\",\"recipient\":\"" + recipient + "\",\"message\":\"" + messageText + "\"}";
        storedJson.add(json);
        try {
            FileWriter file = new FileWriter("storedMessages.json");
            file.write("[\n");
            for (int i = 0; i < storedJson.size(); i++) {
                file.write(storedJson.get(i));
                if (i < storedJson.size() - 1) file.write(",\n");
            }
            file.write("\n]");
            file.close();
        } catch (IOException e) {
            System.out.println("Error storing");
        }
    }

    // PART 3
    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
    public int getNumMessages() { return numMessages; }
}