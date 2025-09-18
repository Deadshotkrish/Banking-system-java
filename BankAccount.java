import java.util.ArrayList;
import java.util.Scanner;

public class BankAccount {
    private double balance;
    private final ArrayList<String> transactions = new ArrayList<>();

    // Constructor
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
        transactions.add("Account created with balance: " + initialBalance);
    }

    // Deposit
    public void deposit(Scanner scanner) {
        String choice;
        do {
            double amount;
            while (true) { // keep asking until valid
                System.out.print("Enter amount to deposit: ");
                amount = scanner.nextDouble();

                if (amount <= 0) {
                    System.out.println("Invalid amount. Please enter a value greater than 0.");
                } else {
                    break; // valid input
                }
            }

            balance += amount;
            transactions.add("Deposited: " + amount);
            System.out.println("Current Balance: " + balance);

            choice = getYesOrNo(scanner, "Do you wish to deposit more? (Y/N): ");
        } while (choice.equals("Y"));
    }

    // Withdraw
    public void withdraw(Scanner scanner) {
        String choice;
        do {
            double amount;
            while (true) { // keep asking until valid
                System.out.print("Enter amount to withdraw: ");
                amount = scanner.nextDouble();

                if (amount <= 0) {
                    System.out.println("Invalid amount. Please enter a value greater than 0.");
                } else if (amount > balance) {
                    System.out.println("Insufficient balance. Please enter an amount within your balance limits.");
                    System.out.println("Your current balance is: " + balance);
                } else {
                    break; // valid input
                }
            }

            balance -= amount;
            transactions.add("Withdrew: " + amount);
            System.out.println("Current Balance: " + balance);

            choice = getYesOrNo(scanner, "Do you wish to withdraw more? (Y/N): ");
        } while (choice.equals("Y"));
    }

    // Transfer
    public void transfer(Scanner scanner) {
        String choice;
        do {
            System.out.print("Enter the account number of the receiver: ");
            int account = scanner.nextInt();

            double amount;
            while (true) { // keep asking until valid
                System.out.print("Enter the amount to transfer: ");
                amount = scanner.nextDouble();

                if (amount <= 0) {
                    System.out.println("Invalid amount. Please enter a value greater than 0.");
                } else if (amount > balance) {
                    System.out.println("Insufficient balance. Please enter an amount within your balance limits.");
                    System.out.println("Your current balance is: " + balance);
                } else {
                    break; // valid input
                }
            }

            balance -= amount;
            transactions.add("Transferred: " + amount + " to account " + account);
            System.out.println("Transferred " + amount + " to account " + account);
            System.out.println("Current Balance: " + balance);

            choice = getYesOrNo(scanner, "Do you wish to transfer more? (Y/N): ");
        } while (choice.equals("Y"));
    }

    // Print Details
    public void printDetails(Scanner scanner) {
        String choice;
        do {
            System.out.println("\n********** ACCOUNT DETAILS **********");
            System.out.println("Balance: " + balance);
            System.out.println("Transaction History:");
            for (String t : transactions) {
                System.out.println("- " + t);
            }

            choice = getYesOrNo(scanner, "Do you wish to view details again? (Y/N): ");
        } while (choice.equals("Y"));
    }

    // Utility: validate Y/N input
    private static String getYesOrNo(Scanner scanner, String message) {
        String choice;
        while (true) {
            System.out.print(message);
            choice = scanner.next().toUpperCase();
            if (choice.equals("Y") || choice.equals("N")) {
                return choice;
            }
            System.out.println("Invalid choice! Please enter Y or N.");
        }
    }

    // Main Menu
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter your initial account balance: ");
        double initialBalance = scanner.nextDouble();
        BankAccount account = new BankAccount(initialBalance);

        int option = -1;
        while (option != 5) {
            System.out.println("\n********** BANKING PROGRAM **********");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Print Details");
            System.out.println("5. Exit Program");
            System.out.print("Enter your choice: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> account.deposit(scanner);
                case 2 -> account.withdraw(scanner);
                case 3 -> account.transfer(scanner);
                case 4 -> account.printDetails(scanner);
                case 5 -> System.out.println("Exiting program. Have a nice day!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}
