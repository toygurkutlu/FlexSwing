package io.github.toygurkutlu.flexswing.objects;


import java.util.Objects;

/**
 * Represents the corner radii of the component border.
 */
public class Radii {

    private int topLeft;
    private int topRight;
    private int bottomLeft;
    private int bottomRight;

    /**
     * Constructs a new configuration for the component border's corners.
     *
     * @param topLeft     radius of the top left corner, cannot be negative
     * @param topRight    radius of the top right corner, cannot be negative
     * @param bottomLeft  radius of the bottom left corner, cannot be negative
     * @param bottomRight radius of the bottom right corner, cannot be negative
     * @throws IllegalArgumentException if any of the provided radius values are negative
     */
    public Radii(int topLeft, int topRight, int bottomLeft, int bottomRight) {
        if (topLeft < 0) throw new IllegalArgumentException("TopLeft radius cannot be negative.");
        if (topRight < 0) throw new IllegalArgumentException("TopRight radius cannot be negative.");
        if (bottomLeft < 0) throw new IllegalArgumentException("BottomLeft radius cannot be negative.");
        if (bottomRight < 0) throw new IllegalArgumentException("BottomRight radius cannot be negative.");
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }

    /**
     * Gets the top left corner radius of the component border.
     *
     * @return the top left corner radius
     */
    public int topLeft() {
        return topLeft;
    }

    /**
     * Sets the top left corner radius of the component border.
     *
     * @param radius the new top left corner radius, cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setTopLeft(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        this.topLeft = radius;
    }

    /**
     * Gets the top right corner radius of the component border.
     *
     * @return the top right corner radius
     */
    public int topRight() {
        return topRight;
    }

    /**
     * Sets the top right corner radius of the component border.
     *
     * @param radius the new top right corner radius, cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setTopRight(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        this.topRight = radius;
    }

    /**
     * Gets the bottom left corner radius of the component border.
     *
     * @return the bottom left corner radius
     */
    public int bottomLeft() {
        return bottomLeft;
    }

    /**
     * Sets the bottom left corner radius of the component border.
     *
     * @param radius the new bottom left corner radius, cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setBottomLeft(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        this.bottomLeft = radius;
    }

    /**
     * Gets the bottom right corner radius of the component border.
     *
     * @return the bottom right corner radius
     */
    public int bottomRight() {
        return bottomRight;
    }

    /**
     * Sets the bottom right corner radius of the component border.
     *
     * @param radius the new bottom right corner radius, cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setBottomRight(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        this.bottomRight = radius;
    }

    /**
     * Sets the same radius for all corners of the {@code CTextField} border.
     *
     * @param radius the radius of the all corners, cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setRadii(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        this.topLeft = radius;
        this.topRight = radius;
        this.bottomLeft = radius;
        this.bottomRight = radius;
    }

    /**
     * Sets the component border's corner {@code radii} according to the provided {@code radii} object.
     *
     * @param radii the new {@code radii} object, cannot be {@code null}
     * @throws NullPointerException if the provided {@code radii} object is {@code null}
     */
    public void setRadii(Radii radii) {
        Objects.requireNonNull(radii, "Radii object cannot be null");
        this.topLeft = radii.topLeft();
        this.topRight = radii.topRight();
        this.bottomLeft = radii.bottomLeft();
        this.bottomRight = radii.bottomRight();
    }

    /**
     * Sets the all corners' radius of the component border according to provided values.
     *
     * @param topLeft     radius of the top left corner, cannot be negative
     * @param topRight    radius of the top right corner, cannot be negative
     * @param bottomLeft  radius of the bottom left corner, cannot be negative
     * @param bottomRight radius of the bottom right corner, cannot be negative
     * @throws IllegalArgumentException if any of the provided radius values are negative
     */
    public void setRadii(int topLeft, int topRight, int bottomLeft, int bottomRight) {
        if (topLeft < 0) throw new IllegalArgumentException("TopLeft radius cannot be negative.");
        if (topRight < 0) throw new IllegalArgumentException("TopRight radius cannot be negative.");
        if (bottomLeft < 0) throw new IllegalArgumentException("BottomLeft radius cannot be negative.");
        if (bottomRight < 0) throw new IllegalArgumentException("BottomRight radius cannot be negative.");
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }

    public Radii getRadii() {
        return this;
    }
}