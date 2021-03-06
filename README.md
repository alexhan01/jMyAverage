# My Personal Project: Course Grade Calculator

## Preliminary Questions

What will the application do?

- I will be designing a **Course Grade Calculator**.
This application will allow users to add
various assignments (e.g. exams, projects, quizzes, etc.)
and their weights to predict an overall grade for the course. 

Who will use it?
- The application will be used by students (e.g. UBC students)
enrolled in classes wishing to gauge what their predicted grade
will be in a course.

Why is this project of interest to you?
- Time management is a critical skill for any student, especially
post-secondary students. With limited time to balance coursework,
extracurriculars, social activities, job search, and more,
the ability to predict your course grade is vital in gauging how
much time one should allocate to each class.
- For example, if a student has one assignment left and has already
secured an 80% average (assuming after a 0 on that assignment) in that
course, it makes little sense for them to allocate equal amount of time to
that assignment and another important activity their life.
- With this application, I and many other students like me, will
be able to more effectively allocate our time for coursework.

## User Stories

Implemented for Phase 1:
- As a user, I want to be able to add new assignments to the assignment list
- As a user, I want to be able to view the list of assignments on my assignment list
- As a user, I want to be able to delete an assignment on my assignment list
- As a user, I want to be able to see the calculated average of the course based on the assignments given

Implemented for Phase 2:
- As a user, I want to be able to save the assignments list to an associated course .json file
- As a user, I want to be able to optionally load my Course Grade Calculator from a file when the program starts
- As a user, I want to be able to create and delete courses

For future:
- As a user, I want to be able to assign tags to each assignment (e.g. Quiz, Exam, Project, Lecture Lab)

## Instructions for Grader

- You can generate the first required event by pressing the
 "Create Course" button (which creates a course based on the name
 given in the JTextField under Course Name)
- You can generate the second required event by selecting a course
from the list of courses visible and pressing the "Delete Course" button
to delete the selected course.
- You can locate my audio component by either pressing the "Play Music"
button or looking at the ./data/sound/music.wav file
- You can save the state of my application by pressing the "Save Data" button
- You can reload the state of my application by pressing the "Load Data" button

## Phase 4: Task 2

- I implemented an appropriate use of Map in the GUI class under panels
- The Map is used to store the various assignmentsPanel associated to each course

## Phase 4: Task 3
Problems:
- (Coupling) AssignmentsPanel and CoursesPanel both have the same createCourse method - repetitive.
- (Coupling) AssignmentsPanel and CoursesPanel both share implementation
detail for the method that adds components to the main JPanel (i.e. initializeUIPane) - repetitive.
- (Coupling) Styling parts of initializeData method in AssignmentsPanel and CoursesPanel 
is repetitive

Solution:
- Created a PanelStyler class to store methods for the above and reduce repetition
and eliminate problematic coupling.