package io.github.toygurkutlu.flexswing.components;

import io.github.toygurkutlu.flexswing.components.combo_box.domain.ComboBoxAttribute;
import io.github.toygurkutlu.flexswing.components.combo_box.domain.ComboBoxConfig;
import io.github.toygurkutlu.flexswing.components.combo_box.domain.PopupConfig;
import io.github.toygurkutlu.flexswing.components.combo_box.domain.TextAlignment;
import io.github.toygurkutlu.flexswing.components.increaser.IncreaserAttribute;
import io.github.toygurkutlu.flexswing.objects.Padding;
import io.github.toygurkutlu.flexswing.objects.Radii;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Arrays;
import java.util.Objects;

/**
 * A customizable Swing component that combines decrease and increase buttons with a combo box.
 * Users can change the selected value either by clicking the buttons or by selecting an item
 * directly from the combo box.
 *
 * <ul>
 *   <li>Use {@link #setAttribute(IncreaserAttribute, Object)} to customize visual or behavioral properties.</li>
 *   <li>Use {@link #setOnValueChangedListener(OnValueChangedListener)} to listen for and handle value changes.</li>
 * </ul>
 *
 * @see IncreaserAttribute
 * @see OnValueChangedListener
 */
public class FlexIncreaser extends JComponent {

    private FlexPanel container;
    private FlexComboBox<Integer> comboBox;
    private FlexButton btnIncrease;
    private FlexButton btnDecrease;
    private final Integer[] arr;
    private OnValueChangedListener listener;
    private boolean isUpdating = false;

    /**
     * Interface for handling value changes.
     */
    public interface OnValueChangedListener {
        void onValueChanged(int index);
    }

    /**
     * Registers a listener to be notified when the selected value changes.
     *
     * @param listener the {@link OnValueChangedListener} to set; may be {@code null} to remove the current listener
     * @apiNote This listener is triggered when the user clicks either the increase or decrease button,
     * or selects an item from the combo box.
     */
    public void setOnValueChangedListener(OnValueChangedListener listener) {
        this.listener = listener;
    }

    /**
     * @param arr the array
     */
    public FlexIncreaser(int[] arr) {
        this.arr = Arrays.stream(arr)
                .boxed()
                .toArray(Integer[]::new);

        init();
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(container);
    }

    public FlexIncreaser(Integer[] arr) {
        this.arr = arr;
        init();
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(container);
    }

    private void init() {
        createViews();
        setupUI();
    }

    private void createViews() {
        container = new FlexPanel(new GridBagLayout());
        container.setBackgroundColor(new Color(125, 125, 125));
        container.setBorderColor(new Color(100, 125, 125));
        container.setHasBorder(true);
        ComboBoxConfig attr = ComboBoxConfig.defaultConfig()
                .withPopupAttributes(PopupConfig.defaultPopupAttributes().withHasTooltip(false));
        comboBox = new FlexComboBox<>(attr, new DefaultComboBoxModel<>(arr));

        btnIncrease = createButton("+");
        btnDecrease = createButton("-");
        updateButtonDimensions();

        comboBox.addItemListener(e -> {
            if (isUpdating) return;
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (listener != null) listener.onValueChanged(comboBox.getSelectedIndex());
            }
        });

        btnIncrease.addActionListener(e -> {
            int index = comboBox.getSelectedIndex();
            if (index < comboBox.getItemCount() - 1) {
                isUpdating = true;
                comboBox.setSelectedIndex(index + 1);
                if (listener != null) listener.onValueChanged(index + 1);
                isUpdating = false;
            }
        });

        btnDecrease.addActionListener(e -> {
            int index = comboBox.getSelectedIndex();
            if (index > 0) {
                isUpdating = true;
                comboBox.setSelectedIndex(index - 1);
                if (listener != null) listener.onValueChanged(index - 1);
                isUpdating = false;
            }
        });
    }

    private void setupUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.fill = GridBagConstraints.VERTICAL;
        container.add(btnDecrease, gbc);

        gbc.gridx++;
        container.add(comboBox, gbc);

        gbc.gridx++;
        container.add(btnIncrease, gbc);
    }

    private void updateButtonDimensions() {
        btnDecrease.setPreferredSize(null);
        btnIncrease.setPreferredSize(null);
        btnDecrease.setMinimumSize(null);
        btnIncrease.setMinimumSize(null);

        Dimension decSize = btnDecrease.getPreferredSize();
        Dimension incSize = btnIncrease.getPreferredSize();

        int maxWidth = Math.max(decSize.width, incSize.width);
        int maxHeight = Math.max(decSize.height, incSize.height);
        Dimension equalSize = new Dimension(maxWidth, maxHeight);

        btnDecrease.setMinimumSize(equalSize);
        btnDecrease.setPreferredSize(equalSize);

        btnIncrease.setMinimumSize(equalSize);
        btnIncrease.setPreferredSize(equalSize);

        container.revalidate();
        container.repaint();
    }

    private FlexButton createButton(String text) {
        FlexButton btn = new FlexButton(text);
        btn.setPadding(new Padding(5, 10, 5, 10));
        return btn;
    }

    /**
     * Gets the instants of the container panel.
     *
     * @return the container of the {@code FlexIncreaser}
     * @see FlexPanel
     */
    public FlexPanel getContainer() {
        return container;
    }

    /**
     * Gets the instants of the combo box.
     *
     * @return the combo box of the {@code FlexIncreaser}
     * @see FlexComboBox
     */
    public FlexComboBox<Integer> getComboBox() {
        return comboBox;
    }

    /**
     * Sets and updates {@code FlexIncreaser} component according to the provided attribute and value.
     *
     * @param attr  the {@link IncreaserAttribute} object for updating, cannot be  {@code null}
     * @param value the new value of the attribute, cannot be  {@code null}
     * @throws NullPointerException if either the provided {@code attr} or the provided {@code value} is {@code null}
     * @see IncreaserAttribute
     */
    public <E> FlexIncreaser setAttribute(IncreaserAttribute<E> attr, E value) {
        Objects.requireNonNull(attr, "Provided attribute cannot be null.");
        Objects.requireNonNull(value, "Provided attribute value cannot be null.");
        if (attr == IncreaserAttribute.BACKGROUND) {
            Color color = (Color) value;
            container.setBackgroundColor(color);
        } else if (attr == IncreaserAttribute.BORDER_COLOR) {
            Color color = (Color) value;
            container.setBorderColor(color);
        } else if (attr == IncreaserAttribute.RADII) {
            Radii radii = (Radii) value;
            container.setRadii(radii);
        } else if (attr == IncreaserAttribute.PADDING) {
            Padding padding = (Padding) value;
            container.setPadding(padding);
        } else if (attr == IncreaserAttribute.GAP_BETWEEN_ITEMS) {
            int gap = (int) value;
            GridBagLayout layout = (GridBagLayout) container.getLayout();
            GridBagConstraints gbcDecrease = layout.getConstraints(btnDecrease);
            gbcDecrease.insets = new Insets(0, 0, 0, gap);
            layout.setConstraints(btnDecrease, gbcDecrease);
            GridBagConstraints gbcCombo = layout.getConstraints(comboBox);
            gbcCombo.insets = new Insets(0, 0, 0, gap);
            layout.setConstraints(comboBox, gbcCombo);
            container.revalidate();
            container.repaint();
        } else if (attr == IncreaserAttribute.BUTTONS_BACKGROUND) {
            Color color = (Color) value;
            btnIncrease.setBackgroundColor(color);
            btnDecrease.setBackgroundColor(color);
        } else if (attr == IncreaserAttribute.BUTTONS_BACKGROUND_HOVER) {
            Color color = (Color) value;
            btnIncrease.setHoverBackgroundColor(color);
            btnDecrease.setHoverBackgroundColor(color);
        } else if (attr == IncreaserAttribute.BUTTONS_BACKGROUND_PRESSED) {
            Color color = (Color) value;
            btnIncrease.setPressedBackgroundColor(color);
            btnDecrease.setPressedBackgroundColor(color);
        } else if (attr == IncreaserAttribute.BUTTONS_FOREGROUND) {
            Color color = (Color) value;
            btnIncrease.setTextColor(color);
            btnDecrease.setTextColor(color);
        } else if (attr == IncreaserAttribute.BUTTONS_FOREGROUND_HOVER) {
            Color color = (Color) value;
            btnIncrease.setHoverTextColor(color);
            btnDecrease.setHoverTextColor(color);
        } else if (attr == IncreaserAttribute.BUTTONS_FOREGROUND_PRESSED) {
            Color color = (Color) value;
            btnIncrease.setPressedTextColor(color);
            btnDecrease.setPressedTextColor(color);
        } else if (attr == IncreaserAttribute.BUTTONS_FONT) {
            Font font = (Font) value;
            btnIncrease.setTextFont(font);
            btnDecrease.setTextFont(font);
            updateButtonDimensions();
        } else if (attr == IncreaserAttribute.DISPLAY_PANEL_BACKGROUND) {
            Color color = (Color) value;
            comboBox.setAttribute(ComboBoxAttribute.DISPLAY_PANEL_BACKGROUND, color);
        } else if (attr == IncreaserAttribute.DISPLAY_PANEL_FOREGROUND) {
            Color color = (Color) value;
            comboBox.setAttribute(ComboBoxAttribute.DISPLAY_PANEL_FOREGROUND, color);
        } else if (attr == IncreaserAttribute.DISPLAY_PANEL_FONT) {
            Font font = (Font) value;
            comboBox.setAttribute(ComboBoxAttribute.DISPLAY_PANEL_FONT, font);
        } else if (attr == IncreaserAttribute.DISPLAY_PANEL_TEXT_ALIGNMENT) {
            TextAlignment alignment = (TextAlignment) value;
            comboBox.setAttribute(ComboBoxAttribute.DISPLAY_PANEL_TEXT_ALIGNMENT, alignment);
        } else if (attr == IncreaserAttribute.DISPLAY_PANEL_BORDER_COLOR) {
            Color color = (Color) value;
            comboBox.setAttribute(ComboBoxAttribute.DISPLAY_PANEL_BORDER_COLOR, color);
        } else if (attr == IncreaserAttribute.DISPLAY_PANEL_RADII) {
            Radii radii = (Radii) value;
            comboBox.setAttribute(ComboBoxAttribute.DISPLAY_PANEL_RADII, radii);
        } else if (attr == IncreaserAttribute.ARROW_BUTTON_ICON_COLOR) {
            Color color = (Color) value;
            comboBox.setAttribute(ComboBoxAttribute.ARROW_BUTTON_ICON_COLOR, color);
        } else if (attr == IncreaserAttribute.ARROW_BUTTON_BACKGROUND) {
            Color color = (Color) value;
            comboBox.setAttribute(ComboBoxAttribute.ARROW_BUTTON_BACKGROUND, color);
        } else if (attr == IncreaserAttribute.ARROW_BUTTON_BACKGROUND_HOVER) {
            Color color = (Color) value;
            comboBox.setAttribute(ComboBoxAttribute.ARROW_BUTTON_BACKGROUND_HOVER, color);
        } else if (attr == IncreaserAttribute.ARROW_BUTTON_BACKGROUND_PRESSED) {
            Color color = (Color) value;
            comboBox.setAttribute(ComboBoxAttribute.ARROW_BUTTON_BACKGROUND_PRESSED, color);
        } else if (attr == IncreaserAttribute.ARROW_BUTTON_BORDER_COLOR) {
            Color color = (Color) value;
            comboBox.setAttribute(ComboBoxAttribute.ARROW_BUTTON_BORDER_COLOR, color);
        } else if (attr == IncreaserAttribute.POPUP_BACKGROUND) {
            Color color = (Color) value;
            comboBox.setAttribute(ComboBoxAttribute.POPUP_BACKGROUND, color);
        } else if (attr == IncreaserAttribute.POPUP_BORDER_COLOR) {
            Color color = (Color) value;
            comboBox.setAttribute(ComboBoxAttribute.POPUP_BORDER_COLOR, color);
        } else if (attr == IncreaserAttribute.POPUP_ITEM_BACKGROUND) {
            Color color = (Color) value;
            comboBox.setAttribute(ComboBoxAttribute.POPUP_ITEM_BACKGROUND, color);
        } else if (attr == IncreaserAttribute.POPUP_ITEM_FOREGROUND) {
            Color color = (Color) value;
            comboBox.setAttribute(ComboBoxAttribute.POPUP_ITEM_FOREGROUND, color);
        } else if (attr == IncreaserAttribute.POPUP_SELECTED_ITEM_BACKGROUND) {
            Color color = (Color) value;
            comboBox.setAttribute(ComboBoxAttribute.POPUP_SELECTED_ITEM_BACKGROUND, color);
        } else if (attr == IncreaserAttribute.POPUP_SELECTED_ITEM_FOREGROUND) {
            Color color = (Color) value;
            comboBox.setAttribute(ComboBoxAttribute.POPUP_SELECTED_ITEM_FOREGROUND, color);
        } else if (attr == IncreaserAttribute.POPUP_FONT) {
            Font font = (Font) value;
            comboBox.setAttribute(ComboBoxAttribute.POPUP_FONT, font);
        } else if (attr == IncreaserAttribute.POPUP_HAS_TOOLTIP) {
            Boolean hasTooltip = (Boolean) value;
            comboBox.setAttribute(ComboBoxAttribute.POPUP_HAS_TOOLTIP, hasTooltip);
        } else if (attr == IncreaserAttribute.POPUP_SCROLL_TRACK_COLOR) {
            Color color = (Color) value;
            comboBox.setAttribute(ComboBoxAttribute.POPUP_SCROLL_TRACK_COLOR, color);
        } else if (attr == IncreaserAttribute.POPUP_SCROLL_THUMB_COLOR) {
            Color color = (Color) value;
            comboBox.setAttribute(ComboBoxAttribute.POPUP_SCROLL_THUMB_COLOR, color);
        } else if (attr == IncreaserAttribute.POPUP_SCROLL_THUMB_DRAG_COLOR) {
            Color color = (Color) value;
            comboBox.setAttribute(ComboBoxAttribute.POPUP_SCROLL_THUMB_DRAG_COLOR, color);
        } else if (attr == IncreaserAttribute.POPUP_SCROLL_THUMB_RADIUS) {
            Integer radius = (Integer) value;
            comboBox.setAttribute(ComboBoxAttribute.POPUP_SCROLL_THUMB_RADIUS, radius);
        }

        return this;
    }

    @Override
    public Dimension getPreferredSize() {
        LayoutManager layout = getLayout();
        if (layout != null) {
            return layout.preferredLayoutSize(this);
        }
        return new Dimension(200, 30);
    }
}