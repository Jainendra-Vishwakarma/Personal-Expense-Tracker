// Expense.java
public class Expense {
    private int id;
    private double amount;
    private String date;
    private String note;
    private String category;

    public Expense(int id, double amount, String date, String note, String category) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.note = note;
        this.category = category;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
