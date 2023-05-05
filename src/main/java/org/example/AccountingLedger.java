package org.example;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class AccountingLedger {
    private static final String TRANSACTIONS_FILE = "transactions.csv";
    private static List<Transaction> transactions = new ArrayList<>();
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) {
        loadTransactions();
        displayHomeScreen();
    }

    // Method to load transactions from the CSV file
    private static void loadTransactions() {
        try (BufferedReader br = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] transactionData = line.split("\\|");
                LocalDate date = LocalDate.parse(transactionData[0]);
                LocalTime time = LocalTime.parse(transactionData[1]);
                String description = transactionData[2];
                String vendor = transactionData[3];
                double amount = Double.parseDouble(transactionData[4]);
                transactions.add(new Transaction(date, time, description, vendor, amount));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to display the Home Screen
    private static void displayHomeScreen() {
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("Home Screen:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    displayLedger();
                    break;
                case "X":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (!choice.equals("X"));
    }

    private static void displayLedger() {
        System.out.println("Ledger:");
        System.out.println("Date       | Time     | Description         | Vendor        | Amount");
        System.out.println("--------------------------------------------------------------------------");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
            // System.out.printf("%s  ........   %.2f \n", transaction.getDescription(), transaction.getAmount());
        }
    }


    // Method to add a deposit
    private static void addDeposit() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the deposit date (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();
        LocalDate date;
        date = LocalDate.parse(dateString);

        System.out.print("Enter the deposit time (HH:mm:ss): ");
        String timeString = scanner.nextLine();
        LocalTime time;
        time = LocalTime.parse(timeString);

        System.out.print("Enter the deposit description: ");
        String description = scanner.nextLine();

        System.out.print("Enter the deposit vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter the deposit amount: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format.");
            return;
        }

        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);
        writeTransactionToFile(transaction);
    }

    private static void writeTransactionToFile(Transaction transaction) {
    }

    // Method to make a payment (debit)
    public static void makePayment() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the payment date (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();
        LocalDate date;
        date = LocalDate.parse(dateString);

        System.out.print("Enter the payment time (HH:mm:ss): ");
        String timeString = scanner.nextLine();
        LocalTime time;
        time = LocalTime.parse(timeString);

        System.out.print("Enter the payment description: ");
        String description = scanner.nextLine();

        System.out.print("Enter the payment vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter the payment amount: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
            if (amount > 0) {
                amount = -amount; // Ensure the amount is negative for payments
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format.");
            return;
        }

        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);
        writeTransactionToFile(transaction);
    }
}


