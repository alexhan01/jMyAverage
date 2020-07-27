package persistence;

// Source: inspired/modified from TellerApp
// Reader that can read course data from a file
//TODO: Will add persistence later
public class Reader {
//    public static final String DELIMITER = ",";
//
//    // EFFECTS: returns a list of assignments parsed from file;
//    //          throws IOException if an exception raised
//    //          from opening/reading from file
//    public static AssignmentList readAssignments(File file) throws IOException {
//        List<String> fileContent = readFile(file);
//        return parseContent(fileContent);
//    }
//
//    // EFFECTS: returns contents of file as a list of strings, each string
//    //          containing the content of one row of the file
//    private static List<String> readFile(File file) throws IOException {
//        return Files.readAllLines(file.toPath());
//    }
//
//    // EFFECTS: returns a list of assignments parsed from list of strings
//    //          where each string contains data for one assignment
//    private static AssignmentList parseContent(List<String> fileContent) {
//        AssignmentList assignments = new AssignmentList(); //use file name as course name maybe
//
//        for (String line : fileContent) {
//            ArrayList<String> lineComponents = splitString(line);
//            assignments.addAssignment(parseAssignment(lineComponents));
//        }
//
//        return assignments;
//    }
//
//    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
//    private static ArrayList<String> splitString(String line) {
//        String[] splits = line.split(DELIMITER);
//        return new ArrayList<>(Arrays.asList(splits));
//    }
//
//    private static Assignment parseAssignment(List<String> components) {
//
//        return new Assignment();
//    }

}
