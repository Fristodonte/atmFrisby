package ui;

import java.util.Scanner;
import models.User;
import services.TransactionManager;

public class Menu {
    private final User user;
    private final TransactionManager transactionManager;
    private final Scanner scanner;

    public Menu(User user, TransactionManager transactionManager) {
        this.user = user;
        this.transactionManager = transactionManager;
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        boolean running = true;

        while (running) {
            try {
                System.out.println("Welcome to Frisby! Please select an option:");
                System.out.println("1. Credit card");
                System.out.println("2. Debit card");
                System.out.println("3. Exit Frisby!");
                System.out.print("Please select one of the options: ");

                String input = scanner.nextLine();
                int choice = Integer.parseInt(input); // safer conversion

                switch (choice) {
                    case 1:
                        displayCreditCardMenu();
                        break;
                    case 2:
                        displayDebitCardMenu();
                        break;
                    case 3:
                        System.out.println("Exiting Frisby. Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private void displayCreditCardMenu() {
        System.out.println("You selected Credit card. Here are your options:");
        System.out.println("a. Check balance");
        System.out.println("b. Make a payment");
        System.out.print("Please select an option: ");

        String input = scanner.next().toLowerCase();
        scanner.nextLine(); // consume leftover newline

        if (input.isEmpty()) {
            System.out.println("No option entered. Returning to main menu.");
            return;
        }

        char creditOption = input.charAt(0);

        switch (creditOption) {
            case 'a':
                transactionManager.checkBalance(user, true);
                break;
            case 'b':
                try {
                    System.out.print("Enter the amount to pay: ");
                    double amount = scanner.nextDouble();
                    transactionManager.withdraw(user, amount, true);
                    scanner.nextLine(); // consume leftover newline
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid amount.");
                    scanner.nextLine(); // Clear the buffer
                }
                break;
            default:
                System.out.println("Invalid option. Returning to main menu.");
        }
    }

    private void displayDebitCardMenu() {
        System.out.println("You selected Debit card. Here are your options:");
        System.out.println("a. Check balance");
        System.out.println("b. Withdraw money");
        System.out.println("c. Deposit money");
        System.out.print("Please select an option: ");

        String input = scanner.next().toLowerCase();
        scanner.nextLine(); // consume leftover newline

        if (input.isEmpty()) {
            System.out.println("No option entered. Returning to main menu.");
            return;
        }

        char debitOption = input.charAt(0);

        switch (debitOption) {
            case 'a':
                transactionManager.checkBalance(user, false);
                break;
            case 'b':
                try {
                    System.out.print("Enter the amount to withdraw: ");
                    double amount = scanner.nextDouble();
                    transactionManager.withdraw(user, amount, false);
                    scanner.nextLine(); // consume leftover newline
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid amount.");
                    scanner.nextLine(); // Clear the buffer
                }
                break;
            case 'c':
                try {
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    transactionManager.deposit(user, depositAmount);
                    scanner.nextLine(); // consume leftover newline
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid amount.");
                    scanner.nextLine(); // Clear the buffer
                }
                break;
            default:
                System.out.println("Invalid option. Returning to main menu.");
        }
    }
}
