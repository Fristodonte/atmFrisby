import models.User;
import services.TransactionManager;
import ui.Menu;

public class App {
    public static void main(String[] args) {
        User user = new User();
        user.setName("Sara Montoya"); // Customize the user's name
        user.setDebitBalance(2000000); // Initial debit balance
        user.setCreditBalance(10000000); // Initial credit balance
        user.setPassword("1234"); // Set a password for the user

        TransactionManager transactionManager = new TransactionManager();
        Menu menu = new Menu(user, transactionManager); // Pass user and transaction manager to the menu

        menu.displayMainMenu(); // Start the menu
    }
}
