package io.github.toygurkutlu.flexswing.components.combo_box.records;

import io.github.toygurkutlu.flexswing.components.FlexComboBox;

import java.util.Objects;

/**
 * Style attributes for the {@code FlexComboBox}.
 * <ul>
 *     <li>Users can create custom styles or use the provided default attributes.</li>
 *     <li>When using default attributes, any specific property can be modified using the fluent {@code with...()} methods.</li>
 * </ul>
 *
 * @param displayPanelAttributes the style attributes for the display panel
 * @param arrowButtonAttributes  the style attributes for the arrow button
 * @param popupAttributes        the style attributes for the popup (drop down list)
 * @see #defaultAttributes()
 * @see #withDisplayPanelAttributes(DisplayPanelAttributes)
 * @see #withArrowButtonAttributes(ArrowButtonAttributes)
 * @see #withPopupAttributes(PopupAttributes)
 * @see FlexComboBox
 * @see DisplayPanelAttributes
 * @see ArrowButtonAttributes
 * @see PopupAttributes
 */
public record ComboAttributes(DisplayPanelAttributes displayPanelAttributes,
                              ArrowButtonAttributes arrowButtonAttributes,
                              PopupAttributes popupAttributes) {

    /**
     * Creates the default style attributes for the {@code FlexComboBox}.
     *
     * @return the {@code ComboAttributes} of the {@code FlexComboBox}
     */
    public static ComboAttributes defaultAttributes() {
        return new ComboAttributes(DisplayPanelAttributes.defaultDisplayPanelAttributes(),
                                   ArrowButtonAttributes.defaultArrowButtonAttributes(),
                                   PopupAttributes.defaultPopupAttributes());
    }

    /**
     * Creates a copy of this {@code ComboAttributes} with the specified display panel style attributes.
     *
     * @param attr the new display panel style attributes for the {@code FlexComboBox}, cannot be {@code null}
     * @return a new {@code ComboAttributes} instance with the updated display panel style attributes
     * @throws NullPointerException if the provided {@code attr} is {@code null}
     */
    public ComboAttributes withDisplayPanelAttributes(DisplayPanelAttributes attr) {
        return new ComboAttributes(Objects.requireNonNull(attr, "DisplayPanelAttributes cannot be null."),
                                   this.arrowButtonAttributes,
                                   this.popupAttributes);
    }

    /**
     * Creates a copy of this {@code ComboAttributes} with the specified arrow button style attributes.
     *
     * @param attr the new arrow button style attributes for the {@code FlexComboBox}, cannot be {@code null}
     * @return a new {@code ComboAttributes} instance with the updated arrow button style attributes
     * @throws NullPointerException if the provided {@code attr} is {@code null}
     */
    public ComboAttributes withArrowButtonAttributes(ArrowButtonAttributes attr) {
        return new ComboAttributes(this.displayPanelAttributes,
                                   Objects.requireNonNull(attr, "ArrowButtonAttributes cannot be null."),
                                   this.popupAttributes);
    }

    /**
     * Creates a copy of this {@code ComboAttributes} with the specified popup style attributes.
     *
     * @param attr the new popup style attributes for the {@code FlexComboBox}, cannot be {@code null}
     * @return a new {@code ComboAttributes} instance with the updated popup style attributes
     * @throws NullPointerException if the provided {@code attr} is {@code null}
     */
    public ComboAttributes withPopupAttributes(PopupAttributes attr) {
        return new ComboAttributes(this.displayPanelAttributes,
                                   this.arrowButtonAttributes,
                                   Objects.requireNonNull(attr, "PopupAttributes cannot be null."));
    }
}