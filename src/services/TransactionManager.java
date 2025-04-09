package services;
import java.util.Scanner;

import models.User;

public class TransactionManager {
    public boolean verifyPassword(User user) {
        if (user == null) {
            System.out.println("Error: User object is null.");
            return false;
        }

        if (user.getPassword() == null) {
            System.out.println("Error: User password is null.");
            return false;
        }

        Scanner scanner = new Scanner(System.in); 
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;
        

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter your password: ");
            String inputPassword = scanner.nextLine();

            if (user.getPassword().equals(inputPassword)) {
                System.out.println("Password verified successfully!");
                return true;
            } else {
                attempts++;
                System.out.println("Incorrect password. Attempts remaining: " + (MAX_ATTEMPTS - attempts));
            }
        }

        System.out.println("Account blocked due to too many failed attempts.");
        // Uncomment the following line if the scanner is not reused elsewhere
        scanner.close();
        return false;
    }

    public void checkBalance(User user, boolean isCredit) {
        if (!verifyPassword(user)) {
            return; // Exit if password verification fails
        }

        if (isCredit) {
            System.out.println("Your credit balance is " + user.getCreditBalance() + " chicks!");
        } else {
            System.out.println("Your debit balance is " + user.getDebitBalance() + " chicks!");
        }
    }

    public void deposit(User user, double amount) {
        if (!verifyPassword(user)) {
            return; // Exit if password verification fails
        }

        if (amount > 0) {
            user.deposit(amount);
            System.out.println("You deposited " + amount + " chicks. New debit balance: " + user.getDebitBalance());
        } else {
            System.out.println("Invalid amount! You must deposit a positive number.");
        }
    }

    public void withdraw(User user, double amount, boolean isCredit) {
        if (!verifyPassword(user)) {
            return; // Exit if password verification fails
        }

        if (amount <= 0) {
            System.out.println("Invalid amount! You must withdraw a positive number.");
            return;
        }

        if (isCredit) {
            if (amount <= user.getCreditBalance()) {
                user.withdrawCredit(amount);
                System.out.println("You withdrew " + amount + " chicks from your credit card. Remaining credit balance: " + user.getCreditBalance());
            } else {
                System.out.println("Insufficient funds in your credit account.");
            }
        } else {
            if (amount <= user.getDebitBalance()) {
                user.withdrawDebit(amount);
                System.out.println("You withdrew " + amount + " chicks from your debit account. Remaining debit balance: " + user.getDebitBalance());
            } else {
                System.out.println("Insufficient funds in your debit account.");
            }
        }
    }
}





