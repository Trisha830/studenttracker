

package com.example.d1;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserService {

    // Register a new user
    public static boolean registerUser(String maheId, String password, String name) {
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO users(mahe_id, password, name) VALUES(?, ?, ?)")) {

            // Hash the password before storing
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            stmt.setString(1, maheId);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, name);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Login user and return userId if successful, else -1
    public static int loginUser(String maheId, String password) {
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT id, password FROM users WHERE mahe_id=?")) {

            stmt.setString(1, maheId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                if (BCrypt.checkpw(password, storedHash)) {
                    return rs.getInt("id"); // return userId
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // login failed
    }
}
