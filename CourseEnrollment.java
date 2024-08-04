import java.util.Scanner;

class Course 
{
    private int courseID;
    private String courseName;
    private int credits;

    Course(int courseID, String courseName, int credits) 
    {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credits = credits;
    }

    public int getCourseID() 
    {
        return courseID;
    }

    public String getCourseName() 
    {
        return courseName;
    }

    public int getCredits() 
    {
        return credits;
    }

    public String toString() 
    {
        return "CourseID: " + courseID + "\n" + "CourseName: " + courseName + "\n" + "Credits: " + credits + "\n";
    }
}

class Enrollment 
{
    private int[][] studentCourses;
    private int[] count;

    Enrollment(int numberOfStudents, int maxCoursesPerStudent) 
    {
        studentCourses = new int[numberOfStudents][maxCoursesPerStudent];
        count = new int[numberOfStudents];
    }

    public void enroll(int studentID, int courseID) 
    {
        if (count[studentID] < studentCourses[studentID].length) 
        {
            studentCourses[studentID][count[studentID]] = courseID;
            count[studentID]++;
        } 
        else 
        {
            System.out.println("Cannot enroll more courses for student " + studentID);
        }
    }

    public void drop(int studentID, int courseID) 
    {
        for (int i = 0; i < count[studentID]; i++) 
        {
            if (studentCourses[studentID][i] == courseID) 
            {
                for (int j = i; j < count[studentID] - 1; j++) 
                {
                    studentCourses[studentID][j] = studentCourses[studentID][j + 1];
                }
                count[studentID]--;
                break;
            }
        }
    }

    public void getEnrolledCourses(int studentID, Course[] courseCatalog) 
    {
        System.out.println("Courses enrolled by student " + studentID + ":");
        for (int i = 0; i < count[studentID]; i++) 
        {
            int courseID = studentCourses[studentID][i];
            for (Course course : courseCatalog) {
                if (course.getCourseID() == courseID) 
                {
                    System.out.println(course);
                }
            }
        }
    }
}

public class CourseEnrollment 
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Course[] courses = {
            new Course(1, "Java",4),
            new Course(2, "DSA", 3),
            new Course(3, "COA", 3),
            new Course(4, "MATHS", 3)
        };

        Enrollment enrollment = new Enrollment(10, 3);

        boolean option = true;
        while (option) 
        {
            System.out.println("\n1. Enroll in a course");
            System.out.println("2. Drop a course");
            System.out.println("3. View enrolled courses");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) 
            {
                case 1:
                    System.out.print("Enter student ID: ");
                    int enrollStudentID = scanner.nextInt();
                    System.out.print("Enter course ID to enroll: ");
                    int enrollCourseID = scanner.nextInt();
                    enrollment.enroll(enrollStudentID, enrollCourseID);
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    int dropStudentID = scanner.nextInt();
                    System.out.print("Enter course ID to drop: ");
                    int dropCourseID = scanner.nextInt();
                    enrollment.drop(dropStudentID, dropCourseID);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    int viewStudentID = scanner.nextInt();
                    enrollment.getEnrolledCourses(viewStudentID, courses);
                    break;
                case 4:
                    option = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
