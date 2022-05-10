/*
Nitin Gupta
3/1/22
Class example
 */

import java.awt.*; // drawing package
import javax.swing.*; // for windows and other core things
import java.util.*;

public class SierpinskiCarpet extends Canvas {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Sierpinski's Carpet");
        frame.setSize(900, 900);

        SierpinskiCarpet sp = new SierpinskiCarpet();
        frame.add(sp);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Override method from Canvas
    public void paint(Graphics g)
    {
        drawCarpet(0, 0, getSize().height, g);
    }

    public void drawCarpet(int x, int y, int side, Graphics g)
    {
        int subDivision = side/3;

        // fill in the middle square...
        g.fillRect(x + subDivision, y + subDivision, subDivision, subDivision);

        if (subDivision >= 3) // arbitrary
        {
            drawCarpet(x, y, subDivision, g); // Top-Left part
            drawCarpet(x + subDivision, y, subDivision, g); // Top-Middle part
            drawCarpet(x + 2*subDivision , y, subDivision, g); // Top-Right part

            drawCarpet(x, y+subDivision, subDivision, g); // Middle-Left part
            // NOT Middle-Middle
            drawCarpet(x + subDivision*2, y+subDivision, subDivision, g); // Middle-Right part

            drawCarpet(x, y+subDivision*2, subDivision, g); // Bottom-Left part
            drawCarpet(x + subDivision, y+subDivision*2, subDivision, g); // Bottom-Middle part
            drawCarpet(x + 2*subDivision , y+subDivision*2, subDivision, g); // Bottom-Right part
        }
    }
}
