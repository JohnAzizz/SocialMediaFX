package com.example.saisoof;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ForgotPassword {

    public static boolean forgotpass(String username, String password) {

        try (Connection connection = DriverManager.getConnection(Main.url, Main.dbuser, Main.dbpassword)) {
            String sql = "update users set pass = ? where username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, password);
            statement.setString(2, username);

            if(statement.executeUpdate() > 0){
                return true;
            }
            else{
                ForgotPasswordController.resetpassErrorLabel.setText("Invalid info");
                return false;
            }

        }
        catch (SQLException e) {
            ForgotPasswordController.resetpassErrorLabel.setText(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}

