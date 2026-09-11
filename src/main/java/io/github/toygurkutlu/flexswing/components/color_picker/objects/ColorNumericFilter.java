package io.github.toygurkutlu.flexswing.components.color_picker.objects;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class ColorNumericFilter extends DocumentFilter {
    private final int maxValue;
    private final int minValue;

    public ColorNumericFilter(int minValue, int maxValue) {
        if (minValue > maxValue) {
            throw new IllegalArgumentException("minValue cannot be greater than maxValue");
        }
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string == null) return;

        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String proposedText = currentText.substring(0, offset) + string + currentText.substring(offset);

        if (isValidInput(proposedText)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null) text = "";

        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String proposedText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

        if (isValidInput(proposedText)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    private boolean isValidInput(String text) {
        if (text.isEmpty()) {
            return true;
        }

        if (!text.matches("\\d+")) {
            return false;
        }

        try {
            long value = Long.parseLong(text);
            return value >= minValue && value <= maxValue;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}