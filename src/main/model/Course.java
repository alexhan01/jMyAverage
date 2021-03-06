package model;

import java.util.*;

// Source: inspired/modified from TellerApp
// Represents a course with course id, course name, and list of assignments
public class Course {
    private String courseName;
    private ArrayList<Assignment> assignments;
    private double average;

    // REQUIRES: courseName has a non-zero length
    // EFFECTS: name of course set to courseName;
    //          assignments is the list of assignments under the course.
    public Course(String courseName, ArrayList<Assignment> assignments) {
        this.courseName = courseName;
        this.assignments = assignments;
    }

    // MODIFIES: this
    // EFFECTS: add given assignment to the assignmentList
    public void addAssignment(Assignment assignment) {
        if (!assignments.contains(assignment)) {
            assignments.add(assignment);
        } else {
            System.out.println("Assignment already exists!");
        }
    }

    // MODIFIES: this
    // EFFECTS: delete given assignment from the assignmentList, if it exists;
    //          otherwise, notify user that assignment does not exist
    public void deleteAssignment(Assignment assignment) {
        if (!assignments.contains(assignment)) {
            System.out.println("Assignment doesn't exist!");
        } else {
            assignments.remove(assignment);
        }
    }

    // MODIFIES: nothing
    // EFFECTS: orderly print out all assignments in the list of assignments
    public ArrayList<String> printAssignments() {
        int i = 0;
        ArrayList<String> printedAssignments = new ArrayList<>();
        for (Assignment assignment : assignments) {
            printedAssignments.add(printedAssignmentStructure(assignment, i));
            i++;
        }
        return printedAssignments;
    }

    // MODIFIES: nothing
    // EFFECTS: this helper function returns printedAssignment string structure
    public String printedAssignmentStructure(Assignment assignment, int i) {
        return "Assignment #"
                + ++i + " is "
                + assignment.getName()
                + " with a grade of "
                + String.valueOf(assignment.getGrade())
                + " and weight "
                + String.valueOf(assignment.getWeight())
                + ".";
    }

    // MODIFIES: nothing
    // EFFECTS: return true if assignmentList contains given assignment
    public boolean contains(Assignment assignment) {
        return assignments.contains(assignment);
    }

    // REQUIRES: i < assignmentList.length()
    // MODIFIES: nothing
    // EFFECTS: return the assignment corresponding to the given index
    // NOTE: will add exception handling later
    public Assignment getAssignmentByIndex(int i) {
        return assignments.get(i);
    }

    // MODIFIES: nothing
    // EFFECTS: returns the course's name
    public String getCourseName() {
        return courseName;
    }

    // MODIFIES: nothing
    // EFFECTS: returns the course's list of assignments
    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    // MODIFIES: nothing
    // EFFECTS: returns the courses's most updated average
    public double getAverage() {
        return calculateAverage();
    }

    // MODIFIES: this
    // EFFECTS: calculate course average from list of assignments
    private double calculateAverage() {
        average = 0;
        for (Assignment assignment : assignments) {
            average += (assignment.getGrade() * (assignment.getWeight() / 100));
        }
        return average;
    }

    // MODIFIES: this
    // EFFECTS: Updates all course info
    public void updateCourseInfo() {
        calculateAverage();
    }
}
