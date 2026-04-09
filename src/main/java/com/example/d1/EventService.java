//package com.example.d1;
//
//
//import com.example.d1.Database;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class EventService {
//    public static void addEvent(String title, java.time.LocalDate date, int userId) {
//        try (Connection conn = com.example.d1.Database.getConnection() ;
//             PreparedStatement stmt = conn.prepareStatement(
//                     "INSERT INTO events(title, date, user_id) VALUES(?, ?, ?)")) {
//            stmt.setString(1, title);
//            stmt.setDate(2, java.sql.Date.valueOf(date));
//            stmt.setInt(3, userId);
//            stmt.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static List<String> getEvents(int userId) {
//        List<String> events = new ArrayList<>();
//        try (Connection conn = com.example.d1.Database.getConnection();
//             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM events WHERE user_id=?")) {
//            stmt.setInt(1, userId);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                events.add(rs.getString("title") + " on " + rs.getDate("date"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return events;
//    }
//}
//

package com.example.d1;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventService {

    public static void addEvent(String title, LocalDate date, int userId) {
        try (
                Connection conn = Database.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO events (title, date, user_id) VALUES (?, ?, ?)"
                )
        ) {
            stmt.setString(1, title);
            stmt.setDate(2, Date.valueOf(date));
            stmt.setInt(3, userId);
            stmt.executeUpdate();

            System.out.println("✅ Event inserted");
        } catch (SQLException e) {
            System.err.println("❌ Error inserting event");
            e.printStackTrace();
        }
    }

    public static List<String> getUpcomingEvents(int userId) {

        List<String> events = new ArrayList<>();

        String sql = "SELECT title, date FROM events " +
                "WHERE user_id = ? AND date >= CURRENT_DATE";

        try (
                Connection conn = Database.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String event =
                        rs.getString("title") +
                                " on " +
                                rs.getDate("date");

                events.add(event);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return events;
    }







   public static List<String> getEvents(int userId) {
        List<String> events = new ArrayList<>();

        try (
                Connection conn = Database.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT title, date FROM events WHERE user_id = ?"
                )
        ) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                LocalDate date = rs.getDate("date").toLocalDate();
                events.add(title + " on " + date);
            }

            System.out.println(" Events loaded: " + events.size());
        } catch (SQLException e) {
            System.err.println(" Error loading events");
            e.printStackTrace();
        }

        return events;
    }
}

