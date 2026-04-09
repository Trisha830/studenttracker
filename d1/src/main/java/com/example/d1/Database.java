package com.example.d1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public static List<Course> courses = new ArrayList<>();
   public static List<Event> events = new ArrayList<>();
    public static List<FitnessActivity> fitness = new ArrayList<>();
    public static List<Note> notes = new ArrayList<>();
public static List<Todo> todos=new ArrayList<>();
    private static final String URL = "jdbc:postgresql://localhost:5432/d1";
    private static final String USER = "postgres"; // your PostgreSQL username
    private static final String PASSWORD = "postgres123"; // your PostgreSQL password

    public static Connection getConnection() throws SQLException, SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(" Connected to PostgreSQL!");
            return conn;
        } catch (Exception e) {
            System.out.println(" Connection failed!");
            e.printStackTrace();
            return null;
        }
    }
}

