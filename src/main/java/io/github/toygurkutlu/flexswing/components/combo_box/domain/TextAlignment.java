package io.github.toygurkutlu.flexswing.components.combo_box.domain;

public enum TextAlignment {
    LEFT(2), CENTER(0), RIGHT(4);

    private final int alignment;

    TextAlignment(int alignment) {
        this.alignment = alignment;
    }

    public int getAlignment() {
        return alignment;
    }
}
