package com.example.saisoof;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.sql.*;

public class UserRegistration{

    public static boolean registerUser(String username, String password, String email, String firstN, String lastN){

        if (username.contains(" ")) {
            RegistrationController.regErrorLabel.setText("Username cannot contain spaces");
            return false;
        }

        if (!verifyEmail(email)){
            return false;
        }

        if (!passLen(password)){
            RegistrationController.regErrorLabel.setText("Password must be more than 6 characters");
            return false;
        }

        try (Connection connection = DriverManager.getConnection(Main.url, Main.dbuser, Main.dbpassword)){
            String sql = "INSERT INTO users (email, username, pass, firstN, lastN) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, firstN);
            preparedStatement.setString(5, lastN);

            try{
                if (preparedStatement.executeUpdate() > 0) {
                    return true;
                }
                else{
                    RegistrationController.regErrorLabel.setText("Registration error");
                    return false;
                }
            }
            catch(SQLIntegrityConstraintViolationException e){
                RegistrationController.regErrorLabel.setText("Username taken");
                return false;
            }

        }
        catch (SQLException e) {
            RegistrationController.regErrorLabel.setText("Registration Error");
            e.printStackTrace();
            return false;
        }
    }

    private static boolean verifyEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(emailRegex);
        Matcher m = p.matcher(email);

        if (m.matches()){
            return true;
        }
        else{
            RegistrationController.regErrorLabel.setText("Invalid email address");
            return false;
        }
    }

    public static boolean passLen(String password){
        return password.length() >= 6;
    }
}