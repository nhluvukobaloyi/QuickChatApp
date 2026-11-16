/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PC
 */
package quickchatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageManagerTest {

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        MessageManager mm = new MessageManager();
        assertEquals("Did you get the cake?", mm.sentMessages.get(0));
        assertEquals("It is dinner time!", mm.sentMessages.get(1));
    }

    @Test
    public void testDisplayLongestMessage() {
        MessageManager mm = new MessageManager();
        assertEquals("Where are you? You are late! I have asked you to be on time.", mm.displayLongestMessage());
    }

    @Test
    public void testSearchForMessageID() {
        MessageManager mm = new MessageManager();
        assertEquals("It is dinner time!", mm.searchByMessageID("0838884567"));
    }

    @Test
    public void testSearchAllMessagesForRecipient() {
        MessageManager mm = new MessageManager();
        String result = mm.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteMessageUsingHash() {
        MessageManager mm = new MessageManager();
        String result = mm.deleteByHash("01:2:WHEREONTIME");
        assertEquals("Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.", result);
    }

    @Test
    public void testDisplayReport() {
        MessageManager mm = new MessageManager();
        String report = mm.displayReport();
        assertTrue(report.contains("Message Hash"));
        assertTrue(report.contains("Recipient"));
        assertTrue(report.contains("Message"));
    }
}
    

