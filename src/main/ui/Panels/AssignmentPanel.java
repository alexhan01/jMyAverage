package ui.Panels;

import model.*;

import javax.swing.*;

public class AssignmentPanel {
    private Assignment assignment;
    private JLabel nameLabel;
    private JLabel gradeLabel;
    private JLabel weightLabel;
    private JTextField nameField;
    private JTextField gradeField;
    private JTextField weightField;

    public AssignmentPanel(Assignment assignment) {
        this.assignment = assignment;
        init(this.assignment);
    }

    private void init(Assignment a) {
        nameLabel = new JLabel("Assignment Name");
        gradeLabel = new JLabel("Grade");
        weightLabel = new JLabel("Weight");

        nameField = new JTextField(a.getName());
        gradeField = new JTextField(String.valueOf(a.getGrade()));
        weightField = new JTextField(String.valueOf(a.getWeight()));
    }
}
