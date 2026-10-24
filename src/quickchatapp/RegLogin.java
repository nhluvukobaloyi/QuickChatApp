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



public class RegLogin {
    
    // Stored values
    private String userName;
    private String password;
    private String cell;
    private String firstName;
    private String lastName;

    public RegLogin() {
    }

   

    public boolean checkUserName(String userName) {
        return userName.contains("_") && userName.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        boolean hasUpper = !password.equals(password.toLowerCase());
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()_+=<>?].*");
        boolean hasLength = password.length() >= 8;
        return hasUpper && hasNumber && hasSpecial && hasLength;
    }

    public boolean checkCellPhoneNumber(String cell) {
        return cell.startsWith("+27") && cell.length() == 12;
    }

    public String registerUser(String userName, String password, String cell, String firstName, String lastName) {
        if (!checkUserName(userName)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }
        if (!checkCellPhoneNumber(cell)) {
            return "Cell phone number is incorrectly formatted or does not contain an international code.";
        }

     
        this.userName = userName;
        this.password = password;
        this.cell = cell;
        this.firstName = firstName;
        this.lastName = lastName;

        return "User registered successfully";
    }

    public boolean loginUser(String inUser, String inPass) {
        if (this.userName == null) {
            return false;
        }
        return inUser.equals(this.userName) && inPass.equals(this.password);
    }

    public String returnLoginStatus(String inUser, String inPass) {
        if (loginUser(inUser, inPass)) {
            return "Welcome " + this.firstName + " " + this.lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}