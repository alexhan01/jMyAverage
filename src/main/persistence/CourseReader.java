package persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;
import java.io.*;
import java.util.*;

public class CourseReader {
    private String filePath;

    // constructor
    public CourseReader(String filePath) {
        this.filePath = filePath;
    }

    //TODO: consider moving extractfilepaths method here.

    //TODO: edit comments
    public Course load(String filePath) throws FileNotFoundException, IOException {
        try (Reader reader = new FileReader(filePath)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            Course newCourse = gson.fromJson(reader, Course.class);
            return newCourse;
        }
    }
}
