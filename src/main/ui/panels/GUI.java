package ui.panels;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GUI extends JFrame {

    private ArrayList<Course> courses;
    private CoursesPanel cp;
    private AssignmentsPanel ap;
    private PersistencePanel pp;

    // TODO: delete later temporary for testing purposes
    private Course cpsc210;
    private Course cpsc221;
    private ArrayList<Assignment> cpsc210Assignments = new ArrayList<>();
    private ArrayList<Assignment> cpsc221Assignments = new ArrayList<>();

    public GUI() {
        super("Course Grade Calculator");
        setVisible(true);
        setSize(1000, 1000);
        setDefaultCloseOperation(3);
        setLayout(new FlowLayout()); // Change layout

        // TODO: Delete later
        courses = new ArrayList<>();
        cpsc210 = new Course("CPSC210", cpsc210Assignments);
        cpsc221 = new Course("CPSC221", cpsc221Assignments);
        courses.add(cpsc210);
        courses.add(cpsc221);

        cp = new CoursesPanel(courses);
        ap = new AssignmentsPanel(cp.selectedCourse);
//        pp = new PersistencePanel();

        add(cp);
        add(ap);
//        add(pp);
    }

}
