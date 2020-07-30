package persistence;

import model.Course;
import java.io.IOException;

// Represents data that can be saved to a file
public interface Saveable {
    //TODO: EDIT COMMENTS HERE
    void save(String filePath, Course course) throws IOException;
}
