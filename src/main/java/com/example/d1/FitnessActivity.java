package com.example.d1;

public class FitnessActivity {
    private int id;
    private String activity;
    private int duration; // minutes
    private int userId;

    public FitnessActivity(int id, String activity, int duration, int userId) {
        this.id = id;
        this.activity = activity;
        this.duration = duration;
        this.userId = userId;
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getActivity() { return activity; }
    public int getDuration() { return duration; }
    public int getUserId() { return userId; }

    public void setId(int id) { this.id = id; }
    public void setActivity(String activity) { this.activity = activity; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setUserId(int userId) { this.userId = userId; }
}

