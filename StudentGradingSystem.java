package projects;
import java.util.Scanner;

public class StudentGradingSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Student Grading System ===");

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student roll number: ");
        int rollNumber = scanner.nextInt();

        System.out.print("Enter number of subjects: ");
        int numberOfSubjects = scanner.nextInt();

        int[] marks = new int[numberOfSubjects];
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
        }

        int totalMarks = calculateTotalMarks(marks);
        double averageMarks = calculateAverageMarks(totalMarks, numberOfSubjects);
        String grade = calculateGrade(averageMarks);

        System.out.println("\n=== Student Result ===");
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Marks: " + averageMarks);
        System.out.println("Grade: " + grade);

        scanner.close();
    }

    public static int calculateTotalMarks(int[] marks) {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    public static double calculateAverageMarks(int totalMarks, int numberOfSubjects) {
        return (double) totalMarks / numberOfSubjects;
    }

    public static String calculateGrade(double averageMarks) {
        if (averageMarks >= 90) {
            return "A+";
        } else if (averageMarks >= 80) {
            return "A";
        } else if (averageMarks >= 70) {
            return "B";
        } else if (averageMarks >= 60) {
            return "C";
        } else if (averageMarks >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
}
