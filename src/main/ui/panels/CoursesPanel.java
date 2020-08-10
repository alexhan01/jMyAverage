package ui.panels;

import model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CoursesPanel extends JPanel implements ActionListener, ListSelectionListener {

    private ArrayList<Course> courses;
    private Course selectedCourse;
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
        createCourseButton.setEnabled(false);

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

    // template for creating buttons for coursespanel
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
            //stub;
        } else if (e.getActionCommand().equals(DELETE_COURSE)) {
            //stub;
        } else if (e.getActionCommand().equals(CREATE_COURSE)) {
            //stub
        } else {
            //stub;
        }
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
