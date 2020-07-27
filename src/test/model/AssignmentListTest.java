package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class AssignmentListTest {
    private AssignmentList testAssignments;
    private Assignment testAssignment1;
    private Assignment testAssignment2;

    @BeforeEach
    void setUp() {
        testAssignments = new AssignmentList();
        testAssignment1 = new Assignment(
                "Sample1",
                100,
                70);
        testAssignment2 = new Assignment(
                "Sample 2",
                50,
                30);
    }

    @Test
    void addAssignmentTest() {
        assertEquals(0, testAssignments.length());
        testAssignments.addAssignment(testAssignment1);
        assertEquals(1, testAssignments.length());
        assertTrue(testAssignments.contains(testAssignment1));
        assertFalse(testAssignments.contains(testAssignment2));
        testAssignments.addAssignment(testAssignment2);
        assertEquals(2, testAssignments.length());
        assertTrue(testAssignments.contains(testAssignment1));
        assertTrue(testAssignments.contains(testAssignment2));
    }

    @Test
    void addDuplicateAssignmentTest() {
        assertEquals(0, testAssignments.length());
        testAssignments.addAssignment(testAssignment1);
        assertEquals(1, testAssignments.length());
        testAssignments.addAssignment(testAssignment1);
        assertEquals(1, testAssignments.length());
    }

    @Test
    void deleteExistingAssignmentTest() {
        testAssignments.addAssignment(testAssignment1);
        testAssignments.addAssignment(testAssignment2);
        assertEquals(2, testAssignments.length());
        testAssignments.deleteAssignment(testAssignment1);
        assertEquals(1, testAssignments.length());
        testAssignments.deleteAssignment(testAssignment2);
        assertEquals(0, testAssignments.length());
    }

    @Test
    void deleteNonExistingAssignmentTest() {
        testAssignments.addAssignment(testAssignment1);
        assertEquals(1, testAssignments.length());
        testAssignments.deleteAssignment(testAssignment2);
        assertEquals(1, testAssignments.length());
    }

//    @Test
//    void printAssignmentTest() {
//        //String printedTestAssignment1 = testAssignments.printAssignment();
//        testAssignments.addAssignment(testAssignment1);
//        testAssignments.printAssignment();
//        testAssignments.printAssignment();
//        //assertEquals("Assignment #1 is Sample1 with a grade of 100.0 and weight 70.0.", printedTestAssignment1);
//        testAssignments.addAssignment(testAssignment2);
//        testAssignments.printAssignment();
//    }

    @Test
    void getAssignmentByIndexTest() {
        testAssignments.addAssignment(testAssignment1);
        testAssignments.addAssignment(testAssignment2);
        assertEquals(testAssignment1, testAssignments.getAssignmentByIndex(0));
        assertEquals(testAssignment2, testAssignments.getAssignmentByIndex(1));
    }
}
