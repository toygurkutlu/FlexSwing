package io.github.toygurkutlu.flexswing.components.combo_box.ui;

import io.github.toygurkutlu.flexswing.components.FlexButton;
import io.github.toygurkutlu.flexswing.components.combo_box.records.ArrowButtonAttributes;
import io.github.toygurkutlu.flexswing.objects.FlexUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Objects;

public class FlexArrowButton extends FlexButton {

    private final int bottomRight;
    private final Icon downIcon;
    private final Icon upIcon;

    public FlexArrowButton(ArrowButtonAttributes attr) {
        super();

        bottomRight = attr.radii().bottomRight();

        URL downURL = Objects.requireNonNull(FlexArrowButton.class.getResource("/icons/arrow_down_32.png"));
        URL upURL = Objects.requireNonNull(FlexArrowButton.class.getResource("/icons/arrow_up_32.png"));
        ImageIcon dIcon = new ImageIcon(downURL);
        downIcon = FlexUtil.recolorIcon(dIcon, attr.arrowColor());
        upIcon = FlexUtil.recolorIcon(new ImageIcon(upURL), attr.arrowColor());

        Color background = attr.background();
        Color borderColor = attr.borderColor();
        Color pressedColor = attr.pressedColor();
        Color hoverColor = attr.hoverColor();

        setBackgroundColor(background);
        setBorderThickness(1);
        setHasBorder(true);
        setBorderColor(borderColor);
        setRadii(attr.radii());

        setIcon(downIcon);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackgroundColor(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackgroundColor(background);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackgroundColor(pressedColor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackgroundColor(hoverColor);
            }
        });
    }

    public void updateState(boolean isVisible){
        int bottomRight = isVisible ? 0 : this.bottomRight;
        Icon ic = isVisible ? upIcon : downIcon;

        setBottomRightRadius(bottomRight);
        setIcon(ic);
    }
}