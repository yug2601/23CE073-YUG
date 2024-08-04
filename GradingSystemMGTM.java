import java.util.Scanner;

class Student {
    private int studentID;
    private String name;

    Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }
}

class Grade {
    private int studentID;
    private int courseID;
    private char grade;

    Grade(int studentID, int courseID, char grade) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public char getGrade() {
        return grade;
    }
}

class GradingSystem {
    private Student[] students;
    private Grade[] grades;
    private int[] courseCredits;
    private int studentCount;
    private int gradeCount;

    GradingSystem(int numberOfCourses, int maxStudents, int maxGrades) {
        students = new Student[maxStudents];
        grades = new Grade[maxGrades];
        courseCredits = new int[numberOfCourses];
        studentCount = 0;
        gradeCount = 0;
    }

    public void addStudent(Student student) {
        if (studentCount < students.length) {
            students[studentCount++] = student;
        } else {
            System.out.println("Max number of students reached.");
        }
    }

    public void addGrade(Grade grade) {
        if (gradeCount < grades.length) {
            grades[gradeCount++] = grade;
        } else {
            System.out.println("Max number of grades reached.");
        }
    }

    public void addCourseCredits(int courseID, int credits) {
        if (courseID >= 0 && courseID < courseCredits.length) {
            courseCredits[courseID] = credits;
        } else {
            System.out.println("Course ID does not exist.");
        }
    }

    public double calculateGPA(int studentID) {
        int totalPoints = 0;
        int totalCredits = 0;

        for (int i = 0; i < gradeCount; i++) {
            Grade grade = grades[i];
            if (grade.getStudentID() == studentID) {
                int credits = courseCredits[grade.getCourseID()];
                totalPoints += gradeToPoints(grade.getGrade()) * credits;
                totalCredits += credits;
            }
        }

        return totalCredits == 0 ? 0.0 : (double) totalPoints / totalCredits;
    }

    public int gradeToPoints(char grade) {
        switch (grade) {
            case 'A':
                return 10;
            case 'B':
                return 8;
            case 'C':
                return 6;
            case 'D':
                return 4;
            case 'F':
                return 0;
            default:
                return 0;
        }
    }

    public void printGradeReport(int studentID) {
        for (int i = 0; i < studentCount; i++) {
            Student student = students[i];
            if (student.getStudentID() == studentID) {
                System.out.println("Grade Report for " + student.getName() + ":");
                for (int j = 0; j < gradeCount; j++) {
                    Grade grade = grades[j];
                    if (grade.getStudentID() == studentID) {
                        System.out.println("Course ID: " + grade.getCourseID() + " | Grade: " + grade.getGrade());
                    }
                }
                System.out.println("GPA: " + calculateGPA(studentID));
                return;
            }
        }
        System.out.println("Student not found.");
    }
}

public class GradingSystemMGTM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradingSystem system = new GradingSystem(5, 100, 100);

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. Add Course Credits");
            System.out.println("4. Print Grade Report");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int studentID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    system.addStudent(new Student(studentID, name));
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    int sID = scanner.nextInt();
                    System.out.print("Enter Course ID: ");
                    int cID = scanner.nextInt();
                    System.out.print("Enter Grade (A, B, C, D, F): ");
                    char grade = scanner.next().charAt(0);
                    system.addGrade(new Grade(sID, cID, grade));
                    break;
                case 3:
                    System.out.print("Enter Course ID: ");
                    int courseID = scanner.nextInt();
                    System.out.print("Enter Credits: ");
                    int credits = scanner.nextInt();
                    system.addCourseCredits(courseID, credits);
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    int reportID = scanner.nextInt();
                    system.printGradeReport(reportID);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
