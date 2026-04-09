
package com.example.d1;

import java.sql.Connection;

public class TestDB {
    public static void main(String[] args) {
        try (Connection conn = Database.getConnection()) {
            System.out.println("✅ Connected to PostgreSQL successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

