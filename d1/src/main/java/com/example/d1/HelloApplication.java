package com.example.d1;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private int loggedInUserId = -1; // store the logged-in user

    @Override
    public void start(Stage stage) {
        TabPane tabPane = new TabPane();

        // --- Login/Register Tab ---
        TextField maheIdField = new TextField();
        maheIdField.setPromptText("Mahe ID");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        TextField nameField = new TextField();
        nameField.setPromptText("Name (for registration)");

        Button loginBtn = new Button("Login");
        Button registerBtn = new Button("Register");
        Label loginStatus = new Label();

        loginBtn.setOnAction(e -> {
            String maheId = maheIdField.getText();
            String password = passwordField.getText();
            int userId = UserService.loginUser(maheId, password);
            if (userId != -1) {
                loggedInUserId = userId;
                loginStatus.setText("✅ Login successful!");
                // Load data for other tabs
                loadTabs(tabPane);
            } else {
                loginStatus.setText("❌ Login failed. Try again.");
            }
        });

        registerBtn.setOnAction(e -> {
            String maheId = maheIdField.getText();
            String password = passwordField.getText();
            String name = nameField.getText();
            boolean success = UserService.registerUser(maheId, password, name);
            loginStatus.setText(success ? "✅ Registered successfully!" : "❌ Registration failed.");
        });

        VBox loginLayout = new VBox(10, maheIdField, passwordField, nameField, loginBtn, registerBtn, loginStatus);
        Tab loginTab = new Tab("Login/Register", loginLayout);

        tabPane.getTabs().add(loginTab);

        Scene scene = new Scene(tabPane, 600, 400);
        stage.setTitle("Student Tracker Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    private void loadTabs(TabPane tabPane) {
        // --- Courses Tab ---
        ListView<String> courseList = new ListView<>();
        TextField courseName = new TextField();
        courseName.setPromptText("Course Name");
        TextField credits = new TextField();
        credits.setPromptText("Credits");
        TextField grade = new TextField();
        grade.setPromptText("Grade");
        Button addCourseBtn = new Button("Add Course");

        courseList.getItems().setAll(CourseService.getCourses(loggedInUserId));
        addCourseBtn.setOnAction(e -> {
            CourseService.addCourse(courseName.getText(), Integer.parseInt(credits.getText()), grade.getText(), loggedInUserId);
            courseList.getItems().setAll(CourseService.getCourses(loggedInUserId));
            courseName.clear(); credits.clear(); grade.clear();
        });
        VBox courseLayout = new VBox(10, courseName, credits, grade, addCourseBtn, courseList);
        Tab courseTab = new Tab("Courses", courseLayout);

        // --- Events Tab ---
        ListView<String> eventList = new ListView<>();
        TextField eventTitle = new TextField();
        eventTitle.setPromptText("Event Title");
        DatePicker eventDate = new DatePicker();
        Button addEventBtn = new Button("Add Event");

        eventList.getItems().setAll(EventService.getEvents(loggedInUserId));
        addEventBtn.setOnAction(e -> {
            EventService.addEvent(eventTitle.getText(), eventDate.getValue(), loggedInUserId);
            eventList.getItems().setAll(EventService.getEvents(loggedInUserId));
            eventTitle.clear(); eventDate.setValue(null);
        });
        VBox eventLayout = new VBox(10, eventTitle, eventDate, addEventBtn, eventList);
        Tab eventTab = new Tab("Events", eventLayout);

        // --- Fitness Tab ---
        ListView<String> fitnessList = new ListView<>();
        TextField activity = new TextField();
        activity.setPromptText("Activity");
        TextField duration = new TextField();
        duration.setPromptText("Duration (minutes)");
        Button addFitnessBtn = new Button("Add Fitness");

        fitnessList.getItems().setAll(FitnessService.getActivities(loggedInUserId));
        addFitnessBtn.setOnAction(e -> {
            FitnessService.addActivity(activity.getText(), Integer.parseInt(duration.getText()), loggedInUserId);
            fitnessList.getItems().setAll(FitnessService.getActivities(loggedInUserId));
            activity.clear(); duration.clear();
        });
        VBox fitnessLayout = new VBox(10, activity, duration, addFitnessBtn, fitnessList);
        Tab fitnessTab = new Tab("Fitness", fitnessLayout);

        // --- Notes Tab ---
        ListView<String> noteList = new ListView<>();
        TextArea noteContent = new TextArea();
        noteContent.setPromptText("Write your note here...");
        Button addNoteBtn = new Button("Add Note");

        noteList.getItems().setAll(NoteService.getNotes(loggedInUserId));
        addNoteBtn.setOnAction(e -> {
            NoteService.addNote(noteContent.getText(), loggedInUserId);
            noteList.getItems().setAll(NoteService.getNotes(loggedInUserId));
            noteContent.clear();
        });
        VBox noteLayout = new VBox(10, noteContent, addNoteBtn, noteList);
        Tab noteTab = new Tab("Notes", noteLayout);

        // --- Todos Tab ---
        ListView<String> todoList = new ListView<>();
        TextField todoTask = new TextField();
        todoTask.setPromptText("Enter task");
        Button addTodoBtn = new Button("Add Todo");

        todoList.getItems().setAll(TodoService.getTodos(loggedInUserId));
        addTodoBtn.setOnAction(e -> {
            TodoService.addTodo(todoTask.getText(), loggedInUserId);
            todoList.getItems().setAll(TodoService.getTodos(loggedInUserId));
            todoTask.clear();
        });
        VBox todoLayout = new VBox(10, todoTask, addTodoBtn, todoList);
        Tab todoTab = new Tab("Todos", todoLayout);

        // Add all tabs after login
        tabPane.getTabs().addAll(courseTab, eventTab, fitnessTab, noteTab, todoTab);
    }

    public static void main(String[] args) {
        launch();
    }
}
