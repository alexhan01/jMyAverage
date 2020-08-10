package ui.panels;

import model.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CoursesPanel extends JPanel implements ActionListener, ListSelectionListener {

    public ArrayList<Course> courses;
    public Course selectedCourse;
    private JList coursesList;
    private DefaultListModel coursesListModel;
    private JScrollPane coursesListScrollPane;
    private JPanel coursesUIPane;
    private JLabel coursesListLabel;

    private JLabel nameLabel;
    private JTextField courseNameField;

    private JButton deleteCourseButton;
    private JButton createCourseButton;
    private JButton selectCourseButton;

    private static final String DELETE_COURSE = "Delete Course";
    private static final String CREATE_COURSE = "Create Course";
    private static final String SELECT_COURSE = "Select Course";

    // Constructor
    public CoursesPanel(ArrayList<Course> courses) {
        this.courses = courses;

        // TODO: Initialize Panel Stuff (fix)
        setPreferredSize(
                new Dimension(250,250) //TODO: Make it variable sizing
        );
        setLayout(new BoxLayout(this,1));

        // TODO: Initialize inner components
        init();

        // TODO: Add everything to panel
        add(coursesListLabel);
        add(coursesListScrollPane);
        add(coursesUIPane);
    }

    // TODO: REFACTOR THESE CODES
    // Initializes internal components
    private void init() {
        // Label
        coursesListLabel = new JLabel("All Courses");

        // Data
        coursesListModel = new DefaultListModel();
        for (Course course : courses) {
            coursesListModel.addElement(course.getCourseName());
        }

        coursesList = new JList(coursesListModel);
        coursesList.setSelectionMode(0);
        coursesList.setSelectedIndex(0);
        coursesList.setVisibleRowCount(7);
        coursesList.addListSelectionListener(this);
        coursesListScrollPane = new JScrollPane(coursesList);

        // Fields to create new course
        nameLabel = new JLabel("Course Name");
        courseNameField = new JTextField(1);
        courseNameField.setSize(50,50); //TODO: make it variable sizing
        courseNameField.addActionListener(this);

        // Buttons
        selectCourseButton = createButton(SELECT_COURSE);
        deleteCourseButton = createButton(DELETE_COURSE);
        createCourseButton = createButton(CREATE_COURSE);

        // Add to panel
        coursesUIPane = new JPanel();
        coursesUIPane.setLayout(new BoxLayout(coursesUIPane, BoxLayout.Y_AXIS));
//        coursesUIPane.setLayout(new FlowLayout());
        coursesUIPane.add(nameLabel);
        coursesUIPane.add(courseNameField);
        coursesUIPane.add(createCourseButton);
        coursesUIPane.add(deleteCourseButton);
        coursesUIPane.add(selectCourseButton);
    }

    // template for creating buttons for courses panel
    private JButton createButton(String str) {
        JButton button = new JButton(str);
        button.setActionCommand(str);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // If it's select
        if (e.getActionCommand().equals(SELECT_COURSE)) {
            int index = coursesList.getSelectedIndex();
            selectedCourse = courses.get(index);
        } else if (e.getActionCommand().equals(DELETE_COURSE)) {
            int index = coursesList.getSelectedIndex();
            courses.remove(index);
            coursesListModel.removeElementAt(index);
            // copy method from app class so I can delete the .json file as well.
        } else if (e.getActionCommand().equals(CREATE_COURSE)) {
            String courseName = courseNameField.getText();
            courses.add(createCourse(courseName)); //adds to courses list
            coursesListModel.addElement(courseName); //add to display

            courseNameField.requestFocusInWindow(); //reset
            courseNameField.setText(""); //reset

            int lastIndex = coursesList.getMaxSelectionIndex();
            coursesList.setSelectedIndex(lastIndex);
            coursesList.ensureIndexIsVisible(lastIndex);
        } else {
            //stub; Nothing Happens
        }
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
                selectCourseButton.setEnabled(false);
                deleteCourseButton.setEnabled(false);
            } else {
                selectCourseButton.setEnabled(true);
                deleteCourseButton.setEnabled(true);
            }
        }
    }
}
