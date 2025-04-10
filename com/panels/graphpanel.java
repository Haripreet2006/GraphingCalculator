package com.panels;

import javax.swing.*;
import java.awt.*;

public class graphpanel extends JPanel {
    float mL, cL = 0.0f;

    public void DrawLine(float slope, float constval)
    {
        this.mL = slope;
        this.cL = constval;
        repaint();
    }

    interface Function {
        double compute(int x);
    }
        // Method to draw one-branched conics like parabolas (y = f(x))
    private void drawConic(Graphics2D g2, int originX, int originY, int xStart, int xEnd, Function f, float cLe) {
        int prevX = 0, prevY = 0;
        boolean first = true;
        for (int x = xStart; x <= xEnd; x++) {
            double y = f.compute(x) + cLe*24;
            int drawX = originX + x;
            int drawY = originY - (int) y ;
            g2.fillOval(drawX - 2, drawY - 2, 4, 4);
            if (!first) {
                g2.drawLine(prevX, prevY, drawX, drawY);
            } else {
                first = false;
            }
            prevX = drawX;
            prevY = drawY;
        }
    }

    // Method to draw symmetric conics like circle/ellipse/hyperbola (y = ±f(x))
    private void drawSymmetricConic(Graphics2D g2, int originX, int originY, int xStart, int xEnd, Function f) {
        int prevTopX = 0, prevTopY = 0, prevBottomX = 0, prevBottomY = 0;
        boolean first = true;
        for (int x = xStart; x <= xEnd; x++) {
            double y;
            try {
                y = f.compute(x);
            } catch (Exception e) {
                continue; // skip invalid values (like sqrt of negative)
            }

            int drawX = originX + x;
            int drawTopY = originY - (int) y;
            int drawBottomY = originY + (int) y;

            // Top point
            g2.fillOval(drawX - 2, drawTopY - 2, 4, 4);
            // Bottom point
            g2.fillOval(drawX - 2, drawBottomY - 2, 4, 4);

            if (!first) {
                g2.drawLine(prevTopX, prevTopY, drawX, drawTopY);
                g2.drawLine(prevBottomX, prevBottomY, drawX, drawBottomY);
            } else {
                first = false;
            }

            prevTopX = drawX;
            prevTopY = drawTopY;
            prevBottomX = drawX;
            prevBottomY = drawBottomY;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Enable anti-aliasing
        Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Graphics2D g3 = (Graphics2D) g;
        g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Graphics2D g4 = (Graphics2D) g;
        g4.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Graphics2D axes = (Graphics2D) g;
        axes.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Graphics2D text = (Graphics2D) g;
        text.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Graphics2D intersections = (Graphics2D) g;
        intersections.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Element colours
        setBackground(Color.WHITE);

        //drawing axis
        axes.setColor(Color.LIGHT_GRAY);
        int width = getWidth();
        int height = getHeight();
        int originX = width / 2;
        int originY = height / 2;
        axes.drawLine(0, originY, width, originY); // X-axis
        axes.drawLine(originX, 0, originX, height); // Y-axis

        //line graph
        g1.setColor(Color.RED);
        drawConic(g2, originX, originY, -360, 360, x -> (mL*x), cL);

        g2.setColor(Color.BLUE);
        g3.setColor(Color.ORANGE);
        g4.setColor(Color.GREEN);
        text.setColor(Color.BLACK);
        intersections.setColor(Color.YELLOW);
        
    }
}
