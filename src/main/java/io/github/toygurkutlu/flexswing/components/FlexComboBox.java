package io.github.toygurkutlu.flexswing.components;

import io.github.toygurkutlu.flexswing.components.combo_box.domain.ComboBoxAttribute;
import io.github.toygurkutlu.flexswing.components.combo_box.domain.ComboBoxConfig;
import io.github.toygurkutlu.flexswing.components.combo_box.domain.PopupConfig;
import io.github.toygurkutlu.flexswing.components.combo_box.domain.TextAlignment;
import io.github.toygurkutlu.flexswing.components.combo_box.ui.FlexComboBoxCellRenderer;
import io.github.toygurkutlu.flexswing.components.combo_box.ui.FlexComboBoxEditor;
import io.github.toygurkutlu.flexswing.components.combo_box.ui.FlexComboBoxUI;
import io.github.toygurkutlu.flexswing.components.combo_box.ui.ListTooltip;
import io.github.toygurkutlu.flexswing.objects.Radii;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;
import java.util.Vector;

/**
 * Customizable component that combines a button or editable field and a drop-down list. The user can select a value
 * from the drop-down list, which appears at the user's request. If you make the combo box editable, then the combo box
 * includes an editable field into which the user can type a value.<br><br>
 *
 * <p>To customize the {@code FlexComboBox}:</p>
 * <ul>
 *     <li>Create a custom {@code ComboBoxAttributes} object (or use
 *     {@link ComboBoxConfig#defaultConfig()}) and pass it when instantiating
 *     the {@code FlexComboBox}.</li>
 *     <li>Use the {@link #setAttribute(ComboBoxAttribute, Object)} method to customize a specific
 *     attribute even after the {@code FlexComboBox} has been created.</li>
 * </ul>
 *
 * @see ComboBoxConfig
 * @see #setAttribute(ComboBoxAttribute, Object)
 */
public class FlexComboBox<T> extends JComboBox<T> {
    private FlexComboBoxCellRenderer<T> cellRenderer;
    private ComboBoxConfig config;
    private FlexComboBoxEditor editor;
    private FlexComboBoxUI comboBoxUI;
    private int originalIndexBeforePopup = -1;
    private boolean isPopupOpeningEventRegistered = false;

    /**
     * Creates a {@code FlexComboBox} that takes its items from an existing ComboBoxModel. Since the ComboBoxModel is provided,
     * a combo box created using this constructor does not create a default combo box model and may impact how the
     * insert, remove and add methods behave.
     *
     * @param aModel the ComboBoxModel that provides the displayed list of items
     */
    public FlexComboBox(ComboBoxModel<T> aModel) {
        super(aModel);
        this.config = ComboBoxConfig.defaultConfig();
        init();
    }

    /**
     * Creates a {@code FlexComboBox} that contains the elements in the specified array. The first item in the array
     * (and therefore the data model) becomes selected as default.
     *
     * @param items an array of objects to insert into the combo box
     */
    public FlexComboBox(T[] items) {
        super(items);
        this.config = ComboBoxConfig.defaultConfig();
        init();
    }

    /**
     * Creates a {@code FlexComboBox} that contains the elements in the specified Vector. The first item in the vector
     * (and therefore the data model) becomes selected as default.
     *
     * @param items an array of vectors to insert into the combo box
     */
    public FlexComboBox(Vector<T> items) {
        super(items);
        this.config = ComboBoxConfig.defaultConfig();
        init();
    }

    /**
     * Creates a {@code FlexComboBox} with a default data model. The default data model is an empty list of objects.
     * Use addItem to add items. The first item in the data model becomes selected as default.
     */
    public FlexComboBox() {
        super();
        this.config = ComboBoxConfig.defaultConfig();
        init();
    }

    /**
     * Creates a {@code FlexComboBox} with the provided config that takes its items from an existing ComboBoxModel.
     * Since the ComboBoxModel is provided, a combo box created using this constructor does not create a default
     * combo box model and may impact how the insert, remove and add methods behave.
     *
     * @param config the style config of the {@code FlexComboBox}
     * @param aModel the ComboBoxModel that provides the displayed list of items
     */
    public FlexComboBox(ComboBoxConfig config, ComboBoxModel<T> aModel) {
        super(aModel);
        this.config = config;
        init();
    }

    /**
     * Creates a {@code FlexComboBox}  with the provided config that contains the elements in the specified array.
     * The first item in the array (and therefore the data model) becomes selected as default.
     *
     * @param config the style config of the {@code FlexComboBox}
     * @param items  an array of objects to insert into the combo box
     */
    public FlexComboBox(ComboBoxConfig config, T[] items) {
        super(items);
        this.config = config;
        init();
    }

    /**
     * Creates a {@code FlexComboBox} with the provided config that contains the elements in the specified Vector.
     * The first item in the vector (and therefore the data model) becomes selected as default.
     *
     * @param config the style config of the {@code FlexComboBox}
     * @param items  an array of vectors to insert into the combo box
     */
    public FlexComboBox(ComboBoxConfig config, Vector<T> items) {
        super(items);
        this.config = config;
        init();
    }

    /**
     * Creates a {@code FlexComboBox} with the provided config and a default data model.
     * The default data model is an empty list of objects. Use addItem to add items.
     * The first item in the data model becomes selected as default.
     *
     * @param config the style config of the {@code FlexComboBox}
     */
    public FlexComboBox(ComboBoxConfig config) {
        super();
        this.config = config;
        init();
    }


    public void setConfig(ComboBoxConfig config) {
        this.config = config;
        revalidate();
        repaint();
    }

    private void init() {
        getData();

        setEditable(true);
        setOpaque(false);
        setBorder(null);
        setFocusable(false);
        setUI(comboBoxUI);
        setRenderer(cellRenderer);
        setEditor(editor);

        if (!isPopupOpeningEventRegistered) {
            super.addPopupMenuListener(new PopupMenuListener() {
                @Override
                public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                    originalIndexBeforePopup = getSelectedIndex();
                    if (getEditor() instanceof FlexComboBoxEditor) {
                        ((FlexComboBoxEditor) getEditor()).updateBorder(true);
                    }
                }

                @Override
                public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                    if (getEditor() instanceof FlexComboBoxEditor) {
                        ((FlexComboBoxEditor) getEditor()).updateBorder(false);
                    }
                }

                @Override
                public void popupMenuCanceled(PopupMenuEvent e) {
                    if (getEditor() instanceof FlexComboBoxEditor) {
                        ((FlexComboBoxEditor) getEditor()).updateBorder(false);
                    }
                }
            });
            isPopupOpeningEventRegistered = true;
        }

        java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (isPopupVisible() && e.getID() == java.awt.event.KeyEvent.KEY_PRESSED) {
                int code = e.getKeyCode();

                Object child = getAccessibleContext().getAccessibleChild(0);
                javax.swing.plaf.basic.ComboPopup popup = (javax.swing.plaf.basic.ComboPopup) child;
                JList<?> list = popup.getList();

                if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_UP ||
                        code == KeyEvent.VK_PAGE_DOWN || code == KeyEvent.VK_PAGE_UP ||
                        code == KeyEvent.VK_HOME || code == KeyEvent.VK_END ||
                        code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {

                    int currentIndex = getSelectedIndex();
                    int maxIndex = getItemCount() - 1;
                    int newIndex = currentIndex;

                    int pageSize = list.getLastVisibleIndex() - list.getFirstVisibleIndex() - 1;
                    pageSize = Math.max(1, pageSize);

                    if (code == java.awt.event.KeyEvent.VK_DOWN && currentIndex < maxIndex) {
                        newIndex = currentIndex + 1;
                    } else if (code == java.awt.event.KeyEvent.VK_DOWN && currentIndex == maxIndex) {
                        newIndex = 0;
                    } else if (code == java.awt.event.KeyEvent.VK_UP && currentIndex > 0) {
                        newIndex = currentIndex - 1;
                    } else if (code == java.awt.event.KeyEvent.VK_UP && currentIndex == 0) {
                        newIndex = maxIndex;
                    } else if (code == KeyEvent.VK_PAGE_DOWN) {
                        newIndex = Math.min(currentIndex + pageSize, maxIndex);
                    } else if (code == KeyEvent.VK_PAGE_UP) {
                        newIndex = Math.max(currentIndex - pageSize, 0);
                    } else if (code == KeyEvent.VK_HOME) {
                        newIndex = 0;
                    } else if (code == KeyEvent.VK_END) {
                        newIndex = maxIndex;
                    } else if (code == KeyEvent.VK_ENTER) {
                        popup.hide();
                        e.consume();
                        return true;
                    } else if (code == KeyEvent.VK_ESCAPE) {
                        if (originalIndexBeforePopup >= 0 && originalIndexBeforePopup <= maxIndex) {
                            list.setSelectedIndex(originalIndexBeforePopup);
                            setSelectedIndex(originalIndexBeforePopup);
                            list.ensureIndexIsVisible(originalIndexBeforePopup);
                        }
                        popup.hide();
                        e.consume();
                        return true;
                    }

                    if (newIndex != currentIndex) {
                        list.setSelectedIndex(newIndex);
                        setSelectedIndex(newIndex);
                        list.ensureIndexIsVisible(newIndex);
                    }

                    e.consume();
                    return true;
                }
            }
            return false;
        });

        setKeySelectionManager((aKey, aModel) -> -1);
    }

    private void getData() {
        cellRenderer = new FlexComboBoxCellRenderer<>(config);
        editor = new FlexComboBoxEditor(config);
        comboBoxUI = new FlexComboBoxUI(config);
    }

    /**
     * Updates the tooltip according to the provided {@code ListTooltipAttribute} and the value object.
     *
     * @param attr  the attribute for updating, cannot be {@code null}
     * @param value the new value for updating, cannot be {@code null}
     * @throws NullPointerException     if any of the following parameters are null:
     *                                  <ul>
     *                                    <li>When the provided <code>attr</code> is <code>null</code></li>
     *                                    <li>When the provided <code>value</code> is <code>null</code></li>
     *                                  </ul>
     * @throws IllegalArgumentException if the tooltip is not enabled
     * @see ComboBoxConfig
     * @see PopupConfig
     * @see PopupConfig#isHasTooltip()
     */
    public void updateTooltip(ListTooltip.ListTooltipAttribute attr, Object value) {
        comboBoxUI.updateTooltip(Objects.requireNonNull(attr, "Attribute cannot be null."),
                                 Objects.requireNonNull(value, "Value cannot be null."));
    }

    /**
     * Sets and updates the {@code FlexComboBox} component according to the provided attributed and value.
     *
     * @param attr  the {@link ComboBoxAttribute} object for updating, cannot be  {@code null}
     * @param value the new value of the attribute, cannot be  {@code null}
     * @throws NullPointerException if either the provided {@code attr} or the provided {@code value} is {@code null}
     * @see ComboBoxAttribute
     */
    public <E> FlexComboBox<T> setAttribute(ComboBoxAttribute<E> attr, E value) {
        Objects.requireNonNull(attr, "Provided attribute cannot be null.");
        Objects.requireNonNull(value, "Provided attribute value cannot be null.");
        if (attr == ComboBoxAttribute.DISPLAY_PANEL_BACKGROUND) {
            Color color = (Color) value;
            this.config.getDisplayPanelConfig().setBackground(color);
            if (getEditor() instanceof FlexComboBoxEditor && editor != null) {
                editor.setBackgroundColor(color);
            }
        } else if (attr == ComboBoxAttribute.DISPLAY_PANEL_FOREGROUND) {
            Color color = (Color) value;
            this.config.getDisplayPanelConfig().setForeground(color);
            if (getEditor() instanceof FlexComboBoxEditor && editor != null) {
                editor.setTextColor(color);
            }
        } else if (attr == ComboBoxAttribute.DISPLAY_PANEL_FONT) {
            Font font = (Font) value;
            this.config.getDisplayPanelConfig().setFont(font);
            if (getEditor() instanceof FlexComboBoxEditor && editor != null) {
                editor.setTextFont(font);
            }
        } else if (attr == ComboBoxAttribute.DISPLAY_PANEL_TEXT_ALIGNMENT) {
            TextAlignment alignment = (TextAlignment) value;
            this.config.getDisplayPanelConfig().setTextAlignment(alignment);
            if (getEditor() instanceof FlexComboBoxEditor && editor != null) {
                editor.setTextAlignment(alignment);
            }
        } else if (attr == ComboBoxAttribute.DISPLAY_PANEL_BORDER_COLOR) {
            Color color = (Color) value;
            this.config.getDisplayPanelConfig().setBorderColor(color);
            if (getEditor() instanceof FlexComboBoxEditor && editor != null) {
                editor.setBorderColor(color);
            }
        } else if (attr == ComboBoxAttribute.DISPLAY_PANEL_RADII) {
            Radii radii = (Radii) value;
            this.config.getDisplayPanelConfig().setRadii(radii);
            if (getEditor() instanceof FlexComboBoxEditor && editor != null) {
                editor.setDisplayPanelRadii(radii.topLeft(), radii.bottomLeft());
            }
            if (getUI() instanceof FlexComboBoxUI) {
                comboBoxUI.setArrowButtonRadii(radii.topRight(), radii.bottomRight());
            }
        } else if (attr == ComboBoxAttribute.ARROW_BUTTON_ICON_COLOR) {
            Color color = (Color) value;
            this.config.getArrowButtonConfig().setArrowIconColor(color);
            if (getUI() instanceof FlexComboBoxUI) {
                comboBoxUI.setArrowButtonIconColor(color);
            }
        } else if (attr == ComboBoxAttribute.ARROW_BUTTON_BACKGROUND) {
            Color color = (Color) value;
            this.config.getArrowButtonConfig().setBackground(color);
            if (getUI() instanceof FlexComboBoxUI) {
                comboBoxUI.setArrowButtonBackground(color);
            }
        } else if (attr == ComboBoxAttribute.ARROW_BUTTON_BACKGROUND_PRESSED) {
            Color color = (Color) value;
            this.config.getArrowButtonConfig().setPressedBackground(color);
            if (getUI() instanceof FlexComboBoxUI) {
                comboBoxUI.setArrowButtonPressedColor(color);
            }
        } else if (attr == ComboBoxAttribute.ARROW_BUTTON_BACKGROUND_HOVER) {
            Color color = (Color) value;
            this.config.getArrowButtonConfig().setHoverBackground(color);
            if (getUI() instanceof FlexComboBoxUI) {
                comboBoxUI.setArrowButtonHoverColor(color);
            }
        } else if (attr == ComboBoxAttribute.ARROW_BUTTON_BORDER_COLOR) {
            Color color = (Color) value;
            this.config.getArrowButtonConfig().setBorderColor(color);
            if (getUI() instanceof FlexComboBoxUI) {
                comboBoxUI.setArrowButtonBorderColor(color);
            }
        } else if (attr == ComboBoxAttribute.POPUP_BACKGROUND) {
            Color color = (Color) value;
            this.config.getPopupConfig().setBackground(color);
            if (getUI() instanceof FlexComboBoxUI) {
                comboBoxUI.setPopupBackground(color);
            }
        } else if (attr == ComboBoxAttribute.POPUP_BORDER_COLOR) {
            Color color = (Color) value;
            this.config.getPopupConfig().setBorderColor(color);
            if (getUI() instanceof FlexComboBoxUI) {
                comboBoxUI.setPopupBorderColor(color);
            }
        } else if (attr == ComboBoxAttribute.POPUP_ITEM_BACKGROUND) {
            Color color = (Color) value;
            this.config.getPopupConfig().setItemBackground(color);
            if (getRenderer() instanceof FlexComboBoxCellRenderer<? super T>) {
                cellRenderer.setItemBackground(color);
            }
        } else if (attr == ComboBoxAttribute.POPUP_ITEM_FOREGROUND) {
            Color color = (Color) value;
            this.config.getPopupConfig().setItemForeground(color);
            if (getRenderer() instanceof FlexComboBoxCellRenderer<? super T>) {
                cellRenderer.setItemForeground(color);
            }
        } else if (attr == ComboBoxAttribute.POPUP_SELECTED_ITEM_BACKGROUND) {
            Color color = (Color) value;
            this.config.getPopupConfig().setSelectedBackground(color);
            if (getRenderer() instanceof FlexComboBoxCellRenderer<? super T>) {
                cellRenderer.setSelectedBackground(color);
            }
        } else if (attr == ComboBoxAttribute.POPUP_SELECTED_ITEM_FOREGROUND) {
            Color color = (Color) value;
            this.config.getPopupConfig().setSelectedForeground(color);
            if (getRenderer() instanceof FlexComboBoxCellRenderer<? super T>) {
                cellRenderer.setSelectedForeground(color);
            }
        } else if (attr == ComboBoxAttribute.POPUP_FONT) {
            Font font = (Font) value;
            this.config.getPopupConfig().setFont(font);
            if (getRenderer() instanceof FlexComboBoxCellRenderer<? super T>) {
                cellRenderer.setItemFont(font);
            }
        } else if (attr == ComboBoxAttribute.POPUP_HAS_TOOLTIP) {
            Boolean hasTooltip = (Boolean) value;
            this.config.getPopupConfig().setHasTooltip(hasTooltip);
            if (getUI() instanceof FlexComboBoxUI) {
                comboBoxUI.setPopupHasTooltip(hasTooltip);
            }
        } else if (attr == ComboBoxAttribute.POPUP_SCROLL_TRACK_COLOR) {
            Color color = (Color) value;
            this.config.getPopupConfig().getScrollAttributes().setTrackColor(color);
            if (getUI() instanceof FlexComboBoxUI) {
                comboBoxUI.setScrollTrackColor(color);
            }
        } else if (attr == ComboBoxAttribute.POPUP_SCROLL_THUMB_COLOR) {
            Color color = (Color) value;
            this.config.getPopupConfig().getScrollAttributes().setThumbColor(color);
            if (getUI() instanceof FlexComboBoxUI) {
                comboBoxUI.setScrollThumbColor(color);
            }
        } else if (attr == ComboBoxAttribute.POPUP_SCROLL_THUMB_DRAG_COLOR) {
            Color color = (Color) value;
            this.config.getPopupConfig().getScrollAttributes().setThumbDragColor(color);
            if (getUI() instanceof FlexComboBoxUI) {
                comboBoxUI.setScrollThumbDragColor(color);
            }
        } else if (attr == ComboBoxAttribute.POPUP_SCROLL_THUMB_RADIUS) {
            Integer radius = (Integer) value;
            this.config.getPopupConfig().getScrollAttributes().setThumbRadius(radius);
            if (getUI() instanceof FlexComboBoxUI) {
                comboBoxUI.setScrollThumbCornerRadius(radius);
            }
        }
        return this;
    }
}