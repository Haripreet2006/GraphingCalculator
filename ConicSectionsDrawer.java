import javax.swing.*;
import java.awt.*;

public class ConicSectionsDrawer extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Setup
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        setBackground(Color.WHITE);

        int width = getWidth();
        int height = getHeight();
        int originX = width / 2;
        int originY = height / 2;

        // === Draw Axes ===
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawLine(0, originY, width, originY); // X-axis
        g2.drawLine(originX, 0, originX, height); // Y-axis

        // === Parabola: y = ax^2 ===
        g2.setColor(Color.BLUE);
        drawSymmetricConic(g2, originX, originY, -300, 300, (x) -> 0.01 * x * x);

        // === Circle: x^2 + y^2 = r^2 ⇒ y = ±√(r^2 - x^2) ===
        g2.setColor(Color.RED);
        int r = 100;
        drawSymmetricConic(g2, originX, originY, -r, r, x -> Math.sqrt(r * r - x * x));

        // === Ellipse: x^2/a^2 + y^2/b^2 = 1 ⇒ y = ±b√(1 - x^2/a^2) ===
        g2.setColor(Color.GREEN.darker());
        int a = 150, b = 80;
        drawSymmetricConic(g2, originX, originY, -a, a, x -> b * Math.sqrt(1 - (x * x) / (double)(a * a)));

        // === Hyperbola: x^2/a^2 - y^2/b^2 = 1 ⇒ y = ±b√(x^2/a^2 - 1), for |x| > a ===
        g2.setColor(Color.MAGENTA);
        drawSymmetricConic(g2, originX, originY, -300, -a - 1, x -> b * Math.sqrt((x * x) / (double)(a * a) - 1));
        drawSymmetricConic(g2, originX, originY, a + 1, 300, x -> b * Math.sqrt((x * x) / (double)(a * a) - 1));
    }

    // Method to draw one-branched conics like parabolas (y = f(x))
    private void drawConic(Graphics2D g2, int originX, int originY, int xStart, int xEnd, Function f) {
        int prevX = 0, prevY = 0;
        boolean first = true;
        for (int x = xStart; x <= xEnd; x++) {
            double y = f.compute(x);
            int drawX = originX + x;
            int drawY = originY - (int) y;
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

    // Functional interface for generic conic drawing
    interface Function {
        double compute(int x);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Conic Sections: Parabola, Circle, Ellipse, Hyperbola");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setContentPane(new ConicSectionsDrawer());
        frame.setVisible(true);
    }
}

