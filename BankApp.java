package Bank_Application;

import java.io.*;
import java.util.*;

class BankAccount implements Serializable {
    private String accNo;
    private String name;
    private String accType;
    private double balance;

    public BankAccount(String accNo, String name, String accType, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.accType = accType;
        this.balance = balance;
    }

    public String getAccNo() { return accNo; }
    public String getName() { return name; }
    public String getAccType() { return accType; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Invalid or insufficient funds!");
        }
    }

    public void transfer(BankAccount receiver, double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            receiver.deposit(amount);
            System.out.println("Transferred " + amount + " to Account No: " + receiver.getAccNo());
        } else {
            System.out.println("Invalid or insufficient funds for transfer!");
        }
    }

    public void displayDetails() {
        System.out.println("Account No: " + accNo);
        System.out.println("Name: " + name);
        System.out.println("Account Type: " + accType);
        System.out.println("Balance: " + balance);
    }
}

class Bank {
    private static final String FILE_NAME = "bank_data.ser";
    private Map<String, BankAccount> accounts = new HashMap<>();

    public Bank() {
        loadAccounts();
    }

    public void createAccount(String accNo, String name, String accType, double balance) {
        if (!accounts.containsKey(accNo)) {
            BankAccount acc = new BankAccount(accNo, name, accType, balance);
            accounts.put(accNo, acc);
            saveAccounts();
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Account already exists!");
        }
    }

    public BankAccount getAccount(String accNo) {
        return accounts.get(accNo);
    }

    private void saveAccounts() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadAccounts() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            accounts = (Map<String, BankAccount>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous data found, starting fresh.");
        }
    }
}

public class BankApp {
    private static final Scanner sc = new Scanner(System.in);
    private static final Bank bank = new Bank();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Create Account\n2. Deposit\n3. Withdraw\n4. Transfer\n5. Check Balance\n6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> transfer();
                case 5 -> checkBalance();
                case 6 -> {
                    System.out.println("Exiting... Thank you!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option, try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter Account No: ");
        String accNo = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Account Type: ");
        String accType = sc.nextLine();
        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();
        bank.createAccount(accNo, name, accType, balance);
    }

    private static void deposit() {
        System.out.print("Enter Account No: ");
        String accNo = sc.nextLine();
        BankAccount acc = bank.getAccount(accNo);
        if (acc != null) {
            System.out.print("Enter Deposit Amount: ");
            double amount = sc.nextDouble();
            acc.deposit(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void withdraw() {
        System.out.print("Enter Account No: ");
        String accNo = sc.nextLine();
        BankAccount acc = bank.getAccount(accNo);
        if (acc != null) {
            System.out.print("Enter Withdraw Amount: ");
            double amount = sc.nextDouble();
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void transfer() {
        System.out.print("Enter Your Account No: ");
        String senderAccNo = sc.nextLine();
        BankAccount sender = bank.getAccount(senderAccNo);
        if (sender != null) {
            System.out.print("Enter Receiver Account No: ");
            String receiverAccNo = sc.nextLine();
            BankAccount receiver = bank.getAccount(receiverAccNo);
            if (receiver != null) {
                System.out.print("Enter Transfer Amount: ");
                double amount = sc.nextDouble();
                sender.transfer(receiver, amount);
            } else {
                System.out.println("Receiver account not found!");
            }
        } else {
            System.out.println("Sender account not found!");
        }
    }

    private static void checkBalance() {
        System.out.print("Enter Account No: ");
        String accNo = sc.nextLine();
        BankAccount acc = bank.getAccount(accNo);
        if (acc != null) {
            acc.displayDetails();
        } else {
            System.out.println("Account not found!");
        }
    }
}