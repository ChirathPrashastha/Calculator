import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private JPanel panel;
    private JButton[] buttons;
    private JButton clearButton, backButton;
    private String operator = "";
    private double num1, num2, result;
    private boolean startNewNumber = true;

    Calculator() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        ImageIcon icon = new ImageIcon("cal.png");
        setIconImage(icon.getImage());

        display = new JTextField();
        display.setBackground(new Color(204, 153, 255));
        display.setFont(new Font("Arial", Font.PLAIN, 40));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBorder(BorderFactory.createLineBorder(new Color(153, 51, 255), 3));
        display.setEditable(false);

        panel = new JPanel(new GridLayout(4, 4));
        String[] keys = {"7", "8", "9", "-", "4", "5", "6", "+", "1", "2", "3", "x", "/", "0", ".", "="};
        buttons = new JButton[keys.length];

        for (int i = 0; i < keys.length; i++) {
            buttons[i] = new JButton(keys[i]);
            buttons[i].setFocusable(false);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 25));
            buttons[i].setBackground(new Color(178, 102, 255));
            buttons[i].setForeground(new Color(32, 32, 32));
            buttons[i].setBorder(BorderFactory.createLineBorder(new Color(153, 51, 255), 2));
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(153, 51, 255));

        clearButton = new JButton("C");
        clearButton.setFont(new Font("Arial", Font.BOLD, 25));
        clearButton.setBackground(new Color(102, 102, 255));
        clearButton.setFocusable(false);
        clearButton.addActionListener(this);
        panel2.add(clearButton);

        backButton = new JButton("<");
        backButton.setFont(new Font("Arial", Font.BOLD, 25));
        backButton.setBackground(new Color(102, 102, 255));
        backButton.setFocusable(false);
        backButton.addActionListener(this);
        panel2.add(backButton);

        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9.]")) {
            if (startNewNumber) {
                display.setText(command);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + command);
            }
        } else if (command.matches("[+\\-x/]") && !display.getText().isEmpty()) {
            num1 = Double.parseDouble(display.getText());
            operator = command;
            startNewNumber = true;
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "x": result = num1 * num2; break;
                case "/": result = num1 / num2; break;
            }
            display.setText(String.valueOf(result));
            startNewNumber = true;
        } else if (command.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
            operator = "";
            startNewNumber = true;
        } else if (command.equals("<") && !display.getText().isEmpty()) {
            String text = display.getText();
            display.setText(text.substring(0, text.length() - 1));
        }
    }
}

class Main {
    public static void main(String[] args) {
        new Calculator();
    }
}