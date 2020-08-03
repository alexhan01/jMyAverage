package persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Course;
import java.io.*;

public class CourseWriter {
    private String filePath;
    private Course course;

    // constructs writer
    // TODO: add REQUIRES/MODIFIES/EFFECTS clauses
    // TODO: Write tests for this method
    public CourseWriter(String filePath, Course course) {
        this.filePath = filePath;
        this.course = course;
    }

    // TODO: add REQUIRES/MODIFIES/EFFECTS clauses
    // TODO: Write tests for this method
    public void write(String filePath, Course course) throws IOException {
        course.updateCourseInfo(); // Extract this method so it runs outside the writer.
        new File(filePath); // Keep
        try (Writer writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            gson.toJson(course, writer);
        }
    }
}
