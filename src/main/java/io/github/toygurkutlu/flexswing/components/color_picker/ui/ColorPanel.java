package io.github.toygurkutlu.flexswing.components.color_picker.ui;

import io.github.toygurkutlu.flexswing.components.ColorPicker;
import io.github.toygurkutlu.flexswing.components.color_picker.components.AlphaSliderUI;
import io.github.toygurkutlu.flexswing.components.color_picker.components.CirclePanel;
import io.github.toygurkutlu.flexswing.components.color_picker.components.ColorArea;
import io.github.toygurkutlu.flexswing.components.color_picker.components.HueSliderUI;
import io.github.toygurkutlu.flexswing.components.color_picker.objects.ColorNumericFilter;
import io.github.toygurkutlu.flexswing.components.color_picker.objects.RGBType;
import io.github.toygurkutlu.flexswing.objects.RoundedBorder;
import io.github.toygurkutlu.flexswing.objects.FlexUtil;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.*;

/**
 * Provides a simple UI for selecting a color.<br><br>
 *
 * <p>
 * {@code ColorPanel} contains the following components:
 * <ul>
 *     <li>{@code ColorArea}: {@code JPanel} for selecting color from palette.</li>
 *     <li>{@code DisplayArea}: {@code JPanel} for displaying selected color.</li>
 *     <li>{@code HueSlider}: {@code JSlider} for changing {@code ColorArea}'s hue.</li>
 *     <li>{@code AlphaSlider}: {@code JSlider} for changing the alpha value as percentage.</li>
 *     <li>{@code RGBFields}: {@code JTextField}s for setting the color from its RGB values.</li>
 *     <li>{@code AlphaField}: {@code JTextField} for setting the color's alpha value as percentage.</li>
 *     <li>{@code HexField}: {@code JTextField} for setting the color from its hex value.</li>
 * </ul>
 * </p>
 *
 * <p>{@code ColorPanel} can be customized by using the following methods:
 * <ul>
 *     <li>For customizing {@code ColorPanel} background: {@link #setPaletteBackground(Color)}</li>
 *     <li>For customizing {@code DisplayArea} border: {@link #setDisplayAreaBorderColor(Color)}</li>
 *     <li>For customizing text color: {@link #setPaletteForeground(Color)}</li>
 *     <li>For customizing text font: {@link #setPaletteFont(Font)}</li>
 *     <li>For customizing default color boxes' hover effect: {@link #setHoverColor(Color)}</li>
 * </ul>
 * </p>
 *
 * <p>
 *     For handling selected color, set {@link ColorPicker.OnColorSelectedListener} for the {@code ColorPanel} and use
 *     its {@link ColorPicker.OnColorSelectedListener#onColorSelected(Color)} method, which receives the newly selected
 *     color as an argument.
 * </p>
 */
public class ColorPanel extends JPanel {

    private int red;
    private int green;
    private int blue;
    private int alpha;
    private Color displayColor;
    private Color paletteBackground;
    private Color paletteForeground;
    private Font paletteFont;
    private Color hoverColor;
    private Color displayAreaBorderColor;
    private Color[] firstRowDefaultColors;
    private Color[] secondRowDefaultColors;
    private JLabel rLabel;
    private JLabel gLabel;
    private JLabel bLabel;
    private JLabel aLabel;
    private JLabel hLabel;
    private JTextField redField;
    private JTextField greenField;
    private JTextField blueField;
    private JTextField alphaField;
    private JTextField hexField;
    private ColorArea colorArea;
    private CirclePanel displayArea;
    private JSlider hueSlider;
    private JSlider alphaSlider;
    private AlphaSliderUI alphaSliderUI;
    private final ColorPicker palette;
    private boolean isInitialized = false;
    private ColorPicker.OnColorSelectedListener listener;
    private boolean isUpdating = false;

    public ColorPanel(ColorPicker palette) {
        this.palette = palette;

        init();
        addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                SwingUtilities.invokeLater(FlexUtil::clearGlobalFocusOwner);
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {

            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FlexUtil.clearGlobalFocusOwner();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                FlexUtil.clearGlobalFocusOwner();
            }
        });
    }

    public void setOnColorSelectedListener(ColorPicker.OnColorSelectedListener listener) {
        this.listener = listener;
    }

    /**
     * Gets the hue value for the user's selected color.
     *
     * @return the hue value for the selected color.
     */
    public float getHue() {
        return colorArea.getHue();
    }

    /**
     * Gets the saturation value for the user's selected color.
     *
     * @return the saturation value for the selected color.
     */
    public float getSaturation() {
        return colorArea.getSaturation();
    }

    /**
     * Gets the brightness value for the user's selected color.
     *
     * @return the brightness value for the selected color.
     */
    public float getBrightness() {
        return colorArea.getBrightness();
    }

    /**
     * Gets the default colors for the first row (1-10).
     *
     * @return color array for the first row default colors.
     */
    public Color[] getFirstRowDefaultColors() {
        return firstRowDefaultColors;
    }

    /**
     * Sets the default colors for the second row (1-10).
     *
     * @param colors the color array for the first row default colors.
     * @throws ArrayIndexOutOfBoundsException if the length of the array is different from 10.
     */
    public void setFirstRowDefaultColors(Color[] colors) {
        if (colors.length == 10) {
            firstRowDefaultColors = colors;
        }
    }

    /**
     * Gets the default colors for the second row (11-20).
     *
     * @return color array for the second row default colors.
     */
    public Color[] getSecondRowDefaultColors() {
        return secondRowDefaultColors;
    }

    /**
     * Sets the default colors for the second row (11-20).
     *
     * @param colors the color array for the second row default colors.
     * @throws ArrayIndexOutOfBoundsException if the length of the array is different from 10.
     */
    public void setSecondRowDefaultColors(Color[] colors) {
        if (colors.length == 10) {
            secondRowDefaultColors = colors;
        }
    }

    /**
     * Gets the color selected by the user.
     *
     * @return the selected color.
     */
    public Color getDisplayColor() {
        return displayColor;
    }

    /**
     * Sets the color for the {@code ColorPanel}.
     *
     * @param newColor the selected color.
     */
    public void setDisplayColor(Color newColor) {
        if (isInitialized && newColor != null && !displayColor.equals(newColor)) {
            isUpdating = true;

            try {
                colorArea.setColor(newColor);
                displayArea.setFillColor(newColor);
                hueSlider.setValue(Math.round(colorArea.getHue() * 360f));
                hexField.setText(FlexUtil.colorToHex(newColor));
                alphaSliderUI.setBaseColor(newColor);

                if (FlexUtil.getAlphaValue(alpha) != newColor.getAlpha()) {
                    alpha = FlexUtil.getAlphaPercentage(newColor.getAlpha());
                    alphaField.setText(String.valueOf(alpha));
                    alphaSlider.setValue(alpha);
                }
                if (red != newColor.getRed()) {
                    red = newColor.getRed();
                    redField.setText(String.valueOf(red));
                }
                if (green != newColor.getGreen()) {
                    green = newColor.getGreen();
                    greenField.setText(String.valueOf(green));
                }
                if (blue != newColor.getBlue()) {
                    blue = newColor.getBlue();
                    blueField.setText(String.valueOf(blue));
                }

                this.displayColor = newColor;

                if (listener != null) listener.onColorSelected(newColor);

                FlexUtil.clearGlobalFocusOwner();
                revalidate();
                repaint();

            } finally {
                isUpdating = false;
            }
        }
    }

    /**
     * Gets the {@code ColorPanel}'s background color.
     *
     * @return background color of the {@code ColorPanel}.
     */
    public Color getPaletteBackground() {
        return paletteBackground;
    }

    /**
     * Sets the {@code ColorPanel}'s background color.
     *
     * @param bg the new background color to set.
     * @apiNote Avoid using {@link JComponent#setBackground(Color)}. This method may throw a {@link NullPointerException} if components are not yet initialized.
     */
    public void setPaletteBackground(Color bg) {
        this.paletteBackground = bg;
        setBackground(bg);
    }

    /**
     * Gets the {@code ColorPanel}'s text color.
     *
     * @return the foreground color of the {@code ColorPanel}'s texts.
     */
    public Color getPaletteForeground() {
        return paletteForeground;
    }

    /**
     * Sets the {@code ColorPanel}'s text color.
     *
     * @param fg the new text color to set.
     * @apiNote Avoid using {@link JComponent#setForeground(Color)}. This method may throw a {@link NullPointerException} if components are not yet initialized.
     */
    public void setPaletteForeground(Color fg) {
        this.paletteForeground = fg;
        redField.setForeground(fg);
        greenField.setForeground(fg);
        blueField.setForeground(fg);
        alphaField.setForeground(fg);
        hexField.setForeground(fg);
        rLabel.setForeground(fg);
        gLabel.setForeground(fg);
        bLabel.setForeground(fg);
        aLabel.setForeground(fg);
        hLabel.setForeground(fg);
    }

    /**
     * Gets the {@code ColorPanel}'s text font.
     *
     * @return the font of the {@code ColorPanel}'s texts.
     */
    public Font getPaletteFont() {
        return paletteFont;
    }

    /**
     * Sets the {@code ColorPanel}'s text font.
     *
     * @param font the new text font to set.
     * @apiNote Avoid using {@link JComponent#setFont(Font)} This method may throw a {@link NullPointerException} if components are not yet initialized.
     */
    public void setPaletteFont(Font font) {
        this.paletteFont = font;
        rLabel.setFont(font);
        gLabel.setFont(font);
        bLabel.setFont(font);
        aLabel.setFont(font);
        hLabel.setFont(font);
    }

    /**
     * Gets the border color of the selected color's display area.
     *
     * @return borderColor the border color.
     */
    public Color getDisplayAreaBorderColor() {
        return displayAreaBorderColor;
    }

    /**
     * Sets the border color of the selected color's display area.
     *
     * @param borderColor the new border color.
     */
    public void setDisplayAreaBorderColor(Color borderColor) {
        this.displayAreaBorderColor = borderColor;
        displayArea.setBorderColor(borderColor);
    }

    /**
     * Gets the hover color for the {@code ColorPanel}'s default color boxes.
     *
     * @return the hover color of the default color boxes.
     * @apiNote The {@code hoverColor} is used to highlight the outer border of the default color boxes when the mouse hovers over them.
     */
    public Color getHoverColor() {
        return hoverColor;
    }

    /**
     * Sets the hover color for the {@code ColorPanel}'s default color boxes.
     *
     * @param hoverColor the new hover color to set.
     * @apiNote The {@code hoverColor} is used to highlight the outer border of the default color boxes when the mouse hovers over them.
     */
    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    //Initialize
    private void init() {
        initColors();
        createViews();
        initUI();
    }

    private void initColors() {
        firstRowDefaultColors = palette.getFirstRowDefaultColors();
        secondRowDefaultColors = palette.getSecondRowDefaultColors();

        displayColor = palette.getDisplayColor();
        paletteBackground = palette.getPaletteBackground();
        paletteForeground = palette.getPaletteForeground();
        paletteFont = palette.getPaletteFont();
        hoverColor = palette.getHoverColor();
        displayAreaBorderColor = palette.getDisplayAreaBorderColor();

        red = displayColor.getRed();
        green = displayColor.getGreen();
        blue = displayColor.getBlue();
        alpha = FlexUtil.getAlphaPercentage(displayColor.getAlpha());
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        setBackground(paletteBackground);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(colorArea, gbc);

        gbc.insets.left = 15;
        gbc.insets.right = 15;
        gbc.insets.top = 5;
        gbc.insets.bottom = 5;
        gbc.gridy = 1;
        add(createSliderPanel(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(createInputPanel(), gbc);

        gbc.gridy = 3;
        add(createDefaultColorsPanel(), gbc);
    }

    private void initListeners() {
        colorArea.addPropertyChangeListener("color", evt -> {
            if (isUpdating) return;

            Color selected = (Color) evt.getNewValue();
            Color newColor = new Color(selected.getRed(), selected.getGreen(), selected.getBlue(), FlexUtil.getAlphaValue(alpha));
            setDisplayColor(newColor);
        });

        hueSlider.addChangeListener(e -> {
            if (isUpdating) return;

            float hue = hueSlider.getValue() / 360f;
            colorArea.setHue(hue);
            Color newColor = colorArea.getColor();
            setDisplayColor(newColor);
        });

        hueSlider.addChangeListener(e -> {
            if (isUpdating) return;

            float hue = hueSlider.getValue() / 360f;
            colorArea.setHue(hue);

            Color selected = colorArea.getColor();
            Color newColor = new Color(selected.getRed(), selected.getGreen(), selected.getBlue(), FlexUtil.getAlphaValue(alpha));
            setDisplayColor(newColor);
        });

        alphaSlider.addChangeListener(e -> {
            if (isUpdating) return;

            int alpha = alphaSlider.getValue();
            Color newColor = new Color(red, green, blue, FlexUtil.getAlphaValue(alpha));
            setDisplayColor(newColor);
        });

        redField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                redField.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                applyRGBField(redField, RGBType.RED);
            }
        });
        redField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    redField.setText(String.valueOf(red));
                    FlexUtil.clearGlobalFocusOwner();
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    applyRGBField(redField, RGBType.RED);
                }
            }
        });

        greenField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                greenField.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                applyRGBField(greenField, RGBType.GREEN);
            }
        });
        greenField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    greenField.setText(String.valueOf(green));
                    FlexUtil.clearGlobalFocusOwner();
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    applyRGBField(greenField, RGBType.GREEN);
                }
            }
        });

        blueField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                blueField.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                applyRGBField(blueField, RGBType.BLUE);
            }
        });
        blueField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    blueField.setText(String.valueOf(blue));
                    FlexUtil.clearGlobalFocusOwner();
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    applyRGBField(blueField, RGBType.BLUE);
                }
            }
        });

        alphaField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                alphaField.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = alphaField.getText();
                int value = Integer.parseInt(text);
                if (!text.isBlank() && value != alpha) {
                    Color newColor = new Color(red, green, blue, FlexUtil.getAlphaValue(value));
                    setDisplayColor(newColor);
                } else {
                    alphaField.setText(String.valueOf(alpha));
                }
            }
        });
        alphaField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    alphaField.setText(String.valueOf(FlexUtil.getAlphaPercentage(alpha)));
                    FlexUtil.clearGlobalFocusOwner();
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String text = alphaField.getText();
                    int value = Integer.parseInt(text);
                    if (!text.isBlank() && value != alpha) {
                        Color newColor = new Color(red, green, blue, FlexUtil.getAlphaValue(value));
                        setDisplayColor(newColor);
                    } else {
                        alphaField.setText(String.valueOf(alpha));
                        FlexUtil.clearGlobalFocusOwner();
                    }
                }
            }
        });

        hexField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                hexField.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                processHexInput();
            }
        });

        hexField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    hexField.setText(FlexUtil.colorToHex(displayColor));
                    FlexUtil.clearGlobalFocusOwner();
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    processHexInput();
                    FlexUtil.clearGlobalFocusOwner();
                }
            }
        });
    }

    private JPanel createSliderPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 15, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets.left = 0;
        gbc.weightx = 1;
        gbc.insets.right = 10;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(displayArea, gbc);

        gbc.insets.right = 0;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.gridx = 1;
        panel.add(hueSlider, gbc);

        gbc.gridy = 1;
        panel.add(alphaSlider, gbc);
        return panel;
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(rLabel, gbc);
        gbc.gridx = 1;
        panel.add(gLabel, gbc);
        gbc.gridx = 2;
        panel.add(bLabel, gbc);
        gbc.gridx = 3;
        panel.add(aLabel, gbc);
        gbc.gridx = 4;
        panel.add(hLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(redField, gbc);
        gbc.gridx = 1;
        panel.add(greenField, gbc);
        gbc.gridx = 2;
        panel.add(blueField, gbc);
        gbc.gridx = 3;
        panel.add(alphaField, gbc);
        gbc.gridx = 4;
        gbc.insets.right = 0;
        panel.add(hexField, gbc);

        return panel;
    }

    private JPanel createDefaultColorsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        for (int i = 0; i < firstRowDefaultColors.length; i++) {
            gbc.gridx = i;
            Color color = firstRowDefaultColors[i];
            JPanel colorPanel = createColorBoxPanel(color, 0, i);
            panel.add(colorPanel, gbc);
        }

        gbc.gridy = 1;
        gbc.gridx = 0;
        for (int i = 0; i < secondRowDefaultColors.length; i++) {
            Color color = secondRowDefaultColors[i];
            JPanel colorPanel = createColorBoxPanel(color, 1, i);
            panel.add(colorPanel, gbc);
            gbc.gridx++;
        }

        return panel;
    }

    private void createViews() {
        colorArea = new ColorArea(displayColor);

        displayArea = new CirclePanel(32);
        displayArea.setFillColor(displayColor);
        setDisplayAreaBorderColor(new Color(200, 200, 175));

        hueSlider = new JSlider(0, 360);
        hueSlider.setUI(new HueSliderUI(hueSlider));
        hueSlider.setPreferredSize(new Dimension(150, 30));

        alphaSlider = new JSlider(0, 100);
        alphaSliderUI = new AlphaSliderUI(alphaSlider);
        alphaSlider.setUI(alphaSliderUI);
        alphaSlider.setValue(alpha);
        alphaSlider.setPreferredSize(new Dimension(150, 30));
        alphaSliderUI.setBaseColor(displayColor);

        rLabel = createInputTitle("R");
        gLabel = createInputTitle("G");
        bLabel = createInputTitle("B");
        aLabel = createInputTitle("A%");
        hLabel = createInputTitle("Hex");

        redField = createInputField(String.valueOf(red), "red.field", 3);
        greenField = createInputField(String.valueOf(green), "green.field", 3);
        blueField = createInputField(String.valueOf(blue), "blue.field", 3);
        alphaField = createInputField(String.valueOf(alpha), "alpha.field", 3);
        hexField = createInputField(FlexUtil.colorToHex(displayColor), "hex.field", 7);

        ((AbstractDocument) redField.getDocument()).setDocumentFilter(new ColorNumericFilter(0, 255));
        ((AbstractDocument) greenField.getDocument()).setDocumentFilter(new ColorNumericFilter(0, 255));
        ((AbstractDocument) blueField.getDocument()).setDocumentFilter(new ColorNumericFilter(0, 255));
        ((AbstractDocument) alphaField.getDocument()).setDocumentFilter(new ColorNumericFilter(0, 100));

        initListeners();
        isInitialized = true;
    }

    private JLabel createInputTitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(paletteFont);
        label.setForeground(paletteForeground);
        return label;
    }

    private JTextField createInputField(String text, String name, int colon) {
        JTextField tf = new JTextField(text, colon);
        tf.setName(name);
        tf.setOpaque(false);
        tf.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(paletteForeground, 1),
                                                        BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        tf.setHorizontalAlignment(SwingConstants.CENTER);
        tf.setBorder(new RoundedBorder(5));
        tf.setForeground(paletteForeground);
        tf.setCaretColor(paletteForeground);

        return tf;
    }

    private JPanel createColorBoxPanel(Color color, int row, int position) {
        int gap = 4;
        int px = 15;

        JPanel colorBox = new JPanel(new GridBagLayout());
        colorBox.setBackground(paletteBackground);
        colorBox.setName("b_" + row + "_" + position);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        colorBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setDisplayColor(color);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                colorBox.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                colorBox.setBackground(paletteBackground);
            }
        });

        gbc.insets = new Insets(gap, gap, gap, gap);

        JPanel pnl = new JPanel();
        Dimension dim2 = new Dimension(px, px);
        pnl.setBackground(color);
        pnl.setPreferredSize(dim2);
        pnl.setMinimumSize(dim2);
        pnl.setMaximumSize(dim2);
        pnl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setDisplayColor(color);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                colorBox.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                colorBox.setBackground(paletteBackground);
            }
        });

        colorBox.add(pnl, gbc);

        return colorBox;
    }

    private void applyRGBField(JTextField field, RGBType type) {
        String text = field.getText().trim();

        if (text.isEmpty() || !text.matches("\\d+")) {
            field.setText(String.valueOf(getRGBValue(type)));
            return;
        }

        int value = Integer.parseInt(text);
        if (value < 0 || value > 255) {
            field.setText(String.valueOf(getRGBValue(type)));
            return;
        }
        if (value == getRGBValue(type)) return;

        Color newColor = switch (type) {
            case RED -> new Color(value, green, blue, FlexUtil.getAlphaValue(alpha));
            case GREEN -> new Color(red, value, blue, FlexUtil.getAlphaValue(alpha));
            case BLUE -> new Color(red, green, value, FlexUtil.getAlphaValue(alpha));
        };

        setDisplayColor(newColor);
    }

    private int getRGBValue(RGBType type) {
        return switch (type) {
            case RED -> red;
            case GREEN -> green;
            case BLUE -> blue;
        };
    }

    private void processHexInput() {
        String text = hexField.getText().trim();
        String currentHex = FlexUtil.colorToHex(displayColor);

        if (text.isBlank() || text.equalsIgnoreCase(currentHex)) {
            hexField.setText(currentHex);
            return;
        }

        try {
            Color newColor = FlexUtil.hexToColor(text);
            setDisplayColor(newColor);

        } catch (Exception ex) {
            hexField.setText(currentHex);
        }
    }
}