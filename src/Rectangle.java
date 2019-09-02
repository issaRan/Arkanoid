import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author ISSA 21/4/18
 * the class is used to defince the rectangle.
 */
public class Rectangle {
    private Point topLeft;
    private double width;
    private double height;
    private Rectangle theRectangle;

    /**
     * constructor.
     *
     * @param upperLeft top left of the rectangle.
     * @param width     the width of him.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.topLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.

    /**
     * the function takes a line and checking a intersection with a rectangle(checking with the border of the rectangle)
     * if there are an intersection it will add it to the list that we created.
     * @param line get the line that we want to check intersection.
     * @return a list of intersection point.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Point theTopleft = this.topLeft;
        Point upperRight = new Point(this.topLeft.getX() + width, this.topLeft.getY());
        Point lowerLeft = new Point(this.topLeft.getX(), this.topLeft.getY() + height);
        Point lowerRight = new Point(this.topLeft.getX() + width, this.topLeft.getY() + height);

        Line upperLine = new Line(topLeft, upperRight);
        Line rightLine = new Line(upperRight, lowerRight);
        Line leftLine = new Line(topLeft, lowerLeft);
        Line lowerLine = new Line(lowerLeft, lowerRight);

        Line[] theLinesOftheRectangle = {upperLine, rightLine, leftLine, lowerLine};
        java.util.List<Point> checking = new ArrayList<>();
        for (int i = 0; i < theLinesOftheRectangle.length; i++) {
            if (theLinesOftheRectangle[i].isIntersecting(line)) {
                checking.add(theLinesOftheRectangle[i].intersectionWith(line));
            }
        }
        return checking;
    }

    /**
     * Create a new rectangle with location and width.
     *
     * @return return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Create a new rectangle with location and height.
     *
     * @return return the width of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * the top left of the rectangle.
     *
     * @return Returns the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.topLeft;
    }

    /**
     * the Bottom right of the rectangle.
     *
     * @return the bottom right point of the rectangle.
     */
    public Point getBottomRight() {
        double xBottomRight = this.topLeft.getX() + this.width;
        double yBottomRight = this.topLeft.getY() + this.height;
        return new Point(xBottomRight, yBottomRight);
    }

    /**
     * the top right of the rectangle.
     *
     * @return the point of the upper right of the rectangle.
     */
    public Point getUpperRight() {
        //Adding to x cordinate the width of the rectangle.
        return new Point(this.topLeft.getX() + getWidth(), this.topLeft.getY());
    }

    /**
     * the Bottom left of the rectangle.
     *
     * @return the point of the bottom left of the rectangle.
     */
    public Point getBottomLeft() {
        return new Point(this.topLeft.getX(), this.topLeft.getY() + getHeight());
    }

    /**
     * draw the rectangle on the screen.
     * @param color color of the rectangle.
     * @param d to use the function that relevant to the drawing operation.
     */
    public void drawOnRectangle(Color color, DrawSurface d) {
        Point startRectangle = this.getUpperLeft();
        d.setColor(color);
        d.fillRectangle((int) startRectangle.getX(), (int) startRectangle.getY(), (int) this.width, (int) this.height);
    }
}
