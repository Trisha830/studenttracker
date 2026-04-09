package com.example.d1;
import java.time.LocalDate;



    public class Event {
        private int id;
        private String title;
        private LocalDate date;
        private int userId;

        public Event(int id, String title, LocalDate date, int userId) {
            this.id = id;
            this.title = title;
            this.date = date;
            this.userId = userId;
        }

        // Getters & Setters
        public int getId() { return id; }
        public String getTitle() { return title; }
        public LocalDate getDate() { return date; }
        public int getUserId() { return userId; }

        public void setId(int id) { this.id = id; }
        public void setTitle(String title) { this.title = title; }
        public void setDate(LocalDate date) { this.date = date; }
        public void setUserId(int userId) { this.userId = userId; }


    }
