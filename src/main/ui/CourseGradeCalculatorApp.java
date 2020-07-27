package ui;

import model.Assignment;
import model.Course;
import java.io.*;
import java.util.*;

// Source: inspired/modified from TellerApp
// Course grade calculator application
public class CourseGradeCalculatorApp {
//    private static final COURSES_FILE = "./data/courses.txt";
    private Course cpsc210;
    private Course cpsc221;
    private ArrayList<Assignment> cpsc210Assignments = new ArrayList<>();
    private ArrayList<Assignment> cpsc221Assignments = new ArrayList<>();
    private Scanner input;

    // EFFECTS: runs the course grade calculator application
    public CourseGradeCalculatorApp() {
        runCourseGradeCalculator();
    }

    // MODIFIES: this
    // EFFECTS: processes user input to run the application
    private void runCourseGradeCalculator() {
        boolean keepRunning = true;
        String command = null;
        input = new Scanner(System.in);

        loadCourses();

        while (keepRunning) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThe End!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command to select target course
    private Course selectCourse() {
        String selection = "";

        while (!(selection.equals("1") || selection.equals("2"))) {
            System.out.println("1 for CPSC 210");
            System.out.println("2 for CPSC 221");
            selection = input.next();
        }

        if (selection.equals("1")) {
            return cpsc210;
        } else {
            return cpsc221;
        }
    }

    // MODIFIES: this
    // EFFECTS loads pre-built courses cpsc210 and cpsc221
    // NOTE: once added persistence, change
    private void loadCourses() {
        init();
    }

    // MODIFIES: this
    // EFFECTS: initializes courses
    private void init() {
        cpsc210 = new Course("CPSC 210", cpsc210Assignments);
        cpsc221 = new Course("CPSC 221", cpsc221Assignments);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddAssignment();
        } else if (command.equals("d")) {
            doDeleteAssignment();
        } else if (command.equals("v")) {
            doViewAssignment();
        } else if (command.equals("g")) {
            doViewCourseGradeAverage();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add assignment");
        System.out.println("\td -> delete assignment");
        System.out.println("\tv -> view assignments");
        System.out.println("\tg -> view course grade average");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: add an assignment to the selected course
    private void doAddAssignment() {
        Course selected = selectCourse();
        System.out.println("What is the assignment's name?");
        String name = input.next();
        System.out.println("What is your grade on this assignment?");
        double grade = Double.parseDouble(input.next());
        System.out.println("What is the weight of this assignment?");
        double weight = Double.parseDouble(input.next());
        Assignment newAssignment = new Assignment(name, grade, weight);
        selected.addAssignment(newAssignment);
        System.out.println("Assignment added successfully!");
    }

    // MODIFIES: this
    // EFFECTS: delete an assignment of a selected course
    private void doDeleteAssignment() {
        Course selected = selectCourse();
        System.out.println(
                "Here are all the assignments for the course: "
                        + selected.getCourseName()
        );
        selected.printAssignments();
        System.out.println("Please input which assignment you would like to delete");
        int index = Integer.parseInt(input.next()) - 1;
        Assignment assignmentToDelete = selected.getAssignmentByIndex(index);
        selected.deleteAssignment(assignmentToDelete);
        System.out.println("Assignment deleted successfully!");
    }

    // MODIFIES: nothing
    // EFFECTS: view all assignments of a selected course
    private void doViewAssignment() {
        Course selected = selectCourse();
        System.out.println(
                "Here are all the assignments for the course: "
                + selected.getCourseName()
        );
        selected.printAssignments();
    }

    // MODIFIES: nothing
    // EFFECTS: view the course grade average of a selected course
    private void doViewCourseGradeAverage() {
        Course selected = selectCourse();
        System.out.println(
                "Here is the course grade average for the course: "
                + selected.getCourseName()
        );
        double courseGradeAverage = selected.getAverage();
        System.out.println(courseGradeAverage);
    }

}
