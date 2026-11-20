package io.github.toygurkutlu.flexswing.objects;

/**
 * Represents the horizontal position of the text relative to the icon.
 */
public enum HorizontalTextPosition {
    /**
     * Specifies that the text is horizontally centered relative to the icon.
     */
    CENTER(0),
    /**
     * Specifies that the text is positioned horizontally to the left of the icon.
     */
    LEFT(2),
    /**
     * Specifies that the text is positioned horizontally to the right of the icon.
     */
    RIGHT(4);

    private final int textPosition;

    HorizontalTextPosition(int textPosition) {
        this.textPosition = textPosition;
    }

    public int getTextPosition() {
        return this.textPosition;
    }
}
