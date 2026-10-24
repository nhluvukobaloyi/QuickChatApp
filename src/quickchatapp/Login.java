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
  


    private String userName;
    private String password;
    private String cell;
    private String firstName;
    private String lastName;

    public boolean checkUserName(String u) {
        return u.contains("_") && u.length() <= 5;
    }

    public boolean checkPasswordComplexity(String p) {
        return p.length() >= 8 && p.matches(".*[A-Z].*") && p.matches(".*[0-9].*") && p.matches(".*[^a-zA-Z0-9].*");
    }

    public boolean checkCellPhoneNumber(String c) {
        return c.startsWith("+27") && c.length() == 12;
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
        return inUser.equals(this.userName) && inPass.equals(this.password);
    }

    public String returnLoginStatus(String inUser, String inPass) {
        if (loginUser(inUser, inPass)) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}

