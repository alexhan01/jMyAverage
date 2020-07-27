package persistence;

import model.AssignmentList;
import model.Course;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

// Source: inspired/modified from TellerApp
// Reader that can read course data from a file
public class Reader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of courses parsed from file;
    //          throws IOException if an exception raised
    //          from opening/reading from file
    public static List<Course> readCourses(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns contents of file as a list of strings, each string
    //          containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of courses parsed from list of strings
    //          where each string contains data for one course
    private static List<Course> parseContent(List<String> fileContent) {
        List<Course> courses = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            courses.add(parseCourse(lineComponents));
        }

        return courses;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    private static Course parseCourse(List<String> components) {
        int nextId = Integer.parseInt(components.get(0));
        int id = Integer.parseInt(components.get(1));
        String courseName = components.get(2);
        AssignmentList assignments = ;
        return new Course(nextId, id, courseName, assignments);
    }

}
