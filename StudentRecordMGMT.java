import java.util.Scanner;

class Student 
{
    private int studentID;
    private String name;
    private int age;
    private String department;

    Student(int studentID, String name, int age, String department) 
    {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public int getStudentID() 
    {
        return studentID;
    }

    public String getName() 
    {
        return name;
    }

    public int getAge() 
    {
        return age;
    }

    public String getDepartment() 
    {
        return department;
    }

    
    public String toString() 
    {
        return "Student ID: " + studentID + "\n" + ", Name: " + name + "\n" + ", Age: " + age + "\n" + ", Department: " + department + "\n";
    }
}

class StudentRecordSystem 
{
    private Student[] students;
    private int count;

    public StudentRecordSystem(int capacity) 
    {
        students = new Student[capacity];
        count = 0;
    }

    public void addStudent(Student student) 
    {
        if (count < students.length) 
        {
            students[count] = student;
            count++;
        }
        else 
        {
            System.out.println("Cannot add more students.");
        }
    }

    public Student getStudent(int studentID) 
    {
        for (int i = 0; i < count; i++) 
        {
            if (students[i].getStudentID() == studentID) 
            {
                return students[i];
            }
        }
        return null;
    }

    public void displayAllStudents() 
    {
        for (int i = 0; i < count; i++) 
        {
            System.out.println(students[i].toString());
        }
    }
}

public class StudentRecordMGMT 
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentRecordSystem s = new StudentRecordSystem(10);

        while (true) 
        {
            System.out.println("Student Record Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch(choice) 
            {
                case 1:
                {
                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter department: ");
                    String department = scanner.nextLine();

                    Student student = new Student(id, name, age, department);
                    s.addStudent(student);
                    System.out.println("Student added successfully.");
                    break;
                }
                case 2:
                {
                    s.displayAllStudents();
                    break;
                }
                case 3:
                {
                    System.out.print("Enter student ID to search: ");
                    int searchID = scanner.nextInt();
                    scanner.nextLine();
                    Student foundStudent = s.getStudent(searchID);
                    if (foundStudent != null) {
                        System.out.println(foundStudent.toString());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                }
                case 4:
                {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default:
                {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
