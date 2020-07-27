package model;

// Represents an assignment with assignment name, grade (in %), and weight (in %)
public class Assignment {
    private String name;
    private double grade;
    private double weight;

    // EFFECTS: constructs an assignment
    public Assignment(String name, double grade, double weight) {
        this.name = "";
        this.grade = 0;
        this.weight = 0;
    }

    // Getter for name
    // MODIFIES: nothing
    // EFFECTS: returns the name of an assignment
    public String getName() {
        return name;
    }

    // Getter for grade
    // MODIFIES: nothing
    // EFFECTS: returns the grade of an assignment
    public double getGrade() {
        return grade;
    }

    // Getter for weight
    // MODIFIES: nothing
    // EFFECTS: returns the weight of an assignment
    public double getWeight() {
        return weight;
    }

    public Assignment createAssignment(String name, double grade, double weight) {
        return new Assignment(name, grade, weight);
    }

    public void deleteAssignment() {

    }

    /*
    // Note: for future ver.
    public void editAssignment() {
    }
     */

}
