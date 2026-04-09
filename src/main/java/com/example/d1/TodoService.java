package com.example.d1;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoService {
    public static void addTodo(String task, int userId) {
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO todos(task, user_id) VALUES(?, ?)")) {
            stmt.setString(1, task);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int getPendingCount(int userId) {
        int count = 0;
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS cnt FROM todos WHERE user_id=?")) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static List<String> getTodos(int userId) {
        List<String> todos = new ArrayList<>();
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM todos WHERE user_id=?")) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                todos.add(rs.getString("task"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return todos;
    }
}

