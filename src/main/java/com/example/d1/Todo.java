package com.example.d1;

public class Todo {
    private int id;
    private String task;
    private boolean completed;
    private int userId;

    public Todo(int id, String task, boolean completed, int userId) {
        this.id = id;
        this.task = task;
        this.completed = completed;
        this.userId = userId;
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getTask() { return task; }
    public boolean isCompleted() { return completed; }
    public int getUserId() { return userId; }

    public void setId(int id) { this.id = id; }
    public void setTask(String task) { this.task = task; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public void setUserId(int userId) { this.userId = userId; }
}
