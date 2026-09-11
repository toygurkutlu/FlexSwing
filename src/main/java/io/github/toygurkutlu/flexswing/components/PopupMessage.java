package io.github.toygurkutlu.flexswing.components;


import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * A customizable and generic transient notification popup (Toast message) that provides
 * brief feedback about an operation in a small popup window.
 * <p>
 * The component fills only the amount of space required for the message and
 * does not intercept user interaction with the underlying application windows.
 * </p>
 *
 * <p>Use the following methods to customize the {@code PopupMessage}:</p>
 * <ol>
 *     <li><strong>Duration:</strong>
 *         <ul>
 *             <li>{@link #setDuration(int)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Alignment:</strong>
 *         <ul>
 *             <li>{@link #setPopupAlignment(PopupAlignment)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Text</strong>
 *         <ul>
 *             <li>{@link #setMessage(String)}</li>
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
 * @apiNote The developer must use {@link #showPopupMessage()} for displaying {@code PopupMessage} on the screen.
 * @see javax.swing.JWindow
 * @see javax.swing.Timer
 */
public class PopupMessage extends JWindow {

    public enum PopupAlignment {
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

    private Color background = new Color(30, 30, 30, 100);
    private Color foreground = Color.WHITE;
    private Color borderColor = new Color(100, 100, 100, 255);
    private Font font = new Font("SansSerif", Font.PLAIN, 13);
    private int borderRadius = 10;
    private String message;
    private JLabel label;
    private JPanel panel;
    private int duration;
    private final JComponent component;
    private int topPadding = 6;
    private int leftPadding = 12;
    private int bottomPadding = 6;
    private int rightPadding = 12;
    private PopupAlignment alignment = PopupAlignment.CENTER_CENTER_TO_CENTER;

    /**
     * Creates a {@code PopupMessage} with the provided values. The default duration is 1000 milliseconds (1 second).
     *
     * @param component the owner of the {@code PopupMessage}, cannot be null
     * @param message   the message to display, cannot be null
     * @throws NullPointerException if any of the following parameters are null:
     *                              <ul>
     *                                <li>When the provided <code>component</code> is <code>null</code></li>
     *                                <li>When the provided <code>message</code> is <code>null</code></li>
     *                              </ul>
     * @see PopupMessage#PopupMessage(JComponent, String, int)
     * @see #setDuration(int)
     * @see #showPopupMessage()
     */
    public PopupMessage(JComponent component, String message) {
        this(component, message, 1000);
    }

    /**
     * Creates a {@code PopupMessage} with the provided values.
     *
     * @param component the owner of the {@code PopupMessage}, cannot be null
     * @param message   the message to display, cannot be null
     * @param duration  the display duration in milliseconds before automatic disposal.
     * @throws NullPointerException     if any of the following parameters are null:
     *                                  <ul>
     *                                    <li>When the provided <code>component</code> is <code>null</code></li>
     *                                    <li>When the provided <code>message</code> is <code>null</code></li>
     *                                  </ul>
     * @throws IllegalArgumentException if the provided {@code duration} is lower than 500 milliseconds (0.5 second)
     * @see #showPopupMessage()
     */
    public PopupMessage(JComponent component, String message, int duration) {
        this.component = Objects.requireNonNull(component, "Component cannot be null.");
        this.message = Objects.requireNonNull(message, "Message cannot be null");
        if (duration < 500)
            throw new IllegalArgumentException("Duration cannot be lower than 500 milliseconds (0.5 second)");
        this.duration = duration;

        createViews();
        setFocusableWindowState(false);
        setType(Type.POPUP);
        setBackground(new Color(0, 0, 0, 0));
        add(panel);
    }

    /**
     * Displays the {@code PopupMessage} on the screen
     */
    public void showPopupMessage() {
        pack();

        Point point = getPoint(alignment);
        setLocation(point.x, point.y);
        setVisible(true);
        toFront();

        Timer timer = new Timer(duration, e -> {
            setVisible(false);
            dispose();
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Sets the alignment location where the {@code PopupMessage} will be displayed.
     *
     * @return the alignment object that represents {@code PopupMessage} location
     */
    public PopupAlignment getPopupAlignment() {
        return alignment;
    }

    /**
     * Sets the alignment location where the {@code PopupMessage} will be displayed.
     *
     * @param alignment the alignment strategy for the {@code PopupMessage}, cannot be {@code null}
     * @throws NullPointerException if the provided {@code alignment} is {@code null}
     * @apiNote To offset the {@code PopupMessage} from its alignment position, use the gap setter methods.
     * @see PopupAlignment
     * @see #setTopPadding(int)
     * @see #setLeftPadding(int)
     * @see #setBottomPadding(int)
     * @see #setRightPadding(int)
     */
    public void setPopupAlignment(PopupAlignment alignment) {
        this.alignment = Objects.requireNonNull(alignment);
        updatePopupForVisibility();
    }

    /**
     * Sets the message of the {@code PopupMessage}.
     *
     * @param message the new message to display
     */
    public void setMessage(String message) {
        this.message = message;
        updatePopupForVisibility();
    }

    /**
     * Gets the text font of the {@code PopupMessage}.
     *
     * @return the text font
     */
    public Font getTextFont() {
        return font;
    }

    /**
     * Sets the text font of the {@code PopupMessage}.
     *
     * @param font the new text font, cannot be {@code null}
     * @throws NullPointerException if the provided {@code font} is {@code null}
     */
    public void setTextFont(Font font) {
        this.font = Objects.requireNonNull(font, "Font cannot be null.");
        if (label != null) label.setFont(font);
        updatePopupForVisibility();
    }

    /**
     * Gets the text color (foreground) of the {@code PopupMessage}.
     *
     * @return the text color
     */
    public Color getTextColor() {
        return foreground;
    }

    /**
     * Sets the text color (foreground) of the {@code PopupMessage}.
     *
     * @param color the new text color, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public void setTextColor(Color color) {
        this.foreground = Objects.requireNonNull(color, "Foreground color cannot be null.");
        label.setForeground(color);
    }

    /**
     * Gets the background color of the {@code PopupMessage}.
     *
     * @return the background color
     */
    public Color getBackgroundColor() {
        return background;
    }

    /**
     * Sets the background color for the {@code PopupMessage}.
     *
     * @param color the new background color, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public void setBackgroundColor(Color color) {
        this.background = Objects.requireNonNull(color, "Background color cannot be null.");
        if (panel != null) panel.repaint();
    }

    /**
     * Gets the border color of the {@code PopupMessage}.
     *
     * @return the border color
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * Sets the border color for the {@code PopupMessage}.
     *
     * @param color the new border color, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public void setBorderColor(Color color) {
        this.borderColor = Objects.requireNonNull(color, "Border color cannot be null.");
        if (panel != null) panel.repaint();
    }

    /**
     * Gets the border radius of the {@code PopupMessage}.
     *
     * @return the border radius
     */
    public int getBorderRadius() {
        return borderRadius;
    }

    /**
     * Sets the border radius for the {@code PopupMessage}.
     *
     * @param radius the new border radius, cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setBorderRadius(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative");
        this.borderRadius = radius;
        if (panel != null) panel.repaint();
        updatePopupForVisibility();
    }

    /**
     * Gets the display duration in milliseconds before automatic disposal.
     *
     * @return the duration of the {@code PopupMessage}
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the display duration in milliseconds before automatic disposal.
     *
     * @param duration the duration of the {@code PopupMessage} as milliseconds
     * @throws IllegalArgumentException if the {@code duration} is lower than 500 milliseconds (0.5 second)
     */
    public void setDuration(int duration) {
        if (duration < 500)
            throw new IllegalArgumentException("Duration cannot lower than 500 milliseconds (0.5 second).");
        this.duration = duration;
        pack();
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
        updatePopupForVisibility();
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
        updatePopupForVisibility();
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
        updatePopupForVisibility();
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
        updatePopupForVisibility();
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
        updatePopupForVisibility();
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

        label = new JLabel(message);
        label.setForeground(foreground);
        label.setFont(font);
        label.setVerticalTextPosition(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));

        panel.add(label, BorderLayout.CENTER);
    }

    private Point getPoint(PopupAlignment alignment) {
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

    private void updatePopupForVisibility() {
        if (isVisible()) {
            pack();
            Point point = getPoint(alignment);
            setLocation(point.x, point.y);
        }
    }
}