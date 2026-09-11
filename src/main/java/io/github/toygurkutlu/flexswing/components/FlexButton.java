package io.github.toygurkutlu.flexswing.components;


import io.github.toygurkutlu.flexswing.objects.FlexUtil;
import io.github.toygurkutlu.flexswing.objects.Padding;
import io.github.toygurkutlu.flexswing.objects.Radii;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.util.Objects;

/**
 * Customizable of a "push" button which extends from {@link JButton}.
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
 *             <li>{@link #setHasBorder(boolean)}</li>
 *             <li>{@link #setBorderThickness(int)}</li>
 *             <li>{@link #setBorderColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Background color:</strong>
 *         <ul>
 *             <li>{@link #setBackgroundColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Foreground color:</strong>
 *         <ul>
 *             <li>{@link #setTextColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Text font:</strong>
 *         <ul>
 *             <li>{@link #setTextFont(Font)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Icon:</strong>
 *         <ul>
 *             <li>{@link #setIconPosition(IconPosition)}</li>
 *             <li>{@link #setIconTextGap(int)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Hover effect</strong>
 *         <ul>
 *             <li>{@link #setHoverIsEnabled(boolean)}</li>
 *             <li>{@link #setHoverTextColor(Color)}</li>
 *             <li>{@link #setHoverBackgroundColor(Color)}</li>
 *             <li>{@link #setHoverBorderColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Pressed effect:</strong>
 *         <ul>
 *             <li>{@link #setPressedEffectIsEnabled(boolean)}</li>
 *             <li>{@link #setPressedTextColor(Color)}</li>
 *             <li>{@link #setPressedBackgroundColor(Color)}</li>
 *             <li>{@link #setPressedBorderColor(Color)}</li>
 *         </ul>
 *     </li>
 * </ol>
 *
 * @see JButton
 * @see Radii
 * @see Padding
 */
public class FlexButton extends JButton {
    public enum IconPosition {
        LEFT, RIGHT, TOP, BOTTOM
    }

    private Radii radii = new Radii(15, 15, 15, 15);
    private final Padding padding = new Padding(10, 10, 10, 10);
    private int borderThickness = 1;

    private Font font = new Font("Arial", Font.PLAIN, 14);
    ;
    private Color defaultBackground = new Color(40, 40, 40);
    private Color defaultBorderColor = new Color(15, 15, 15);
    private Color foreground = new Color(250, 250, 250);
    private Color background = defaultBackground;
    private Color borderColor = defaultBorderColor;
    private boolean hasBorder = false;

    private boolean isHoverEnabled = true;
    private Color hoverForeground = Color.WHITE;
    private Color hoverBackground = new Color(80, 80, 80);
    private Color hoverBorderColor = new Color(60, 60, 60);
    private boolean isPressedEffectEnabled = true;
    private Color pressedForeground = new Color(40, 40, 40);
    private Color pressedBackground = new Color(200, 200, 200);
    private Color pressedBorderColor = pressedBackground;

    private Icon icon;

    /**
     * Creates a button with no set text or icon.
     */
    public FlexButton() {
        super();
        init();
    }

    /**
     * Creates a button with text.
     *
     * @param text the text of the button
     */
    public FlexButton(String text) {
        super(text);
        init();
    }

    /**
     * Creates a button with an icon.
     *
     * @param icon the Icon image to display on the button
     */
    public FlexButton(Icon icon) {
        super(icon);
        this.icon = icon;
        init();
    }

    /**
     * Creates a button with initial text and an icon.
     *
     * @param text the text of the button
     * @param icon the Icon image to display on the button
     */
    public FlexButton(String text, Icon icon) {
        super(text, icon);
        this.icon = icon;
        init();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (icon != null) setIcon(icon);
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

        if (hasBorder && borderThickness > 0) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderThickness));
            g2.draw(area);
        }

        g2.dispose();
        super.paintComponent(g);
    }

    public void setIconPosition(IconPosition position) {
        switch (position) {
            case TOP -> {
                setHorizontalTextPosition(SwingConstants.CENTER);
                setVerticalTextPosition(SwingConstants.BOTTOM);
            }
            case BOTTOM -> {
                setHorizontalTextPosition(SwingConstants.CENTER);
                setVerticalTextPosition(SwingConstants.TOP);
            }
            case LEFT -> {
                setHorizontalTextPosition(SwingConstants.RIGHT);
                setVerticalTextPosition(SwingConstants.CENTER);
            }
            case RIGHT -> {
                setHorizontalTextPosition(SwingConstants.LEFT);
                setVerticalTextPosition(SwingConstants.CENTER);
            }
        }
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
     * @param font the new font for the text, cannot be {@code null}
     * @throws NullPointerException if the provided {@code font} is {@code null}
     */
    public void setTextFont(Font font) {
        this.font = Objects.requireNonNull(font, "Font cannot be null.");
        super.setFont(font);
        repaint();
    }

    /**
     * Gets the text color (foreground) of the button.
     *
     * @return the foreground color
     */
    public Color getTextColor() {
        return foreground;
    }

    /**
     * Sets the text color (foreground) of the button.
     *
     * @param color the new foreground for the text, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public void setTextColor(Color color) {
        this.foreground = Objects.requireNonNull(color, "Color cannot be null.");
        super.setForeground(color);
        repaint();
    }

    /**
     * Gets the background color of the button.
     *
     * @return the background color
     */
    public Color getBackgroundColor() {
        return background;
    }


    /**
     * Sets the background color of the button.
     *
     * @param color the new background color, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public void setBackgroundColor(Color color) {
        this.defaultBackground = Objects.requireNonNull(color, "Color cannot be null.");
        this.background = color;
        repaint();
    }

    /**
     * Gets whether the button has border.
     *
     * @return {@code true} if the button has border; {@code false} otherwise
     */
    public boolean isHasBorder() {
        return hasBorder;
    }

    /**
     * Sets the button as has border and paint according to that.
     *
     * @param hasBorder {@code true} if the button has border; {@code false} otherwise
     */
    public void setHasBorder(boolean hasBorder) {
        this.hasBorder = hasBorder;
        if (hasBorder) {
            updatePadding();
        } else {
            setBorder(null);
            updatePadding();
        }
        repaint();
    }

    /**
     * Gets the border color of the button.
     *
     * @return the border color
     * @see #setHasBorder(boolean)
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * Sets the border color of the button.
     *
     * @param color the new border color, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     * @see #setHasBorder(boolean)
     */
    public void setBorderColor(Color color) {
        this.defaultBorderColor = Objects.requireNonNull(color, "Color cannot be null.");
        this.borderColor = color;
        repaint();
    }

    /**
     * Sets whether the hover effect is enabled.
     *
     * @param isEnabled {@code true} for enabling hover effect; {@code false} otherwise
     * @apiNote When enabled, the hover effect automatically highlights the button when the mouse cursor
     * enters the button boundaries according to the {@code hoverBackground}, {@code hoverForeground} and
     * {@code hoverBorderColor}
     * @see #setHoverTextColor(Color)
     * @see #setHoverBackgroundColor(Color)
     * @see #setHoverBorderColor(Color)
     */
    public void setHoverIsEnabled(boolean isEnabled) {
        this.isHoverEnabled = isEnabled;
        if (isEnabled) {
            addMouseListener(hoverAdapter);
        } else {
            removeMouseListener(hoverAdapter);
        }
    }

    /**
     * Gets whether the hover effect is enabled.
     *
     * @return {@code true} if the hover effect is enabled; {@code false} otherwise
     */
    public boolean isHoverEnabled() {
        return isHoverEnabled;
    }

    /**
     * Gets the background color of the button's hover effect.
     *
     * @return the background color of the hover effect
     */
    public Color getHoverBackgroundColor() {
        return hoverBackground;
    }

    /**
     * Sets the background color of the button for hover effect.
     *
     * @param color the new background color, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public void setHoverBackgroundColor(Color color) {
        this.hoverBackground = Objects.requireNonNull(color, "Color cannot be null.");
    }

    /**
     * Gets the text color of the button's hover effect.
     *
     * @return the text color of the hover effect
     */
    public Color getHoverTextColor() {
        return hoverForeground;
    }

    /**
     * Sets the foreground color of the button for hover effect.
     *
     * @param color the new foreground color, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public void setHoverTextColor(Color color) {
        this.hoverForeground = Objects.requireNonNull(color, "Color cannot be null.");
    }

    /**
     * Gets the border color of the button's hover effect.
     *
     * @return the border color of the hover effect
     * @see #setHasBorder(boolean)
     */
    public Color getHoverBorderColor() {
        return hoverBorderColor;
    }

    /**
     * Sets the border color of the button for hover effect.
     *
     * @param color the new border color, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     * @see #setHasBorder(boolean)
     */
    public void setHoverBorderColor(Color color) {
        this.hoverBorderColor = Objects.requireNonNull(color, "Color cannot be null.");
    }

    /**
     * Sets whether the pressed effect is enabled.
     *
     * @param isEnabled {@code true} for enabling pressed effect; {@code false} otherwise
     * @apiNote When enabled, the pressed effect automatically highlights the button when the mouse is pressed
     * according to the {@code pressedBackground}, {@code pressedForeground} and
     * {@code pressedBorderColor}
     * @see #setPressedTextColor(Color)
     * @see #setPressedBackgroundColor(Color)
     * @see #setPressedBorderColor(Color)
     */
    public void setPressedEffectIsEnabled(boolean isEnabled) {
        this.isPressedEffectEnabled = isEnabled;
        if (isEnabled) {
            addMouseListener(pressedEffectAdapter);
        } else {
            removeMouseListener(pressedEffectAdapter);
        }
    }

    /**
     * Gets whether the pressed effect is enabled.
     *
     * @return {@code true} if the pressed effect is enabled; {@code false} otherwise
     */
    public boolean isPressedEffectEnabled() {
        return isPressedEffectEnabled;
    }

    /**
     * Gets the text color of the button's pressed effect.
     *
     * @return the text color of the pressed effect
     */
    public Color getPressedTextColor() {
        return pressedForeground;
    }

    /**
     * Sets the text color of the button for pressed effect.
     *
     * @param color the new text color, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public void setPressedTextColor(Color color) {
        this.pressedForeground = Objects.requireNonNull(color, "Color cannot be null.");
    }

    /**
     * Gets the background color of the button's pressed effect.
     *
     * @return the background color of the pressed effect
     */
    public Color getPressedBackgroundColor() {
        return pressedBackground;
    }

    /**
     * Sets the background color of the button for pressed effect.
     *
     * @param color the new background color, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public void setPressedBackgroundColor(Color color) {
        this.pressedBackground = Objects.requireNonNull(color, "Color cannot be null.");
    }

    /**
     * Gets the border color of the button's pressed effect.
     *
     * @return the border color of the pressed effect
     * @see #setHasBorder(boolean)
     */
    public Color getPressedBorderColor() {
        return pressedBorderColor;
    }

    /**
     * Sets the border color of the button for pressed effect.
     *
     * @param color the new border color, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     * @see #setHasBorder(boolean)
     */
    public void setPressedBorderColor(Color color) {
        this.pressedBorderColor = Objects.requireNonNull(color, "Color cannot be null.");
    }

    /**
     * Gets the thickness of the button border.
     *
     * @return the border thickness
     * @see #setHasBorder(boolean)
     */
    public int getBorderThickness() {
        return borderThickness;
    }

    /**
     * Sets the thickness of the button border.
     *
     * @param thickness the new border thickness, cannot be negative
     * @throws IllegalArgumentException if the provided {@code thickness} is negative
     * @see #setHasBorder(boolean)
     */
    public void setBorderThickness(int thickness) {
        if(thickness < 0) throw new IllegalArgumentException("Thickness cannot be negative.");
        this.borderThickness = thickness;
        repaint();
    }

    /**
     * Gets the button border corners' radii.
     *
     * @return the {@code radii} object that represents corners' radii
     */
    public Radii getRadii() {
        return radii;
    }

    /**
     * Sets the corner radii of the button border according to the provided {@code raii} object.
     *
     * @param radii the new {@code radii} object, cannot be {@code null}
     * @throws NullPointerException if the provided {@code radii} object is {@code null}
     * @see Radii
     * @see Radii#Radii(int, int, int, int)
     */
    public void setRadii(Radii radii) {
        this.radii = Objects.requireNonNull(radii, "Radii cannot be null.");
        repaint();
    }

    /**
     * Sets the same radius for all corners of the button border.
     *
     * @param radius the corner radius, cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setRadii(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        this.radii.setRadii(radius);
        repaint();
    }

    /**
     * Sets the all corners' radius of the button border according to provided values.
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
        if (bottomLeft < 0) throw new IllegalArgumentException("BottomLeft Radius cannot be negative.");
        if (bottomRight < 0) throw new IllegalArgumentException("BottomRight radius cannot be negative.");
        this.radii.setRadii(topLeft, topRight, bottomLeft, bottomRight);
        repaint();
    }

    /**
     * Gets the top left corner radius of the button border.
     *
     * @return the top left corner radius
     */
    public int getTopLeftRadius() {
        return radii.topLeft();
    }

    /**
     * Sets the top left corner radius of the button border.
     *
     * @param radius the new top left corner radius, cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setTopLeftRadius(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        radii.setTopLeft(radius);
        repaint();
    }

    /**
     * Gets the top right corner radius of the button border.
     *
     * @return the right left corner radius
     */
    public int getTopRightRadius() {
        return radii.topRight();
    }

    /**
     * Sets the top right corner radius of the button border.
     *
     * @param radius the new top right corner radius, cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setTopRightRadius(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        radii.setTopRight(radius);
        repaint();
    }

    /**
     * Gets the bottom left corner radius of the button border.
     *
     * @return the bottom left corner radius
     */
    public int getBottomLeftRadius() {
        return radii.bottomLeft();
    }

    /**
     * Sets the bottom left corner radius of the button border.
     *
     * @param radius the new bottom left corner radius, cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setBottomLeftRadius(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        radii.setBottomLeft(radius);
        repaint();
    }

    /**
     * Gets the bottom right corner radius of the button border.
     *
     * @return the bottom right corner radius
     */
    public int getBottomRightRadius() {
        return radii.bottomRight();
    }

    /**
     * Sets the bottom right corner radius of the button border.
     *
     * @param radius the new bottom right corner radius, cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setBottomRightRadius(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        radii.setBottomRight(radius);
        repaint();
    }

    /**
     * Gets the internal gaps between the input area and the button border.
     *
     * @return internal gaps
     */
    public Padding getPadding() {
        return padding;
    }

    /**
     * Sets the internal gaps between the input area and the button border according to provided {@code padding}
     * object.
     *
     * @param padding the new {@code padding} object, cannot be {@code null}
     * @throws NullPointerException if the provided {@code padding} object is {@code null}
     * @see Padding
     * @see Padding#Padding(int, int, int, int)
     */
    public void setPadding(Padding padding) {
        this.padding.setPadding(Objects.requireNonNull(padding, "Padding cannot be null"));
        updatePadding();
    }

    /**
     * Sets the same internal gaps for all directions between the input area and the button
     *
     * @param padding the new internal gaps for all directions, cannot be negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setPadding(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.padding.setPadding(padding);
        updatePadding();
    }

    /**
     * Sets the internal gaps for all directions between the input area and the button according to
     * the provided gaps.
     *
     * @param top    the internal gap between the input area and the button border's top side, cannot be negative
     * @param left   the internal gap between the input area and the button border's left side, cannot be negative
     * @param bottom the internal gap between the input area and the button border's bottom side, cannot be negative
     * @param right  the internal gap between the input area and the button border's right side, cannot be negative
     * @throws IllegalArgumentException if any of the provided padding values are negative
     */
    public void setPadding(int top, int left, int bottom, int right) {
        if (top < 0) throw new IllegalArgumentException("Top padding cannot be negative.");
        if (left < 0) throw new IllegalArgumentException("Left padding cannot be negative.");
        if (bottom < 0) throw new IllegalArgumentException("Bottom padding cannot be negative.");
        if (right < 0) throw new IllegalArgumentException("Right padding cannot be negative.");
        this.padding.setPadding(top, left, bottom, right);
        updatePadding();
    }

    /**
     * Gets the internal gap between the input area and the button border's top side.
     *
     * @return the internal gap between the input area and the button border's top side
     */
    public int getTopPadding() {
        return padding.top();
    }

    /**
     * Sets the internal gap between the input area and the button border's top side.
     *
     * @param padding the internal gap between the input area and the button border's top side, cannot be negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setTopPadding(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.padding.setTop(padding);
        updatePadding();
    }

    /**
     * Gets the internal gap between the input area and the button border's left side.
     *
     * @return the internal gap between the input area and the button border's left side
     */
    public int getLeftPadding() {
        return padding.left();
    }

    /**
     * Sets the internal gap between the input area and the button border's left side.
     *
     * @param padding the internal gap between the input area and the button border's left side, cannot be negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setLeftPadding(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.padding.setLeft(padding);
        updatePadding();
    }

    /**
     * Gets the internal gap between the input area and the button border's bottom side.
     *
     * @return the internal gap between the input area and the button border's bottom side
     */
    public int getBottomPadding() {
        return padding.bottom();
    }

    /**
     * Sets the internal gap between the input area and the button border's bottom side.
     *
     * @param padding the internal gap between the input area and the button border's bottom side, cannot be negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setBottomPadding(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.padding.setBottom(padding);
        updatePadding();
    }

    /**
     * Gets the internal gap between the input area and the button border's right side.
     *
     * @return the internal gap between the input area and the button border's right side
     */
    public int getRightPadding() {
        return padding.right();
    }

    /**
     * Sets the internal gap between the input area and the button border's right side.
     *
     * @param padding the internal gap between the input area and the button border's right side, cannot be negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setRightPadding(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.padding.setRight(padding);
        updatePadding();
    }

    private void updatePadding() {
        int gapTop = padding.top();
        int gapLeft = padding.left();
        int gapBottom = padding.bottom();
        int gapRight = padding.right();

        setBorder(BorderFactory.createEmptyBorder(gapTop, gapLeft, gapBottom, gapRight));
    }

    private void init() {
        setOpaque(false);
        setBorder(null);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFocusable(false);
        super.setFont(font);
        super.setForeground(foreground);
        setBackgroundColor(background);

        updatePadding();
        setHoverIsEnabled(isHoverEnabled);
        setPressedEffectIsEnabled(isPressedEffectEnabled);
    }

    private final MouseAdapter hoverAdapter = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            setForeground(hoverForeground);
            background = hoverBackground;
            if (hasBorder) borderColor = hoverBorderColor;
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setForeground(foreground);
            background = defaultBackground;
            if (hasBorder) borderColor = defaultBorderColor;
            repaint();
        }
    };

    private final MouseAdapter pressedEffectAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            setForeground(pressedForeground);
            background = pressedBackground;
            if (hasBorder) borderColor = pressedBorderColor;
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            setForeground(foreground);
            background = defaultBackground;
            if (hasBorder) borderColor = defaultBorderColor;
            repaint();
        }
    };
}