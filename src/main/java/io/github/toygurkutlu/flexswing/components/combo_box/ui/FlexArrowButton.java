package io.github.toygurkutlu.flexswing.components.combo_box.ui;

import io.github.toygurkutlu.flexswing.components.FlexButton;
import io.github.toygurkutlu.flexswing.components.combo_box.domain.ArrowButtonConfig;
import io.github.toygurkutlu.flexswing.components.combo_box.domain.ComboBoxConfig;
import io.github.toygurkutlu.flexswing.objects.FlexUtil;
import io.github.toygurkutlu.flexswing.objects.Radii;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Objects;

public class FlexArrowButton extends FlexButton {

    private ArrowButtonConfig attr;
    private final ComboBoxConfig comboAttr;
    private final Radii radii;
    private int bottomRight;
    private int topRight;
    private final Icon dIcon;
    private final Icon uIcon;
    private Icon downIcon;
    private Icon upIcon;
    private Color background;
    private Color borderColor;
    private Color arrowColor;
    private Color hoverColor;
    private Color pressedColor;

    public FlexArrowButton(ComboBoxConfig comboAttr) {
        super();
        this.comboAttr = comboAttr;
        this.attr = comboAttr.getArrowButtonConfig();

        bottomRight = comboAttr.getDisplayPanelConfig().getRadii().bottomRight();
        topRight = comboAttr.getDisplayPanelConfig().getRadii().topRight();

        radii = new Radii(0, topRight, 0, bottomRight);

        background = attr.getBackground();
        borderColor = attr.getBorderColor();
        pressedColor = attr.getPressedBackground();
        hoverColor = attr.getHoverBackground();
        arrowColor = attr.getArrowIconColor();

        URL downURL = Objects.requireNonNull(FlexArrowButton.class.getResource("/icons/arrow_down_32.png"));
        URL upURL = Objects.requireNonNull(FlexArrowButton.class.getResource("/icons/arrow_up_32.png"));
        dIcon = new ImageIcon(downURL);
        uIcon = new ImageIcon(upURL);
        downIcon = FlexUtil.recolorIcon(dIcon, arrowColor);
        upIcon = FlexUtil.recolorIcon(uIcon, arrowColor);

        setBackgroundColor(background);
        setBorderThickness(1);
        setHasBorder(true);
        setBorderColor(borderColor);
        setRadii(radii);

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

    public void updateState(boolean isVisible) {
        int radius = isVisible ? 0 : this.bottomRight;
        Icon ic = isVisible ? upIcon : downIcon;

        setBottomRightRadius(radius);
        setIcon(ic);
    }

    public void setArrowButtonBackground(Color bg) {
        this.background = bg;
        attr.setBackground(bg);
        setBackgroundColor(bg);
        revalidate();
        repaint();
    }

    public void setArrowButtonBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        attr.setBorderColor(borderColor);
        setBorderColor(borderColor);
        revalidate();
        repaint();
    }

    public void setArrowButtonIconColor(Color iconColor) {
        this.arrowColor = iconColor;
        attr.setArrowIconColor(iconColor);
        downIcon = FlexUtil.recolorIcon(dIcon, iconColor);
        upIcon = FlexUtil.recolorIcon(uIcon, iconColor);
        setIcon(downIcon);
    }

    public void setArrowButtonHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
        attr.setHoverBackground(hoverColor);
        setHoverBackgroundColor(hoverColor);
    }

    public void setArrowButtonPressedColor(Color pressedColor) {
        this.pressedColor = pressedColor;
        attr.setPressedBackground(pressedColor);
        setPressedBackgroundColor(pressedColor);
    }

    public void setArrowButtonRadii(int topRight, int bottomRight) {
        this.topRight = topRight;
        this.bottomRight = bottomRight;
        this.radii.setTopRight(topRight);
        this.radii.setBottomRight(bottomRight);
        setRadii(this.radii);
    }
}