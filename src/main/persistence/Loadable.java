package persistence;

import model.Course;

import java.io.*;
import java.util.*;

// Represents data that can be saved to a file
public interface Loadable {
    //TODO: EDIT COMMENTS HERE
    public List<Course> load(String filePath, Course course) throws FileNotFoundException;
}

