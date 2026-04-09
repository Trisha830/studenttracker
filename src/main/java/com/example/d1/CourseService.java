package com.example.d1;




import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseService {
    public static void addCourse(String name, int credits, String grade, int userId) {
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO courses(name, credits, grade, user_id) VALUES(?, ?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setInt(2, credits);
            stmt.setString(3, grade);
            stmt.setInt(4, userId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static double calculateGPA(int userId) {
        double totalPoints = 0;
        int totalCredits = 0;
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT credits, grade FROM courses WHERE user_id=?")) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int credits = rs.getInt("credits");
                String grade = rs.getString("grade").toUpperCase();

                int gradePoints = switch (grade) {
                    case "A" -> 4;
                    case "B" -> 3;
                    case "C" -> 2;
                    case "D" -> 1;
                    default -> 0;
                };

                totalPoints += gradePoints * credits;
                totalCredits += credits;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }


    public static List<String> getCourses(int userId) {
        List<String> courses = new ArrayList<>();
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM courses WHERE user_id=?")) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                courses.add(rs.getString("name") + " (" + rs.getInt("credits") +
                        " credits, Grade: " + rs.getString("grade") + ")");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }
}

