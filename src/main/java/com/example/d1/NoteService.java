

package com.example.d1;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteService {
    public static void addNote(String content, int userId) {
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO notes(content, user_id) VALUES(?, ?)")) {
            stmt.setString(1, content);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getNotes(int userId) {
        List<String> notes = new ArrayList<>();
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM notes WHERE user_id=?")) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                notes.add(rs.getString("content"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notes;
    }
}

