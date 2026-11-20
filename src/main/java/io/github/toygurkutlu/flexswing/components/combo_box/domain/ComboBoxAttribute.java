package io.github.toygurkutlu.flexswing.components.combo_box.domain;

import io.github.toygurkutlu.flexswing.objects.Radii;

import java.awt.*;

public class ComboBoxAttribute<T> {
    /**
     * Represents the background color of the combo box.
     *
     * @see #ARROW_BUTTON_BACKGROUND
     */
    public static ComboBoxAttribute<Color> DISPLAY_PANEL_BACKGROUND = new ComboBoxAttribute<>("DISPLAY_PANEL_BACKGROUND");
    /**
     * Represents the foreground color of the combo box.
     *
     * @see #ARROW_BUTTON_ICON_COLOR
     */
    public static ComboBoxAttribute<Color> DISPLAY_PANEL_FOREGROUND = new ComboBoxAttribute<>("DISPLAY_PANEL_FOREGROUND");
    /**
     * Represents the font of the combo box.
     */
    public static ComboBoxAttribute<Font> DISPLAY_PANEL_FONT = new ComboBoxAttribute<>("DISPLAY_PANEL_FONT");
    /**
     * Represents the text alignment of the combo box.
     */
    public static ComboBoxAttribute<TextAlignment> DISPLAY_PANEL_TEXT_ALIGNMENT = new ComboBoxAttribute<>("DISPLAY_PANEL_TEXT_ALIGNMENT");
    /**
     * Represents the border color of the combo box.
     *
     * @see #ARROW_BUTTON_BORDER_COLOR
     */
    public static ComboBoxAttribute<Color> DISPLAY_PANEL_BORDER_COLOR = new ComboBoxAttribute<>("DISPLAY_PANEL_BORDER_COLOR");
    /**
     * Represents the corner radii of the outer border of the combo box.
     */
    public static ComboBoxAttribute<Radii> DISPLAY_PANEL_RADII = new ComboBoxAttribute<>("DISPLAY_PANEL_RADII");
    /**
     * Represents the icon color of the arrow button.
     */
    public static ComboBoxAttribute<Color> ARROW_BUTTON_ICON_COLOR = new ComboBoxAttribute<>("ARROW_BUTTON_ICON_COLOR");
    /**
     * Represents the background color of the arrow button.
     */
    public static ComboBoxAttribute<Color> ARROW_BUTTON_BACKGROUND = new ComboBoxAttribute<>("ARROW_BUTTON_BACKGROUND");
    /**
     * Represents the background color of the arrow button on hover.
     */
    public static ComboBoxAttribute<Color> ARROW_BUTTON_BACKGROUND_HOVER = new ComboBoxAttribute<>("ARROW_BUTTON_BACKGROUND_HOVER");
    /**
     * Represents the background color of the arrow button when pressed.
     */
    public static ComboBoxAttribute<Color> ARROW_BUTTON_BACKGROUND_PRESSED = new ComboBoxAttribute<>("ARROW_BUTTON_BACKGROUND_PRESSED");
    /**
     * Represents the border color of the arrow button.
     *
     * @see #DISPLAY_PANEL_BORDER_COLOR
     * @see #POPUP_BORDER_COLOR
     */
    public static ComboBoxAttribute<Color> ARROW_BUTTON_BORDER_COLOR = new ComboBoxAttribute<>("ARROW_BUTTON_BORDER_COLOR");
    /**
     * Represents the background color of the popup (drop-down list).
     */
    public static ComboBoxAttribute<Color> POPUP_BACKGROUND = new ComboBoxAttribute<>("POPUP_BACKGROUND");
    /**
     * Represents the border color of the popup (drop-down list).
     *
     * @see #DISPLAY_PANEL_BORDER_COLOR
     * @see #ARROW_BUTTON_BORDER_COLOR
     */
    public static ComboBoxAttribute<Color> POPUP_BORDER_COLOR = new ComboBoxAttribute<>("POPUP_BORDER_COLOR");
    /**
     * Represents the background color of the items in the popup (drop-down list).
     *
     * @see #POPUP_SELECTED_ITEM_BACKGROUND
     */
    public static ComboBoxAttribute<Color> POPUP_ITEM_BACKGROUND = new ComboBoxAttribute<>("POPUP_ITEM_BACKGROUND");
    /**
     * Represents the foreground color of the items in the popup (drop-down list).
     *
     * @see #POPUP_SELECTED_ITEM_FOREGROUND
     */
    public static ComboBoxAttribute<Color> POPUP_ITEM_FOREGROUND = new ComboBoxAttribute<>("POPUP_ITEM_FOREGROUND");
    /**
     * Represents the background and hover background colors of the selected item in the popup (drop-down list).
     *
     * @see #POPUP_ITEM_BACKGROUND
     */
    public static ComboBoxAttribute<Color> POPUP_SELECTED_ITEM_BACKGROUND = new ComboBoxAttribute<>("POPUP_SELECTED_ITEM_BACKGROUND");
    /**
     * Represents the foreground and hover foreground colors of the selected item in the popup (drop-down list).
     *
     * @see #POPUP_ITEM_FOREGROUND
     */
    public static ComboBoxAttribute<Color> POPUP_SELECTED_ITEM_FOREGROUND = new ComboBoxAttribute<>("POPUP_SELECTED_ITEM_FOREGROUND");
    /**
     * Represents the font of the items in the popup (drop-down list).
     */
    public static ComboBoxAttribute<Font> POPUP_FONT = new ComboBoxAttribute<>("POPUP_FONT");
    /**
     * Represents whether popup contains tooltip.
     */
    public static ComboBoxAttribute<Boolean> POPUP_HAS_TOOLTIP = new ComboBoxAttribute<>("POPUP_HAS_TOOLTIP");
    /**
     * Represents the track color of the vertical scroll bar in the popup (drop-down list).
     */
    public static ComboBoxAttribute<Color> POPUP_SCROLL_TRACK_COLOR = new ComboBoxAttribute<>("POPUP_SCROLL_TRACK_COLOR");
    /**
     * Represents the thumb color of the vertical scroll bar in the popup (drop-down list).
     */
    public static ComboBoxAttribute<Color> POPUP_SCROLL_THUMB_COLOR = new ComboBoxAttribute<>("POPUP_SCROLL_THUMB_COLOR");
    /**
     * Represents the thumb color of the vertical scroll bar in the popup (drop-down list) while dragging.
     */
    public static ComboBoxAttribute<Color> POPUP_SCROLL_THUMB_DRAG_COLOR = new ComboBoxAttribute<>("POPUP_SCROLL_THUMB_DRAG_COLOR");
    /**
     * Represents the radius of thumb for the vertical scroll bar in the popup (drop-down list).
     */
    public static ComboBoxAttribute<Integer> POPUP_SCROLL_THUMB_RADIUS = new ComboBoxAttribute<>("POPUP_SCROLL_THUMB_RADIUS");

    private final String name;

    public ComboBoxAttribute(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
