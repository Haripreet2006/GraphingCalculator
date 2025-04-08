package com.panels;

import javax.swing.*;
import java.awt.*;

public class graphpanel extends JPanel {

    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Enable anti-aliasing
        Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Graphics2D axes = (Graphics2D) g;
        axes.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Graphics2D text = (Graphics2D) g;
        text.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Graphics2D intersections = (Graphics2D) g;
        intersections.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Element colours
        setBackground(Color.WHITE);
        axes.setColor(Color.GRAY);
        g1.setColor(Color.GRAY);
        g2.setColor(Color.GRAY);
        text.setColor(Color.BLACK);
        intersections.setColor(Color.YELLOW);

        //
        
    }
}
