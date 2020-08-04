package ui;

import javax.swing.*;

// Source: inspired/modified from SpaceInvadersBase
// Represents the main window in which the Course Grade Calculator App is run
public class CourseGradeCalculatorGUI extends JFrame {

    private CourseGradeCalculatorApp app;
    private CoursesPanel cp;
    private AssignmentsPanel ap;
    private ButtonsPanel bp;

    // Constructs main windows
    // EFFECTS: sets up window in which the Course Grade Calculator app will run
    public CourseGradeCalculatorGUI() {

        app = new CourseGradeCalculatorApp();
        cp = new CoursesPanel();
        ap = new AssignmentsPanel();
        bp = new ButtonsPanel();
    }
}
