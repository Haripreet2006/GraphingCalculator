package com;

import javax.swing.*;

import com.panels.graphpanel;
import com.panels.linePanel;
import com.panels.selector;

import java.awt.*;
import java.util.function.Function;

public class main {



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Basic Graphing Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1280, 720);
            frame.setLayout(null); // Absolute positioning
            frame.setResizable(false);

            // Custom Panel 1 (graph panel)
            graphpanel graphBox = new graphpanel();
            graphBox.setBounds(0, 0, 720, 720); // x, y, width, height
            frame.add(graphBox);

            // Custom Panel 2 (form-style panel)
            linePanel lineEqn = new linePanel(graphBox);
            lineEqn.setBounds(720, 0, 560, 180); // another position
            frame.add(lineEqn);

            frame.setVisible(true);
        });
    }
}
