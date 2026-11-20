package io.github.toygurkutlu.flexswing.components.cycler;

import io.github.toygurkutlu.flexswing.components.combo_box.domain.TextAlignment;
import io.github.toygurkutlu.flexswing.objects.Padding;
import io.github.toygurkutlu.flexswing.objects.Radii;

import java.awt.*;

public class CyclerAttribute<T> {

    /**
     * Represents the background color of the combo box.
     *
     * @see #ARROW_BUTTON_BACKGROUND
     */
    public static CyclerAttribute<Color> DISPLAY_PANEL_BACKGROUND = new CyclerAttribute<>("DISPLAY_PANEL_BACKGROUND");
    /**
     * Represents the foreground color of the combo box.
     *
     * @see #ARROW_BUTTON_ICON_COLOR
     */
    public static CyclerAttribute<Color> DISPLAY_PANEL_FOREGROUND = new CyclerAttribute<>("DISPLAY_PANEL_FOREGROUND");
    /**
     * Represents the font of the combo box.
     */
    public static CyclerAttribute<Font> DISPLAY_PANEL_FONT = new CyclerAttribute<>("DISPLAY_PANEL_FONT");
    /**
     * Represents the text alignment of the combo box.
     */
    public static CyclerAttribute<TextAlignment> DISPLAY_PANEL_TEXT_ALIGNMENT = new CyclerAttribute<>("DISPLAY_PANEL_TEXT_ALIGNMENT");
    /**
     * Represents the border color of the combo box.
     *
     * @see #ARROW_BUTTON_BORDER_COLOR
     */
    public static CyclerAttribute<Color> DISPLAY_PANEL_BORDER_COLOR = new CyclerAttribute<>("DISPLAY_PANEL_BORDER_COLOR");
    /**
     * Represents the corner radii of the outer border of the combo box.
     */
    public static CyclerAttribute<Radii> DISPLAY_PANEL_RADII = new CyclerAttribute<>("DISPLAY_PANEL_RADII");
    /**
     * Represents the icon color of the arrow button.
     */
    public static CyclerAttribute<Color> ARROW_BUTTON_ICON_COLOR = new CyclerAttribute<>("ARROW_BUTTON_ICON_COLOR");
    /**
     * Represents the background color of the arrow button.
     */
    public static CyclerAttribute<Color> ARROW_BUTTON_BACKGROUND = new CyclerAttribute<>("ARROW_BUTTON_BACKGROUND");
    /**
     * Represents the background color of the arrow button on hover.
     */
    public static CyclerAttribute<Color> ARROW_BUTTON_BACKGROUND_HOVER = new CyclerAttribute<>("ARROW_BUTTON_BACKGROUND_HOVER");
    /**
     * Represents the background color of the arrow button when pressed.
     */
    public static CyclerAttribute<Color> ARROW_BUTTON_BACKGROUND_PRESSED = new CyclerAttribute<>("ARROW_BUTTON_BACKGROUND_PRESSED");
    /**
     * Represents the border color of the arrow button.
     *
     * @see #DISPLAY_PANEL_BORDER_COLOR
     * @see #POPUP_BORDER_COLOR
     */
    public static CyclerAttribute<Color> ARROW_BUTTON_BORDER_COLOR = new CyclerAttribute<>("ARROW_BUTTON_BORDER_COLOR");
    /**
     * Represents the background color of the popup (drop-down list).
     */
    public static CyclerAttribute<Color> POPUP_BACKGROUND = new CyclerAttribute<>("POPUP_BACKGROUND");
    /**
     * Represents the border color of the popup (drop-down list).
     *
     * @see #DISPLAY_PANEL_BORDER_COLOR
     * @see #ARROW_BUTTON_BORDER_COLOR
     */
    public static CyclerAttribute<Color> POPUP_BORDER_COLOR = new CyclerAttribute<>("POPUP_BORDER_COLOR");
    /**
     * Represents the background color of each item in the popup (drop-down list).
     *
     * @see #POPUP_SELECTED_ITEM_BACKGROUND
     */
    public static CyclerAttribute<Color> POPUP_ITEM_BACKGROUND = new CyclerAttribute<>("POPUP_ITEM_BACKGROUND");
    /**
     * Represents the foreground color of each item in the popup (drop-down list).
     *
     * @see #POPUP_SELECTED_ITEM_FOREGROUND
     */
    public static CyclerAttribute<Color> POPUP_ITEM_FOREGROUND = new CyclerAttribute<>("POPUP_ITEM_FOREGROUND");
    /**
     * Represents the background and hover background colors of the selected item in the popup (drop-down list).
     *
     * @see #POPUP_ITEM_BACKGROUND
     */
    public static CyclerAttribute<Color> POPUP_SELECTED_ITEM_BACKGROUND = new CyclerAttribute<>("POPUP_SELECTED_ITEM_BACKGROUND");
    /**
     * Represents the foreground and hover foreground colors of the selected item in the popup (drop-down list).
     *
     * @see #POPUP_ITEM_FOREGROUND
     */
    public static CyclerAttribute<Color> POPUP_SELECTED_ITEM_FOREGROUND = new CyclerAttribute<>("POPUP_SELECTED_ITEM_FOREGROUND");
    /**
     * Represents the font of the items in the popup (drop-down list).
     */
    public static CyclerAttribute<Font> POPUP_FONT = new CyclerAttribute<>("POPUP_FONT");
    /**
     * Represents whether popup contains tooltip.
     */
    public static CyclerAttribute<Boolean> POPUP_HAS_TOOLTIP = new CyclerAttribute<>("POPUP_HAS_TOOLTIP");
    /**
     * Represents the track color of the vertical scroll bar in the popup (drop-down list).
     */
    public static CyclerAttribute<Color> POPUP_SCROLL_TRACK_COLOR = new CyclerAttribute<>("POPUP_SCROLL_TRACK_COLOR");
    /**
     * Represents the thumb color of the vertical scroll bar in the popup (drop-down list).
     */
    public static CyclerAttribute<Color> POPUP_SCROLL_THUMB_COLOR = new CyclerAttribute<>("POPUP_SCROLL_THUMB_COLOR");
    /**
     * Represents the thumb color of the vertical scroll bar in the popup (drop-down list) while dragging.
     */
    public static CyclerAttribute<Color> POPUP_SCROLL_THUMB_DRAG_COLOR = new CyclerAttribute<>("POPUP_SCROLL_THUMB_DRAG_COLOR");
    /**
     * Represents the radius of thumb for the vertical scroll bar in the popup (drop-down list).
     */
    public static CyclerAttribute<Integer> POPUP_SCROLL_THUMB_RADIUS = new CyclerAttribute<>("POPUP_SCROLL_THUMB_RADIUS");
    /**
     * Represents the background color of the {@code FlexCycler}.
     */
    public static CyclerAttribute<Color> BACKGROUND = new CyclerAttribute<>("BACKGROUND");
    /**
     * Represents the border color of the {@code FlexCycler}.
     */
    public static CyclerAttribute<Color> BORDER_COLOR = new CyclerAttribute<>("BORDER_COLOR");
    /**
     * Represents the corner radii of the {@code FlexCycler}.
     */
    public static CyclerAttribute<Radii> RADII = new CyclerAttribute<>("RADII");
    /**
     * Represents the inner padding of the {@code FlexIncreaser}.
     */
    public static CyclerAttribute<Padding> PADDING = new CyclerAttribute<>("PADDING");
    /**
     * Represents the gap between items (left-previous button, combo box and right-next button).
     */
    public static CyclerAttribute<Integer> GAP_BETWEEN_ITEMS = new CyclerAttribute<>("GAP_BETWEEN_ITEMS");
    /**
     * Represents the background color of the navigation buttons.
     */
    public static CyclerAttribute<Color> BUTTONS_BACKGROUND = new CyclerAttribute<>("BUTTONS_BACKGROUND");
    /**
     * Represents the background color of the navigation buttons on hover.
     */
    public static CyclerAttribute<Color> BUTTONS_BACKGROUND_HOVER = new CyclerAttribute<>("BUTTONS_BACKGROUND_HOVER");
    /**
     * Represents the background color of the navigation buttons when pressed.
     */
    public static CyclerAttribute<Color> BUTTONS_BACKGROUND_PRESSED = new CyclerAttribute<>("BUTTONS_BACKGROUND_PRESSED");
    /**
     * Represents the foreground color of the navigation buttons.
     */
    public static CyclerAttribute<Color> BUTTONS_FOREGROUND = new CyclerAttribute<>("BUTTONS_FOREGROUND");
    /**
     * Represents the foreground color of the navigation buttons on hover.
     */
    public static CyclerAttribute<Color> BUTTONS_FOREGROUND_HOVER = new CyclerAttribute<>("BUTTONS_FOREGROUND_HOVER");
    /**
     * Represents the foreground color of the navigation buttons when pressed.
     */
    public static CyclerAttribute<Color> BUTTONS_FOREGROUND_PRESSED = new CyclerAttribute<>("BUTTONS_FOREGROUND_PRESSED");
    /**
     * Represents the font of the navigation buttons.
     */
    public static CyclerAttribute<Font> BUTTONS_FONT = new CyclerAttribute<>("BUTTONS_FONT");

    private final String name;

    public CyclerAttribute(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
