package io.github.toygurkutlu.flexswing.objects;

import java.util.Objects;

/**
 * Represents internal gaps between components or the content and the component border.
 *
 * <ul>
 *     <li>If used with container (or parent and component ex CardView), it represents internal gaps between components
 *         and corners,</li>
 *     <li>If used with a component (such as CLabel, CTextField etc.), it represents internal gaps between the content and
 *         the component's border.</li>
 * </ul>
 */
public class Padding {

    private int top;
    private int left;
    private int bottom;
    private int right;

    /**
     * Constructs a new configuration for the internal gaps between components or the content and the component
     * border.
     *
     * @param top    the vertical gap between two components or the content and the component border's top side,
     *               cannot be negative
     * @param left   the horizontal gap between two components or the content and the component border's left side,
     *               cannot be negative
     * @param bottom the vertical gap between two components or the content and the component border's bottom side,
     *               cannot be negative
     * @param right  the horizontal gap between two components or the content and the component border's right side,
     *               cannot be negative
     * @throws IllegalArgumentException if any of the provided padding values are negative
     */
    public Padding(int top, int left, int bottom, int right) {
        if (top < 0) throw new IllegalArgumentException("Top padding cannot be negative.");
        if (left < 0) throw new IllegalArgumentException("Left padding cannot be negative.");
        if (bottom < 0) throw new IllegalArgumentException("Bottom padding cannot be negative.");
        if (right < 0) throw new IllegalArgumentException("Right padding cannot be negative.");
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }

    /**
     * Sets the vertical gap between two components or the content and the component border's top side.
     */
    public int top() {
        return top;
    }

    /**
     * Sets the vertical gap between two components or the content and the component border's top side.
     *
     * @param padding the vertical gap between two components or the content and the component border's top side,
     *                cannot be negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setTop(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.top = padding;
    }

    /**
     * Gets the horizontal gap between two components or the content and the component border's left side.
     */
    public int left() {
        return left;
    }

    /**
     * Sets the horizontal gap between two components or the content and the component border's left side.
     *
     * @param padding the horizontal gap between two components or the content and the component border's left side,
     *                cannot be negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setLeft(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.left = padding;
    }

    /**
     * Gets the vertical gap between two components or the content and the component border's bottom side.
     */
    public int bottom() {
        return bottom;
    }

    /**
     * Sets the vertical gap between two components or the content and the component border's bottom side.
     *
     * @param padding the vertical gap between two components or the content and the component border's bottom side,
     *                cannot be negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setBottom(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.bottom = padding;
    }

    /**
     * Gets the horizontal gap between two components or the content and the component border's right see.
     */
    public int right() {
        return right;
    }

    /**
     * Sets the horizontal gap between two components or the content and the component border's right see.
     *
     * @param padding the horizontal gap between two components or the content and the component border's right side,
     *                cannot be negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setRight(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.right = padding;
    }

    /**
     * Sets the internal gaps between components or the content and the component border.
     *
     * @param padding the padding object for the internal gaps, cannot be {@code null}
     * @throws NullPointerException if the provided {@code padding} object is {@code null}
     */
    public void setPadding(Padding padding) {
        Objects.requireNonNull(padding, "Padding object cannot be null");
        this.top = padding.top();
        this.left = padding.left();
        this.bottom = padding.bottom();
        this.right = padding.right();
    }

    /**
     * Sets the same internal gaps between components or the content and the component border.
     *
     * @param padding the gaps between components or the content and the component border, cannot be negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setPadding(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.top = padding;
        this.left = padding;
        this.bottom = padding;
        this.right = padding;
    }

    /**
     * Sets the internal gaps between components or the content and the component border.
     *
     * @param top    the vertical gap between two components or the content and the component border's top side,
     *               cannot be negative
     * @param left   the horizontal gap between two components or the content and the component border's left side,
     *               cannot be negative
     * @param bottom the vertical gap between two components or the content and the component border's bottom side,
     *               cannot be negative
     * @param right  the horizontal gap between two components or the content and the component border's right side,
     *               cannot be negative
     * @throws IllegalArgumentException if any of the provided padding values are negative
     */
    public void setPadding(int top, int left, int bottom, int right) {
        if (top < 0) throw new IllegalArgumentException("Top padding cannot be negative.");
        if (left < 0) throw new IllegalArgumentException("Left padding cannot be negative.");
        if (bottom < 0) throw new IllegalArgumentException("Bottom padding cannot be negative.");
        if (right < 0) throw new IllegalArgumentException("Right padding cannot be negative.");
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }

    /**
     * Gets the internal gaps between components or the content and the component border.
     *
     * @return the padding object that represents internal gaps
     */
    public Padding getPadding() {
        return this;
    }
}