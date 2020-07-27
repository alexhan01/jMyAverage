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

    /*
    // Note: for future ver.
    public void editAssignment() {
    }
     */

}
