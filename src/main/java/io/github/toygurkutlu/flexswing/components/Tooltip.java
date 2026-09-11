package io.github.toygurkutlu.flexswing.components;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * Customizable tooltip that displays a "Tip" for a Component.
 * <p>Use the following methods to customize the {@code Tooltip}:</p>
 * <ol>
 *     <li><strong>Alignment:</strong>
 *         <ul>
 *             <li>{@link #setTooltipAlignment(TooltipAlignment)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Text</strong>
 *         <ul>
 *             <li>{@link #setTooltipText(String)} (String)}</li>
 *             <li>{@link #setTextColor(Color)}</li>
 *             <li>{@link #setTextFont(Font)}</li>
 *             <li>{@link #setPadding(int)}</li>
 *             <li>{@link #setTopPadding(int)}</li>
 *             <li>{@link #setLeftPadding(int)}</li>
 *             <li>{@link #setBottomPadding(int)}</li>
 *             <li>{@link #setRightPadding(int)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Background color:</strong>
 *         <ul>
 *             <li>{@link #setBackgroundColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Border:</strong>
 *         <ul>
 *             <li>{@link #setBorderColor(Color)}</li>
 *             <li>{@link #setBorderRadius(int)}</li>
 *         </ul>
 *     </li>
 * </ol>
 *
 * @apiNote The developer must use {@link #attachTooltip()} for binding the {@code Tooltip} and the {@code JComponent}
 * @see #updateTooltip(TooltipAttribute, Object)
 * @see TooltipAttribute
 * @see TooltipAlignment
 * @see javax.swing.JWindow
 */
public class Tooltip extends JWindow {

    private JLabel label;
    private JPanel panel;
    private final JComponent component;
    private String text;
    private Color background = new Color(45, 45, 45, 230);
    private Color foreground = new Color(255, 255, 255, 255);
    private Color borderColor = new Color(100, 100, 100, 255);
    private int borderRadius = 10;
    private Font font = new Font("Arial", Font.PLAIN, 14);
    private int topPadding = 6;
    private int leftPadding = 12;
    private int bottomPadding = 6;
    private int rightPadding = 12;
    private TooltipAlignment alignment = TooltipAlignment.BOTTOM_CENTER_TO_CENTER;

    /**
     * Represents the location of the tooltip
     */
    public enum TooltipAlignment {
        TOP_LEFT_TO_LEFT, TOP_LEFT_TO_CENTER, TOP_LEFT_TO_RIGHT,
        TOP_CENTER_TO_LEFT, TOP_CENTER_TO_CENTER, TOP_CENTER_TO_RIGHT,
        TOP_RIGHT_TO_LEFT, TOP_RIGHT_TO_CENTER, TOP_RIGHT_TO_RIGHT,
        CENTER_LEFT_TO_LEFT, CENTER_LEFT_TO_CENTER, CENTER_LEFT_TO_RIGHT,
        CENTER_CENTER_TO_LEFT, CENTER_CENTER_TO_CENTER, CENTER_CENTER_TO_RIGHT,
        CENTER_RIGHT_TO_LEFT, CENTER_RIGHT_TO_CENTER, CENTER_RIGHT_TO_RIGHT,
        BOTTOM_LEFT_TO_LEFT, BOTTOM_LEFT_TO_CENTER, BOTTOM_LEFT_TO_RIGHT,
        BOTTOM_CENTER_TO_LEFT, BOTTOM_CENTER_TO_CENTER, BOTTOM_CENTER_TO_RIGHT,
        BOTTOM_RIGHT_TO_LEFT, BOTTOM_RIGHT_TO_CENTER, BOTTOM_RIGHT_TO_RIGHT
    }

    /**
     * Represents the customizable attributes
     */
    public enum TooltipAttribute {
        ALIGNMENT,
        BACKGROUND,
        BORDER_COLOR,
        BORDER_RADIUS,
        FOREGROUND,
        FONT,
        PADDING,
        TOP_PADDING,
        LEFT_PADDING,
        BOTTOM_PADDING,
        RIGHT_PADDING
    }

    /**
     * Creates {@code Tooltip} and binds the provided {@code component}.
     *
     * @param component the owner of the tooltip, cannot be null
     * @param text      the text to display, cannot be null
     * @throws NullPointerException if any of the following parameters are null:
     *                              <ul>
     *                                <li>When the provided <code>component</code> is <code>null</code></li>
     *                                <li>When the provided <code>text</code> is <code>null</code></li>
     *                              </ul>
     */
    public Tooltip(JComponent component, String text) {
        this.component = Objects.requireNonNull(component, "Component cannot be null.");
        this.text = Objects.requireNonNull(text, "Text cannot be null.");

        init();
    }

    /**
     * Updates the {@code Tooltip} according to the provided {@code TooltipAttribute} and {@code value}.
     *
     * @param attr  the attribute object that represents which attribute will be updated
     * @param value the new value for the {@code attr}, cannot be null
     * @throws NullPointerException if any of the following parameters are null:
     *                              <ul>
     *                                <li>When the provided <code>attr</code> is <code>null</code></li>
     *                                <li>When the provided <code>value</code> is <code>null</code></li>
     *                              </ul>
     * @see TooltipAttribute
     */
    public void updateTooltip(TooltipAttribute attr, Object value) {
        Objects.requireNonNull(attr, "TooltipAttribute cannot be null.");
        Objects.requireNonNull(value, "Value cannot be null.");
        switch (attr) {
            case ALIGNMENT -> setTooltipAlignment((TooltipAlignment) value);
            case BACKGROUND -> setBackgroundColor((Color) value);
            case BORDER_COLOR -> setBorderColor((Color) value);
            case BORDER_RADIUS -> setBorderRadius((int) value);
            case FOREGROUND -> setTextColor((Color) value);
            case FONT -> setTextFont((Font) value);
            case PADDING -> setPadding((int) value);
            case TOP_PADDING -> setTopPadding((int) value);
            case LEFT_PADDING -> setLeftPadding((int) value);
            case BOTTOM_PADDING -> setBottomPadding((int) value);
            case RIGHT_PADDING -> setRightPadding((int) value);
        }
    }

    /**
     * Attaches the tooltip to the provided {@code component} and sets a {@link java.awt.event.MouseAdapter}
     * with {@link MouseAdapter#mouseEntered(MouseEvent)} and {@link MouseAdapter#mouseExited(MouseEvent)} methods.
     * After calling this method the provided {@code component} will show the tooltip when the mouse cursor comes
     * the {@code component}'s boundaries and will hide the tooltip when the mouse cursor goes out of boundaries.
     */
    public void attachTooltip() {
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                pack();

                SwingUtilities.invokeLater(() -> {
                    Point point = getPoint(alignment);
                    setLocation(point.x, point.y);
                    setVisible(true);
                    toFront();
                });
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SwingUtilities.invokeLater(() -> setVisible(false));
            }
        });
    }

    /**
     * Sets the alignment location where the {@code Tooltip} will be displayed.
     *
     * @return the alignment object that represents {@code Tooltip} location
     */
    public TooltipAlignment getPTooltipAlignment() {
        return alignment;
    }

    /**
     * Sets the alignment location where the {@code Tooltip} will be displayed.
     *
     * @param alignment the alignment strategy for the {@code Tooltip}, cannot be {@code null}
     * @throws NullPointerException if the provided {@code alignment} is {@code null}
     * @apiNote To offset the {@code Tooltip} from its alignment position, use the gap setter methods.
     * @see #setTopPadding(int)
     * @see #setLeftPadding(int)
     * @see #setBottomPadding(int)
     * @see #setRightPadding(int)
     */
    public void setTooltipAlignment(TooltipAlignment alignment) {
        this.alignment = Objects.requireNonNull(alignment, "Alignment cannot be null.");
        updateTooltipForVisibility();
    }

    /**
     * Gets the text to be displayed in the tooltip.
     *
     * @return the displayed text
     */
    public String getTooltipText() {
        return text;
    }

    /**
     * Sets the text to be displayed in the tooltip.
     *
     * @param text the text to display, cannot be {@code null}
     * @throws NullPointerException if the provided {@code text} is {@code null}
     */
    public void setTooltipText(String text) {
        this.text = Objects.requireNonNull(text, "Text cannot be null");
        label.setText(text);
        updateTooltipForVisibility();
    }

    /**
     * Gets the text font of the {@code Tooltip}.
     *
     * @return the text font
     */
    public Font getTextFont() {
        return font;
    }

    /**
     * Sets the font of the {@code Tooltip} text.
     *
     * @param font the new text font, cannot be {@code null}
     * @throws NullPointerException if the provided {@code font} is {@code null}
     */
    public void setTextFont(Font font) {
        this.font = Objects.requireNonNull(font, "Font cannot be null.");
        if (label != null) {
            label.setFont(font);
            updateTooltipForVisibility();
        }
    }

    /**
     * Gets the text color (foreground) of the {@code Tooltip}.
     *
     * @return the text color
     */
    public Color getTextColor() {
        return foreground;
    }

    /**
     * Sets the color of the {@code Tooltip} text.
     *
     * @param color the new text color, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public void setTextColor(Color color) {
        this.foreground = Objects.requireNonNull(color, "Color cannot be null");
        if (label != null) label.setForeground(color);
    }

    /**
     * Gets the background color of the {@code Tooltip}.
     *
     * @return the background color
     */
    public Color getBackgroundColor() {
        return background;
    }

    /**
     * Sets the color of the {@code Tooltip} background.
     *
     * @param color the new background color, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public void setBackgroundColor(Color color) {
        this.background = Objects.requireNonNull(color, "Color cannot be null.");
        if (panel != null) panel.repaint();

    }

    /**
     * Gets the border color of the {@code Tooltip}.
     *
     * @return the border color
     */
    public Color getBorderColor(){
        return borderColor;
    }

    /**
     * Sets the color of the {@code Tooltip} border.
     *
     * @param color the new border color, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public void setBorderColor(Color color) {
        this.borderColor = Objects.requireNonNull(color, "Color cannot be null.");
        if (panel != null) panel.repaint();

    }

    /**
     * Gets the border radius of the {@code Tooltip}.
     *
     * @return the border radius
     */
    public int getBorderRadius() {
        return borderRadius;
    }

    /**
     * Sets the border radius for the {@code Tooltip}.
     *
     * @param radius the new border radius, cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setBorderRadius(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative");
        this.borderRadius = radius;
        panel.repaint();
    }

    /**
     * Sets the same internal gaps for all directions between the text and its container.
     *
     * @param padding the internal gaps, cannot be negative
     * @throws IllegalArgumentException if the {@code padding} is negative
     */
    public void setPadding(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.topPadding = padding;
        this.leftPadding = padding;
        this.bottomPadding = padding;
        this.rightPadding = padding;
        label.setBorder(BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));
        updateTooltipForVisibility();
    }

    /**
     * Gets the internal gap for between the text and its container's top side.
     *
     * @return the internal gap between the text and the container border's top side
     */
    public int getTopPadding() {
        return topPadding;
    }

    /**
     * Sets the internal gap between the text and its container border's top side.
     *
     * @param padding the internal gap between the text and its container border's top side, cannot be negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setTopPadding(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.topPadding = padding;
        label.setBorder(BorderFactory.createEmptyBorder(padding, leftPadding, bottomPadding, rightPadding));
        updateTooltipForVisibility();
    }

    /**
     * Gets the internal gap for between the text and its container's left side.
     *
     * @return the internal gap between the text and the container border's left side
     */
    public int getLeftPadding() {
        return leftPadding;
    }

    /**
     * Sets the internal gap between the text and its container border's left side.
     *
     * @param padding the internal gap between the text and its container border's left side, cannot be negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setLeftPadding(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.leftPadding = padding;
        label.setBorder(BorderFactory.createEmptyBorder(topPadding, padding, bottomPadding, rightPadding));
        updateTooltipForVisibility();
    }

    /**
     * Gets the internal gap for between the text and its container's bottom side.
     *
     * @return the internal gap between the text and the container border's bottom side
     */
    public int getBottomPadding() {
        return bottomPadding;
    }

    /**
     * Sets the internal gap between the text and its container border's bottom side.
     *
     * @param padding the internal gap between the text and its container border's bottom side, cannot be negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setBottomPadding(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.bottomPadding = padding;
        label.setBorder(BorderFactory.createEmptyBorder(topPadding, leftPadding, padding, rightPadding));
        updateTooltipForVisibility();
    }

    /**
     * Gets the internal gap for between the text and its container's right side.
     *
     * @return the internal gap between the text and the container border's right side
     */
    public int getRightPadding() {
        return rightPadding;
    }

    /**
     * Sets the internal gap between the text and its container border's right side.
     *
     * @param padding the internal gap between the text and its container border's right side, cannot be negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setRightPadding(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.rightPadding = padding;
        label.setBorder(BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, padding));
        updateTooltipForVisibility();
    }

    private void createViews() {
        panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(background);
                g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, borderRadius, borderRadius);

                g2d.setColor(borderColor);
                g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, borderRadius, borderRadius);

                g2d.dispose();
            }
        };
        panel.setOpaque(false);

        label = new JLabel(text);
        label.setForeground(foreground);
        label.setFont(font);
        label.setVerticalTextPosition(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));
    }

    private void init() {
        createViews();

        panel.add(label, BorderLayout.CENTER);

        setBackground(new Color(0, 0, 0, 0));

        setAlwaysOnTop(true);
        setFocusableWindowState(false);
        add(panel);
        pack();

        attachTooltip();
    }

    private Point getPoint(TooltipAlignment alignment) {
        Point componentPoint = component.getLocationOnScreen();

        int yAbove = componentPoint.y - getHeight();
        int yCenter = componentPoint.y + (component.getHeight() / 2) - (getHeight() / 2);
        int yBelow = componentPoint.y + component.getHeight();

        int xLeftToLeft = componentPoint.x;
        int xLeftToCenter = xLeftToLeft - (getWidth() / 2);
        int xLeftToRight = xLeftToLeft - getWidth();

        int xCenterToLeft = componentPoint.x + (component.getWidth() / 2);
        int xCenterToCenter = xCenterToLeft - (getWidth() / 2);
        int xCenterToRight = xCenterToLeft - getWidth();

        int xRightToLeft = componentPoint.x + component.getWidth();
        int xRightToCenter = xRightToLeft - (getWidth() / 2);
        int xRightToRight = xRightToLeft - getWidth();
        return switch (alignment) {
            case TOP_LEFT_TO_LEFT -> new Point(xLeftToLeft, yAbove);
            case TOP_LEFT_TO_CENTER -> new Point(xLeftToCenter, yAbove);
            case TOP_LEFT_TO_RIGHT -> new Point(xLeftToRight, yAbove);
            case TOP_CENTER_TO_LEFT -> new Point(xCenterToLeft, yAbove);
            case TOP_CENTER_TO_CENTER -> new Point(xCenterToCenter, yAbove);
            case TOP_CENTER_TO_RIGHT -> new Point(xCenterToRight, yAbove);
            case TOP_RIGHT_TO_LEFT -> new Point(xRightToLeft, yAbove);
            case TOP_RIGHT_TO_CENTER -> new Point(xRightToCenter, yAbove);
            case TOP_RIGHT_TO_RIGHT -> new Point(xRightToRight, yAbove);

            case CENTER_LEFT_TO_LEFT -> new Point(xLeftToLeft, yCenter);
            case CENTER_LEFT_TO_CENTER -> new Point(xLeftToCenter, yCenter);
            case CENTER_LEFT_TO_RIGHT -> new Point(xLeftToRight, yCenter);
            case CENTER_CENTER_TO_LEFT -> new Point(xCenterToLeft, yCenter);
            case CENTER_CENTER_TO_CENTER -> new Point(xCenterToCenter, yCenter);
            case CENTER_CENTER_TO_RIGHT -> new Point(xCenterToRight, yCenter);
            case CENTER_RIGHT_TO_LEFT -> new Point(xRightToLeft, yCenter);
            case CENTER_RIGHT_TO_CENTER -> new Point(xRightToCenter, yCenter);
            case CENTER_RIGHT_TO_RIGHT -> new Point(xRightToRight, yCenter);

            case BOTTOM_LEFT_TO_LEFT -> new Point(xLeftToLeft, yBelow);
            case BOTTOM_LEFT_TO_CENTER -> new Point(xLeftToCenter, yBelow);
            case BOTTOM_LEFT_TO_RIGHT -> new Point(xLeftToRight, yBelow);
            case BOTTOM_CENTER_TO_LEFT -> new Point(xCenterToLeft, yBelow);
            case BOTTOM_CENTER_TO_CENTER -> new Point(xCenterToCenter, yBelow);
            case BOTTOM_CENTER_TO_RIGHT -> new Point(xCenterToRight, yBelow);
            case BOTTOM_RIGHT_TO_LEFT -> new Point(xRightToLeft, yBelow);
            case BOTTOM_RIGHT_TO_CENTER -> new Point(xRightToCenter, yBelow);
            case BOTTOM_RIGHT_TO_RIGHT -> new Point(xRightToRight, yBelow);
        };
    }

    private void updateTooltipForVisibility() {
        if (isVisible()) {
            pack();
            Point point = getPoint(alignment);
            setLocation(point.x, point.y);
        }
    }
}