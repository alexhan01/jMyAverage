package persistence;

import java.io.PrintWriter;

// Source: TellerApp
// Represents data that can be saved to file
public interface Saveable {
    // MODIFIES: printWriter
    // EFFECTS: writes the saveable to printWriter
    void save(PrintWriter printWriter);
}
