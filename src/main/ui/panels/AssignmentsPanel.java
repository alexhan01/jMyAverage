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
                new Dimension(500,250) //TODO: Make it variable sizing
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
        initializeLabelPane();

        // Adding components to fieldPane
        initializeFieldPane();

        // Adding components to assignmentsUIPane
        assignmentsUIPane = new JPanel();
        initializeUIPane(
                assignmentsUIPane,
                new Component[]{
                        labelPane,
                        fieldPane,
                        deleteAssignmentButton,
                        createAssignmentButton
                });
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

        assignmentsList.setSelectionMode(0);
        assignmentsList.setSelectedIndex(0);
        assignmentsList.setVisibleRowCount(7);
        assignmentsList.addListSelectionListener(this);
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

    // MODIFIES: this
    // EFFECTS: initializes labelPane
    private void initializeLabelPane() {
        labelPane = new JPanel();
        labelPane.setLayout(new BoxLayout(labelPane, 0));
        labelPane.add(nameLabel);
        labelPane.add(gradeLabel);
        labelPane.add(weightLabel);
    }

    // MODIFIES: this
    // EFFECTS: initializes fieldPane
    private void initializeFieldPane() {
        fieldPane = new JPanel();
        fieldPane.setLayout(new BoxLayout(fieldPane, 0));
        fieldPane.add(nameField);
        fieldPane.add(gradeField);
        fieldPane.add(weightField);
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
