package ui;

import model.Assignment;
import model.Course;
import persistence.*;
import java.io.*;
import java.util.*;

// Source: inspired/modified from TellerApp
// Course grade calculator application
public class CourseGradeCalculatorApp {
    private static final String COURSES_FOLDER = "./data/"; //TODO: rename this
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

    // TODO: Create course function
    public Course createCourse(String name) {
        ArrayList<Assignment> newCourseAssignments = new ArrayList<>();
        Course newCourse = new Course(name,newCourseAssignments);
        return newCourse;
    }

    // MODIFIES: this
    // EFFECTS: processes user input to run the application
    private void runCourseGradeCalculator() {
        boolean keepRunning = true;
        String command = null;
        input = new Scanner(System.in);

        try {
            loadCourses();
        } catch (Exception e) {
            init();
        }

        while (keepRunning) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepRunning = false;
                // TODO: method that auto saves here
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThanks for using the Course Grade Calculator.");
    }

    // TODO: test
    private ArrayList<String> possibleInputForCourseSelection() {
        ArrayList<String> possibleInputs = new ArrayList<>();
        for (int i = 0; i < courses.size(); i++) {
            String input = Integer.toString(i + 1);
            possibleInputs.add(input);
        }
        return possibleInputs;
    }

    // TODO: test
    // Combine the above possible inputs with the actual course names from list of courses.
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
    // TODO: completely redesign this function so it spits out based on the number of courses in the loaded file.
    private Course selectCourse() {
        String selection = "";

        // Returns list of possible inputs.
        ArrayList<String> possibleInputs = possibleInputForCourseSelection();

        // Spit out all the courses available
        while (!(possibleInputs.contains(selection))) {
            for (int i = 0; i < printPossibleCourses().size(); i++) {
                System.out.println(printPossibleCourses().get(i));
            }
            selection = input.next();
        }

        // selection parsing
        if (possibleInputs.contains(selection)) {
            int pos = Integer.parseInt(selection);
            int newPos = pos - 1;
            return courses.get(newPos);
        } else {
            return cpsc221; //TODO: change so default isn't cpsc221 but test
        }
//        if (selection.equals(some element i in the array of possible inputs))
//            return ith element in the courses list.

//        while (!(selection.equals("1") || selection.equals("2"))) {
//            System.out.println("1 for CPSC 210");
//            System.out.println("2 for CPSC 221");
//            selection = input.next();
//        }
//
//        if (selection.equals("1")) {
//            return cpsc210;
//        } else {
//            return cpsc221;
//        }
    }

    // TODO: the clauses
    private ArrayList<String> extractFilePaths() {
        // 1. Extract all necessary file paths
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

    // TODO: REQUIRES...clauses
    // EFFECTS: it sets the courses with the attributes from the .json files in data folder
    // TODO: edit
    private void loadCourses() throws IOException {
        CourseReader courseReader = new CourseReader("");
        ArrayList<String> filePaths = extractFilePaths();

        // 2. send those stream those filepaths to the coursereader
        for (String filePath : filePaths) {
            Course newCourse = courseReader.load(filePath); //TODO: change newCourse name
            courses.add(newCourse);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads courses from COURSES_FILE, if that file exists;
    //          otherwise initialize accounts with default values
//    //TODO: REMOVE
//    private void loadCourse() {
//        init();
////        try {
////            course.load(filePath);
////        } catch (FileNotFoundException e) {
////            System.out.println("Unable to locate file");
////        } catch (IOException e) {
////            System.out.println("Unable to load courses.");
////            init();
////        }
//    }

    // MODIFIES: this
    // EFFECTS: initializes courses
    private void init() {
        cpsc210 = new Course("CPSC210", cpsc210Assignments);
        cpsc221 = new Course("CPSC221", cpsc221Assignments);
        courses.add(cpsc210);
        courses.add(cpsc221);
    }

    private void saveCourse(Course course) {
        String filePath = COURSES_FOLDER + course.getCourseName() + ".json";
        CourseWriter writer = new CourseWriter(filePath, course);
        try {
            writer.write(filePath, course);
        } catch (FileNotFoundException e) { //TODO: Do I need this exception for writer?
            System.out.println("Unable to save courses.");
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
        System.out.println("\ta -> add assignment");
        System.out.println("\td -> delete assignment");
        System.out.println("\tv -> view assignments");
        System.out.println("\tg -> view course grade average");
        System.out.println("\ts -> save assignments");
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
