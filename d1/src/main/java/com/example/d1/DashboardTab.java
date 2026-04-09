package com.example.d1;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import java.util.List;

public class DashboardTab {

    public static Tab create(int userId) {

        // ✅ Only return early IF user is not logged in
        if (userId <= 0) {
            return new Tab(
                    "Dashboard",
                    new VBox(new Label("Please log in to view your dashboard."))
            );
        }

        // ✅ Now Java knows execution continues from here
        double gpa = CourseService.calculateGPA(userId);

        List<String> upcomingEventsList =
                EventService.getUpcomingEvents(userId);
        int upcomingEventsCount = upcomingEventsList.size();

        int totalFitnessMinutes =
                FitnessService.getTotalMinutes(userId);

        int notesCount =
                NoteService.getNotes(userId).size();

        int pendingTodos =
                TodoService.getPendingCount(userId);

        Label gpaLabel = new Label("📊 GPA: " + gpa);
        Label eventsLabel =
                new Label("📅 Upcoming Events: " + upcomingEventsCount);
        Label fitnessLabel =
                new Label("🏃 Total Fitness Minutes: " + totalFitnessMinutes);
        Label notesLabel =
                new Label("📝 Notes Count: " + notesCount);
        Label todosLabel =
                new Label("✅ Pending Todos: " + pendingTodos);

        VBox layout = new VBox(
                15,
                gpaLabel,
                eventsLabel,
                fitnessLabel,
                notesLabel,
                todosLabel
        );
        layout.setStyle("-fx-padding: 20;");

        return new Tab("Dashboard", layout);
    }

}
