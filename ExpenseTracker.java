
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ExpenseTracker extends JFrame {

    JTextField titleField, amountField, dateField, descriptionField, budgetField;
    JComboBox<String> categoryBox;
    JTable table;
    DefaultTableModel model;

    double monthlyBudget = 0;
    String fileName = "expenses.csv";

    public ExpenseTracker() {
        
        ImageIcon icon=new ImageIcon("log0.png");
        setIconImage(icon.getImage());
        setTitle("Expense Tracker");
        setSize(420, 420);
       setLocationRelativeTo(null);    
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLayout(new BorderLayout());
       setBackground(Color.BLACK);

        //label
        JLabel label=new JLabel();
        label.setBackground(Color.BLACK);
        label.setOpaque(true);

        //Input panel
        JPanel inputPanel = new JPanel(new GridLayout(6,2));

        inputPanel.add(new JLabel("Expense Title"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Category"));
        String[] categories = {"Food","Travel","Bills","Shopping","EMI","Saving","fees","Other"};
        categoryBox = new JComboBox<>(categories);
        inputPanel.add(categoryBox);

        inputPanel.add(new JLabel("Amount"));
        amountField = new JTextField();
        inputPanel.add(amountField);
        inputPanel.add(new JLabel("Date"));
        dateField = new JTextField();
        inputPanel.add(dateField);
        inputPanel.add(new JLabel("Description"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);
        add(inputPanel, BorderLayout.NORTH);

        //table
        String[] column = {"Title","Category","Amount","Date","Description"};
        model = new DefaultTableModel(column,0);
        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        //Button panel
        JPanel buttonPanel = new JPanel();

        JButton addBtn = new JButton("Add Expense");
        JButton loadBtn = new JButton("Load Expenses");
        JButton totalBtn = new JButton("Show Total");
        buttonPanel.add(addBtn);
        buttonPanel.add(loadBtn);
        buttonPanel.add(totalBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        // budget
        JPanel budgetPanel = new JPanel();

        budgetPanel.add(new JLabel("Monthly Budget"));
        budgetField = new JTextField(10);
        budgetPanel.add(budgetField);

        JButton setBudgetBtn = new JButton("Set Budget");
        budgetPanel.add(setBudgetBtn);

        add(budgetPanel, BorderLayout.WEST);

        // ===== Button Actions =====

        addBtn.addActionListener(e -> addExpense());
        loadBtn.addActionListener(e -> loadExpenses());
        totalBtn.addActionListener(e -> showTotal());
        setBudgetBtn.addActionListener(e -> setBudget());

        setVisible(true);
    }

    // ===== Add Expense =====
    void addExpense(){

        String title = titleField.getText();
        String category = categoryBox.getSelectedItem().toString();
        String amount = amountField.getText();
        String date = dateField.getText();
        String desc = descriptionField.getText();

        var row = new ArrayList<String>();
        row.add(title);
        row.add(category);
        row.add(amount);
        row.add(date);
        row.add(desc);
        System.err.println(row);

        saveToFile(title,category,amount,date,desc);

        checkBudget();
    }

    // ===== Save to CSV =====
    void saveToFile(String t,String c,String a,String d,String desc){

            try(FileWriter fw = new FileWriter(fileName,true)){
            fw.write(t+","+c+","+a+","+d+","+desc+"\n");
            fw.close(); 

        }catch(IOException e){

        }

    }

    // ===== Load Expenses =====
    void loadExpenses(){
            try(BufferedReader b = new BufferedReader(new FileReader(fileName))){
            model.setRowCount(0);
            String line;

            while((line = b.readLine()) != null){
                String[] data = line.split(",");
                model.addRow(data);

            }

            b.close();

        }
        catch(IOException e){
            JOptionPane.showMessageDialog(this,"No file found yet");
        }

    }

    // ===== Show Total Expense =====
    void showTotal(){

        double total = 0;

        for(int i=0;i<model.getRowCount();i++){

            total += Double.parseDouble(model.getValueAt(i,2).toString());

        }

        JOptionPane.showMessageDialog(this,"Total Expense: "+total);
    }

    // ===== Set Budget =====
    void setBudget(){

        try{
            monthlyBudget = Double.parseDouble(budgetField.getText());
            JOptionPane.showMessageDialog(this,"Budget Set Successfully");

        }catch(HeadlessException | NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Enter valid budget");
        }

    }

    // ===== Budget Alert =====
    void checkBudget(){

        double total = 0;

        for(int i=0;i<model.getRowCount();i++){

            total += Double.parseDouble(model.getValueAt(i,2).toString());

        }

        if(monthlyBudget > 0 && total > monthlyBudget){

            JOptionPane.showMessageDialog(this,
                    "⚠ Budget Limit Exceeded!");

        }
    }

    // ===== Main Method =====
    public static void main(String[] args) {
        ExpenseTracker exp= new ExpenseTracker();
        exp.loadExpenses();

    }
    public String getFileName()
     {
        return fileName;
    }
}