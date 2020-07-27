package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class AssignmentTest {
    private Assignment testAssignment;

    @BeforeEach
    void setUp() {
        testAssignment = new Assignment("Test", 100, 100);
    }

    @Test
    void constructorTest() {
        assertEquals("Test", testAssignment.getName());
        assertEquals(100, testAssignment.getGrade());
        assertEquals(100, testAssignment.getWeight());
    }
}
