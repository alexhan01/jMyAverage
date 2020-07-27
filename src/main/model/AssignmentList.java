package model;

import persistence.Saveable;

import java.io.*;
import java.util.*;

public class AssignmentList implements Saveable {
    private ArrayList<Assignment> assignmentList;

    // EFFECTS: constructs an assignment list
    public AssignmentList() {
        ArrayList<Assignment> assignmentList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add given assignment to the assignmentList
    public void addAssignment(Assignment assignment) {
        if (!contains(assignment)) {
            assignmentList.add(assignment);
        } else {
            System.out.println("Assignment already exists!");
        }
    }

    // MODIFIES: this
    // EFFECTS: delete given assignment from the assignmentList, if it exists;
    //          otherwise, notify user that assignment does not exist
    public void deleteAssignment(Assignment assignment) {
        if (!contains(assignment)) {
            System.out.println("Assignment doesn't exist!");
        } else {
            assignmentList.remove(assignment);
            System.out.println("Assignment deleted"); //TODO: delete later

        }
    }

    // MODIFIES: nothing
    // EFFECTS: orderly print out all assignments in the assignmentList
    public void printAssignment() {
        for (Assignment assignment : assignmentList) {
            System.out.println("");
        }
    }

    // MODIFIES: nothing
    // EFFECTS: return true if assignmentList contains given assignment
    public boolean contains(Assignment assignment) {
        return assignmentList.contains(assignment);
    }

    // MODIFIES: nothing
    // EFFECTS: return the length of the assignmentList
    public int length() {
        return assignmentList.size();
    }

    // MODIFIES: nothing
    // EFFECTS: return the assignment corresponding to the given index
    public Assignment getAssignmentByIndex(int i) {
        return assignmentList.get(i);
    }

    @Override
    public void save(PrintWriter printWriter) {

    }
}