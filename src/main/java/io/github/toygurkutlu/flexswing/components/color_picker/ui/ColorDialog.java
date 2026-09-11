package io.github.toygurkutlu.flexswing.components.color_picker.ui;


import io.github.toygurkutlu.flexswing.components.ColorPicker;
import io.github.toygurkutlu.flexswing.objects.FlexUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Provides a simple dialog for selecting color.<br><br>
 * <p>
 *     Contains {@link ColorPicker.OnColorSelectedListener} which user can handle color selection via
 *     the {@link ColorPicker.OnColorSelectedListener#onColorSelected(Color)} method,
 *      which receives the newly selected color as an argument.
 * </p>
 * */
public class ColorDialog extends JDialog {

    private String positiveText;
    private String negativeText;
    private JPanel panel;
    private final ColorPanel colorPanel;
    private final ColorPicker.OnColorSelectedListener listener;

    public ColorDialog(ColorPanel colorPanel, String title, String positiveText, String negativeText,
                       ColorPicker.OnColorSelectedListener listener) {
        super();
        this.colorPanel = colorPanel;
        this.positiveText = positiveText;
        this.negativeText = negativeText;
        this.listener = listener;

        init();
        initKeyBindings();
        add(panel);
        pack();
        setTitle(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setIconImages(FlexUtil.getColorPickerImageIcons());
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
    }

    /**
     * Displays the {@code ColorDialog} on the screen.
     * <p>
     * This method blocks execution if the dialog is modal, allowing the user
     * to select a color before returning control to the caller.
     * </p>
     *
     * @apiNote Ensure that an {@link ColorPicker.OnColorSelectedListener} is registered before calling
     * this method to correctly handle the user's color selection.
     */
    public void showDialog() {
        setVisible(true);
    }

    private void initKeyBindings() {
        JRootPane root = getRootPane();

        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESC_CLOSE");

        root.getActionMap().put("ESC_CLOSE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER_CONFIRM");

        root.getActionMap().put("ENTER_CONFIRM", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listener != null) {
                    listener.onColorSelected(colorPanel.getDisplayColor());
                }
                dispose();
            }
        });
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusable(false);
        btn.setBackground(colorPanel.getPaletteForeground());
        btn.setBackground(colorPanel.getPaletteForeground());
        return btn;
    }

    private void init() {
        if (positiveText == null) positiveText = "Select";
        if (negativeText == null) negativeText = "Cancel";

        JButton positiveBtn = createButton(positiveText);
        JButton negativeBtn = createButton(negativeText);

        panel = new JPanel(new GridBagLayout());
        panel.setBackground(colorPanel.getPaletteBackground());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(colorPanel, gbc);

        gbc.insets = new Insets(15, 5, 15, 5);
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = 1;
        panel.add(negativeBtn, gbc);

        gbc.insets.right = 25;
        gbc.gridx = 1;
        gbc.weightx = 0;
        panel.add(positiveBtn, gbc);

        positiveBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (listener != null) listener.onColorSelected(colorPanel.getDisplayColor());
                dispose();
            }
        });

        negativeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
    }
}