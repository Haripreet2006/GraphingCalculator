package com.panels;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class selector extends JPanel {
    public selector() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title Label
        JLabel titleLabel = new JLabel("Main Title");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(titleLabel);

        add(Box.createVerticalStrut(10)); // spacing

        // Panel with small label and text field
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel smallLabel = new JLabel("Value:");
        JTextField textField = new JTextField("0", 3);
        inputPanel.add(smallLabel);
        inputPanel.add(textField);
        inputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(inputPanel);

        // Value change listener using try-catch for float parsing
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                onChange();
            }

            public void removeUpdate(DocumentEvent e) {
                onChange();
            }

            public void insertUpdate(DocumentEvent e) {
                onChange();
            }

            private void onChange() {
                String value = textField.getText();
                try {
                    float f = Float.parseFloat(value);
                    System.out.println("Parsed float: " + f);
                    // you can trigger more actions here
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid float: " + value);
                    // Optional: show error or reset
                }
            }
        });
    }
}

