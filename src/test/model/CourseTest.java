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

//    @Test
//    void printAssignmentTest() {
//        ArrayList<String> list1 = null;
//        list1.add("Assignment #1 is Sample 1 with a grade of 100.0 and weight 70.0.");
//        ArrayList<String> list2 = null;
//        list2.add("Assignment #1 is Sample 1 with a grade of 100.0 and weight 70.0.");
//        list2.add("Assignment #2 is Sample 2 with a grade of 50.0 and weight 30.0.");
//
//        testCourse.addAssignment(testAssignment1);
//        assertEquals(list1, testCourse.printAssignment());
//        testCourse.addAssignment(testAssignment2);
//        assertEquals(list2, testCourse.printAssignment());
//    }

    @Test
    void getAssignmentByIndexTest() {
        testCourse.addAssignment(testAssignment1);
        testCourse.addAssignment(testAssignment2);
        assertEquals(testAssignment1, testCourse.getAssignmentByIndex(0));
        assertEquals(testAssignment2, testCourse.getAssignmentByIndex(1));
    }
}
