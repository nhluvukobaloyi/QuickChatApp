/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package quickchatapp;
class Logintest {
    public boolean checkUserName(String userName) {
        if (userName.contains("_") && userName.length() <= 5) {
            System.out.println("Username successfully captured.");
            return true;
        } else {
            System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
            return false;
        }
    }

    public boolean checkPasswordComplexity(String password) {
        boolean hasCap = !password.equals(password.toLowerCase());
        boolean hasNum = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()_+\\-].*");
        if (password.length() >= 8 && hasCap && hasNum && hasSpecial) {
            System.out.println("Password successfully captured.");
            return true;
        } else {
            System.out.println("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.");
            return false;
        }
    }

    public boolean checkCellPhoneNumber(String cell) {
        if (cell.startsWith("+27") && cell.length() == 12) {
            System.out.println("Cell phone number successfully added.");
            return true;
        } else {
            System.out.println("Cell phone number is incorrectly formatted or does not contain an international code.");
            return false;
        }
    }

    boolean checkuserName(String userName) {
       if (userName.contains("_") && userName.length() <= 5) {
    System.out.println("Username successfully captured.");
    return true;
}
       else {
    System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
    return false;
       }
    }
    String registerUser(String userName, String password, String cell) {
        if (checkUserName(userName) && checkPasswordComplexity(password) && checkCellPhoneNumber(cell)) {
            return "User registered successfully.";
        } else {
            return "Registration failed - check your inputs.";
        }
    }

    boolean loginUser(String loginUser, String loginPass, String userName, String password) {
        return loginUser.equals(userName) && loginPass.equals(password);
    }

    String returnLoginStatus(boolean loggedIn, String firstName, String lastName) {
        if (loggedIn) {
            return "Welcome " + firstName + " " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    }