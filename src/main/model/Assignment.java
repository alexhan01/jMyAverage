package model;

// Represents an assignment with assignment name, grade (in %), and weight (in %)
public class Assignment {
    private String name;
    private double grade;
    private double weight;

    // EFFECTS: constructs an assignment
    public Assignment(String name, double grade, double weight) {
        this.name = name;
        this.grade = grade;
        this.weight = weight;
    }

    // MODIFIES: nothing
    // EFFECTS: returns the name of an assignment
    public String getName() {
        return name;
    }

    // MODIFIES: nothing
    // EFFECTS: returns the grade of an assignment
    public double getGrade() {
        return grade;
    }

    // MODIFIES: nothing
    // EFFECTS: returns the weight of an assignment
    public double getWeight() {
        return weight;
    }

    // REQUIRES: name cannot be and empty string;
    //           grade cannot be negative;
    //           weight cannot be negative;
    // MODIFIES: this
    // EFFECTS creates a new assignment with the given name, grade, and weight
    public Assignment createAssignment(String name, double grade, double weight) {
        return new Assignment(name, grade, weight);
    }

    /*
    // Note: for future ver.
    public void editAssignment() {
    }
     */

}
