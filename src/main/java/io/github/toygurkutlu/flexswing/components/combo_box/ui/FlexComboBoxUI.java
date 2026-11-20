package io.github.toygurkutlu.flexswing.components.combo_box.ui;

import io.github.toygurkutlu.flexswing.components.combo_box.domain.ComboBoxConfig;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.util.Objects;

public class FlexComboBoxUI extends BasicComboBoxUI {

    private final ComboBoxConfig attr;
    private final FlexArrowButton flexArrowButton;
    private FlexComboPopup flexComboPopup;

    public FlexComboBoxUI(ComboBoxConfig attr) {
        this.attr = attr;
        flexArrowButton = new FlexArrowButton(attr);
    }

    @Override
    protected FlexComboPopup createPopup() {
        flexComboPopup = new FlexComboPopup(comboBox, attr, flexArrowButton);
        return flexComboPopup;
    }

    @Override
    protected JButton createArrowButton() {
        return flexArrowButton;
    }

    public void updateTooltip(ListTooltip.ListTooltipAttribute attr, Object value) {
        if (!this.attr.getPopupConfig().isHasTooltip())
            throw new IllegalArgumentException("Tooltip is not active.");

        flexComboPopup.updateTooltip(Objects.requireNonNull(attr, "Attribute cannot be null."),
                                     Objects.requireNonNull(value, "Value cannot be null."));
    }

    public void setArrowButtonBackground(Color bg) {
        flexArrowButton.setArrowButtonBackground(bg);
    }

    public void setArrowButtonBorderColor(Color borderColor) {
        flexArrowButton.setArrowButtonBorderColor(borderColor);
    }

    public void setArrowButtonIconColor(Color iconColor) {
        flexArrowButton.setArrowButtonIconColor(iconColor);
    }

    public void setArrowButtonHoverColor(Color hoverColor) {
        flexArrowButton.setArrowButtonHoverColor(hoverColor);
    }

    public void setArrowButtonPressedColor(Color pressedColor) {
        flexArrowButton.setArrowButtonPressedColor(pressedColor);
    }

    public void setArrowButtonRadii(int topRight, int bottomRight) {
        flexArrowButton.setArrowButtonRadii(topRight, bottomRight);
    }

    public void setPopupHasTooltip(boolean hasTooltip) {
        flexComboPopup.setHasTooltip(hasTooltip);
    }

    public void setPopupBackground(Color bg) {
        flexComboPopup.setPopupBackground(bg);
    }

    public void setPopupBorderColor(Color borderColor) {
        flexComboPopup.setPopupBorderColor(borderColor);
    }

    public void setScrollTrackColor(Color color) {
        flexComboPopup.setScrollTrackColor(color);
    }

    public void setScrollThumbColor(Color color) {
        flexComboPopup.setScrollThumbColor(color);
    }

    public void setScrollThumbDragColor(Color color) {
        flexComboPopup.setScrollThumbDragColor(color);
    }

    public void setScrollThumbCornerRadius(int radius) {
        flexComboPopup.setScrollThumbCornerRadius(radius);
    }
}