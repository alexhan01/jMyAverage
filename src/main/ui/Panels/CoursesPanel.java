package ui.Panels;

import model.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CoursesPanel extends JPanel implements ListSelectionListener {
//
////    private static final String COURSES_FOLDER = "./data/";
////    private ArrayList<Course> courses = new ArrayList<>();
////    private Course cpsc210;
////    private Course cpsc221;
////    private ArrayList<Assignment> cpsc210Assignments = new ArrayList<>();
////    private ArrayList<Assignment> cpsc221Assignments = new ArrayList<>();
//
//    private static final int APP_WIDTH = 1000;
//    private static final int APP_HEIGHT = 800;
//
//    // TODO:
//    private ArrayList<Course> courses;
//
//    private DefaultListModel coursesListModel;
//    private JList coursesList;
//    private JButton deleteCourseButton;
//    private JButton addCourseButton;
//    private JPanel buttonPane;
//    private JScrollPane coursesListScrollPane;
//
//    private static final String DELETE_COURSE = "Delete Course";
//    private static final String ADD_COURSE = "Add Course";
//
//    private JTextField courseName;
//
//    // TODO:
//    public CoursesPanel(ArrayList<Course> courses) {
//        this.courses = courses;
//        setBackground(Color.RED);
//        setPreferredSize(
//                new Dimension(APP_WIDTH, (int) (0.1 * APP_HEIGHT))
//        );
//
//        // Set Up coursesListModel
//        createCoursesListModel();
//
//        // Create the JList and put it in a scroll pane
//        createCoursesList();
//
//        // Creates Add Course Button and Input Field
//        createAddButtonAndInput();
//
//        // Creates Delete Course Button
//        createDeleteButton();
//
//        // Create Panel for Button
//        createButtonPane();
//
//        add(coursesListScrollPane, BorderLayout.CENTER);
//        add(buttonPane, BorderLayout.PAGE_END);
//    }
//
//    private void createCoursesListModel() {
//        coursesListModel = new DefaultListModel();
//        for (Course course : courses) {
//            coursesListModel.addElement(course);
//        }
//    }
//
//    private void createCoursesList() {
//        coursesList = new JList(coursesListModel);
//        coursesList.setSelectionMode(0);
//        coursesList.setSelectedIndex(0);
//        coursesList.addListSelectionListener(this);
//        coursesList.setVisibleRowCount(5);
//        coursesListScrollPane = new JScrollPane(coursesList);
//    }
//
//    private void createAddButtonAndInput() {
//        addCourseButton = new JButton(ADD_COURSE);
//        AddListener addListener = new AddListener(addCourseButton);
//        addCourseButton.setActionCommand(ADD_COURSE);
//        addCourseButton.addActionListener(addListener);
//        addCourseButton.setEnabled(false);
//
//        courseName = new JTextField(10);
//        courseName.addActionListener(addListener);
//        courseName.getDocument().addDocumentListener(addListener);
//        String name = coursesListModel.getElementAt(coursesList.getSelectedIndex()).toString();
//    }
//
//    private void createDeleteButton() {
//        deleteCourseButton = new JButton(DELETE_COURSE);
//        deleteCourseButton.setActionCommand(DELETE_COURSE);
//        deleteCourseButton.addActionListener(new DeleteListener());
//    }
//
//    private void createButtonPane() {
//        buttonPane = new JPanel();
//        buttonPane.setLayout(
//                new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
//
//        buttonPane.add(addCourseButton);
//        buttonPane.add(Box.createHorizontalStrut(5));
//        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
//        buttonPane.add(Box.createHorizontalStrut(5));
//        buttonPane.add(courseName);
//        buttonPane.add(deleteCourseButton);
//        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
//    }
//
//    @Override
//    public void valueChanged(ListSelectionEvent e) {
//        if (e.getValueIsAdjusting() == false) {
//
//            if (coursesList.getSelectedIndex() == -1) {
//                //No selection, disable fire button.
//                deleteCourseButton.setEnabled(false);
//
//            } else {
//                //Selection, enable the fire button.
//                deleteCourseButton.setEnabled(true);
//            }
//        }
//    }
//
//    class AddListener implements ActionListener, DocumentListener {
//        private boolean alreadyEnabled = false;
//        private JButton button;
//
//        public AddListener(JButton button) {
//            this.button = button;
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String name = courseName.getText();
//            Course newCourse = createCourse(name);
//            courses.add(newCourse);
//
//            int index = coursesList.getSelectedIndex();
//            if (index == -1) {
//                index = 0;
//            } else {
//                index++;
//            }
//
//            coursesListModel.insertElementAt(newCourse, index);
//
//            // Reset the text field
//            courseName.requestFocusInWindow();
//            courseName.setText("");
//
//            // Select the new item and make it visible
//            coursesList.setSelectedIndex(index);
//            coursesList.ensureIndexIsVisible(index);
//        }
//
//        // MODIFIES: this
//        // EFFECTS: creates a new course
//        private Course createCourse(String name) {
//            ArrayList<Assignment> newCourseAssignments = new ArrayList<>();
//            Course newCourse = new Course(name, newCourseAssignments);
//            return newCourse;
//        }
//
//
//        @Override
//        public void insertUpdate(DocumentEvent e) {
//            enableButton();
//        }
//
//        @Override
//        public void removeUpdate(DocumentEvent e) {
//            handleEmptyTextField(e);
//        }
//
//        @Override
//        public void changedUpdate(DocumentEvent e) {
//            if (!handleEmptyTextField(e)) {
//                enableButton();
//            }
//        }
//
//        private void enableButton() {
//            if (!alreadyEnabled) {
//                button.setEnabled(true);
//            }
//        }
//
//        private boolean handleEmptyTextField(DocumentEvent e) {
//            if (e.getDocument().getLength() <= 0) {
//                button.setEnabled(false);
//                alreadyEnabled = false;
//                return true;
//            }
//            return false;
//        }
//    }
//
//    class DeleteListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            int index = coursesList.getSelectedIndex();
//            coursesListModel.remove(index);
//
//            int size = coursesListModel.getSize();
//
//            if (size == 0) {
//                deleteCourseButton.setEnabled(false);
//            } else {
//                if (index == coursesListModel.getSize()) {
//                    index--;
//                }
//
//                coursesList.setSelectedIndex(index);
//                coursesList.ensureIndexIsVisible(index);
//            }
//        }
//    }
}
