package io.github.toygurkutlu.flexswing.components;


import io.github.toygurkutlu.flexswing.objects.FlexUtil;
import io.github.toygurkutlu.flexswing.objects.Padding;
import io.github.toygurkutlu.flexswing.objects.Radii;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Area;
import java.util.Objects;

/**
 * A lightweight and customizable component which extends from {@link JTextField}.
 * <p>
 * {@code FTextField} allows the editing of a single line of text.
 * </p>
 *
 * <p>Use the following methods to customize attributes:</p>
 * <ol>
 *     <li><strong>Corner radii:</strong>
 *         <ul>
 *             <li>{@link #setRadii(int)}</li>
 *             <li>{@link #setRadii(Radii)}</li>
 *             <li>{@link #setRadii(int, int, int, int)}</li>
 *             <li>{@link #setTopLeftRadius(int)}</li>
 *             <li>{@link #setTopRightRadius(int)}</li>
 *             <li>{@link #setBottomLeftRadius(int)}</li>
 *             <li>{@link #setBottomRightRadius(int)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Internal gaps:</strong>
 *         <ul>
 *             <li>{@link #setPadding(int)}</li>
 *             <li>{@link #setPadding(Padding)}</li>
 *             <li>{@link #setPadding(int, int, int, int)}</li>
 *             <li>{@link #setTopPadding(int)}</li>
 *             <li>{@link #setLeftPadding(int)}</li>
 *             <li>{@link #setBottomPadding(int)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Border:</strong>
 *         <ul>
 *             <li>{@link #setBorderThickness(int)}</li>
 *             <li>{@link #setBorderColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Background color:</strong>
 *         <ul>
 *             <li>{@link #setBackgroundColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Text color:</strong>
 *         <ul>
 *             <li>{@link #setTextColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Text font:</strong>
 *         <ul>
 *             <li>{@link #setTextFont(Font)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Baseline color:</strong>
 *         <ul>
 *             <li>{@link #setHasBaseline(boolean)}</li>
 *             <li>{@link #setBaselineColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Caret color:</strong>
 *         <ul>
 *             <li>{@link #setCaretColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Text selection:</strong>
 *         <ul>
 *             <li>{@link #setSelectionColor(Color)}</li>
 *             <li>{@link #setSelectedTextColor(Color)}</li>
 *         </ul>
 *     </li>
 * </ol>
 *
 * @see JTextField
 * @see Radii
 * @see Padding
 */
public class FlexTextField extends JTextField {
    private Radii radii = new Radii(15, 15, 15, 15);
    private final Padding padding = new Padding(5, 8, 5, 8);
    private int borderThickness = 1;
    private Font font = new Font("Arial", Font.PLAIN, 14);
    private Color foreground = new Color(255, 255, 255);
    private Color background = new Color(40, 40, 40);
    private Color borderColor = new Color(80, 80, 80);
    private Color baselineColor = new Color(100, 100, 100);
    private boolean hasBaseline = true;

    /**
     * Constructs a new TextField. A default model is created, the initial string is null, and the number of
     * columns is set to 0.
     */
    public FlexTextField() {
        super();
        init();
    }

    /**
     * Constructs a new TextField initialized with the specified text. A default model is created and the number
     * of columns is 0.
     *
     * @param text the text to be displayed, or {@code null}
     */
    public FlexTextField(String text) {
        super(text);
        init();
    }

    /**
     * Constructs a new empty TextField with the specified number of columns. A default model is created and
     * the initial string is set to {@code null}.
     *
     * @param columns the number of columns to use to calculate the preferred width; if columns is set to zero,
     *                the preferred width will be whatever naturally results from the component implementation
     */
    public FlexTextField(int columns) {
        super(columns);
        init();
    }

    /**
     * Constructs a new TextField initialized with the specified text and columns. A default model is created.
     *
     * @param text    the text to be displayed, or {@code null}
     * @param columns the number of columns to use to calculate the preferred width; if columns is set to zero,
     *                the preferred width will be whatever naturally results from the component implementation
     */
    public FlexTextField(String text, int columns) {
        super(text, columns);
        init();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Area area = new Area(FlexUtil.createRoundTopLeft(this, radii.topLeft(), borderThickness));

        if (radii.topRight() > 0) {
            area.intersect(new Area(FlexUtil.createRoundTopRight(this, radii.topRight(), borderThickness)));
        }
        if (radii.bottomLeft() > 0) {
            area.intersect(new Area(FlexUtil.createRoundBottomLeft(this, radii.bottomLeft(), borderThickness)));
        }
        if (radii.bottomRight() > 0) {
            area.intersect(new Area(FlexUtil.createRoundBottomRight(this, radii.bottomRight(), borderThickness)));
        }

        g2.setColor(background);
        g2.fill(area);

        if (hasBaseline) {
            g2.setColor(baselineColor);
            g2.drawLine(startPoint().x, startPoint().y, endPoint().x, endPoint().y);
        }

        if (borderThickness > 0) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderThickness));
            g2.draw(area);
        }

        g2.dispose();

        super.paintComponent(g);
    }

    /**
     * Gets whether the baseline is painted.
     *
     * @return {@code true} if baseline painted; {@code false} otherwise
     */
    public boolean isHasBaseline() {
        return hasBaseline;
    }

    /**
     * Sets  whether the baseline is painted.
     *
     * @param hasBaseline {@code true} if the baseline is painted; {@code false} otherwise
     */
    public void setHasBaseline(boolean hasBaseline) {
        this.hasBaseline = hasBaseline;
        update();
    }

    /**
     * Gets the color of the baseline of the {@code textField}.
     *
     * @return the baseline color
     */
    public Color getBaselineColor() {
        return baselineColor;
    }

    /**
     * Sets the baseline color of the {@code textField}.
     *
     * @param color the new color of the baseline, cannot be {@code null}
     * @throws NullPointerException if the {@code color} is {@code null}
     */
    public void setBaselineColor(Color color) {
        this.baselineColor = Objects.requireNonNull(color, "Color cannot be  null");
        update();
    }

    /**
     * Gets the font of the text.
     *
     * @return the text font
     */
    public Font getTextFont() {
        return font;
    }

    /**
     * Sets the font of the text.
     *
     * @param font the new font of the text, cannot be {@code null}
     * @throws NullPointerException if the {@code font} is {@code null}
     */
    public void setTextFont(Font font) {
        this.font = Objects.requireNonNull(font, "Font cannot be null");
        super.setFont(font);
        update();
    }
    /**
     * Gets the foreground color of the text.
     *
     * @return the foreground color
     */
    public Color getTextColor() {
        return foreground;
    }

    /**
     * Sets the foreground color of the text.
     *
     * @param color the new foreground color, cannot be {@code null}
     * @throws NullPointerException if the {@code color} is {@code null}
     */
    public void setTextColor(Color color) {
        this.foreground = Objects.requireNonNull(color, "Color cannot be null.");
        super.setForeground(color);
        update();
    }

    /**
     * Gets the background color of the {@code textField}.
     *
     * @return the background color
     */
    public Color getBackgroundColor() {
        return background;
    }

    /**
     * Sets the background color of the {@code textField}.
     *
     * @param color the new background color, cannot be {@code null}
     * @throws NullPointerException if the {@code color} is {@code null}
     */
    public void setBackgroundColor(Color color) {
        this.background = Objects.requireNonNull(color, "Color cannot be null.");
        update();
    }

    /**
     * Gets the border color of the {@code textField}.
     *
     * @return the border color
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * Sets the border color of the {@code textField}.
     *
     * @param color the new border color, cannot be {@code null}
     * @throws NullPointerException if the {@code color} is {@code null}
     */
    public void setBorderColor(Color color) {
        this.borderColor = Objects.requireNonNull(color, "Color cannot be null.");
        update();
    }

    /**
     * Gets the border thickness of the {@code textField}.
     *
     * @return the thickness of the border
     */
    public int getBorderThickness() {
        return borderThickness;
    }

    /**
     * Sets the border thickness of the {@code textField}.
     *
     * @param thickness the new thickness of the border, cannot be negative
     * @throws IllegalArgumentException if the provided {@code thickness} is negative
     */
    public void setBorderThickness(int thickness) {
        if (thickness < 0) throw new IllegalArgumentException("Thickness cannot be negative.");
        this.borderThickness = thickness;
        update();
    }

    /**
     * Gets the corner radii of the {@code textField} border.
     *
     * @return the radii of the border
     */
    public Radii getRadii() {
        return radii;
    }

    /**
     * Sets the same radius for all corners of the {@code textField} border.
     *
     * @param radius the corner radius, cannot be negative
     * @throws IllegalArgumentException if the {@code radius} is negative
     */
    public void setRadii(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        this.radii.setRadii(radius);
        update();
    }

    /**
     * Sets the corner radii of the {@code textField} border according to the provided {@code raii} object.
     *
     * @param radii the new {@code radii} object, cannot be {@code null}
     * @throws NullPointerException if the provided {@code radii} object is {@code null}
     * @see Radii
     * @see Radii#Radii(int, int, int, int)
     */
    public void setRadii(Radii radii) {
        this.radii = Objects.requireNonNull(radii, "Radii object cannot be null.");
        update();
    }

    /**
     * Sets the all corners' radius of the {@code textField} border according to provided values.
     *
     * @param topLeft     radius of the top left corner, cannot be negative
     * @param topRight    radius of the top right corner, cannot be negative
     * @param bottomLeft  radius of the bottom left corner, cannot be negative
     * @param bottomRight radius of the bottom right corner, cannot be negative
     * @throws IllegalArgumentException if any of the provided values are negative
     */
    public void setRadii(int topLeft, int topRight, int bottomLeft, int bottomRight) {
        if (topLeft < 0) throw new IllegalArgumentException("TopLeft radius cannot be negative.");
        if (topRight < 0) throw new IllegalArgumentException("TopRight radius cannot be negative.");
        if (bottomLeft < 0) throw new IllegalArgumentException("BottomLeft radius cannot be negative.");
        if (bottomRight < 0) throw new IllegalArgumentException("BottomRight radius cannot be negative.");
        this.radii.setRadii(topLeft, topRight, bottomLeft, bottomRight);
        update();
    }

    /**
     * Gets the top left corner radius of the {@code textField} border.
     *
     * @return the top left corner radius
     */
    public int getTopLeftRadius() {
        return radii.topLeft();
    }

    /**
     * Sets the top left corner radius of the {@code textField} border.
     *
     * @param radius the new top left corner radius, cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setTopLeftRadius(int radius) {
        if(radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        radii.setTopLeft(radius);
        update();
    }

    /**
     * Gets the top right corner radius of the {@code textField} border.
     *
     * @return the top right corner radius
     */
    public int getTopRightRadius() {
        return radii.topRight();
    }

    /**
     * Sets the top right corner radius of the {@code textField} border.
     *
     * @param radius the new top right corner radius, cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setTopRightRadius(int radius) {
        if(radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        radii.setTopRight(radius);
        update();
    }

    /**
     * Gets the bottom left corner radius of the {@code textField} border.
     *
     * @return the bottom left corner radius
     */
    public int getBottomLeftRadius() {
        return radii.bottomLeft();
    }

    /**
     * Sets the bottom left corner radius of the {@code textField} border.
     *
     * @param radius the new bottom left corner radius, cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setBottomLeftRadius(int radius) {
        if(radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        radii.setBottomLeft(radius);
        update();
    }

    /**
     * Gets the bottom right corner radius of the {@code textField} border.
     *
     * @return the bottom right corner radius
     */
    public int getBottomRightRadius() {
        return radii.bottomRight();
    }

    /**
     * Sets the bottom right corner radius of the {@code textField} border.
     *
     * @param radius the new bottom right corner radius, cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setBottomRightRadius(int radius) {
        if(radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        radii.setBottomRight(radius);
        update();
    }

    /**
     * Gets the internal gaps between the input area and the {@code textField} border.
     *
     * @return internal gaps
     */
    public Padding getPadding() {
        return padding;
    }

    /**
     * Sets the internal gaps between the input area and the {@code textField} border according to provided {@code padding}
     * object.
     *
     * @param padding the new {@code padding} object, cannot be {@code null}
     * @throws NullPointerException if the provided {@code padding} object is {@code null}
     * @see Padding
     * @see Padding#Padding(int, int, int, int)
     */
    public void setPadding(Padding padding) {
        this.padding.setPadding(Objects.requireNonNull(padding, "Padding object cannot be null."));
        updatePadding();
    }

    /**
     * Sets the same internal gaps for all directions between the input area and the {@code textField}
     *
     * @param padding the new internal gaps for all directions, cannot be negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setPadding(int padding) {
        if(padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.padding.setPadding(padding);
        updatePadding();
    }

    /**
     * Sets the internal gaps for all directions between the input area and the {@code textField} according to
     * the provided gaps.
     *
     * @param top    the internal gap between the input area and the {@code textField} border's top side,
     *               cannot be negative
     * @param left   the internal gap between the input area and the {@code textField} border's left side,
     *               cannot be negative
     * @param bottom the internal gap between the input area and the {@code textField} border's bottom side,
     *               cannot be negative
     * @param right  the internal gap between the input area and the {@code textField} border's right side,
     *               cannot be negative
     * @throws IllegalArgumentException if any of the provided padding values are negative
     */
    public void setPadding(int top, int left, int bottom, int right) {
        if(top < 0) throw new IllegalArgumentException("Top padding cannot be negative.");
        if(left < 0) throw new IllegalArgumentException("Left padding cannot be negative.");
        if(bottom < 0) throw new IllegalArgumentException("Bottom padding cannot be negative.");
        if(right < 0) throw new IllegalArgumentException("Right padding cannot be negative.");
        this.padding.setPadding(top, left, bottom, right);
        updatePadding();
    }

    /**
     * Gets the internal gap between the input area and the {@code textField} border's top side.
     *
     * @return the internal gap between the input area and the {@code textField} border's top side
     */
    public int getTopPadding() {
        return padding.top();
    }

    /**
     * Sets the internal gap between the input area and the {@code textField} border's top side.
     *
     * @param padding the internal gap between the input area and the {@code textField} border's top side,
     *                cannot be negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setTopPadding(int padding) {
        if(padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.padding.setTop(padding);
        updatePadding();
    }

    /**
     * Gets the internal gap between the input area and the {@code textField} border's left side.
     *
     * @return the internal gap between the input area and the {@code textField} border's left side
     */
    public int getLeftPadding() {
        return padding.left();
    }

    /**
     * Sets the internal gap between the input area and the {@code textField} border's left side.
     *
     * @param padding the internal gap between the input area and the {@code textField} border's left side,
     *                cannot be negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setLeftPadding(int padding) {
        if(padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.padding.setLeft(padding);
        updatePadding();
    }

    /**
     * Gets the internal gap between the input area and the {@code textField} border's bottom side.
     *
     * @return the internal gap between the input area and the {@code textField} border's bottom side
     */
    public int getBottomPadding() {
        return padding.bottom();
    }

    /**
     * Sets the internal gap between the input area and the {@code textField} border's bottom side.
     *
     * @param padding the internal gap between the input area and the {@code textField} border's bottom side,
     *                cannot be negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setBottomPadding(int padding) {
        if(padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.padding.setBottom(padding);
        updatePadding();
    }

    /**
     * Gets the internal gap between the input area and the {@code textField} border's right side.
     *
     * @return the internal gap between the input area and the {@code textField} border's right side
     */
    public int getRightPadding() {
        return padding.right();
    }

    /**
     * Sets the internal gap between the input area and the {@code textField} border's right side.
     *
     * @param padding the internal gap between the input area and the {@code textField} border's right side,
     *                cannot be negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setRightPadding(int padding) {
        if(padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.padding.setRight(padding);
        updatePadding();
    }

    private Point startPoint() {
        return new Point(getInsets().left, getYCoordinate());
    }

    private Point endPoint() {
        return new Point(getWidth() - getInsets().right, getYCoordinate());
    }

    private int getYCoordinate() {
        return getHeight() - getInsets().bottom - 1;
    }

    private void updatePadding() {
        int gapTop = padding.top();
        int gapLeft = padding.left();
        int gapBottom = padding.bottom();
        int gapRight = padding.right();

        setBorder(BorderFactory.createEmptyBorder(gapTop, gapLeft, gapBottom, gapRight));
    }

    private void init() {
        getData();

        setOpaque(false);
        setSelectionColor(new Color(200, 200, 200));
        setSelectedTextColor(new Color(40, 40, 40));
        updatePadding();
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
    }

    private void getData() {
        super.setFont(font);
        super.setForeground(foreground);
        setBackgroundColor(background);
        setCaretColor(Color.WHITE);
    }

    private void update() {
        revalidate();
        repaint();
    }
}