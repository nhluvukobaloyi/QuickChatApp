/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quickchatapp;

/**
 *
 * @author PC
 */

   

import org.junit.Test;
import static org.junit.Assert.*;

public class MessagingTest {

    @Test
    public void testMessageIDLength() {
        String id = Messaging.StoreMessageID();
        assertEquals(10, id.length());
    }

    @Test
    public void testMessageIDIsNumbersOnly() {
        String id = Messaging.StoreMessageID();
        // Check if it only has numbers 0-9
        boolean isNumbers = id.matches("[0-9]+");
        assertTrue(isNumbers);
    }

    @Test
    public void testCreateMessageHash() {
        
        String hash = Messaging.createMessageHash("1234567890", 0, "Hello World");
        assertEquals("12:0:HELLOWORLD", hash);
    }

    @Test
    public void testCreateMessageHashWithOneWord() {
        String hash = Messaging.createMessageHash("0012345678", 1, "Hi");
        
        assertEquals("00:1:HIHI", hash);
    }

    @Test
    public void testSaveToJsonCreatesFile() {
      
        Messaging.saveToJson("1234567890", "12:0:HELLOWORLD", "+27831234567", "Hello World");
        
        java.io.File file = new java.io.File("storedMessages.json");
        assertTrue(file.exists());
    }
    
    @Test
    public void testHashIsUppercase() {
        String hash = Messaging.createMessageHash("1234567890", 2, "hello world");
        assertEquals(hash, hash.toUpperCase());
    }
}

