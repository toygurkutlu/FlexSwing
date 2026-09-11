package io.github.toygurkutlu.flexswing.components.color_picker.ui;

import io.github.toygurkutlu.flexswing.components.ColorPicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Provides a simple popup for selecting color which associated with {@link javax.swing.JComponent}.<br><br>
 *
 * <p>
 *     Contains {@link ColorPicker.OnColorSelectedListener} which user can handle color selection via
 *     the {@link ColorPicker.OnColorSelectedListener#onColorSelected(Color)} method,
 *      which receives the newly selected color as an argument.
 * </p>
 *
 * */
public class ColorPopup extends JDialog {

    private final ColorPicker.OnColorSelectedListener listener;

    public ColorPopup(ColorPanel colorPanel, Window owner, ColorPicker.OnColorSelectedListener listener) {
        super(owner);
        setUndecorated(true);
        setModal(false);

        this.listener = listener;
        colorPanel.setOnColorSelectedListener(listener);
        if(listener != null) listener.onColorSelected(colorPanel.getDisplayColor());

        setContentPane(colorPanel);
        pack();

        initKeyBindings();
        initFocusClear(colorPanel);
        initOutsideClickClose();

    }

    private void initKeyBindings() {
        JRootPane root = getRootPane();

        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESC_CLOSE");

        root.getActionMap().put("ESC_CLOSE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER_CONFIRM");

        root.getActionMap().put("ENTER_CONFIRM", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ColorPanel panel = (ColorPanel) getContentPane();
                if (listener != null) {
                    listener.onColorSelected(panel.getDisplayColor());
                }
                setVisible(false);
            }
        });
    }

    private void initFocusClear(ColorPanel colorPanel) {
        colorPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clearFocus();
            }
        });
    }

    private void initOutsideClickClose() {
        Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
            if (event instanceof MouseEvent me && me.getID() == MouseEvent.MOUSE_PRESSED) {
                if (!SwingUtilities.isDescendingFrom(me.getComponent(), this)) {
                    setVisible(false);
                }
            }
        }, AWTEvent.MOUSE_EVENT_MASK);
    }

    private void clearFocus(){
        KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
    }

    /**
     * Displays the {@code ColorPopup} on the screen.

     * @apiNote Ensure that an {@link ColorPicker.OnColorSelectedListener} is registered before calling
     * this method to correctly handle the user's color selection.
     */
    public void showPopup() {
        setVisible(true);
        clearFocus();
    }
}