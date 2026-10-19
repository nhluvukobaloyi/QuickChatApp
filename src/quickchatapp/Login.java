/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quickchatapp;

/**
 *
 * @author PC
 */
class Login {
 


    // Save the user details after registration
    private String savedUsername;
    private String savedPassword;
    private String savedFirstName;
    private String savedLastName;

    // Check username
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Check password complexity
    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 
            && password.matches(".*[A-Z].*") 
            && password.matches(".*[0-9].*") 
            && password.matches(".*[!@#$%^&*()_+\\-={}:;\"'?/>.<,].*");
    }

    // Check cell phone number
    public boolean checkCellPhoneNumber(String cell) {
        return cell.matches("^\\+27[0-9]{9}$");
    }

    // Register user - POE required method
    public String registerUser(String username, String password, String cell, String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }
        if (!checkCellPhoneNumber(cell)) {
            return "Cell phone number is incorrectly formatted or does not contain an international code, please correct the number and try again.";
        }
        
        // Save details
        this.savedUsername = username;
        this.savedPassword = password;
        this.savedFirstName = firstName;
        this.savedLastName = lastName;
        
        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    // Login - POE required method
    public boolean loginUser(String username, String password) {
        return username.equals(savedUsername) && password.equals(savedPassword);
    }

    // Return login status - POE required method
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + savedFirstName + " " + savedLastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}

