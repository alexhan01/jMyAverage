package model;

import org.junit.jupiter.api.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    Course testCourse;
    ArrayList<Assignment> testAssignments;
    Assignment testAssignment1;
    Assignment testAssignment2;

    @BeforeEach
    void setUp() {
        testAssignments = new ArrayList<>();
        testAssignment1 = new Assignment(
                "Sample 1",
                100,
                70);
        testAssignment2 = new Assignment(
                "Sample 2",
                50,
                30);
        testCourse = new Course("CPSC 210", testAssignments);
    }

    @Test
    void testConstructor() {
        assertEquals("CPSC 210",testCourse.getCourseName());
        assertEquals(testAssignments,testCourse.getAssignments());
        assertEquals(0,testCourse.getAverage());
    }

    @Test
    void getAverageUpdateTest() {
        testCourse.addAssignment(testAssignment1);
        assertEquals(70, testCourse.getAverage());
        testCourse.addAssignment(testAssignment2);
        assertEquals(85, testCourse.getAverage());
        testCourse.deleteAssignment(testAssignment2);
        assertEquals(70, testCourse.getAverage());
    }

    @Test
    void addAssignmentTest() {
        assertEquals(0, testAssignments.size());
        testCourse.addAssignment(testAssignment1);
        assertEquals(1, testAssignments.size());
        assertTrue(testCourse.contains(testAssignment1));
        assertFalse(testCourse.contains(testAssignment2));
        testCourse.addAssignment(testAssignment2);
        assertEquals(2, testAssignments.size());
        assertTrue(testCourse.contains(testAssignment1));
        assertTrue(testCourse.contains(testAssignment2));
    }

    @Test
    void addDuplicateAssignmentTest() {
        assertEquals(0, testAssignments.size());
        testCourse.addAssignment(testAssignment1);
        assertEquals(1, testAssignments.size());
        testCourse.addAssignment(testAssignment1);
        assertEquals(1, testAssignments.size());
    }

    @Test
    void deleteExistingAssignmentTest() {
        testCourse.addAssignment(testAssignment1);
        testCourse.addAssignment(testAssignment2);
        assertEquals(2, testAssignments.size());
        testCourse.deleteAssignment(testAssignment1);
        assertEquals(1, testAssignments.size());
        testCourse.deleteAssignment(testAssignment2);
        assertEquals(0, testAssignments.size());
    }

    @Test
    void deleteNonExistingAssignmentTest() {
        testCourse.addAssignment(testAssignment1);
        assertEquals(1, testAssignments.size());
        testCourse.deleteAssignment(testAssignment2);
        assertEquals(1, testAssignments.size());
    }

    @Test
    void getAssignmentByIndexTest() {
        testCourse.addAssignment(testAssignment1);
        testCourse.addAssignment(testAssignment2);
        assertEquals(testAssignment1, testCourse.getAssignmentByIndex(0));
        assertEquals(testAssignment2, testCourse.getAssignmentByIndex(1));
    }

    @Test
    void printedAssignmentStructureTest() {
        testAssignments.add(testAssignment1);
        String result = testCourse.printedAssignmentStructure(testAssignment1, 0);
        assertEquals(
                "Assignment #1 is Sample 1 with a grade of 100.0 and weight 70.0.",
                result);
    }

    @Test
    void printAssignmentsTest() {
        ArrayList<String> results = new ArrayList<>();
        results = testCourse.printAssignments();
        assertTrue(results.size() == 0);
        testAssignments.add(testAssignment1);
        testAssignments.add(testAssignment2);
        results = testCourse.printAssignments();
        assertTrue(results.size() == 2);
        assertEquals(
                "Assignment #1 is Sample 1 with a grade of 100.0 and weight 70.0.",
                results.get(0));
        assertEquals(
                "Assignment #2 is Sample 2 with a grade of 50.0 and weight 30.0.",
                results.get(1));
    }
}
