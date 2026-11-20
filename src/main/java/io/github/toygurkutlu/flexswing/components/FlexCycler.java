package io.github.toygurkutlu.flexswing.components;

import io.github.toygurkutlu.flexswing.components.combo_box.domain.ComboBoxConfig;
import io.github.toygurkutlu.flexswing.components.combo_box.domain.PopupConfig;
import io.github.toygurkutlu.flexswing.objects.FlexUtil;
import io.github.toygurkutlu.flexswing.objects.Padding;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class FlexCycler<T> extends JComponent {
    private FlexPanel container;
    private FlexComboBox<T> comboBox;
    private FlexButton btnNext;
    private FlexButton btnPrevious;
    private Icon leftArrow;
    private Icon rightArrow;
    private final T[] arr;
    private boolean isUpdating = false;
    private OnCycleListener listener;

    private Color containerBackground = new Color(25, 25, 25);
    private Color background = containerBackground;
    private Color foreground = Color.WHITE;
    private Color hoverBackground = new Color(80, 80, 80);
    private Color hoverForeground = new Color(25, 25, 25);

    public interface OnCycleListener {
        void onCycle(int position);
    }

    public void setOnCycleListener(OnCycleListener listener) {
        this.listener = listener;
    }

    public FlexCycler(T[] arr) {
        this.arr = arr;
        init();
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(container);
    }

    private void init() {
        createViews();
        setupUI();
    }

    private void createViews() {
        container = new FlexPanel(new GridBagLayout());
        container.setHasBorder(true);
        container.setBackgroundColor(containerBackground);
        container.setBorderColor(containerBackground);

        ComboBoxConfig attr = ComboBoxConfig.defaultConfig()
                .withPopupAttributes(PopupConfig.defaultPopupAttributes().withHasTooltip(false));
        comboBox = new FlexComboBox<>(attr, new DefaultComboBoxModel<>(arr));

        leftArrow = FlexUtil.recolorIcon(new ImageIcon(Objects.requireNonNull(
                FlexCycler.class.getResource("/icons/arrow_left.png"))), Color.WHITE);
        rightArrow = FlexUtil.recolorIcon(new ImageIcon(Objects.requireNonNull(
                FlexCycler.class.getResource("/icons/arrow_right.png"))), Color.WHITE);

        btnPrevious = createButton(getPrevious(0), leftArrow);
        btnPrevious.setPadding(new Padding(0, 0, 0, 10));

        btnNext = createButton(getNext(0), rightArrow);
        btnNext.setPadding(new Padding(0, 10, 0, 0));
        btnNext.setHorizontalTextPosition(SwingConstants.LEFT);

        comboBox.addItemListener(e -> {
            if (isUpdating) return;
            if (e.getStateChange() == ItemEvent.SELECTED) {
                int index = comboBox.getSelectedIndex();
                cycle(index);
                if (listener != null) listener.onCycle(index);
            }
        });

        btnPrevious.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isUpdating = true;
                int currentIndex = comboBox.getSelectedIndex();
                int newIndex = (currentIndex - 1 + arr.length) % arr.length;
                comboBox.setSelectedIndex(newIndex);
                cycle(newIndex);
                if (listener != null) listener.onCycle(newIndex);
                isUpdating = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnPrevious.setIcon(FlexUtil.recolorIcon(leftArrow, hoverForeground));
                btnPrevious.setForeground(hoverForeground);
                btnPrevious.setBackgroundColor(hoverBackground);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnPrevious.setIcon(leftArrow);
                btnPrevious.setForeground(foreground);
                btnPrevious.setBackgroundColor(background);
            }
        });

        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isUpdating = true;
                int currentIndex = comboBox.getSelectedIndex();
                int newIndex = (currentIndex + 1) % arr.length;
                comboBox.setSelectedIndex(newIndex);
                cycle(newIndex);
                if (listener != null) listener.onCycle(newIndex);
                isUpdating = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnNext.setIcon(FlexUtil.recolorIcon(rightArrow, hoverForeground));
                btnNext.setForeground(hoverForeground);
                btnNext.setBackgroundColor(hoverBackground);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnNext.setIcon(rightArrow);
                btnNext.setForeground(foreground);
                btnNext.setBackgroundColor(background);
            }
        });
    }

    private void setupUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.fill = GridBagConstraints.VERTICAL;
        container.add(btnPrevious, gbc);

        gbc.insets.right = 0;
        gbc.gridx++;
        container.add(comboBox, gbc);

        gbc.gridx++;
        gbc.insets.left = 5;
        container.add(btnNext, gbc);
    }

    private String getPrevious(int position) {
        T t = position == 0 ? arr[arr.length - 1] : arr[position - 1];
        return t.toString();
    }

    private String getNext(int position) {
        T t = position == arr.length - 1 ? arr[0] : arr[position + 1];
        return t.toString();
    }

    private void cycle(int position) {
        btnNext.setText(getNext(position));
        btnPrevious.setText(getPrevious(position));
    }

    private FlexButton createButton(String text, Icon icon) {
        Font font = new Font("Dialog", Font.BOLD, 12);

        FlexButton btn = new FlexButton(text);
        btn.setIcon(icon);
        btn.setIconTextGap(0);
        btn.setFont(font);
        btn.setBackgroundColor(background);
        btn.setBorderColor(background);
        btn.setForeground(foreground);

        return btn;
    }


    @Override
    public Dimension getPreferredSize() {
        LayoutManager layout = getLayout();
        if (layout != null) {
            return layout.preferredLayoutSize(this);
        }
        return new Dimension(200, 30);
    }
}