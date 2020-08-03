package persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;
import java.io.*;

// A reader that can read course data from a file
public class CourseReader {

    // EFFECTS: returns a course with the attributes parsed from .json file
    public Course load(String filePath) throws IOException {
        try (Reader reader = new FileReader(filePath)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            Course newCourse = gson.fromJson(reader, Course.class);
            return newCourse;
        }
    }
}
