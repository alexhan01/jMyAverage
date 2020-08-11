package ui.panels;

import model.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

// Source: inspired/modified from ListDemo and SpaceInvaders
// Represents JPanel that allows user interaction with courses
public class CoursesPanel extends PanelStyler implements ActionListener, ListSelectionListener {
    private static final String COURSES_FOLDER = "./data/";
    public ArrayList<Course> courses;
    private Course selectedCourse;
    public JList coursesList;
    private DefaultListModel coursesListModel;
    private JScrollPane coursesListScrollPane;
    private JPanel coursesUIPane;
    private JLabel coursesListLabel;
    private JLabel nameLabel;
    private JLabel averageLabel;
    public JLabel courseAverage;
    private JTextField courseNameField;
    private JButton deleteCourseButton;
    private JButton createCourseButton;
    private static final String DELETE_COURSE = "Delete Course";
    private static final String CREATE_COURSE = "Create Course";
    private static final String SELECT_COURSE = "Select Course";

    // EFFECTS: constructs the Courses Panel
    public CoursesPanel(ArrayList<Course> courses) {
        this.courses = courses;

        setPreferredSize(
                new Dimension(250,250)
        );
        setLayout(new BoxLayout(this,1));

        init();

        add(coursesListLabel);
        add(coursesListScrollPane);
        add(coursesUIPane);
    }

    // MODIFIES: this
    // EFFECTS: initializes the various components
    private void init() {
        // Initializing Main Label
        coursesListLabel = new JLabel("All Courses");

        // Initializing JList + JListModel
        initializeData();

        // Initializing selectedCourse
        selectedCourse = courses.get(0);

        // Initializing UIPane Fields + Labels
        initializeFieldsAndLabels();

        // Initializing Buttons
        deleteCourseButton = createButton(DELETE_COURSE);
        createCourseButton = createButton(CREATE_COURSE);

        // Adding Components to coursesUIPane
        coursesUIPane = new JPanel();
        initializeUIPane(
                coursesUIPane,
                new Component[]{
                        averageLabel,
                        courseAverage,
                        nameLabel,
                        courseNameField,
                        createCourseButton,
                        deleteCourseButton
                },
                1);
    }

    // MODIFIES: this
    // EFFECTS: initializes fields + labels
    private void initializeFieldsAndLabels() {
        nameLabel = new JLabel("Course Name");
        averageLabel = new JLabel("Course Average");
        courseAverage = new JLabel(String.valueOf(selectedCourse.getAverage()));
        courseNameField = new JTextField(1);
        courseNameField.setSize(50,50);
        courseNameField.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: initializes JList + JListModel components
    private void initializeData() {
        coursesListModel = new DefaultListModel();
        for (Course course : courses) {
            coursesListModel.addElement(course.getCourseName());
        }
        coursesList = new JList(coursesListModel);
        listStyler(coursesList);
        coursesListScrollPane = new JScrollPane(coursesList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(DELETE_COURSE)) {
            int index = coursesList.getSelectedIndex();
            Course deleteCourse = courses.get(index);
            removeCourseFile(deleteCourse);
            courses.remove(deleteCourse);
            coursesListModel.removeElementAt(index);
        } else if (e.getActionCommand().equals(CREATE_COURSE)) {
            String courseName = courseNameField.getText();
            courses.add(createCourse(courseName));
            coursesListModel.addElement(courseName);
            courseNameField.requestFocusInWindow();
            courseNameField.setText("");
            int lastIndex = coursesList.getMaxSelectionIndex();
            coursesList.setSelectedIndex(lastIndex);
            coursesList.ensureIndexIsVisible(lastIndex);
        }
    }

    // EFFECTS: removes associated .json file for the selected course
    private void removeCourseFile(Course course) {
        String filePath = COURSES_FOLDER + course.getCourseName() + ".json";
        File f = new File(filePath);
        if (f.delete()) {
            System.out.println(course.getCourseName() + " file successfully removed.");
        } else {
            System.out.println("Unable to delete that file");
        }
    }

    // EFFECTS: returns selectedCourse
    public Course getSelectedCourse() {
        return selectedCourse;
    }

    // MODIFIES: this
    // EFFECTS: creates a new course
    public Course createCourse(String name) {
        ArrayList<Assignment> newCourseAssignments = new ArrayList<>();
        Course newCourse = new Course(name, newCourseAssignments);
        return newCourse;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (coursesList.getSelectedIndex() == -1) {
//                selectCourseButton.setEnabled(false);
                deleteCourseButton.setEnabled(false);
            } else {
//                selectCourseButton.setEnabled(true);
                deleteCourseButton.setEnabled(true);
            }
        }
    }
}
