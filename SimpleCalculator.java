package projects;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SimpleCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Simple Calculator ===");

        while (running) {
            try {
                System.out.print("Enter the first number: ");
                double num1 = scanner.nextDouble();

                System.out.print("Enter the second number: ");
                double num2 = scanner.nextDouble();

                System.out.print("Choose an operation (+, -, *, /): ");
                char operation = scanner.next().charAt(0);

                double result = calculate(num1, num2, operation);
                System.out.println("Result: " + result);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numeric values.");
                scanner.next();
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.print("Do you want to perform another calculation? (yes/no): ");
            String choice = scanner.next().toLowerCase();

            if (!choice.equals("yes")) {
                running = false;
                System.out.println("Thank you for using the Simple Calculator!");
            }
        }

        scanner.close();
    }

    public static double calculate(double num1, double num2, char operation) {
        switch (operation) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero is not allowed.");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Invalid operation! Please choose +, -, *, or /.");
        }
    }
}
