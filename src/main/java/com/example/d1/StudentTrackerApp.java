//package com.example.d1;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class StudentTrackerApp extends Application {
//    @Override
//    public void start(Stage stage) {
//        // UI elements
//        TextField courseName = new TextField();
//        courseName.setPromptText("Course Name");
//        TextField credits = new TextField();
//        credits.setPromptText("Credits");
//        TextField grade = new TextField();
//        grade.setPromptText("Grade");
//        Button addCourseBtn = new Button("Add Course");
//        ListView<String> courseList = new ListView<>();
//
//        // Action
//        addCourseBtn.setOnAction(e -> {
//            String name = courseName.getText();
//            int creditVal = Integer.parseInt(credits.getText());
//            String gradeVal = grade.getText();
//            CourseService.addCourse(name, creditVal, gradeVal);
//            courseList.getItems().add(name + " (" + creditVal + " credits, Grade: " + gradeVal + ")");
//            courseName.clear();
//            credits.clear();
//            grade.clear();
//        });
//
//        VBox layout = new VBox(10, courseName, credits, grade, addCourseBtn, courseList);
//        Scene scene = new Scene(layout, 400, 300);
//
//       stage.setTitle("Student Tracker Dashboard");
//        stage.setScene(scene);
//       stage.show();
//    }
//
//                                                                public static void main(String[] args) {
//                                                                   launch();
//                                                                }


