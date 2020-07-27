package model;

import persistence.Saveable;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;

// Source: inspired/modified from TellerApp
// Represents an course with course id, course name, and list of assignments
public class Course {
    private String courseName;                  // the course name
    private ArrayList<Assignment> assignments;  // the list of assignments under the course
    private double average;                     // course average calculated from assignments

    // REQUIRES: courseName has a non-zero length
    // EFFECTS: name of course set to courseName;
    //          assignments is the list of assignments under the course.
    public Course(String courseName, ArrayList<Assignment> assignments) {
//        id = nextCourseId++;
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
            System.out.println("Assignment deleted");

        }
    }

//    // MODIFIES: nothing
//    // EFFECTS: orderly print out all assignments in the assignmentList
//    public ArrayList<String> printAssignment() {
//        int i = 0;
//        ArrayList<String> printedAssignments = null;
//        for (Assignment assignment : assignments) {
//            printedAssignments.add(
//                    "Assignment #"
//                    + ++i
//                    + " is "
//                    + assignment.getName()
//                    + " with a grade of "
//                    + String.valueOf(assignment.getGrade())
//                    + " and weight "
//                    + String.valueOf(assignment.getWeight())
//                    + ".");
//        }
//        return printedAssignments;
//    }

    // MODIFIES: nothing
    // EFFECTS: orderly print out all assignments in the list of assignments
    public void printAssignments() {
        int i = 0;
        for (Assignment assignment : assignments) {
            System.out.println(
                    "Assignment #"
                    + ++i
                    + " is "
                    + assignment.getName()
                    + " with a grade of "
                    + String.valueOf(assignment.getGrade())
                    + " and weight "
                    + String.valueOf(assignment.getWeight())
                    + ".");
        }
    }

    // MODIFIES: nothing
    // EFFECTS: return true if assignmentList contains given assignment
    public boolean contains(Assignment assignment) {
        return assignments.contains(assignment);
    }

    // REQUIRES: i < assignmentList.length()
    // MODIFIES: nothing
    // EFFECTS: return the assignment corresponding to the given index
    public Assignment getAssignmentByIndex(int i) {
        return assignments.get(i);
        /*
        if (i < length()) {
            return assignmentList.get(i);
        } else {
            System.out.println("No assignment at the index");
            throw someException;
        }
         */
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
        if (assignments != null) {
            for (Assignment assignment : assignments) {
                average += (assignment.getGrade() * (assignment.getWeight() / 100));
            }
            return average;
        }
        return 0;
    }

    //TODO: Will add persistence later
//    @Override
//    public void save(PrintWriter printWriter) {
//
//    }
}
