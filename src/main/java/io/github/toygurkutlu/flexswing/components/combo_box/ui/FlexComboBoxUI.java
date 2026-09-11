package io.github.toygurkutlu.flexswing.components.combo_box.ui;

import io.github.toygurkutlu.flexswing.components.combo_box.records.ComboAttributes;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.util.Objects;

public class FlexComboBoxUI extends BasicComboBoxUI {

    private final ComboAttributes attr;
    private final FlexArrowButton flexArrowButton;
    private FlexComboPopup flexComboPopup;

    public FlexComboBoxUI(ComboAttributes attr) {
        this.attr = attr;
        flexArrowButton = new FlexArrowButton(attr.arrowButtonAttributes());

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

    public void updateTooltip(ListTooltip.ListTooltipAttribute attr, Object value){
        if (!this.attr.popupAttributes().hasTooltip())
            throw new IllegalArgumentException("Tooltip is not active.");

        flexComboPopup.updateTooltip(Objects.requireNonNull(attr, "Attribute cannot be null."),
                                     Objects.requireNonNull(value, "Value cannot be null."));
    }
}