package geekbrains.java.dubovik.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    private final JTextField textField;
    private char operation;
    private double total;
    private boolean isFirstDigit;
    private boolean isLastDigit;
    private boolean cleanTextField;

    public MyFrame() {
        setTitle("Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(860, 400, 250, 350);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        isFirstDigit = true;
        total = 0;

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(250, 70));
        Font resFont = textField.getFont().deriveFont(Font.PLAIN, 40f);
        textField.setFont(resFont);
        textField.setEditable(false);
        textField.setBackground(Color.lightGray);
        panel1.setBackground(Color.lightGray);
        panel1.add(textField);

        panel2.setPreferredSize(new Dimension(250,280));
        panel2.setLayout(new GridLayout(5,4));
        panel2.setBackground(Color.lightGray);

        JButton[] jbs = new JButton[9];

        Font numFont = new Font(Font.DIALOG, Font.PLAIN, 18);
        for (int i = 0; i < jbs.length; i++) {
            jbs[i] = new JButton(String.valueOf(i + 1));
            jbs[i].setFont(numFont);
            jbs[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    action(e);
                }
            });
        }
        JButton buttonZero = new JButton("0");
        buttonZero.setFont(numFont);
        JButton button000 = new JButton("000");
        button000.setFont(numFont);

        Font actionFont = new Font(Font.DIALOG, Font.PLAIN, 25);
        JButton buttonCancel = new JButton("C");
        buttonCancel.setFont(actionFont);
        JButton buttonDelete = new JButton("\u21E4");
        buttonDelete.setFont(actionFont);
        JButton buttonSquare = new JButton("x\u00B2");
        buttonSquare.setFont(actionFont);
        JButton buttonPlus = new JButton("+");
        buttonPlus.setFont(actionFont);
        JButton buttonMinus = new JButton("-");
        buttonMinus.setFont(actionFont);
        JButton buttonMulti = new JButton("\u00D7");
        buttonMulti.setFont(actionFont);
        JButton buttonDivide = new JButton("\u00F7");
        buttonDivide.setFont(actionFont);
        JButton buttonPoint = new JButton(".");
        buttonPoint.setFont(actionFont);
        JButton buttonEquals = new JButton("=");
        buttonEquals.setFont(actionFont);

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });

        button000.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });

        buttonMulti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });

        buttonDivide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });

        buttonSquare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });

        buttonPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });

        buttonZero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });

        buttonPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });

        buttonMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });

        buttonEquals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculating(e);
            }
        });

        panel2.add(buttonCancel);
        panel2.add(buttonDelete);
        panel2.add(buttonSquare);
        panel2.add(buttonDivide);

        panel2.add(jbs[6]);
        panel2.add(jbs[7]);
        panel2.add(jbs[8]);
        panel2.add(buttonMulti);

        panel2.add(jbs[3]);
        panel2.add(jbs[4]);
        panel2.add(jbs[5]);
        panel2.add(buttonMinus);

        panel2.add(jbs[0]);
        panel2.add(jbs[1]);
        panel2.add(jbs[2]);
        panel2.add(buttonPlus);

        panel2.add(button000);
        panel2.add(buttonZero);
        panel2.add(buttonPoint);
        panel2.add(buttonEquals);

        add(panel1);
        add(panel2);

        setVisible(true);
    }

    /**
     * 1) Берём текст из текстового поля
     * 2) Идём по каждому символу пока не встретим + или -
     * 3) Затем переводим наше число из String в Double
     * 4) Записываем текущий символ(+ или -)
     * 5) Если у нас уже записано одно число, проводим операцию и переходим на шаг 2, иначе переходим на шаг 2
     *
     * @param e
     */
    private void calculating(ActionEvent e) {
        String text = textField.getText();
        String digit = "";
        double currentValue;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '-' || c == '+' || c == '\u00D7' || c == '\u00F7' || c == '\u00B2') {
                currentValue = Double.parseDouble(digit);
                if (isFirstDigit) {
                    total += currentValue;
                    isFirstDigit = false;
                } else {
                    operation(operation, currentValue);
                }

                digit = "";
                operation = c;
                continue;
            }

            digit += c;
        }

        if (operation == '\u00B2') currentValue = total;
            else currentValue = Double.parseDouble(digit);
        operation(operation, currentValue);

        isFirstDigit = true;
        textField.setText(String.valueOf(total));
        cleanTextField = true;
        total = 0;
    }

    public void operation (char operand, double currentValue) {
        switch (operation) {
            case '-':
                total -= currentValue;
                break;
            case '+':
                total += currentValue;
                break;
            case '\u00D7':
            case '\u00B2':
                total = total * currentValue;
                break;
            case '\u00F7':
                total = total / currentValue;
                break;
        }
    }

    public void action(ActionEvent event) {
        String e = event.getActionCommand();

        switch (e) {
            case "C":
                textField.setText("");
                break;

            case "\u21E4":
                String text = textField.getText();
                StringBuilder strBuilder = new StringBuilder(text);
                int lastPosition = text.length()-1;
                strBuilder.deleteCharAt(lastPosition);
                textField.setText(strBuilder.toString());
                break;

            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":
                if (cleanTextField) {
                    textField.setText("");
                    cleanTextField = false;
                }
                textField.setText(textField.getText() + e);
                isLastDigit = true;
                break;

            case "000":
                if (isLastDigit && !cleanTextField) {
                    textField.setText(textField.getText() + e);
                    isLastDigit = true;
                }
                break;

            default:
                if (e.equals("x\u00B2")) e = "\u00B2";
                if (isLastDigit) {
                    textField.setText(textField.getText() + e);
                }
                else {
                    text = textField.getText();
                    strBuilder = new StringBuilder(text);
                    lastPosition = text.length();
                    strBuilder.replace(lastPosition-1, lastPosition, e);
                    textField.setText(strBuilder.toString());
                }
                isLastDigit = false;
                cleanTextField = false;
                break;
        }
    }
}