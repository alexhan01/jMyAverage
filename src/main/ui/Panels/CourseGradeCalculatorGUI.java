package ui.Panels;

import model.*;

import javax.swing.*;
import java.util.*;

// TODO: Edit
// Source: inspired/modified from SpaceInvadersBase, ListDemo
// Represents the main window in which the Course Grade Calculator App is run
public class CourseGradeCalculatorGUI {
//
//    // TODO: from CourseGradeCalc
    private static final String COURSES_FOLDER = "./data/";
    private ArrayList<Course> courses = new ArrayList<>();
    private Course cpsc210;
    private Course cpsc221;
    private ArrayList<Assignment> cpsc210Assignments = new ArrayList<>();
    private ArrayList<Assignment> cpsc221Assignments = new ArrayList<>();
//    private Scanner input;
//
//    // TODO: GUI Components
    private JFrame mainFrame;
    private CoursesPanel coursesPanel;
//    private JPanel assignmentsPanel;
//    private JPanel coursesPanel;
//    private JPanel buttonsPanel;
//
    private static final int APP_WIDTH = 1000;
    private static final int APP_HEIGHT = 800;
//
//    // TODO: ButtonsPanel Components
//    private JButton saveButton;
//    private JButton loadButton;
//    private JButton deleteButton;
//    private JButton addButton;
//
//    // TODO: AssignmentsPanel Components
//
//    // TODO: CoursesPanel Components
//    private DefaultListModel coursesListModel;
//    private JList coursesList;
//

    public CourseGradeCalculatorGUI() {
        runGUI();
    }

    private void runGUI() {
        mainFrame = new JFrame("Course Grade Calculator");
        mainFrame.setVisible(true);
        mainFrame.setSize(APP_WIDTH, APP_HEIGHT);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        coursesPanel = new CoursesPanel(courses);
        mainFrame.add(coursesPanel);
    }

    // MODIFIES: this
    // EFFECTS: initializes courses
    private void init() {
        cpsc210 = new Course("CPSC210", cpsc210Assignments);
        cpsc221 = new Course("CPSC221", cpsc221Assignments);
        courses.add(cpsc210);
        courses.add(cpsc221);
    }
//
//    // TODO: All things related to buttons
//
//    private void initializeButtonsPanel() {
//        buttonsPanel = new JPanel();
//        buttonsPanel.setBackground(Color.GREEN);
//        buttonsPanel.setPreferredSize(
//                new Dimension((int) (0.3 * APP_WIDTH), (int) (0.9 * APP_HEIGHT))
//        );
//        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
//
//        initializeSaveButton();
//        initializeLoadButton();
//        initializeDeleteButton();
//        initializeAddButton();
//
//        buttonsPanel.add(saveButton);
//        buttonsPanel.add(loadButton);
//        buttonsPanel.add(deleteButton);
//        buttonsPanel.add(addButton);
//    }
//
//    private void initializeSaveButton() {
//        saveButton = new JButton("Save Courses");
//        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        saveButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //stub;
//            }
//        });
//    }
//
//    private void initializeLoadButton() {
//        loadButton = new JButton("Load Courses");
//        loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        loadButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //stub;
//            }
//        });
//    }
//
//    private void initializeDeleteButton() {
//        deleteButton = new JButton("Delete Selected");
//        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        deleteButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //stub
//            }
//        });
//    }
//
//    private void initializeAddButton() {
//        addButton = new JButton("Add Course/Assignment");
//        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //stub
//            }
//        });
//    }
//
//    // TODO: All things related to courses
//
//    private void initializeCoursesPanel() {
//        coursesPanel = new JPanel();
//        coursesPanel.setBackground(Color.RED);
//        coursesPanel.setPreferredSize(
//                new Dimension(APP_WIDTH, (int) (0.1 * APP_HEIGHT))
//        );
//
//        // Setup ListModel
//        coursesListModel = new DefaultListModel();
//        for (Course course : courses) {
//            coursesListModel.addElement(course);
//        }
//
//        // Create the JList and put it in a scroll pane
//        coursesList = new JList(coursesListModel);
//        coursesList.setSelectionMode(0);
//        coursesList.setSelectedIndex(0);
//        coursesList.addListSelectionListener(this);
//        coursesList.setVisibleRowCount(5);
//        JScrollPane coursesListScrollPane = new JScrollPane(coursesList);
//
//    }
//
//    // TODO: All things related to assignments
//
//    private void initializeAssignmentsPanel() {
//        assignmentsPanel = new JPanel();
//        assignmentsPanel.setBackground(Color.PINK);
//        assignmentsPanel.setPreferredSize(
//                new Dimension((int) (0.7 * APP_WIDTH), (int) (0.9 * APP_HEIGHT))
//        );
//    }
}
