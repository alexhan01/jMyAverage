package persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Course;
import java.io.*;

// A writer that can write course data to a file
public class CourseWriter {

    // EFFECTS: writes course to a file
    public void write(String filePath, Course course) throws IOException {
        course.updateCourseInfo();
        new File(filePath);
        try (Writer writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            gson.toJson(course, writer);
        }
    }
}
