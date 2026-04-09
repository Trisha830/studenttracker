package com.example.d1;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FitnessService {
    public static void addActivity(String activity, int duration, int userId) {
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO fitness(activity, duration, user_id) VALUES(?, ?, ?)")) {
            stmt.setString(1, activity);
            stmt.setInt(2, duration);
            stmt.setInt(3, userId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int getTotalMinutes(int userId) {
        int total = 0;
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT SUM(duration) AS total FROM fitness WHERE user_id=?")) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }


    public static List<String> getActivities(int userId) {
        List<String> activities = new ArrayList<>();
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM fitness WHERE user_id=?")) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                activities.add(rs.getString("activity") + " - " + rs.getInt("duration") + " mins");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activities;
    }
}

