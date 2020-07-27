package model;

import persistence.Saveable;
import java.io.PrintWriter;
import java.util.*;

// Source: inspired/modified from TellerApp
// Represents an course with course id, course name, and list of assignments
public class Course implements Saveable {
    private static int nextCourseId = 1; // tracks id of next course created
    private int id;                      // course id
    private String courseName;           // the course name
    private AssignmentList assignments;  // the list of assignments under the course

    // REQUIRES: courseName has a non-zero length
    // EFFECTS: name of course set to courseName;
    //          course id is a positive integer, globally unique;
    //          assignments is the list of assignments under the course.
    public Course(String courseName, AssignmentList assignments) {
        id = nextCourseId++;
        this.courseName = courseName;
        this.assignments = assignments;
    }

    // REQUIRES: name has a non-zero length
    // EFFECTS: constructs a course with id, name, and list of associated assignments;
    //          nextCourseId is the id of the next course to be constructed
    // NOTE: only use this constructor when constructing course from data stored in file
    public Course(int nextId, int id, String courseName, AssignmentList assignments) {
        nextCourseId = nextId;
        this.id = id;
        this.courseName = courseName;
        this.assignments = assignments;
    }

    // MODIFIES: nothing
    // EFFECTS: returns the course's id
    public int getId() {
        return id;
    }

    // MODIFIES: nothing
    // EFFECTS: returns the course's name
    public String getCourseName() {
        return courseName;
    }

    // MODIFIES: nothing
    // EFFECTS: returns the course's list of assignments
    public AssignmentList getAssignments() {
        return assignments;
    }

    @Override
    public void save(PrintWriter printWriter) {

    }
}
