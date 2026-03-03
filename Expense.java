
import java.util.ArrayList;
import java.util.Scanner;

public class Expense {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<String> expense = new ArrayList<>();
            ArrayList<Double> amount = new ArrayList<>();
            double totalExpense = 0;

            while (true) {
                System.out.print("type'view-> view/'delete'-> delete/'expense'-> expense/'done'->exit:");
                String expenses = scanner.nextLine();
                if (expenses.equalsIgnoreCase("expense")) {
                    System.err.print("Enter the expense:");
                    String expName = scanner.nextLine();
                    System.out.print("Enter Amount: ");
                    double amo = scanner.nextDouble();
                    scanner.nextLine();
                    expense.add(expName);
                    amount.add(amo);
                    totalExpense += amo;
                    System.out.println("expense Added Successfully\n");

                } else if (expenses.equalsIgnoreCase("view")) {
                    System.out.println("Expenses:");
                    if (!expense.isEmpty()) {
                        for (int i = 0; i < expense.size(); i++) {
                            System.out.println(expense.get(i) + " - $" + amount.get(i));
                        }
                        System.out.println("Total Expense: $" + totalExpense);
                    } else {
                        System.out.println("No expense data entered.");
                    }
                } else if (expenses.equalsIgnoreCase("delete")) {
                    if (!expense.isEmpty()) {
                        System.out.println("eneter the expense to be delete:");
                        String del = scanner.nextLine();
                        for (int i = 0; i < expense.size(); i++) {
                            if (expense.get(i).equalsIgnoreCase(del)) {
                                totalExpense -= amount.get(i);
                                expense.remove(i);
                                amount.remove(i);
                                System.out.println("expense deleted successfully\n");
                                break;
                            } else {
                                System.out.println("expense not found\n");
                            }
                        }

                    } else {
                        System.out.println("No expenses to delete.");
                    }
                } else if (expenses.equalsIgnoreCase("done")) {
                    System.out.println("complete");
                    break;
                }
            }
        }
    }
}
