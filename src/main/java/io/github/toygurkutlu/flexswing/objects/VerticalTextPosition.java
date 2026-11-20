package io.github.toygurkutlu.flexswing.objects;


/**
 * Represents the vertical position of the text relative to the icon.
 * */
public enum VerticalTextPosition {

    /**
     * Specifies that the text is vertically centered relative to the icon.
     */
    CENTER(0),
    /**
     * Specifies that the text is positioned vertically above the icon.
     */
    TOP(1),
    /**
     * Specifies that the text is positioned vertically below the icon.
     */
    BOTTOM(3);

    private final int textPosition;

    VerticalTextPosition(int textPosition) {
        this.textPosition = textPosition;
    }

    public int getTextPosition() {
        return this.textPosition;
    }
}
