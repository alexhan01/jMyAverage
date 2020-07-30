package ui;

import model.Assignment;
import model.Course;

import java.io.*;
import java.util.*;

// Source: inspired/modified from TellerApp
// Course grade calculator application
public class CourseGradeCalculatorApp {
    private static final String COURSES_FILE = "./data/";
    private ArrayList<Course> courses = new ArrayList<>();
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
                // method that auto saves here
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThanks for using the Course Grade Calculator.");
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
    // EFFECTS: loads courses from COURSES_FILE, if that file exists;
    //          otherwise initialize accounts with default values
    //TODO: EDIT
    private void loadCourses() {
//        try {
//            // List<Course> courses = Reader.readAccounts(new JSON OBJECT COURSES_FILE))
//            // write a for loop that takes the name of each entry (e.g. cpsc210, cpsc213)
//            and assign them as new Course object
//        } catch (IOException e) {
//            init();
//        }
        init();
    }

    // MODIFIES: this
    // EFFECTS: initializes courses
    private void init() {
        cpsc210 = new Course("CPSC 210", cpsc210Assignments);
        cpsc221 = new Course("CPSC 221", cpsc221Assignments);
        courses.add(cpsc210);
        courses.add(cpsc221);
    }

    // EFFECTS: saves state of courses to COURSES_FILE
    // TODO: EDIT
    private void saveCourse(Course course) {
        String filePath = COURSES_FILE + course.getCourseName() + ".json";
        try {
            course.save(filePath, course);
        } catch (IOException e) {
            System.out.println("Unable to save courses");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddAssignment();
        } else if (command.equals("s")) {
            for (Course course : courses) {
                saveCourse(course);
            }
            System.out.println("Courses saved to file");
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
        System.out.println("\ts -> save assignments");
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
        System.out.println(selected.printAssignments());
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
        System.out.println(selected.printAssignments());
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
