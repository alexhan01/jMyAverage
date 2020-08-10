package ui.panels;

import model.*;
import persistence.CourseReader;
import persistence.CourseWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.sound.sampled.*;

// Source: inspired/modified from ListDemo and SpaceInvaders
// Course grade calculator application graphic user interface
public class GUI extends JFrame implements ActionListener {
    private static final String COURSES_FOLDER = "./data/";
    private ArrayList<Course> courses = new ArrayList<>();
    private Course selectedCourse;
    private CoursesPanel cp;
    private AssignmentsPanel ap;
    private JPanel persistencePanel;
    private JButton loadButton;
    private JButton saveButton;
    private static final String LOAD_DATA = "Load Data";
    private static final String SAVE_DATA = "Save Data";
    private static final String MUSIC = "Play Music";
    private JButton playMusic;
    private Course cpsc210;
    private Course cpsc221;
    private ArrayList<Assignment> cpsc210Assignments = new ArrayList<>();
    private ArrayList<Assignment> cpsc221Assignments = new ArrayList<>();

    // EFFECTS: constructs the graphical user interface
    public GUI() {
        super("Course Grade Calculator");
        setSize(1500, 600);
        setDefaultCloseOperation(3);
        setLayout(new FlowLayout());

        loadCourses();

        cp = new CoursesPanel(courses);
        selectedCourse = cp.getSelectedCourse();
        ap = new AssignmentsPanel(selectedCourse);

        initializePersistence();

        add(cp);
        add(ap);
        add(persistencePanel);

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: allows save and load functionality
    private void initializePersistence() {
        loadButton = createButton(LOAD_DATA);
        saveButton = createButton(SAVE_DATA);
        playMusic = createButton(MUSIC);

        persistencePanel = new JPanel();
        persistencePanel.setLayout(new BoxLayout(persistencePanel, 1));
        persistencePanel.add(loadButton);
        persistencePanel.add(saveButton);
        persistencePanel.add(playMusic);
    }

    // EFFECTS: creates button based on given string
    private JButton createButton(String str) {
        JButton button = new JButton(str);
        button.setActionCommand(str);
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(250,50)); //might delete
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(LOAD_DATA)) {
            loadCourses();
        } else if (e.getActionCommand().equals(SAVE_DATA)) {
            saveCourses();
        } else if (e.getActionCommand().equals(MUSIC)) {
            playSound("./data/sound/music.wav");
        }
    }

    // EFFECTS: Creates a list of file paths to the to-be-loaded .json files.
    private ArrayList<String> extractFilePaths() {
        File f = new File(COURSES_FOLDER);
        FilenameFilter jsonFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.endsWith(".json")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        File[] files = f.listFiles(jsonFilter);
        ArrayList<String> filePaths = new ArrayList<>();

        for (File file : files) {
            String filePath = file.getAbsolutePath();
            filePaths.add(filePath);
        }
        return filePaths;
    }

    // EFFECTS: loads the courses from the data folder
    private void loadCourses() {
        CourseReader courseReader = new CourseReader();
        ArrayList<String> filePaths = extractFilePaths();

        try {
            if (!(filePaths.size() == 0)) {
                for (String filePath : filePaths) {
                    Course newCourse = courseReader.load(filePath);
                    courses.add(newCourse);
                }
            } else {
                initializeCourses();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes courses
    private void initializeCourses() {
        cpsc210 = new Course("CPSC210", cpsc210Assignments);
        cpsc221 = new Course("CPSC221", cpsc221Assignments);
        courses.add(cpsc210);
        courses.add(cpsc221);
    }

    // EFFECTS: saves courses to the data folder
    private void saveCourses() {
        for (Course course : courses) {
            saveCourse(course);
        }
    }

    // EFFECTS: saves courses to the data folder
    private void saveCourse(Course course) {
        String filePath = COURSES_FOLDER + course.getCourseName() + ".json";
        CourseWriter courseWriter = new CourseWriter();
        try {
            courseWriter.write(filePath, course);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to save courses.");
        }
    }

    // EFFECTS: plays music.wav
    public void playSound(String musicName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(musicName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error playing music");
            e.printStackTrace();
        }
    }
}
