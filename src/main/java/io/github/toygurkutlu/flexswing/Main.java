package io.github.toygurkutlu.flexswing;

import io.github.toygurkutlu.flexswing.components.FlexButton;
import io.github.toygurkutlu.flexswing.components.FlexCycler;
import io.github.toygurkutlu.flexswing.components.FlexIncreaser;
import io.github.toygurkutlu.flexswing.components.increaser.IncreaserAttribute;
import io.github.toygurkutlu.flexswing.objects.Radii;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private JPanel panel;

    public Main() {

        init();
        Dimension dim = new Dimension(500, 500);
        add(panel);
        pack();
        setMinimumSize(dim);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private String[] getArr() {
        return new String[]{
                "Uzunluk",
                "Hacim",
                "Ağırlık",
                "Hız",
                "Basınç",
                "Enerji"
        };
    }

    private void init() {
//        FlexButton button = new FlexButton("MyButton", config);
//
//        FlexButton button = new FlexButton("MyButton");
//        button.setConfig(config);

        FlexIncreaser increaser = new FlexIncreaser(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        increaser.setAttribute(IncreaserAttribute.RADII, new Radii(20, 0, 0, 20))
                .setAttribute(IncreaserAttribute.GAP_BETWEEN_ITEMS, 10)
                .setAttribute(IncreaserAttribute.POPUP_BACKGROUND, Color.YELLOW)
                .setAttribute(IncreaserAttribute.POPUP_ITEM_BACKGROUND, Color.WHITE)
                .setAttribute(IncreaserAttribute.POPUP_ITEM_FOREGROUND, Color.RED)
                .setAttribute(IncreaserAttribute.POPUP_SELECTED_ITEM_FOREGROUND, Color.WHITE)
                .setAttribute(IncreaserAttribute.POPUP_SELECTED_ITEM_BACKGROUND, Color.BLUE);//+

        String[] arr = getArr();

        FlexCycler<String> cycler = new FlexCycler<>(arr);

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(new JLabel("This is FlexIncreaser"), gbc);
        gbc.gridy++;
        panel.add(new JLabel("FlexIncreaser:"), gbc);
        gbc.gridx++;
        panel.add(increaser, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("This is FlexCycler"), gbc);
        gbc.gridx++;
        panel.add(cycler, gbc);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(Main::new);
    }
}
