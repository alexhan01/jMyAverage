package persistence;

import model.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class PersistenceTest {
    ArrayList<Assignment> testAssignments;
    Course testCourse;
    String testFilePath;
    CourseWriter courseWriter;

    @BeforeEach
    void setUp() {
        testAssignments = new ArrayList<>();
        testCourse = new Course("Test", testAssignments);
        testFilePath = "./data/Test.json";
        courseWriter = new CourseWriter();
    }

    @Test
    void writeAndReadTest() {
        try {
            courseWriter.write(testFilePath, testCourse);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }

        CourseReader courseReader = new CourseReader();

        try {
            Course readCourse = courseReader.load(testFilePath);
            assertEquals("Test",readCourse.getCourseName());
            assertEquals(testAssignments, readCourse.getAssignments());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }

        File testF = new File(testFilePath);
        testF.delete();
    }
}
