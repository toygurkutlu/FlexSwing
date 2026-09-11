package io.github.toygurkutlu.flexswing.components;

import io.github.toygurkutlu.flexswing.components.combo_box.records.ComboAttributes;
import io.github.toygurkutlu.flexswing.components.combo_box.records.PopupAttributes;
import io.github.toygurkutlu.flexswing.components.combo_box.ui.FlexComboBoxCellRenderer;
import io.github.toygurkutlu.flexswing.components.combo_box.ui.FlexComboBoxEditor;
import io.github.toygurkutlu.flexswing.components.combo_box.ui.FlexComboBoxUI;
import io.github.toygurkutlu.flexswing.components.combo_box.ui.ListTooltip;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.event.KeyEvent;
import java.util.Objects;
import java.util.Vector;

/**
 * Customizable component that combines a button or editable field and a drop-down list. The user can select a value
 * from the drop-down list, which appears at the user's request. If you make the combo box editable, then the combo box
 * includes an editable field into which the user can type a value.
 *
 * <p>For customizing the {@code FlexComboBox} the developer should create custom {@code ComboAttributes}</p>
 *
 * @see ComboAttributes
 */
public class FlexComboBox<T> extends JComboBox<T> {
    private FlexComboBoxCellRenderer<T> cellRenderer;
    private final ComboAttributes attr;
    private FlexComboBoxEditor editor;
    private FlexComboBoxUI comboBoxUI;
    private int originalIndexBeforePopup = -1;
    private boolean isPopupOpeningEventRegistered = false;

    /**
     * Creates a JComboBox that takes its items from an existing ComboBoxModel. Since the ComboBoxModel is provided,
     * a combo box created using this constructor does not create a default combo box model and may impact how the
     * insert, remove and add methods behave.
     *
     * @param attr   the style config of the {@code FlexComboBox}
     * @param aModel the ComboBoxModel that provides the displayed list of items
     */
    public FlexComboBox(ComboAttributes attr, ComboBoxModel<T> aModel) {
        super(aModel);
        this.attr = attr;
        init();
    }

    /**
     * Creates a FlexComboBox that contains the elements in the specified array. The first item in the array
     * (and therefore the data model) becomes selected as default.
     *
     * @param attr  the style config of the {@code FlexComboBox}
     * @param items an array of objects to insert into the combo box
     */
    public FlexComboBox(ComboAttributes attr, T[] items) {
        super(items);
        this.attr = attr;
        init();
    }

    /**
     * Creates a JComboBox that contains the elements in the specified Vector. The first item in the vector
     * (and therefore the data model) becomes selected as default.
     *
     * @param attr  the style config of the {@code FlexComboBox}
     * @param items an array of vectors to insert into the combo box
     */
    public FlexComboBox(ComboAttributes attr, Vector<T> items) {
        super(items);
        this.attr = attr;
        init();
    }

    /**
     * Creates a JComboBox with a default data model. The default data model is an empty list of objects.
     * Use addItem to add items. The first item in the data model becomes selected as default.
     *
     * @param attr the style config of the {@code FlexComboBox}
     */
    public FlexComboBox(ComboAttributes attr) {
        super();
        this.attr = attr;
        init();
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
        cellRenderer = new FlexComboBoxCellRenderer<>(attr);
        editor = new FlexComboBoxEditor(attr);
        comboBoxUI = new FlexComboBoxUI(attr);
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
     * @see ComboAttributes
     * @see PopupAttributes
     * @see PopupAttributes#hasTooltip()
     */
    public void updateTooltip(ListTooltip.ListTooltipAttribute attr, Object value) {
        comboBoxUI.updateTooltip(Objects.requireNonNull(attr, "Attribute cannot be null."),
                                 Objects.requireNonNull(value, "Value cannot be null."));
    }
}