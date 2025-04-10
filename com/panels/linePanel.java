package com.panels;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class linePanel extends JPanel 
{
    float m, c;

    public linePanel(graphpanel graph)
    {
        //setting layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("Line Equation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(titleLabel);

        add(Box.createVerticalStrut(15));

        //setting elements
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel firstlabel = new JLabel("y = ");
        JTextField slope = new JTextField("0",3);
        JLabel secondlabel = new JLabel("x + ");
        JTextField constval = new JTextField("0",3);

        //adding elements
        inputPanel.add(firstlabel);
        inputPanel.add(slope);
        inputPanel.add(secondlabel);
        inputPanel.add(constval);
        inputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(inputPanel);

        slope.getDocument().addDocumentListener(new DocumentListener() {
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

                

                String value = slope.getText();
                try {
                    m = Float.parseFloat(value);
                    graph.DrawLine(m, c);

                } catch (NumberFormatException ex) {
                    m = 0;
                }
            }
        });

        constval.getDocument().addDocumentListener(new DocumentListener() {
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
                String value = slope.getText();
                try {
                    c = Float.parseFloat(value);
                    graph.DrawLine(m, c);

                } catch (NumberFormatException ex) {
                    c = 0;
                }
            }
        });
    }
    
}
