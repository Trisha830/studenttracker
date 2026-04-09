package com.example.d1;

public class Note {
    private int id;
    private String content;
    private int userId;

    public Note(int id, String content, int userId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getContent() { return content; }
    public int getUserId() { return userId; }

    public void setId(int id) { this.id = id; }
    public void setContent(String content) { this.content = content; }
    public void setUserId(int userId) { this.userId = userId; }
}

