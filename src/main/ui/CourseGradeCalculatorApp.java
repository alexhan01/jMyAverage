package ui;

import model.*;
import persistence.*;

import java.io.*;
import java.util.*;

// Source: inspired/modified from TellerApp
// Course grade calculator application
public class CourseGradeCalculatorApp {

    private static final String COURSES_FOLDER = "./data/";
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
    // EFFECTS: creates a new course
    public Course createCourse(String name) {
        ArrayList<Assignment> newCourseAssignments = new ArrayList<>();
        Course newCourse = new Course(name, newCourseAssignments);
        return newCourse;
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

        System.out.println("\nThanks for using the Course Grade Calculator.");
    }

    // EFFECTS: Creates a formatted list of possible inputs for course selection
    private ArrayList<String> possibleInputForCourseSelection() {
        ArrayList<String> possibleInputs = new ArrayList<>();
        for (int i = 0; i < courses.size(); i++) {
            String input = Integer.toString(i + 1);
            possibleInputs.add(input);
        }
        return possibleInputs;
    }

    // EFFECTS: Conducts the formatting of inputs for course selection
    private ArrayList<String> printPossibleCourses() {
        ArrayList<String> printedPossibleCourses = new ArrayList<>();
        int i = 1;
        for (Course course : courses) {
            printedPossibleCourses.add(
                    Integer.toString(i)
                    + ". "
                    + course.getCourseName()
            );
            i++;
        }
        return printedPossibleCourses;
    }

    // MODIFIES: this
    // EFFECTS: processes user command to select target course
    private Course selectCourse() {
        String selection = "";

        // Returns a list of possible inputs.
        ArrayList<String> possibleInputs = possibleInputForCourseSelection();

        // Allows users to view all possible course selection
        while (!(possibleInputs.contains(selection))) {
            for (int i = 0; i < printPossibleCourses().size(); i++) {
                System.out.println(printPossibleCourses().get(i));
            }
            selection = input.next();
        }

        // Returns course based on user input
        if (possibleInputs.contains(selection)) {
            int pos = Integer.parseInt(selection);
            int newPos = pos - 1;
            return courses.get(newPos);
        } else {
            System.out.println("Such course doesn't exist - loading CPSC210 as default option.");
            return cpsc210;
        }
    }

    // EFFECTS: Creates a list of file paths to the to-be-loaded .json files.
    private ArrayList<String> extractFilePaths() {
        File f = new File(COURSES_FOLDER);
        FilenameFilter jsonFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.endsWith(".json")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        File[] files = f.listFiles(jsonFilter);
        ArrayList<String> filePaths = new ArrayList<>();

        for (File file : files) {
            String filePath = file.getAbsolutePath();
            filePaths.add(filePath);
        }
        return filePaths;
    }

    // EFFECTS: loads the courses from the data folder
    private void loadCourses() {
        CourseReader courseReader = new CourseReader();
        ArrayList<String> filePaths = extractFilePaths();

        try {
            if (!(filePaths.size() == 0)) {
                for (String filePath : filePaths) {
                    Course newCourse = courseReader.load(filePath);
                    courses.add(newCourse);
                }
            } else {
                init();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes courses
    private void init() {
        cpsc210 = new Course("CPSC210", cpsc210Assignments);
        cpsc221 = new Course("CPSC221", cpsc221Assignments);
        courses.add(cpsc210);
        courses.add(cpsc221);
    }

    // EFFECTS: saves courses to the data folder
    private void saveCourse(Course course) {
        String filePath = COURSES_FOLDER + course.getCourseName() + ".json";
        CourseWriter courseWriter = new CourseWriter();
        try {
            courseWriter.write(filePath, course);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to save courses.");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddAssignment();
        } else if (command.equals("c")) {
            doCreateCourse();
        } else if (command.equals("k")) {
            doDeleteCourse();
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
        System.out.println("\tc -> create course");
        System.out.println("\tk -> delete course");
        System.out.println("\ta -> add assignment");
        System.out.println("\td -> delete assignment");
        System.out.println("\tv -> view assignments");
        System.out.println("\tg -> view course grade average");
        System.out.println("\ts -> save courses");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: creates a course according to given name
    private void doCreateCourse() {
        System.out.println("What is the name of the course?");
        String name = input.next();
        courses.add(createCourse(name));
        System.out.println("Course successfully created!");
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
    // EFFECTS: deletes selected course
    private void doDeleteCourse() {
        System.out.println("Please select which course you would like to delete: ");
        Course selected = selectCourse();
        removeCourseFile(selected);
        courses.remove(selected);
        System.out.println("Course successfully deleted!");
    }

    // EFFECTS: removes associated .json file for the selected course
    private void removeCourseFile(Course course) {
        String filePath = COURSES_FOLDER + course.getCourseName() + ".json";
        File f = new File(filePath);
        if (f.delete()) {
            System.out.println(course.getCourseName() + " file successfully removed.");
        } else {
            System.out.println("Unable to delete that file");
        }
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
