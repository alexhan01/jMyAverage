package ui.panels;

import model.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// Source: inspired/modified from ListDemo and SpaceInvaders
// Represents JPanel that allows user interaction with assignments
public class AssignmentsPanel extends PanelStyler implements ListSelectionListener, ActionListener {
    private Course selectedCourse;
    private JPanel assignmentsUIPane;
    private JPanel labelPane;
    private JPanel fieldPane;
    public JLabel assignmentsLabel;
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

    // EFFECTS: constructs the AssignmentsPanel
    public AssignmentsPanel(Course selectedCourse) {
        // Styles Panel
        setPreferredSize(
                new Dimension(500,250)
        );
        setLayout(new BoxLayout(this,1));

        // Initializes selectedCourse
        this.selectedCourse = selectedCourse;

        // Initializes necessary components
        init();

        // Adding necessary components
        add(assignmentsLabel);
        add(assignmentsListScrollPane);
        add(assignmentsUIPane);
    }

    // MODIFIES: this
    // EFFECTS: initializes necessary components
    private void init() {
        // Initializing Main Label
        assignmentsLabel = new JLabel("All Assignments in " + selectedCourse.getCourseName());

        // Initializing JList + JListModel
        initializeData();

        // Initializing UIPane Fields + Labels
        initializeFieldsAndLabels();

        // Initializing Buttons
        deleteAssignmentButton = createButton(DELETE_ASSIGNMENT);
        createAssignmentButton = createButton(CREATE_ASSIGNMENT);

        // Adding components to labelPane
        labelPane = new JPanel();
        initializeUIPane(
                labelPane,
                new Component[] { nameLabel, gradeLabel, weightLabel },
                0
        );

        // Adding components to fieldPane
        fieldPane = new JPanel();
        initializeUIPane(
                fieldPane,
                new Component[] { nameField, gradeField, weightField },
                0
        );

        // Adding components to assignmentsUIPane
        assignmentsUIPane = new JPanel();
        initializeUIPane(
                assignmentsUIPane,
                new Component[]{ labelPane, fieldPane, deleteAssignmentButton, createAssignmentButton },
                1
        );
    }

    // MODIFIES: this
    // EFFECTS: initializes JList + JListModel components
    public void initializeData() {
        assignmentsListModel = new DefaultListModel();
        ArrayList<String> printedAssignments = selectedCourse.printAssignments();
        for (String a : printedAssignments) {
            assignmentsListModel.addElement(a);
        }
        assignmentsList = new JList(assignmentsListModel);
        listStyler(assignmentsList);
        assignmentsListScrollPane = new JScrollPane(assignmentsList);
    }

    // MODIFIES: this
    // EFFECTS: initializes fields + labels
    private void initializeFieldsAndLabels() {
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
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (assignmentsList.getSelectedIndex() == -1) {
                deleteAssignmentButton.setEnabled(false);
            } else {
                deleteAssignmentButton.setEnabled(true);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Case 1: Delete Assignment
        if (e.getActionCommand().equals(DELETE_ASSIGNMENT)) {
            int index = assignmentsList.getSelectedIndex();
            selectedCourse.getAssignments().remove(index);
            assignmentsListModel.removeElementAt(index);
        // Case 2: Create Assignment
        } else if (e.getActionCommand().equals(CREATE_ASSIGNMENT)) {
            String name = nameField.getText();
            Double grade = Double.parseDouble(gradeField.getText());
            Double weight = Double.parseDouble(weightField.getText());
            Assignment newAssignment = new Assignment(name, grade, weight);
            selectedCourse.addAssignment(newAssignment);

            ArrayList<String> printedAssignments = selectedCourse.printAssignments();
            int finalIndex = printedAssignments.size() - 1;
            assignmentsListModel.addElement(printedAssignments.get(finalIndex));

            nameField.requestFocusInWindow();
            nameField.setText("");
            gradeField.requestFocusInWindow();
            gradeField.setText("");
            weightField.requestFocusInWindow();
            weightField.setText("");

            int lastIndex = assignmentsList.getMaxSelectionIndex();
            assignmentsList.setSelectedIndex(lastIndex);
            assignmentsList.ensureIndexIsVisible(lastIndex);
        }
    }
}
