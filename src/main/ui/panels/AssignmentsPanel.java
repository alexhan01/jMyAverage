package ui.panels;

import model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AssignmentsPanel extends JPanel implements ListSelectionListener, ActionListener {

    // TODO: delete later temporary for testing purposes
    private Course cpsc210;
    private ArrayList<Assignment> cpsc210Assignments = new ArrayList<>();


    private Course selectedCourse;
    private JPanel assignmentsUIPane;
    private JPanel labelPane;
    private JPanel fieldPane;

    private JLabel assignmentsLabel;

    private JList assignmentsList;
    private DefaultListModel assignmentsListModel;
    private JScrollPane assignmentsListScrollPane;

    private JLabel nameLabel;
    private JLabel gradeLabel;
    private JLabel weightLabel;

    private JTextField nameField;
    private JTextField gradeField;
    private JTextField weightField;

    private JButton deleteAssignmentButton;
    private JButton createAssignmentButton;

    private static final String DELETE_ASSIGNMENT = "Delete Assignment";
    private static final String CREATE_ASSIGNMENT = "Create Assignment";

    // TODO: Stuff to think about: what if selectedCourse is null?
    public AssignmentsPanel(Course selectedCourse) {
        this.selectedCourse = selectedCourse;

        // TODO: style this panel
        setPreferredSize(
                new Dimension(500,250) //TODO: Make it variable sizing
        );
        setLayout(new BoxLayout(this,1));

        // TODO: remove this later
        cpsc210 = new Course("CPSC210", cpsc210Assignments);
        Assignment testAssignment = new Assignment("Test",90,10);
        cpsc210Assignments.add(testAssignment);

        if (selectedCourse == null) {
            testCourseSet();
        } else {
            this.selectedCourse = selectedCourse;
        }
        init();

        // TODO: initialize inner components
        init();

        // TODO: add all components
        add(assignmentsLabel);
        add(assignmentsListScrollPane);
        add(assignmentsUIPane);
    }

    private void init() {
        // Head Title
        assignmentsLabel = new JLabel("All Assignments");

        // Data
        assignmentsListModel = new DefaultListModel();
        ArrayList<String> printedAssignments = selectedCourse.printAssignments();
        for (String a : printedAssignments) {
            assignmentsListModel.addElement(a);
        }

        assignmentsList = new JList(assignmentsListModel);

        assignmentsList.setSelectionMode(0);
        assignmentsList.setSelectedIndex(0);
        assignmentsList.setVisibleRowCount(7);
        assignmentsList.addListSelectionListener(this);
        assignmentsListScrollPane = new JScrollPane(assignmentsList);

        // UIPane fields/label setup
        nameLabel = new JLabel("Assignment Name");
        gradeLabel = new JLabel("Grade (in %)");
        weightLabel = new JLabel("Weight (in %)");

        nameField = new JTextField(1);
        nameField.setSize(10,10);
        nameField.addActionListener(this);

        gradeField = new JTextField(1);
        gradeField.setSize(10,10);
        gradeField.addActionListener(this);

        weightField = new JTextField(1);
        weightField.setSize(10,10);
        weightField.addActionListener(this);

        // Buttons setup
        deleteAssignmentButton = createButton(DELETE_ASSIGNMENT);
        createAssignmentButton = createButton(CREATE_ASSIGNMENT);

        // Subsidiary Pane setup
        labelPane = new JPanel();
        labelPane.setLayout(new BoxLayout(labelPane, 0));
        labelPane.add(nameLabel);
        labelPane.add(gradeLabel);
        labelPane.add(weightLabel);

        fieldPane = new JPanel();
        fieldPane.setLayout(new BoxLayout(fieldPane, 0));
        fieldPane.add(nameField);
        fieldPane.add(gradeField);
        fieldPane.add(weightField);

        // assignmentsUIPane setup
        assignmentsUIPane = new JPanel();
        assignmentsUIPane.setLayout(new BoxLayout(assignmentsUIPane, 1));
        assignmentsUIPane.add(labelPane);
        assignmentsUIPane.add(fieldPane);
        assignmentsUIPane.add(deleteAssignmentButton);
        assignmentsUIPane.add(createAssignmentButton);
    }

    // TODO: REMOVE
    private void testCourseSet() {
        selectedCourse = cpsc210;
    }

    // template for creating buttons for courses panel
    private JButton createButton(String str) {
        JButton button = new JButton(str);
        button.setActionCommand(str);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        //stub;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
