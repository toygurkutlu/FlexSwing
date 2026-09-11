package io.github.toygurkutlu.flexswing.components;


import io.github.toygurkutlu.flexswing.components.color_picker.ui.ColorDialog;
import io.github.toygurkutlu.flexswing.components.color_picker.ui.ColorPanel;
import io.github.toygurkutlu.flexswing.components.color_picker.ui.ColorPopup;

import javax.swing.*;
import java.awt.*;

/**
 * {@code ColorPicker} is highly flexible in deployment, providing three versatile components:
 * <ul>
 *     <li>{@link ColorPanel}: A standalone panel that can be added directly into any standard Swing {@link java.awt.Container}.</li>
 *     <li>{@link ColorDialog}: A displayable dialog based on {@link javax.swing.JDialog}.</li>
 *     <li>{@link ColorPopup}: A displayable lightweight popup associated with any standard {@link javax.swing.JComponent}.</li>
 * </ul>
 *
 * <p>Use one of the following factory methods to create the desired component:</p>
 * <ul>
 *     <li>{@link #createColorPanel()}: Creates a {@link ColorPanel} component. You can add it to any
 *     Swing container using the {@code add()} method (e.g., {@code container.add(colorPanel)}).</li>
 *     <li>{@link #createColorDialog(String, String, String, OnColorSelectedListener)}: Creates a {@link ColorDialog}
 *     component. Use the {@link ColorDialog#showDialog()} method to display it.</li>
 *     <li>{@link #createColorPopup(Component, OnColorSelectedListener)}: Creates a {@link ColorPopup}
 *     component. Use the {@link ColorPopup#showPopup()} method to display it.</li>
 * </ul>
 */
public class ColorPicker {

    private Color[] firstRowDefaultColors;
    private Color[] secondRowDefaultColors;
    private Color displayColor;
    private Color paletteBackground = new Color(45, 45, 45, 255);
    private Color paletteForeground = new Color(200, 200, 175, 255);
    private Font paletteFont = new Font("Sky Sans Medium Small Caps", Font.BOLD, 16);
    private Color hoverColor = new Color(95, 95, 95, 255);
    private Color displayAreaBorderColor = new Color(175, 175, 150, 255);
    private ColorPanel colorPanel;

    public interface OnColorSelectedListener {
        void onColorSelected(Color color);
    }

    /**
     * Main constructor for {@code ColorPicker}.
     *
     * @param displayColor the initial color shown when {@code ColorPicker} starts up
     */
    public ColorPicker(Color displayColor) {
        this.displayColor = displayColor;
        initColors();
    }

    private Color[] firstRowDefaultColors() {
        return new Color[]{
                new Color(40, 13, 13, 255),
                new Color(150, 75, 255, 255),
                new Color(75, 85, 60, 255),
                new Color(14, 90, 145, 255),
                new Color(100, 110, 110, 255),
                new Color(56, 91, 8, 255),
                new Color(122, 107, 55, 255),
                new Color(180, 80, 80, 255),
                new Color(190, 102, 17, 255),
                new Color(25, 89, 86, 255)
        };
    }

    private Color[] secondRowDefaultColors() {
        return new Color[]{
                new Color(248, 0, 0, 255),
                new Color(246, 190, 11, 255),
                new Color(204, 159, 5, 255),
                new Color(75, 65, 75, 255),
                new Color(13, 164, 146, 255),
                new Color(255, 255, 255, 255),
                new Color(95, 31, 95, 255),
                new Color(0, 0, 0, 255),
                new Color(65, 75, 125, 255),
                new Color(19, 165, 38, 255)
        };
    }

    private void initColors() {
        firstRowDefaultColors = firstRowDefaultColors();
        secondRowDefaultColors = secondRowDefaultColors();
    }

    /**
     * Gets the default colors for the first row (1-10).
     *
     * @return color array for the first row default colors.
     */
    public Color[] getFirstRowDefaultColors() {
        return colorPanel == null ? firstRowDefaultColors : colorPanel.getFirstRowDefaultColors();
    }

    /**
     * Sets the default colors for the second row (1-10).
     *
     * @param colors the color array for the first row default colors.
     * @throws ArrayIndexOutOfBoundsException if the length of the array is different from 10.
     */
    public void setFirstRowDefaultColors(Color[] colors) {
        if (colors.length == 10) {
            firstRowDefaultColors = colors;
            if (colorPanel != null) colorPanel.setFirstRowDefaultColors(colors);
        }
    }

    /**
     * Gets the default colors for the second row (11-20).
     *
     * @return color array for the second row default colors.
     */
    public Color[] getSecondRowDefaultColors() {
        return colorPanel == null ? secondRowDefaultColors : colorPanel.getSecondRowDefaultColors();
    }

    /**
     * Sets the default colors for the second row (11-20).
     *
     * @param colors the color array for the second row default colors.
     * @throws ArrayIndexOutOfBoundsException if the length of the array is different from 10.
     */
    public void setSecondRowDefaultColors(Color[] colors) {
        if (colors.length == 10) {
            secondRowDefaultColors = colors;
            if (colorPanel != null) colorPanel.setSecondRowDefaultColors(colors);
        }
    }

    /**
     * Gets the color selected by the user.
     *
     * @return the selected color.
     */
    public Color getDisplayColor() {
        return colorPanel == null ? displayColor : colorPanel.getDisplayColor();
    }

    /**
     * Sets the color for the {@code ColorPicker}.
     *
     * @param color the selected color.
     */
    public void setDisplayColor(Color color) {
        this.displayColor = color;

        if (colorPanel != null) colorPanel.setDisplayColor(color);
    }

    /**
     * Gets the {@code ColorPicker}'s background color.
     *
     * @return background color of the {@code ColorPicker}.
     */
    public Color getPaletteBackground() {
        return colorPanel == null ? paletteBackground : colorPanel.getPaletteBackground();
    }

    /**
     * Sets the {@code ColorPicker}'s background color.
     *
     * @param bg the new background color to set.
     * @apiNote Avoid using {@link JComponent#setBackground(Color)}. This method may throw a {@link NullPointerException} if components are not yet initialized.
     */
    public void setPaletteBackground(Color bg) {
        this.paletteBackground = bg;
        if (colorPanel != null) colorPanel.setPaletteBackground(bg);
    }

    /**
     * Gets the {@code ColorPicker}'s text color.
     *
     * @return the foreground color of the {@code ColorPicker}'s texts.
     */
    public Color getPaletteForeground() {
        return colorPanel == null ? paletteForeground : colorPanel.getPaletteForeground();
    }

    /**
     * Sets the {@code ColorPicker}'s text color.
     *
     * @param fg the new text color to set.
     * @apiNote Avoid using {@link JComponent#setForeground(Color)}. This method may throw a {@link NullPointerException} if components are not yet initialized.
     */
    public void setPaletteForeground(Color fg) {
        this.paletteForeground = fg;
        if (colorPanel != null) colorPanel.setPaletteForeground(fg);
    }

    /**
     * Gets the {@code ColorPicker}'s text font.
     *
     * @return the font of the {@code ColorPicker}'s texts.
     */
    public Font getPaletteFont() {
        return colorPanel == null ? paletteFont : colorPanel.getPaletteFont();
    }

    /**
     * Sets the {@code ColorPicker}'s text font.
     *
     * @param font the new text font to set.
     * @apiNote Avoid using {@link JComponent#setFont(Font)} This method may throw a {@link NullPointerException} if components are not yet initialized.
     */
    public void setPaletteFont(Font font) {
        this.paletteFont = font;
        if (colorPanel != null) colorPanel.setPaletteFont(font);
    }

    /**
     * Gets the border color of the selected color's display area.
     *
     * @return borderColor the border color.
     */
    public Color getDisplayAreaBorderColor() {
        return colorPanel == null ? displayAreaBorderColor : colorPanel.getDisplayAreaBorderColor();
    }

    /**
     * Sets the border color of the selected color's display area.
     *
     * @param borderColor the new border color.
     */
    public void setDisplayAreaBorderColor(Color borderColor) {
        this.displayAreaBorderColor = borderColor;
        if (colorPanel != null) colorPanel.setDisplayAreaBorderColor(borderColor);
    }

    /**
     * Gets the hover color for the {@code ColorPicker}'s default color boxes.
     *
     * @return the hover color of the default color boxes.
     * @apiNote The {@code hoverColor} is used to highlight the outer border of the default color boxes when the mouse hovers over them.
     */
    public Color getHoverColor() {
        return colorPanel == null ? hoverColor : colorPanel.getHoverColor();
    }

    /**
     * Sets the hover color for the {@code ColorPicker}'s default color boxes.
     *
     * @param hoverColor the new hover color to set.
     * @apiNote The {@code hoverColor} is used to highlight the outer border of the default color boxes when the mouse hovers over them.
     */
    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
        if (colorPanel != null) colorPanel.setHoverColor(hoverColor);
    }

    //UI

    /**
     * Creates a {@link ColorPanel} that can be added directly into any standard Swing container
     * (e.g., {@code container.add(colorPanel)}).
     *
     * @return the {@link ColorPanel} component which extends {@link javax.swing.JPanel}
     * @apiNote After creation, the user can set an {@link OnColorSelectedListener} for handling
     * color selection via the {@link OnColorSelectedListener#onColorSelected(Color)} method,
     * which receives the newly selected color as an argument.
     */
    public ColorPanel createColorPanel() {
        colorPanel = new ColorPanel(this);
        return colorPanel;
    }

    /**
     * Creates a {@link ColorDialog} containing positive and negative buttons for the {@link ColorPanel}.
     *
     * @param title        the title of the {@link javax.swing.JDialog}
     * @param positiveText the text of the positive {@link javax.swing.JButton} (default text is "Select")
     * @param negativeText the text of the negative {@link javax.swing.JButton} (default text is "Cancel")
     * @param listener     {@link OnColorSelectedListener} which user can handle color selection.
     * @return the {@link ColorDialog} component which extends {@link javax.swing.JDialog}
     * @apiNote Call {@link ColorDialog#showDialog()} method for displaying {@link ColorDialog}.
     */
    public ColorDialog createColorDialog(String title, String positiveText, String negativeText,
                                         OnColorSelectedListener listener) {
        colorPanel = new ColorPanel(this);
        return new ColorDialog(colorPanel, title, positiveText, negativeText, listener);
    }

    /**
     * Creates a {@link ColorPopup} designed to look and behave like a popup menu.
     *
     * @param parent   the parent {@link javax.swing.JComponent} associated with this {@link ColorPopup}
     * @param listener the {@link OnColorSelectedListener} to handle color selection events
     * @return the {@link ColorPopup} component which extends {@link javax.swing.JDialog}
     * @apiNote Call {@link ColorPopup#showPopup()} method for displaying {@link ColorPopup}.
     */
    public ColorPopup createColorPopup(Component parent, OnColorSelectedListener listener) {
        colorPanel = new ColorPanel(this);

        Window owner = SwingUtilities.getWindowAncestor(parent);
        ColorPopup popup = new ColorPopup(colorPanel, owner, listener);

        Point p = parent.getLocationOnScreen();

        popup.pack();
        int pw = popup.getWidth();
        int ph = popup.getHeight();

        Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

        int parentCenterX = p.x + (parent.getWidth() / 2);
        int x = parentCenterX - (pw / 2);
        int y = p.y + parent.getHeight();

        if (y + ph > screen.y + screen.height) y = p.y - ph;
        if (x < screen.x) x = screen.x;
        if (x + pw > screen.x + screen.width) x = (screen.x + screen.width) - pw;
        if (y < screen.y) y = screen.y;
        if (y + ph > screen.y + screen.height) y = (screen.y + screen.height) - ph;

        popup.setLocation(x, y);

        return popup;
    }
}