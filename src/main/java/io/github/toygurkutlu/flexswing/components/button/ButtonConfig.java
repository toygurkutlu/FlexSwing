package io.github.toygurkutlu.flexswing.components.button;

import io.github.toygurkutlu.flexswing.components.FlexButton;
import io.github.toygurkutlu.flexswing.objects.HorizontalTextPosition;
import io.github.toygurkutlu.flexswing.objects.Padding;
import io.github.toygurkutlu.flexswing.objects.Radii;
import io.github.toygurkutlu.flexswing.objects.VerticalTextPosition;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

/**
 * Configuration of the {@code FlexButton}. Contains all required attribute to build and customize
 * the {@code FlexButton}.
 * <ol><li>Creating a config for the {@code FlexButton}:</li><ul>
 * <li>Either create a new config instance via using the constructor. For example:<pre>{@code
 *   ButtonConfig config = new ButtonConfig(new ButtonColors(Color.RED, Color.GREEN, Color.BLUE),
 *                                          true,
 *                                          new ButtonColors(Color.GREEN, Color.BLUE, Color.RED),
 *                                          new ButtonColors(Color.BLUE, Color.RED, Color.GREEN),
 *                                          new Font("Arial", Font.BOLD, 14),
 *                                          HorizontalTextPosition.RIGHT,
 *                                          VerticalTextPosition.CENTER,
 *                                          0,
 *                                          new Padding(5,10,5,10),
 *                                          new Radii(15,0,0,15));
 * }</pre></li>
 *
 * <li>Or use the default config with {@link #defaultConfig()} method and customize specific properties during
 * creation by chaining {@code with...()} methods. For example:<pre>{@code
 *                ButtonConfig config = ButtonConfig.defaultConfig()
 *                         .withBackgroundMain(Color.RED)
 *                         .withBackgroundHover(Color.GREEN)
 *                         .withBackgroundPressed(Color.BLUE)
 *                         .withBorderColors(new ButtonColors(Color.GREEN, Color.BLUE, Color.RED))
 *                         .withForegrounds(new ButtonColors(new Color(0, 0, 255),
 *                                                           new Color(255, 0, 0),
 *                                                           new Color(0, 255, 0)))
 *                         .withFont(new Font("Arial", Font.BOLD, 14))
 *                         .withHorizontalTextPosition(HorizontalTextPosition.RIGHT);
 * }</pre></li></ul>
 * <li>Setting the config to the {@code FlexButton}:</li><ul>
 *     <li>Either pass the config instance as a parameter via constructor while creating an instance for the button.
 *     For example:<pre>{@code
 *         FlexButton button = new FlexButton("MyButton", config);
 *     }</pre></li>
 *     <li>Or after creating an instance, use {@link FlexButton#setConfig(ButtonConfig)} method. For example:<pre>{@code
 *         FlexButton button = new FlexButton("MyButton");
 *         button.setConfig(config);
 *     }</pre></li>
 * </ul>
 * </ol>
 *
 * @see FlexButton
 * @see ButtonColors
 * @see HorizontalTextPosition
 * @see VerticalTextPosition
 * @see Padding
 * @see Radii
 */
public class ButtonConfig {

    /**
     * Represents the background colors (main, hover and pressed) of the button.
     */
    private ButtonColors bgs;
    /**
     * Represents whether the border is painted or not.
     */
    private boolean hasBorder;
    /**
     * Represents the border colors (main, hover and pressed) of the button.
     */
    private ButtonColors bcs;
    /**
     * Represents the foreground colors (main, hover and pressed) of the button.
     */
    private ButtonColors fgs;
    /**
     * Represents the text font of the button.
     */
    private Font font;
    /**
     * Represents the horizontal text position according to the icon.
     */
    private HorizontalTextPosition htp;
    /**
     * Represents the vertical text position according to the icon.
     */
    private VerticalTextPosition vtp;
    /**
     * Represents the gap between the icon and the text.
     */
    private int iconTextGap;
    /**
     * Represents the inner gap between the input area (text and icon) and the button border.
     */
    private Padding padding;
    /**
     * Represents the all corners' radii of the button.
     */
    private Radii radii;

    /**
     * Constructs a new configuration for the button.
     *
     * @param hasBorder   {@code true} to paint the border of the button; {@code false} otherwise
     * @param bgs         the {@code ButtonColors} object that contains the colors of the all 3 background states
     *                    (main, hover and pressed); cannot be {@code null}
     * @param bcs         the {@code ButtonColors} object that contains the colors of the all 3 border color states
     *                    (main, hover and pressed); cannot be {@code null}
     * @param fgs         the {@code ButtonColors} object that contains the colors of the all 3 foreground states
     *                    (main, hover and pressed); cannot be {@code null}
     * @param font        the font of the text; cannot be {@code null}
     * @param htp         the horizontal position of the text; cannot be {@code null}
     * @param vtp         the vertical position of the text; cannot be {@code null}
     * @param iconTextGap the gap between the icon and the text; cannot be negative
     * @param padding     the inner padding between the input area and the border button; cannot be {@code null}
     * @param radii       the {@code Radii} object that represents the corners' radii; cannot be {@code null}
     * @throws NullPointerException     if any of the provided parameters are {@code null}
     * @throws IllegalArgumentException if the provided iconTextGap is negative
     * @see ButtonColors
     * @see HorizontalTextPosition
     * @see VerticalTextPosition
     * @see Padding
     * @see Radii
     */
    public ButtonConfig(boolean hasBorder, @NotNull ButtonColors bgs, @NotNull ButtonColors bcs,
                        @NotNull ButtonColors fgs, @NotNull Font font, @NotNull HorizontalTextPosition htp,
                        @NotNull VerticalTextPosition vtp, int iconTextGap, @NotNull Padding padding,
                        @NotNull Radii radii) {
        this.bgs = Objects.requireNonNull(bgs, "Backgrounds cannot be null.");
        this.hasBorder = hasBorder;
        this.bcs = Objects.requireNonNull(bcs, "BorderColors cannot be null.");
        this.fgs = Objects.requireNonNull(fgs, "Foregrounds cannot be null.");
        this.font = Objects.requireNonNull(font, "Font cannot be null.");
        this.htp = htp;
        this.vtp = vtp;
        this.iconTextGap = iconTextGap;
        this.padding = Objects.requireNonNull(padding, "Padding cannot be null.");
        this.radii = Objects.requireNonNull(radii, "Radii cannot be null.");
    }

    /**
     * Gets whether the button has border or not.
     *
     * @return {@code true} if the button has border; {@code false} otherwise
     */
    public boolean isHasBorder() {
        return hasBorder;
    }

    /**
     * Sets the button border config according to the provided value.
     *
     * @param hasBorder {@code true} to paint the border of the button; {@code false} otherwise
     */
    public void setHasBorder(boolean hasBorder) {
        this.hasBorder = hasBorder;
    }

    /**
     * Gets the background colors.
     *
     * @return {@code ButtonColors} instance for background colors
     * @see ButtonColors
     */
    public ButtonColors getBackgrounds() {
        return bgs;
    }

    /**
     * Sets the background colors according to the provided colors.
     *
     * @param bgs the new {@code ButtonColors} instance; cannot be {@code null}
     * @throws NullPointerException if the provided bgs is {@code null}
     * @see ButtonColors
     */
    public void setBackgrounds(ButtonColors bgs) {
        this.bgs = Objects.requireNonNull(bgs, "Backgrounds cannot be null.");
    }

    /**
     * Sets the main background color according to the provided color.
     *
     * @param bgm the new main background color; cannot be {@code null}
     * @throws NullPointerException if the provided fgm is {@code null}
     */
    public void setBackgroundMain(Color bgm) {
        this.bgs.setMain(Objects.requireNonNull(bgm, "Main background color cannot be null."));
    }

    /**
     * Sets the hover background color according to the provided color.
     *
     * @param bgh the new hover background color; cannot be {@code null}
     * @throws NullPointerException if the provided bgh is {@code null}
     */
    public void setBackgroundHover(Color bgh) {
        this.bgs.setHover(Objects.requireNonNull(bgh, "Hover background color cannot be null."));
    }

    /**
     * Sets the pressed background color according to the provided color.
     *
     * @param bgp the new pressed background color; cannot be {@code null}
     * @throws NullPointerException if the provided bgp is {@code null}
     */
    public void setBackgroundPressed(Color bgp) {
        this.bgs.setPressed(Objects.requireNonNull(bgp, "Pressed background color cannot be null."));
    }


    /**
     * Gets the border colors.
     *
     * @return {@code ButtonColors} instance for border colors
     * @see ButtonColors
     */
    public ButtonColors getBorderColors() {
        return bcs;
    }

    /**
     * Sets the border colors according to the provided colors.
     *
     * @param bcs the new {@code ButtonColors} instance; cannot be {@code null}
     * @throws NullPointerException if the provided bcs is {@code null}
     * @see ButtonColors
     */
    public void setBorderColors(ButtonColors bcs) {
        this.bcs = Objects.requireNonNull(bcs, "BorderColors cannot be null.");
    }


    /**
     * Sets the main border color according to the provided color.
     *
     * @param bcm the new main border color; cannot be {@code null}
     * @throws NullPointerException if the provided bcm is {@code null}
     */
    public void setBorderColorMain(Color bcm) {
        this.bcs.setMain(Objects.requireNonNull(bcm, "Main border color cannot be null."));
    }

    /**
     * Sets the hover border color according to the provided color.
     *
     * @param bch the new hover border color; cannot be {@code null}
     * @throws NullPointerException if the provided bch is {@code null}
     */
    public void setBorderColorHover(Color bch) {
        this.bcs.setHover(Objects.requireNonNull(bch, "Hover border color cannot be null."));
    }

    /**
     * Sets the pressed border color according to the provided color.
     *
     * @param bcp the new pressed border color; cannot be {@code null}
     * @throws NullPointerException if the provided bcp is {@code null}
     */
    public void setBorderColorPressed(Color bcp) {
        this.bcs.setPressed(Objects.requireNonNull(bcp, "Pressed border color cannot be null."));
    }

    /**
     * Gets the foreground colors.
     *
     * @return {@code ButtonColors} instance for foreground colors
     * @see ButtonColors
     */
    public ButtonColors getForegrounds() {
        return fgs;
    }

    /**
     * Sets the foreground colors according to the provided colors.
     *
     * @param fgs the new {@code ButtonColors} instance; cannot be {@code null}
     * @throws NullPointerException if the provided bcs is {@code null}
     * @see ButtonColors
     */
    public void setForegrounds(ButtonColors fgs) {
        this.fgs = Objects.requireNonNull(fgs, "Foregrounds cannot be null.");
    }

    /**
     * Sets the main foreground color according to the provided color.
     *
     * @param fgm the new main foreground color; cannot be {@code null}
     * @throws NullPointerException if the provided fgm is {@code null}
     */
    public void setForegroundMain(Color fgm) {
        this.fgs.setMain(Objects.requireNonNull(fgm, "Main foreground color cannot be null."));
    }

    /**
     * Sets the hover foreground color according to the provided color.
     *
     * @param fgh the new hover foreground color; cannot be {@code null}
     * @throws NullPointerException if the provided fgh is {@code null}
     */
    public void setForegroundHover(Color fgh) {
        this.fgs.setHover(Objects.requireNonNull(fgh, "Hover foreground color cannot be null."));
    }

    /**
     * Sets the pressed foreground color according to the provided color.
     *
     * @param fgp the new pressed foreground color; cannot be {@code null}
     * @throws NullPointerException if the provided fgp is {@code null}
     */
    public void setForegroundPressed(Color fgp) {
        this.fgs.setPressed(Objects.requireNonNull(fgp, "Pressed foreground color cannot be null."));
    }

    /**
     * Gets the font of the button.
     *
     * @return the font of the button.
     */
    public Font getFont() {
        return font;
    }

    /**
     * Sets the font of the button.
     *
     * @param font the new font; cannot be {@code null}
     * @throws NullPointerException if the provided font is {@code null}
     */
    public void setFont(Font font) {
        this.font = Objects.requireNonNull(font, "Font cannot be null.");
    }

    /**
     * Gets the horizontal text position.
     *
     * @return the {@code HorizontalTextPosition} instance of the button
     */
    public HorizontalTextPosition getHorizontalTextPosition() {
        return htp;
    }

    /**
     * Sets the horizontal text position of the button.
     *
     * @param htp the new horizontal position of the text; cannot be {@code null}
     * @throws NullPointerException if the provided htp is {@code null}
     */
    public void setHorizontalTextPosition(HorizontalTextPosition htp) {
        this.htp = Objects.requireNonNull(htp, "HorizontalTextPosition cannot be null.");
    }

    /**
     * Gets the vertical text position.
     *
     * @return the {@code VerticalTextPosition} instance of the button
     */
    public VerticalTextPosition getVerticalTextPosition() {
        return vtp;
    }

    /**
     * Sets the vertical text position of the button.
     *
     * @param vtp the new vertical position of the text; cannot be {@code null}
     * @throws NullPointerException if the provided vtp is {@code null}
     */
    public void setVerticalTextPosition(VerticalTextPosition vtp) {
        this.vtp = Objects.requireNonNull(vtp, "VerticalTextPosition cannot be null.");
    }

    /**
     * Gets the gap between the icon and the text.
     *
     * @return icon text gap as pixel
     */
    public int getIconTextGap() {
        return iconTextGap;
    }

    /**
     * Sets the gap between the icon and the text.
     *
     * @param iconTextGap the new gap between the icon and the text; cannot be negative
     * @throws IllegalArgumentException if the provided gap is negative
     */
    public void setIconTextGap(int iconTextGap) {
        if (iconTextGap < 0) throw new IllegalArgumentException("IconTextGap cannot be negative.");
        this.iconTextGap = iconTextGap;
    }

    /**
     * Gets the internal gaps between the input area and the button border.
     *
     * @return the {@code Padding} object that represents internal gaps
     */
    public Padding getPadding() {
        return padding;
    }

    /**
     * Sets the internal gaps between the input area and the button border according to provided padding object.
     *
     * @param padding the new {@code Padding} instance; cannot be {@code null}
     * @throws NullPointerException if the provided padding is {@code null}
     */
    public void setPadding(Padding padding) {
        this.padding = Objects.requireNonNull(padding, "Padding cannot be null.");
    }

    /**
     * Sets the same internal gaps for all directions between the input area and the button borders.
     *
     * @param padding the new internal gaps for all directions; cannot be negative
     * @throws IllegalArgumentException if the provided padding is negative
     */
    public void setPadding(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.padding = new Padding(padding, padding, padding, padding);
    }

    /**
     * Sets the internal gaps for all directions between the input area and the button according to the provided gaps.
     *
     * @param top    the internal gap between the input area and the button border's top side; cannot be negative
     * @param left   the internal gap between the input area and the button border's left side; cannot be negative
     * @param bottom he internal gap between the input area and the button border's bottom side; cannot be negative
     * @param right  the internal gap between the input area and the button border's right side; cannot be negative
     * @throws IllegalArgumentException if any of the provided padding values are negative
     */
    public void setPadding(int top, int left, int bottom, int right) {
        if (top < 0) throw new IllegalArgumentException("Top padding cannot be negative.");
        if (left < 0) throw new IllegalArgumentException("Left padding cannot be negative.");
        if (bottom < 0) throw new IllegalArgumentException("Bottom padding cannot be negative.");
        if (right < 0) throw new IllegalArgumentException("Right padding cannot be negative.");

        this.padding.setPadding(top, left, bottom, right);
    }

    /**
     * Gets the internal gap between the input area and the button border's top side.
     *
     * @return the internal gap between the input area and the button border's top side
     */
    public int getPaddingTop() {
        return this.padding.top();
    }

    /**
     * Sets the internal gap between the input area and the button border's top side.
     *
     * @param padding the internal gap between the input area and the button border's top side; cannot be negative
     * @throws IllegalArgumentException if the provided padding is negative
     */
    public void setPaddingTop(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.padding.setTop(padding);
    }

    /**
     * Gets the internal gap between the input area and the button border's left side.
     *
     * @return the internal gap between the input area and the button border's left side
     */
    public int getPaddingLeft() {
        return this.padding.left();
    }

    /**
     * Sets the internal gap between the input area and the button border's left side.
     *
     * @param padding the internal gap between the input area and the button border's left side; cannot be negative
     * @throws IllegalArgumentException if the provided padding is negative
     */
    public void setPaddingLeft(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.padding.setLeft(padding);
    }

    /**
     * Gets the internal gap between the input area and the button border's bottom side.
     *
     * @return the internal gap between the input area and the button border's bottom side
     */
    public int getPaddingBottom() {
        return this.padding.bottom();
    }

    /**
     * Sets the internal gap between the input area and the button border's bottom side.
     *
     * @param padding the internal gap between the input area and the button border's bottom side; cannot be negative
     * @throws IllegalArgumentException if the provided padding is negative
     */
    public void setPaddingBottom(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.padding.setBottom(padding);
    }

    /**
     * Gets the internal gap between the input area and the button border's right side.
     *
     * @return the internal gap between the input area and the button border's right side
     */
    public int getPaddingRight() {
        return this.padding.right();
    }

    /**
     * Sets the internal gap between the input area and the button border's right side.
     *
     * @param padding the internal gap between the input area and the button border's right side; cannot be negative
     * @throws IllegalArgumentException if the provided padding is negative
     */
    public void setPaddingRight(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative.");
        this.padding.setRight(padding);
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
     * @param radii the new {@code radii} object; cannot be {@code null}
     * @throws NullPointerException if the provided {@code radii} object is {@code null}
     * @see Radii
     * @see Radii#Radii(int, int, int, int)
     */
    public void setRadii(Radii radii) {
        this.radii = Objects.requireNonNull(radii, "Radii cannot be null.");
    }

    /**
     * Sets the all corners' radius of the button border according to provided values.
     *
     * @param topLeft     radius of the top left corner; cannot be negative
     * @param topRight    radius of the top right corner; cannot be negative
     * @param bottomLeft  radius of the bottom left corner; cannot be negative
     * @param bottomRight radius of the bottom right corner; cannot be negative
     * @throws IllegalArgumentException if any of the provided radius values are negative
     */
    public void setRadii(int topLeft, int topRight, int bottomLeft, int bottomRight) {
        if (topLeft < 0) throw new IllegalArgumentException("TopLeft radius cannot be negative.");
        if (topRight < 0) throw new IllegalArgumentException("TopRight radius cannot be negative.");
        if (bottomLeft < 0) throw new IllegalArgumentException("BottomLeft radius cannot be negative.");
        if (bottomRight < 0) throw new IllegalArgumentException("BottomRight radius cannot be negative.");
        this.radii.setRadii(topLeft, topRight, bottomLeft, bottomRight);
    }

    /**
     * Gets the top left corner radius of the button border.
     *
     * @return the top left corner radius
     */
    public int getTopLeftRadius() {
        return this.radii.topLeft();
    }

    /**
     * Sets the top left corner radius of the button border.
     *
     * @param radius the new top left corner radius; cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setTopLeftRadius(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        radii.setTopRight(radius);
    }

    /**
     * Gets the top right corner radius of the button border.
     *
     * @return the top right corner radius
     */
    public int getTopRightRadius() {
        return this.radii.topRight();
    }

    /**
     * Sets the top right corner radius of the button border.
     *
     * @param radius the new top right corner radius; cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setTopRightRadius(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        radii.setTopRight(radius);
    }

    /**
     * Gets the bottom left corner radius of the button border.
     *
     * @return the bottom left corner radius
     */
    public int getBottomLeftRadius() {
        return this.radii.bottomLeft();
    }

    /**
     * Sets the bottom left corner radius of the button border.
     *
     * @param radius the new bottom left radius; cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setBottomLeftRadius(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        radii.setBottomLeft(radius);
    }

    /**
     * Gets the bottom right corner radius of the button border.
     *
     * @return the bottom right corner radius
     */
    public int getBottomRightRadius() {
        return this.radii.bottomRight();
    }

    /**
     * Sets the bottom right corner radius of the button border.
     *
     * @param radius the new bottom right corner radius; cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setBottomRightRadius(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        radii.setBottomRight(radius);
    }

    /**
     * Sets the same radius for all corners of the button border.
     *
     * @param radius the corner radius; cannot be negative
     * @throws IllegalArgumentException if the provided {@code radius} is negative
     */
    public void setRadius(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        radii.setRadius(radius);
    }

    /**
     * Creates a new {@code ButtonConfig} instance initialized with the default values.
     *
     * @return a new {@code ButtonConfig} instance with default values
     * @apiNote You can customize specific properties during creation by chaining {@code with...()} methods,
     * or modify them afterward using the setter methods of this class.
     */
    public static ButtonConfig defaultConfig() {
        ButtonColors backgrounds = new ButtonColors(new Color(25, 25, 25),
                                                    new Color(75, 75, 75),
                                                    new Color(155, 155, 155));
        ButtonColors borderColors = new ButtonColors(new Color(25, 25, 25),
                                                     new Color(75, 75, 75),
                                                     new Color(155, 155, 155));
        ButtonColors foregrounds = new ButtonColors(new Color(255, 255, 255),
                                                    new Color(25, 25, 25),
                                                    new Color(0, 0, 0));
        Font font = new Font("Dialog", Font.BOLD, 12);

        return new ButtonConfig(true, ButtonColors.defaultBackgrounds(), ButtonColors.defaultBorderColors(),
                                ButtonColors.defaultForegrounds(), font, HorizontalTextPosition.RIGHT,
                                VerticalTextPosition.CENTER, 0,
                                new Padding(5, 10, 5, 10),
                                new Radii(15, 15, 15, 15));
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the specified background colors.
     *
     * @param bgs the new background colors; cannot be {@code null}
     * @return a new {@code ButtonConfig} instance with the updated background colors
     * @throws NullPointerException if {@code bgs} is {@code null}
     * @see ButtonColors
     * @see #withBackgroundMain(Color)
     * @see #withBackgroundHover(Color)
     * @see #withBackgroundPressed(Color)
     */
    public ButtonConfig withBackgrounds(ButtonColors bgs) {
        return new ButtonConfig(this.hasBorder, Objects.requireNonNull(bgs, "Backgrounds cannot be null."),
                                this.bcs, this.fgs, this.font, this.htp, this.vtp, this.iconTextGap,
                                this.padding, this.radii);
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the specified main background color.
     *
     * @param bgm the new background color; cannot be {@code null}
     * @return a new {@code ButtonConfig} instance with the updated main background color
     * @throws NullPointerException if {@code bgm} is {@code null}
     * @apiNote Useful for changing only the main background color. To change all background colors at once,
     * use {@link #withBackgrounds(ButtonColors)} instead.
     * @see ButtonColors
     * @see #withBackgrounds(ButtonColors)
     * @see #withBackgroundHover(Color)
     * @see #withBackgroundPressed(Color)
     */
    public ButtonConfig withBackgroundMain(Color bgm) {
        return withBackgrounds(this.bgs.withMain(Objects.requireNonNull(bgm, "BackgroundMain cannot be null.")));
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the specified hover background color.
     *
     * @param bgh the new background color when hovered; cannot be {@code null}
     * @return a new {@code ButtonConfig} instance with the updated hover background color
     * @throws NullPointerException if {@code bgh} is {@code null}
     * @apiNote Useful for changing only the hover background color. To change all background colors at once,
     * use {@link #withBackgrounds(ButtonColors)} instead.
     * @see ButtonColors
     * @see #withBackgrounds(ButtonColors)
     * @see #withBackgroundMain(Color)
     * @see #withBackgroundPressed(Color)
     */
    public ButtonConfig withBackgroundHover(Color bgh) {
        return withBackgrounds(this.bgs.withHover(Objects.requireNonNull(bgh, "BackgroundHover cannot be null.")));
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the specified pressed background color.
     *
     * @param bgp the new background color when pressed; cannot be {@code null}
     * @return a new {@code ButtonConfig} instance with the updated pressed background color
     * @throws NullPointerException if {@code bgp} is {@code null}
     * @apiNote Useful for changing only the pressed background color. To change all background colors at once,
     * use {@link #withBackgrounds(ButtonColors)} instead.
     * @see ButtonColors
     * @see #withBackgrounds(ButtonColors)
     * @see #withBackgroundMain(Color)
     * @see #withBackgroundHover(Color)
     */
    public ButtonConfig withBackgroundPressed(Color bgp) {
        return withBackgrounds(this.bgs.withPressed(Objects.requireNonNull(bgp, "BackgroundPressed cannot be null.")));
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the specified button border config.
     *
     * @param hasBorder {@code true} to paint the border of the button; {@code false} otherwise
     * @return a new {@code ButtonConfig} instance with the updated border config
     */
    public ButtonConfig withHasBorder(boolean hasBorder) {
        return new ButtonConfig(hasBorder, this.bgs, this.bcs, this.fgs, this.font, this.htp, this.vtp,
                                this.iconTextGap, this.padding, this.radii);
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the specified border colors.
     *
     * @param bcs the new border colors; cannot be {@code null}
     * @return a new {@code ButtonConfig} instance with the updated border colors
     * @throws NullPointerException if {@code bcs} is {@code null}
     * @apiNote If {@link ButtonAttribute#HAS_BORDER} is {@code false}, the border is not drawn and
     * these colors are ignored.
     * @see ButtonColors
     * @see #withBorderColorMain(Color)
     * @see #withBorderColorHover(Color)
     * @see #withBorderColorPressed(Color)
     */
    public ButtonConfig withBorderColors(ButtonColors bcs) {
        return new ButtonConfig(this.hasBorder, this.bgs,
                                Objects.requireNonNull(bcs, "BorderColors cannot be null."), this.fgs,
                                this.font, this.htp, this.vtp, this.iconTextGap, this.padding, this.radii);
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the specified main border color.
     *
     * @param bcm the new border color; cannot be {@code null}
     * @return a new {@code ButtonConfig} instance with the updated main border color
     * @throws NullPointerException if {@code bcm} is {@code null}
     * @apiNote Useful for changing only the main border color. To change all border colors at once,
     * use {@link #withBorderColors(ButtonColors)} instead. Note that if {@link ButtonAttribute#HAS_BORDER}
     * is {@code false}, the border is not drawn and this color is ignored.
     * @see ButtonColors
     * @see #withBorderColors(ButtonColors)
     * @see #withBorderColorHover(Color)
     * @see #withBorderColorPressed(Color)
     */
    public ButtonConfig withBorderColorMain(Color bcm) {
        return withBorderColors(this.bgs.withMain(Objects.requireNonNull(bcm, "BorderColorMain cannot be null.")));
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the specified hover border color.
     *
     * @param bch the new border color when hovered; cannot be {@code null}
     * @return a new {@code ButtonConfig} instance with the updated hover border color
     * @throws NullPointerException if {@code bch} is {@code null}
     * @apiNote Useful for changing only the hover border color. To change all border colors at once,
     * use {@link #withBorderColors(ButtonColors)} instead. Note that if {@link ButtonAttribute#HAS_BORDER}
     * is {@code false}, the border is not drawn and this color is ignored.
     * @see ButtonColors
     * @see #withBorderColors(ButtonColors)
     * @see #withBorderColorMain(Color)
     * @see #withBorderColorPressed(Color)
     */
    public ButtonConfig withBorderColorHover(Color bch) {
        return withBorderColors(this.bcs.withHover(Objects.requireNonNull(bch, "BorderColorHover cannot be null.")));
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the specified pressed border color.
     *
     * @param bcp the new border color when pressed; cannot be {@code null}
     * @return a new {@code ButtonConfig} instance with the updated pressed border color
     * @throws NullPointerException if {@code bcp} is {@code null}
     * @apiNote Useful for changing only the pressed border color. To change all border colors at once,
     * use {@link #withBorderColors(ButtonColors)} instead. Note that if {@link ButtonAttribute#HAS_BORDER}
     * is {@code false}, the border is not drawn and this color is ignored.
     * @see ButtonColors
     * @see #withBorderColors(ButtonColors)
     * @see #withBorderColorMain(Color)
     * @see #withBorderColorHover(Color)
     */
    public ButtonConfig withBorderColorPressed(Color bcp) {
        return withBorderColors(this.bcs.withPressed(Objects.requireNonNull(bcp, "borderColorPressed cannot be null.")));
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the specified foreground colors.
     *
     * @param fgs the new foreground colors; cannot be {@code null}
     * @return a new {@code ButtonConfig} instance with the updated foreground colors
     * @throws NullPointerException if {@code fgs} is {@code null}
     * @see ButtonColors
     * @see #withForegroundMain(Color)
     * @see #withForegroundHover(Color)
     * @see #withForegroundPressed(Color)
     */
    public ButtonConfig withForegrounds(ButtonColors fgs) {
        return new ButtonConfig(this.hasBorder, this.bgs, this.bcs,
                                Objects.requireNonNull(fgs, "Foregrounds cannot be null."), this.font,
                                this.htp, this.vtp, this.iconTextGap, this.padding, this.radii);
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the specified main foreground color.
     *
     * @param fgm the new foreground color; cannot be {@code null}
     * @return a new {@code ButtonConfig} instance with the updated main foreground color
     * @throws NullPointerException if {@code fgm} is {@code null}
     * @apiNote Useful for changing only the main foreground color. To change all foreground colors at once,
     * use {@link #withForegrounds(ButtonColors)} instead.
     * @see ButtonColors
     * @see #withForegrounds(ButtonColors)
     * @see #withForegroundHover(Color)
     * @see #withForegroundPressed(Color)
     */
    public ButtonConfig withForegroundMain(Color fgm) {
        return withForegrounds(this.fgs.withMain(Objects.requireNonNull(fgm, "ForegroundMain cannot be null.")));
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the specified hover foreground color.
     *
     * @param fgh the new foreground color when hovered; cannot be {@code null}
     * @return a new {@code ButtonConfig} instance with the updated hover foreground color
     * @throws NullPointerException if {@code fgh} is {@code null}
     * @apiNote Useful for changing only the hover foreground color. To change all foreground colors at once,
     * use {@link #withForegrounds(ButtonColors)} instead.
     * @see ButtonColors
     * @see #withForegrounds(ButtonColors)
     * @see #withForegroundMain(Color)
     * @see #withForegroundPressed(Color)
     */
    public ButtonConfig withForegroundHover(Color fgh) {
        return withForegrounds(this.fgs.withHover(Objects.requireNonNull(fgh, "ForegroundHover cannot be null.")));
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the specified pressed foreground color.
     *
     * @param fgp the new foreground color when pressed; cannot be {@code null}
     * @return a new {@code ButtonConfig} instance with the updated pressed foreground color
     * @throws NullPointerException if {@code fgp} is {@code null}
     * @apiNote Useful for changing only the pressed foreground color. To change all foreground colors at once,
     * use {@link #withForegrounds(ButtonColors)} instead.
     * @see ButtonColors
     * @see #withForegrounds(ButtonColors)
     * @see #withForegroundMain(Color)
     * @see #withForegroundHover(Color)
     */
    public ButtonConfig withForegroundPressed(Color fgp) {
        return withForegrounds(this.fgs.withPressed(Objects.requireNonNull(fgp, "ForegroundPressed cannot be null.")));
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the new text font.
     *
     * @param font the new font; cannot be {@code null}
     * @throws NullPointerException if the provided font is {@code null}
     */
    public ButtonConfig withFont(Font font) {
        return new ButtonConfig(this.hasBorder, this.bgs, this.bcs, this.fgs,
                                Objects.requireNonNull(font, "Font cannot be null."), this.htp,
                                this.vtp, this.iconTextGap, this.padding, this.radii);
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the new horizontal position of the text relative to the icon.
     *
     * @param htp the horizontal text position; cannot be {@code null}
     * @throws NullPointerException if the provided htp is {@code null}
     */
    public ButtonConfig withHorizontalTextPosition(HorizontalTextPosition htp) {
        return new ButtonConfig(this.hasBorder, this.bgs, this.bcs, this.fgs, this.font,
                                Objects.requireNonNull(htp, "TextPosition cannot be null."), this.vtp,
                                this.iconTextGap, this.padding, this.radii);
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the new vertical position of the text relative to the icon.
     *
     * @param vtp the vertical text position; cannot be {@code null}
     * @throws NullPointerException if the provided vtp is {@code null}
     */
    public ButtonConfig withVerticalTextPosition(VerticalTextPosition vtp) {
        return new ButtonConfig(this.hasBorder, this.bgs, this.bcs, this.fgs, this.font, this.htp,
                                Objects.requireNonNull(vtp, "TextPosition cannot be null."), this.iconTextGap,
                                this.padding, this.radii);
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the new gap between the icon and the text.
     *
     * @param iconTextGap the gap between the icon and the text; cannot be negative
     * @throws IllegalArgumentException if the provided iconTextGap is negative
     */
    public ButtonConfig withIconTextGap(int iconTextGap) {
        if (iconTextGap < 0) throw new IllegalArgumentException("IconTextGap cannot be negative");
        return new ButtonConfig(this.hasBorder, this.bgs, this.bcs, this.fgs, this.font, this.htp, this.vtp,
                                iconTextGap, this.padding, this.radii);
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the new internal padding.
     *
     * @param padding the internal padding of the button; cannot be {@code null}
     * @throws NullPointerException if the provided padding is {@code null}
     */
    public ButtonConfig withPadding(Padding padding) {
        return new ButtonConfig(this.hasBorder, this.bgs, this.bcs, this.fgs, this.font, this.htp, this.vtp,
                                this.iconTextGap, Objects.requireNonNull(padding, "Padding cannot be null."),
                                this.radii);
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the new internal padding.
     *
     * @param padding the internal padding of the button; cannot be negative
     * @throws IllegalArgumentException if the provided padding is negative
     */
    public ButtonConfig withPadding(int padding) {
        if (padding < 0) throw new IllegalArgumentException("Padding cannot be negative");
        return new ButtonConfig(this.hasBorder, this.bgs, this.bcs, this.fgs, this.font, this.htp, this.vtp,
                                this.iconTextGap, new Padding(padding), this.radii);
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the new corner radii.
     *
     * @param radii the corner radii of the button; cannot be {@code null}
     * @throws NullPointerException if the provided radii is {@code null}
     */
    public ButtonConfig withRadii(Radii radii) {
        return new ButtonConfig(this.hasBorder, this.bgs, this.bcs, this.fgs, this.font, this.htp, this.vtp,
                                this.iconTextGap, this.padding,
                                Objects.requireNonNull(radii, "Radii cannot be null."));
    }

    /**
     * Creates a copy of this {@code ButtonConfig} with the new corner radius.
     *
     * @param radius the radius for applying all corners; cannot be negative
     * @throws IllegalArgumentException if the provided padding is negative
     */
    public ButtonConfig withRadii(int radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative");
        return new ButtonConfig(this.hasBorder, this.bgs, this.bcs, this.fgs, this.font, this.htp, this.vtp,
                                this.iconTextGap, this.padding, new Radii(radius));
    }
}