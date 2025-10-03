import java.util.*;
import java.io.*;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();
    private static final String FILE_NAME = "expenses.csv";

    // Load data from file
    public void loadExpenses() {
        expenses.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    int id = Integer.parseInt(data[0]);
                    double amount = Double.parseDouble(data[1]);
                    String date = data[2];
                    String category = data[3];
                    String note = data[4];
                    expenses.add(new Expense(id, amount, date, note, category));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved expenses found (new file will be created).");
        } catch (IOException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
    }

    // Save data to file
    public void saveExpenses() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Expense exp : expenses) {
                pw.println(exp.getId() + "," + exp.getAmount() + "," + exp.getDate() + "," + exp.getCategory() + "," + exp.getNote());
            }
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    // Add new expense
    public void addExpense(Expense exp) {
        if (exp.getAmount() <= 0) {
            System.out.println("Amount must be greater than 0!");
            return;
        }
        expenses.add(exp);
        saveExpenses();
        System.out.println("Expense added successfully!");
    }

    // View all expenses
    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }
        System.out.println("ID   Amount     Date        Category   Note");
        for (Expense exp : expenses) {
            System.out.printf("%-4d %-10.2f %-10s %-10s %s\n",
                    exp.getId(), exp.getAmount(), exp.getDate(), exp.getCategory(), exp.getNote());
        }
    }

    // Update expense
    public void updateExpense(int id, Expense updated) {
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getId() == id) {
                expenses.set(i, updated);
                saveExpenses();
                System.out.println("Expense updated successfully!");
                return;
            }
        }
        System.out.println("Expense not found!");
    }

    // Delete expense
    public void deleteExpense(int id) {
        boolean removed = expenses.removeIf(exp -> exp.getId() == id);
        if (removed) {
            saveExpenses();
            System.out.println("Expense deleted successfully!");
        } else {
            System.out.println("Expense not found!");
        }
    }

    // Auto increment ID
    public int getNextId() {
        int maxId = 0;
        for (Expense exp : expenses) {
            if (exp.getId() > maxId) {
                maxId = exp.getId();
            }
        }
        return maxId + 1;
    }
}
