
import java.util.ArrayList;
import java.util.Scanner;

class Student {

    String name;
    int rollNumber;
    double marks;

    Student(String name, int rollNumber, double marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }
}

public class Students {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<Student> students = new ArrayList<>();
            int choice;

            do {
                System.out.println("\n===== Students RECORD =====");
                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Search Student by Roll Number");
                System.out.println("4. Calculate Average Marks");
                System.out.println("5. Display Topper");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                choice = scanner.nextInt();

                switch (choice) {

                    case 1 -> {
                        scanner.nextLine(); // consume newline
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Roll Number: ");
                        int roll = scanner.nextInt();
                        System.out.print("Enter Marks: ");
                        double marks = scanner.nextDouble();
                        students.add(new Student(name, roll, marks));
                        System.out.println("Student added successfully!");
                    }

                    case 2 -> {
                        if (students.isEmpty()) {
                            System.out.println("No student records found.");
                        } else {
                            System.out.println("\n--- Student Records ---");
                            for (Student s : students) {
                                System.out.println("Name: " + s.name + ", Roll No: " + s.rollNumber + ", Marks: " + s.marks);
                            }
                        }
                    }

                    case 3 -> {
                        System.out.print("Enter Roll Number to search: ");
                        int searchRoll = scanner.nextInt();
                        boolean found = false;

                        for (Student s : students) {
                            if (s.rollNumber == searchRoll) {
                                System.out.println("Student Found:");
                                System.out.println("Name: " + s.name + ", Marks: " + s.marks);
                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            System.out.println("Student not found.");
                        }
                    }

                    case 4 -> {
                        if (students.isEmpty()) {
                            System.out.println("No student records to calculate average.");
                        } else {
                            double total = 0;
                            for (Student s : students) {
                                total += s.marks;
                            }
                            double average = total / students.size();
                            System.out.println("Average Marks: " + average);
                        }
                    }

                    case 5 -> {
                        if (students.isEmpty()) {
                            System.out.println("No student records available.");
                        } else {
                            Student topper = students.get(0);
                            for (Student s : students) {
                                if (s.marks > topper.marks) {
                                    topper = s;
                                }
                            }
                            System.out.println("Topper Details:");
                            System.out.println("Name: " + topper.name + ", Roll No: " + topper.rollNumber + ", Marks: " + topper.marks);
                        }
                    }

                    case 6 -> {
                        System.out.println("thank you for visiting!");
                    }

                    default ->
                        System.out.println("Invalid choice! Please try again.");
                }

            } while (choice != 6);
        }
    }
}
