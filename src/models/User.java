package models;

public class User {
    private String name;
    private double debitBalance;
    private double creditBalance;
    private String password; 
    private double deposit; 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDebitBalance() {
        return debitBalance;
    }

    public void setDebitBalance(double debitBalance) {
        this.debitBalance = debitBalance;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }

    public String getPassword() { // Getter for password
        return password;
    }

    public void setPassword(String password) { // Setter for password
        this.password = password;
    }

    public void depositDebit(double amount) {
        this.debitBalance += amount;
    }

    public void withdrawDebit(double amount) {
        if (amount <= this.debitBalance) {
            this.debitBalance -= amount;
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void withdrawCredit(double amount) {
        if (amount <= this.creditBalance) {
            this.creditBalance -= amount;
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void deposit(double amount) {
        this.debitBalance += amount;
    }
}



