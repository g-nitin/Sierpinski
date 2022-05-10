/*
Nitin Gupta
Homework 04

Triangles! Oh Fractals!
 */

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SierpenskisTriangle extends Canvas {

    public static final Random r = new Random(); // for randomly generating colors

    public static void main(String[] args) {

        JFrame frame = new JFrame("Sierpinski's Triangle");
        frame.setSize(900, 900); // the size of the window

        SierpenskisTriangle st = new SierpenskisTriangle();
        frame.add(st);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    // override method from canvas
    public void paint(Graphics g)
    {
        // first draw that main triangle...

        int side = getSize().height; // the side of the window

        // x-coordinates...
        int pointX1 = side/2; // top
        int pointX2 = 0; // lower left
        int pointX3 = side; // lower right

        // y-coordinates...
        int pointY1 = 0; // top
        int pointY2 = side; // lower left
        int pointY3 = side; // lower right

        // put into arrays...
        int[] xArray = {pointX1, pointX2, pointX3};
        int[] yArray = {pointY1, pointY2, pointY3};

        // get random colors values (from 0 to 255)
        int red = r.nextInt(256);
        int green = r.nextInt(256);
        int blue = r.nextInt(256);

        g.setColor(new Color(red, green, blue)); // get and set the new color
        g.fillPolygon(xArray, yArray, xArray.length);

        // get new random colors values (from 0 to 255) for the recursive triangles
        red = r.nextInt(256);
        green = r.nextInt(256);
        blue = r.nextInt(256);

        drawTriangle(xArray, yArray, getSize().height, g, red, green, blue);

    }

    public void drawTriangle(int[] xPoints, int[] yPoints, int side, Graphics g,
                            int red, int green, int blue)
    {
        int subDivision = side/3;

        if (subDivision >= 4) // arbitrary pixel limit
        {
            // draw triangle that is flipped...

            int midPointX1 = (xPoints[0] + xPoints[1])/2; // left x
            int midPointX2 = (xPoints[1] + xPoints[2])/2; // bottom middle x
            int midpointX3 = (xPoints[2] + xPoints[0])/2; // right x

            int midPointY1 = (yPoints[0] + yPoints[1])/2; // left y
            int midPointY2 = (yPoints[1] + yPoints[2])/2; // bottom middle y
            int midpointY3 = (yPoints[2] + yPoints[0])/2; // right y

            int[] xArray2 = {midPointX1, midPointX2, midpointX3};
            int[] yArray2 = {midPointY1, midPointY2, midpointY3};


            g.setColor(new Color(red, green, blue)); // get and set the new color
            g.fillPolygon(xArray2, yArray2, xArray2.length);

            // for recursive calls, inside the parameters for drawTriangle(),
            // need to give the boundary of the area for the next triangle to be drawn in.

            // Left triangle
            drawTriangle(new int[] {midPointX1, xPoints[1], midPointX2},
                         new int[] {midPointY1, yPoints[1], midPointY2},
                         subDivision, g, red, green, blue);

            // Right Triangle
            drawTriangle(new int[] {midpointX3, midPointX2, xPoints[2]},
                         new int[] {midPointY1, midPointY2, yPoints[2]},
                         subDivision, g, red, green, blue);

            // Top Triangle
            drawTriangle(new int[] {xPoints[0], midPointX1, midpointX3},
                         new int[] {yPoints[0], midPointY1, midpointY3},
                         subDivision, g, red, green, blue);
        }

    }

}
