package io.github.toygurkutlu.flexswing.components.combo_box.domain;

import io.github.toygurkutlu.flexswing.components.FlexComboBox;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Style attributes for the {@code FlexComboBox}.
 * <ul>
 *     <li>Users can create custom styles or use the provided default attributes.</li>
 *     <li>When using default attributes, any specific property can be modified using the fluent {@code with...()} methods.</li>
 * </ul>
 *
 * @see #defaultConfig()
 * @see #withDisplayPanelAttributes(DisplayPanelConfig)
 * @see #withArrowButtonAttributes(ArrowButtonConfig)
 * @see #withPopupAttributes(PopupConfig)
 * @see FlexComboBox
 * @see DisplayPanelConfig
 * @see ArrowButtonConfig
 * @see PopupConfig
 */
public class ComboBoxConfig {

    private DisplayPanelConfig displayPanelConfig;
    private ArrowButtonConfig arrowButtonConfig;
    private PopupConfig popupConfig;

    /**
     * The default constructor of the {@code ComboBoxConfig}. Creates a {@code ComboBoxConfig} according to the
     * provided attributes.
     *
     * @param displayPanelConfig the config of the display panel; cannot be {@code null}
     * @param arrowButtonConfig  the config of the arrow button; cannot be {@code null}
     * @param popupConfig        the config of the popup; cannot be {@code null}
     * @see DisplayPanelConfig
     * @see ArrowButtonConfig
     * @see PopupConfig
     */
    public ComboBoxConfig(@NotNull DisplayPanelConfig displayPanelConfig, @NotNull ArrowButtonConfig arrowButtonConfig,
                          @NotNull PopupConfig popupConfig) {
        this.displayPanelConfig = Objects.requireNonNull(displayPanelConfig, "DisplayPanelConfig cannot be null.");
        this.arrowButtonConfig = Objects.requireNonNull(arrowButtonConfig, "ArrowButtonConfig cannot be null.");
        this.popupConfig = Objects.requireNonNull(popupConfig, "PopupConfig cannot be null.");
    }

    /**
     * Gets the configuration of the display panel.
     *
     * @return the instance of {@code DisplayPanelConfig}
     */
    public DisplayPanelConfig getDisplayPanelConfig() {
        return displayPanelConfig;
    }

    /**
     * Sets the configuration of the display panel.
     *
     * @param displayPanelConfig the new config for the display panel; cannot be {@code null}
     * @throws NullPointerException if the provided config is {@code null}
     */
    public void setDisplayPanelConfig(DisplayPanelConfig displayPanelConfig) {
        this.displayPanelConfig = Objects.requireNonNull(displayPanelConfig,
                                                         "DisplayPanelConfig cannot be null.");
    }

    /**
     * Gets the configuration of the arrow button.
     *
     * @return the instance of {@code ArrowButtonConfig}
     */
    public ArrowButtonConfig getArrowButtonConfig() {
        return arrowButtonConfig;
    }

    /**
     * Sets the configuration of the arrow button.
     *
     * @param arrowButtonConfig the new config for the arrow button; cannot be {@code null}
     * @throws NullPointerException if the provided config is {@code null}
     */
    public void setArrowButtonConfig(ArrowButtonConfig arrowButtonConfig) {
        this.arrowButtonConfig = Objects.requireNonNull(arrowButtonConfig,
                                                        "ArrowButtonConfig cannot be null.");
    }

    /**
     * Gets the configuration of the popup (drop-down list).
     *
     * @return the instance of {@code PopupConfig}
     */
    public PopupConfig getPopupConfig() {
        return popupConfig;
    }

    /**
     * Sets the configuration of the popup (drop-down list).
     *
     * @param popupConfig the new config for the popup; cannot be {@code null}
     * @throws NullPointerException if the provided config is {@code null}
     */
    public void setPopupConfig(PopupConfig popupConfig) {
        this.popupConfig = Objects.requireNonNull(popupConfig, "PopupConfig cannot be null.");
    }

    /**
     * Creates the default style attributes for the {@code FlexComboBox}.
     *
     * @return the {@code ComboAttributes} of the {@code FlexComboBox}
     */
    public static ComboBoxConfig defaultConfig() {
        return new ComboBoxConfig(DisplayPanelConfig.defaultDisplayPanelAttributes(),
                                  ArrowButtonConfig.defaultArrowButtonAttributes(),
                                  PopupConfig.defaultPopupAttributes());
    }

    /**
     * Creates a copy of this {@code ComboAttributes} with the specified display panel style attributes.
     *
     * @param attr the new display panel style attributes for the {@code FlexComboBox}, cannot be {@code null}
     * @return a new {@code ComboAttributes} instance with the updated display panel style attributes
     * @throws NullPointerException if the provided {@code attr} is {@code null}
     */
    public ComboBoxConfig withDisplayPanelAttributes(DisplayPanelConfig attr) {
        return new ComboBoxConfig(Objects.requireNonNull(attr, "DisplayPanelAttributes cannot be null."),
                                  this.arrowButtonConfig,
                                  this.popupConfig);
    }

    /**
     * Creates a copy of this {@code ComboAttributes} with the specified arrow button style attributes.
     *
     * @param attr the new arrow button style attributes for the {@code FlexComboBox}, cannot be {@code null}
     * @return a new {@code ComboAttributes} instance with the updated arrow button style attributes
     * @throws NullPointerException if the provided {@code attr} is {@code null}
     */
    public ComboBoxConfig withArrowButtonAttributes(ArrowButtonConfig attr) {
        return new ComboBoxConfig(this.displayPanelConfig,
                                  Objects.requireNonNull(attr, "ArrowButtonAttributes cannot be null."),
                                  this.popupConfig);
    }

    /**
     * Creates a copy of this {@code ComboAttributes} with the specified popup style attributes.
     *
     * @param attr the new popup style attributes for the {@code FlexComboBox}, cannot be {@code null}
     * @return a new {@code ComboAttributes} instance with the updated popup style attributes
     * @throws NullPointerException if the provided {@code attr} is {@code null}
     */
    public ComboBoxConfig withPopupAttributes(PopupConfig attr) {
        return new ComboBoxConfig(this.displayPanelConfig,
                                  this.arrowButtonConfig,
                                  Objects.requireNonNull(attr, "PopupAttributes cannot be null."));
    }
}