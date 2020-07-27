package model;

import persistence.Saveable;
import java.io.PrintWriter;
import java.util.*;

// Source: inspired/modified from TellerApp
// Represents an course with course id, course name, and list of assignments
public class Course implements Saveable {
    private static int nextCourseId = 1;
    private int id;
    private String courseName;
    private AssignmentList assignments;

    // REQUIRES: courseName has a non-zero length
    // EFFECTS: name on
    public Course(String courseName, ArrayList<Assignment>) {
        id = nextCourseId++;
        this.courseName = courseName;


    }

    @Override
    public void save(PrintWriter printWriter) {

    }
}
