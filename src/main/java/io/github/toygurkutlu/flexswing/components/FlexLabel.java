package io.github.toygurkutlu.flexswing.components;


import io.github.toygurkutlu.flexswing.enums.StateType;
import io.github.toygurkutlu.flexswing.objects.FlexUtil;
import io.github.toygurkutlu.flexswing.objects.Padding;
import io.github.toygurkutlu.flexswing.objects.Radii;
import io.github.toygurkutlu.flexswing.objects.StateColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.util.Objects;

/**
 * A customizable display area for a short text string or an image, or both which extends from {@link JLabel}.
 *
 * <p>Use the following methods to customize the {@code label}:</p>
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
 *             <li>{@link #setBorderColorByState(StateType, Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Background color:</strong>
 *         <ul>
 *             <li>{@link #setBackgroundColor(Color)}</li>
 *             <li>{@link #setBackgroundColorByState(StateType, Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Foreground color:</strong>
 *         <ul>
 *             <li>{@link #setTextColor(Color)}</li>
 *             <li>{@link #setTextColorByState(StateType, Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Text font:</strong>
 *         <ul>
 *             <li>{@link #setTextFont(Font)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>State:</strong>
 *         <ul>
 *             <li>{@link #setState(StateType)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Hover mechanism</strong>
 *         <ul>
 *             <li>{@link #setHoverEnabled(boolean)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Select mechanism:</strong>
 *         <ul>
 *             <li>{@link #setSelectionEnabled(boolean)}</li>
 *         </ul>
 *     </li>
 * </ol>
 *
 * @see JLabel
 * @see Radii
 * @see Padding
 * @see StateType
 * @see StateColors
 */
public class FlexLabel extends JLabel {

    private StateColors foregrounds;
    private StateColors backgrounds;
    private StateColors borderColors;
    private Radii radii = new Radii(15, 15, 15, 15);;
    private Padding padding = new Padding(5, 5, 5, 5);;
    private int borderThickness = 1;
    private Font font = new Font("Arial", Font.PLAIN, 14);;
    private Color foreground;
    private Color background;
    private Color borderColor;
    private StateType state = StateType.MAIN;
    private boolean isHoverEnabled = false;
    private boolean isSelectionEnabled = false;
    private boolean isSelected = false;
    private boolean hasBorder = true;

    /**
     * Creates an instance with no image and with an empty string for the title. The label is centered
     * vertically in its display area. The label's contents, once set, will be displayed on the leading edge of
     * the label's display area.
     */
    public FlexLabel() {
        super();
        init();
    }

    /**
     * Creates an instance with the specified text. The label is aligned against the leading edge of its
     * display area, and centered vertically.
     *
     * @param text the text to be displayed by the label
     */
    public FlexLabel(String text) {
        super(text);
        init();
    }

    /**
     * Creates an instance with the specified image. The label is centered vertically and horizontally in
     * its display area.
     *
     * @param image the image to be displayed by the label
     */
    public FlexLabel(Icon image) {
        super(image);
        init();
    }

    /**
     * Creates an instance with the specified image and horizontal alignment. The label is centered vertically
     * in its display area.
     *
     * @param image               the image to be displayed by the label
     * @param horizontalAlignment can be one of the following constants: {@link SwingConstants#LEFT},
     *                            {@link SwingConstants#CENTER}, {@link SwingConstants#RIGHT},
     *                            {@link SwingConstants#LEADING} or {@link SwingConstants#TRAILING}
     */
    public FlexLabel(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
        init();
    }

    /**
     * Creates an instance with the specified text and horizontal alignment. The label is centered vertically
     * in its display area.
     *
     * @param text                the text to be displayed by the label
     * @param horizontalAlignment can be one of the following constants: {@link SwingConstants#LEFT},
     *                            {@link SwingConstants#CENTER}, {@link SwingConstants#RIGHT},
     *                            {@link SwingConstants#LEADING} or {@link SwingConstants#TRAILING}
     */
    public FlexLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        init();
    }

    /**
     * Creates an instance with the specified text, image, and horizontal alignment. The label is centered
     * vertically in its display area. The text is on the trailing edge of the image.
     *
     * @param text                the text to be displayed by the label
     * @param icon                the image to be displayed by the label
     * @param horizontalAlignment can be one of the following constants: {@link SwingConstants#LEFT},
     *                            {@link SwingConstants#CENTER}, {@link SwingConstants#RIGHT},
     *                            {@link SwingConstants#LEADING} or {@link SwingConstants#TRAILING}
     */
    public FlexLabel(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
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

        if (hasBorder && borderThickness > 0) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderThickness));
            g2.draw(area);
        }


        g2.dispose();
        super.paintComponent(g);
    }

    private void paintIcon(Graphics g) {
        if (getIcon() == null) return;

        int x = (getWidth() - getIcon().getIconWidth()) / 2;
        int y = (getHeight() - getIcon().getIconHeight()) / 2;

        getIcon().paintIcon(this, g, x, y);
    }

    /**
     * Sets the hover mechanism.
     *
     * @param isEnabled {@code true} for enabling hover mechanism; {@code false} otherwise
     * @apiNote When enabled, the hover mechanism automatically highlights the label when the mouse cursor
     * enters the label boundaries according to the {@code StateColor}'s  {@link StateType#MAIN_HOVER} or
     * {@link StateType#SELECTED_HOVER} state.
     * @see StateColors
     */
    public void setHoverEnabled(boolean isEnabled) {
        this.isHoverEnabled = isEnabled;
        if (isEnabled) {
            addMouseListener(hoverAdapter);
        } else {
            removeMouseListener(hoverAdapter);
        }
    }

    /**
     * Gets whether hover mechanism is enabled.
     *
     * @return {@code true} if hover mechanism is enabled; {@code false} otherwise
     */
    public boolean isHoverEnabled() {
        return isHoverEnabled;
    }

    /**
     * Sets the select mechanism.
     *
     * @param isEnabled {@code true} for enabling hover mechanism; {@code false} otherwise
     * @apiNote When enabled, the select mechanism paints the label according to the {@code StateColor}'s
     * {@link StateType#SELECTED} state.
     * @see StateColors
     */
    public void setSelectionEnabled(boolean isEnabled) {
        this.isSelectionEnabled = isEnabled;
        if (isEnabled) {
            addMouseListener(selectionAdapter);
        } else {
            removeMouseListener(selectionAdapter);
        }
    }

    /**
     * Gets whether select mechanism is enabled.
     *
     * @return {@code true} if select mechanism is enabled; {@code false} otherwise
     */
    public boolean isSelectionEnabled() {
        return isSelectionEnabled;
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
     * Gets the text color (foreground).
     *
     * @return the foreground color
     */
    public Color getTextColor() {
        return foreground;
    }

    /**
     * Sets the text color (foreground).
     *
     * @param color the new foreground for the text, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public void setTextColor(Color color) {
        foregrounds.setMain(Objects.requireNonNull(color, "Color cannot be null."));
        updateTextColor(color);
        repaint();
    }

    /**
     * Gets the text color (foreground) according to the provided {@code stateType}.
     *
     * @param stateType the type of the state, cannot be {@code null}
     * @return the foreground that matches with the provided {@code stateType}
     * @throws NullPointerException if the provided {@code stateType} is {@code null}
     */
    public Color getTextColorByState(StateType stateType) {
        return foregrounds.getColorByState(stateType);
    }

    /**
     * Sets the text color (foreground) according to the provided {@code stateType}.
     *
     * @param stateType the type of the state, cannot be {@code null}
     * @param color     the new foreground for the text, cannot be {@code null}
     * @throws NullPointerException if any of the following parameters are null:
     *                              <ul>
     *                                <li>When the provided <code>stateType</code> is <code>null</code></li>
     *                                <li>When the provided <code>color</code> object is <code>null</code></li>
     *                              </ul>
     */
    public void setTextColorByState(StateType stateType, Color color) {
        foregrounds.setColorByState(Objects.requireNonNull(stateType, "StateType cannot be null.")
                , Objects.requireNonNull(color, "Color cannot be null."));
        if (this.state == stateType) {
            updateTextColor(color);
            repaint();
        }
    }

    /**
     * Gets the background color of the {@code label}.
     *
     * @return the background color
     */
    public Color getBackgroundColor() {
        return background;
    }

    /**
     * Sets the background color of the {@code label}.
     *
     * @param color the new background color, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     */
    public void setBackgroundColor(Color color) {
        backgrounds.setMain(Objects.requireNonNull(color, "Color cannot be null."));
        updateBackgroundColor(color);
        repaint();
    }

    /**
     * Gets the background color of the {@code label} according to the provided {@code stateType}.
     *
     * @param stateType the type of the state, cannot be {@code null}
     * @return the background color that matches with the provided {@code stateType}
     * @throws NullPointerException if the provided {@code stateType} is {@code null}
     */
    public Color getBackgroundColorByState(StateType stateType) {
        return backgrounds.getColorByState(stateType);
    }

    /**
     * Sets the background color of the {@code label} according to the provided {@code stateType}.
     *
     * @param stateType the type of the state, cannot be {@code null}
     * @param color     the new background color, cannot be {@code null}
     * @throws NullPointerException if any of the following parameters are null:
     *                              <ul>
     *                                <li>When the provided <code>stateType</code> is <code>null</code></li>
     *                                <li>When the provided <code>color</code> object is <code>null</code></li>
     *                              </ul>
     */
    public void setBackgroundColorByState(StateType stateType, Color color) {
        backgrounds.setColorByState(Objects.requireNonNull(stateType, "StateType cannot be null.")
                , Objects.requireNonNull(color, "Color cannot be null."));
        if (this.state == stateType) {
            updateBackgroundColor(color);
            repaint();
        }
    }

    /**
     * Gets whether the {@code label} has border.
     *
     * @return {@code true} if the {@code label} has border; {@code false} otherwise
     */
    public boolean isHasBorder() {
        return hasBorder;
    }

    /**
     * Sets the {@code label} as has border and paint according to that.
     *
     * @param hasBorder {@code true} if the {@code label} has border; {@code false} otherwise
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
     * Gets the border color of the {@code label}.
     *
     * @return the border color
     * @see #setHasBorder(boolean)
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * Sets the border color of the {@code label}.
     *
     * @param color the new border color, cannot be {@code null}
     * @throws NullPointerException if the provided {@code color} is {@code null}
     * @see #setHasBorder(boolean)
     */
    public void setBorderColor(Color color) {
        borderColors.setMain(Objects.requireNonNull(color, "Color cannot be null."));
        updateBorderColor(color);
        repaint();
    }

    /**
     * Gets the border color of the {@code label} according to the provided {@code stateType}.
     *
     * @param stateType the type of the state, cannot be {@code null}
     * @return the border color that matches with the provided {@code stateType}
     * @throws NullPointerException if the provided {@code stateType} is {@code null}
     * @see #setHasBorder(boolean)
     */
    public Color getBorderColorByState(StateType stateType) {
        return borderColors.getColorByState(Objects.requireNonNull(stateType, "StateType cannot be null."));
    }

    /**
     * Sets the border color of the {@code label} according to the provided {@code stateType}.
     *
     * @param stateType the type of the state, cannot be {@code null}
     * @param color     the new border color, cannot be {@code null}
     * @throws NullPointerException if any of the following parameters are null:
     *                              <ul>
     *                                <li>When the provided <code>stateType</code> is <code>null</code></li>
     *                                <li>When the provided <code>color</code> object is <code>null</code></li>
     *                              </ul>
     * @see #setHasBorder(boolean)
     */
    public void setBorderColorByState(StateType stateType, Color color) {
        borderColors.setColorByState(Objects.requireNonNull(stateType, "StateType cannot be null."),
                                     Objects.requireNonNull(color, "Color cannot be null."));
        if (this.state == stateType) {
            updateBorderColor(color);
            repaint();
        }
    }

    /**
     * Gets the thickness of the {@code label} border.
     *
     * @return the border thickness
     * @see #setHasBorder(boolean)
     */
    public int getBorderThickness() {
        return borderThickness;
    }

    /**
     * Sets the thickness of the {@code label} border.
     *
     * @param thickness the new border thickness, cannot be negative
     * @throws IllegalArgumentException if the provided {@code thickness} is negative
     * @see #setHasBorder(boolean)
     */
    public void setBorderThickness(int thickness) {
        if (thickness < 0) throw new IllegalArgumentException("Thickness cannot be negative.");
        this.borderThickness = thickness;
        repaint();
    }

    /**
     * Gets the current state of the label.
     */
    public StateType getState() {
        return state;
    }

    /**
     * Sets the current state and repaint {@code label} according to the provided {@code stateType}.
     *
     * @param stateType the type of the state, cannot be {@code null}
     * @throws NullPointerException if the provided {@code stateType} is {@code null}
     */
    public void setState(StateType stateType) {
        this.state = Objects.requireNonNull(stateType, "StateType cannot be null.");
        updateTextColor(foregrounds.getColorByState(stateType));
        updateBackgroundColor(backgrounds.getColorByState(stateType));
        updateBorderColor(borderColors.getColorByState(stateType));
        repaint();
    }

    /**
     * Gets the {@code label} border corners' radii.
     *
     * @return the {@code radii} object that represents corners' radii
     */
    public Radii getRadii() {
        return radii;
    }

    /**
     * Sets the corner radii of the {@code label} border according to the provided {@code raii} object.
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
     * Sets the same radius for all corners of the {@code label} border.
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
     * Sets the all corners' radius of the {@code label} border according to provided values.
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
        this.radii.setRadii(topLeft, topRight, bottomLeft, bottomRight);
        repaint();
    }

    /**
     * Gets the top left corner radius of the {@code label} border.
     *
     * @return the top left corner radius
     */
    public int getTopLeftRadius() {
        return radii.topLeft();
    }

    /**
     * Sets the top left corner radius of the {@code label} border.
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
     * Gets the top right corner radius of the {@code label} border.
     *
     * @return the right left corner radius
     */
    public int getTopRightRadius() {
        return radii.topRight();
    }

    /**
     * Sets the top right corner radius of the {@code label} border.
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
     * Gets the bottom left corner radius of the {@code label} border.
     *
     * @return the bottom left corner radius
     */
    public int getBottomLeftRadius() {
        return radii.bottomLeft();
    }

    /**
     * Sets the bottom left corner radius of the {@code label} border.
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
     * Gets the bottom right corner radius of the {@code label} border.
     *
     * @return the bottom right corner radius
     */
    public int getBottomRightRadius() {
        return radii.bottomRight();
    }

    /**
     * Sets the bottom right corner radius of the {@code label} border.
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
     * Gets the internal gaps between the input area and the {@code label} border.
     *
     * @return internal gaps
     */
    public Padding getPadding() {
        return padding;
    }

    /**
     * Sets the internal gaps between the input area and the {@code label} border according to provided {@code padding}
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
     * Sets the same internal gaps for all directions between the input area and the {@code label}
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
     * Sets the internal gaps for all directions between the input area and the {@code label} according to
     * the provided gaps.
     *
     * @param top    the internal gap between the input area and the {@code label} border's top side, cannot be
     *               negative
     * @param left   the internal gap between the input area and the {@code label} border's left side, cannot be
     *               negative
     * @param bottom the internal gap between the input area and the {@code label} border's bottom side, cannot be
     *               negative
     * @param right  the internal gap between the input area and the {@code label} border's right side, cannot be
     *               negative
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
     * Gets the internal gap between the input area and the {@code label} border's top side.
     *
     * @return the internal gap between the input area and the {@code label} border's top side
     */
    public int getTopPadding() {
        return padding.top();
    }

    /**
     * Sets the internal gap between the input area and the {@code label} border's top side.
     *
     * @param padding the internal gap between the input area and the {@code label} border's top side, cannot be
     *                negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setTopPadding(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.padding.setTop(padding);
        updatePadding();
    }

    /**
     * Gets the internal gap between the input area and the {@code label} border's left side.
     *
     * @return the internal gap between the input area and the {@code label} border's left side
     */
    public int getLeftPadding() {
        return padding.left();
    }

    /**
     * Sets the internal gap between the input area and the {@code label} border's left side.
     *
     * @param padding the internal gap between the input area and the {@code label} border's left side, cannot be
     *                negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setLeftPadding(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.padding.setLeft(padding);
        updatePadding();
    }

    /**
     * Gets the internal gap between the input area and the {@code label} border's bottom side.
     *
     * @return the internal gap between the input area and the {@code label} border's bottom side
     */
    public int getBottomPadding() {
        return padding.bottom();
    }

    /**
     * Sets the internal gap between the input area and the {@code label} border's bottom side.
     *
     * @param padding the internal gap between the input area and the {@code label} border's bottom side, cannot be
     *                negative
     * @throws IllegalArgumentException if the provided {@code padding} is negative
     */
    public void setBottomPadding(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.padding.setBottom(padding);
        updatePadding();
    }

    /**
     * Gets the internal gap between the input area and the {@code label} border's right side.
     *
     * @return the internal gap between the input area and the {@code label} border's right side
     */
    public int getRightPadding() {
        return padding.right();
    }

    /**
     * Sets the internal gap between the input area and the {@code label} border's right side.
     *
     * @param padding the internal gap between the input area and the {@code label} border's right side, cannot be
     *                negative
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
        getData();
        updatePadding();

        setOpaque(false);
        super.setFont(font);
        super.setForeground(foreground);
        setBackgroundColor(background);
    }

    private StateColors getDefaultForegrounds(){
        return new StateColors(new Color(255, 255, 255),
                        new Color(200, 200, 200),
                        new Color(255, 255, 255),
                        new Color(200, 200, 200));
    }

    private StateColors getDefaultBackgrounds(){
        return new StateColors(new Color(80, 80, 80),
                               new Color(120, 120, 120),
                               new Color(0, 100, 0),
                               new Color(0, 140, 0));
    }

    private StateColors getDefaultBorderColors(){
        return new StateColors(new Color(40, 40, 40),
                               new Color(80, 80, 80),
                               new Color(0, 60, 0),
                               new Color(0, 100, 0));
    }

    private void getData() {
        foregrounds = getDefaultForegrounds();
        backgrounds = getDefaultBackgrounds();
        borderColors = getDefaultBorderColors();

        foreground = foregrounds.getColorByState(StateType.MAIN);
        background = backgrounds.getColorByState(StateType.MAIN);
        borderColor = borderColors.getColorByState(StateType.MAIN);

        super.setFont(font);
        super.setForeground(foreground);
        setBackgroundColor(background);
    }

    private void updateTextColor(Color color) {
        this.foreground = color;
        super.setForeground(color);
    }

    private void updateBackgroundColor(Color color) {
        this.background = color;
    }

    private void updateBorderColor(Color color) {
        this.borderColor = color;
    }

    private final MouseAdapter hoverAdapter = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            setState(isSelected ? StateType.SELECTED_HOVER : StateType.MAIN_HOVER);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setState(isSelected ? StateType.SELECTED : StateType.MAIN);
        }
    };

    private final MouseAdapter selectionAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            isSelected = !isSelected;
            setState(isSelected ? StateType.SELECTED : StateType.MAIN);
        }
    };
}