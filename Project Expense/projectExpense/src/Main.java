// Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        manager.loadExpenses();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Personal Expense Tracker ===");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Update Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Amount: ");
                    double amount = sc.nextDouble(); sc.nextLine();
                    if (amount < 0) { System.out.println("⚠ Amount must be non-negative."); break; }

                    System.out.print("Date (YYYY-MM-DD): ");
                    String date = sc.nextLine();
                    if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        System.out.println("⚠ Invalid date format."); break;
                    }

                    System.out.print("Note: ");
                    String note = sc.nextLine();

                    System.out.print("Category: ");
                    String category = sc.nextLine();

                    manager.addExpense(new Expense(manager.getNextId(), amount, date, note, category));
                    break;

                case 2:
                    manager.viewExpenses();
                    break;

                case 3:
                    System.out.print("Enter expense ID to update: ");
                    int idUpdate = sc.nextInt(); sc.nextLine();
                    System.out.print("New Amount: ");
                    double newAmount = sc.nextDouble(); sc.nextLine();
                    System.out.print("New Date (YYYY-MM-DD): ");
                    String newDate = sc.nextLine();
                    System.out.print("New Note: ");
                    String newNote = sc.nextLine();
                    System.out.print("New Category: ");
                    String newCategory = sc.nextLine();

                    manager.updateExpense(idUpdate, new Expense(idUpdate, newAmount, newDate, newNote, newCategory));
                    break;

                case 4:
                    System.out.print("Enter expense ID to delete: ");
                    int idDelete = sc.nextInt(); sc.nextLine();
                    manager.deleteExpense(idDelete);
                    break;

                case 5:
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("⚠ Invalid choice. Try again.");
            }
        }
    }
}
