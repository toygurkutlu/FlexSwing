package io.github.toygurkutlu.flexswing.components.combo_box.ui;

import io.github.toygurkutlu.flexswing.components.combo_box.domain.ComboBoxConfig;
import io.github.toygurkutlu.flexswing.components.combo_box.domain.PopupConfig;
import io.github.toygurkutlu.flexswing.components.combo_box.domain.TooltipProvider;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.Objects;

public class FlexComboPopup extends BasicComboPopup {

    private boolean hasTooltip;
    private final ListTooltip tooltip;
    private final JList<?> list;
    private final JScrollPane sp;

    private Color background;
    private Color borderColor;
    private Color scrollTrackColor;
    private Color scrollThumbColor;
    private Color scrollThumbDragColor;
    private int scrollThumbRadius;

    public FlexComboPopup(JComboBox<Object> comboBox, ComboBoxConfig attr, FlexArrowButton flexArrowButton) {
        super(comboBox);

        PopupConfig popupAttr = attr.getPopupConfig();

        this.hasTooltip = popupAttr.isHasTooltip();

        background = popupAttr.getBackground();
        borderColor = popupAttr.getBorderColor();

        scrollTrackColor = attr.getPopupConfig().getScrollAttributes().getTrackColor();
        scrollThumbColor = attr.getPopupConfig().getScrollAttributes().getThumbColor();
        scrollThumbDragColor = attr.getPopupConfig().getScrollAttributes().getThumbDragColor();
        scrollThumbRadius = attr.getPopupConfig().getScrollAttributes().getThumbRadius();

        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(borderColor, 1));
        setBackground(background);

        tooltip = new ListTooltip();
        tooltip.setBorderRadius(10);

        sp = (JScrollPane) getComponent(0);
        list = (JList<?>) sp.getViewport().getView();
        list.setFixedCellHeight(30);
        list.setBackground(background);

        list.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {
                if (!hasTooltip) return;
                int index = list.locationToIndex(e.getPoint());

                if (index >= 0 && list.getCellBounds(index, index).contains(e.getPoint())) {
                    Object value = list.getModel().getElementAt(index);
                    if (value instanceof TooltipProvider provider) {
                        String text = provider.toTooltip();
                        Point screenPos = list.getLocationOnScreen();
                        screenPos.translate(e.getX(), e.getY());
                        tooltip.showTooltip(text, screenPos);
                    }
                } else {
                    tooltip.hideTooltip();
                }
            }
        });

        list.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (hasTooltip) tooltip.hideTooltip();
            }
        });

        comboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent ev) {
                JScrollBar vsb = sp.getVerticalScrollBar();
                vsb.setPreferredSize(new Dimension(10, 0));
                vsb.setOpaque(false);
                sp.setOpaque(false);
                sp.getViewport().setOpaque(false);
                vsb.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        vsb.repaint();
                    }

                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        vsb.repaint();
                    }
                });
                vsb.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                    @Override
                    public void mouseMoved(java.awt.event.MouseEvent e) {
                        vsb.repaint();
                    }
                });

                vsb.setUI(new BasicScrollBarUI() {
                    final int FIXED_KNOB_HEIGHT = 40;

                    @Override
                    protected JButton createDecreaseButton(int orientation) {
                        return createZeroButton();
                    }

                    @Override
                    protected JButton createIncreaseButton(int orientation) {
                        return createZeroButton();
                    }

                    private JButton createZeroButton() {
                        JButton button = new JButton();
                        button.setPreferredSize(new Dimension(0, 0));
                        return button;
                    }

                    @Override
                    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                        g.setColor(scrollTrackColor);
                        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
                    }

                    @Override
                    protected void setThumbBounds(int x, int y, int width, int height) {
                        if (height > FIXED_KNOB_HEIGHT) {
                            int trackHeight = scrollbar.getHeight();
                            int maxOriginalY = trackHeight - height;
                            int maxNewY = trackHeight - FIXED_KNOB_HEIGHT;

                            if (maxOriginalY > 0) {
                                double ratio = (double) y / maxOriginalY;
                                y = (int) (ratio * maxNewY);
                            }
                            height = FIXED_KNOB_HEIGHT;
                        }
                        super.setThumbBounds(x, y, width, height);
                    }

                    @Override
                    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                        if (isDragging || scrollbar.getValueIsAdjusting())
                            g2.setColor(scrollThumbDragColor);
                        else
                            g2.setColor(scrollThumbColor);

                        int round = scrollThumbRadius;
                        g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, round, round);
                        g2.dispose();
                    }

                    @Override
                    protected void installListeners() {
                        super.installListeners();
                    }

                    @Override
                    protected void scrollByBlock(int direction) {
                        int customStep = 10;
                        int currentVal = scrollbar.getValue();
                        int newValue = currentVal + (direction * customStep);

                        if (newValue < scrollbar.getMinimum()) newValue = scrollbar.getMinimum();
                        int maxScrollable = scrollbar.getMaximum() - scrollbar.getModel().getExtent();
                        if (newValue > maxScrollable) newValue = maxScrollable;
                        scrollbar.setValue(newValue);
                    }
                });

                sp.setWheelScrollingEnabled(false);
                sp.addMouseWheelListener(e -> {
                    int scrollSpeedModifier = 5;
                    int moveAmount = e.getWheelRotation() * scrollSpeedModifier;

                    vsb.setValue(vsb.getValue() + moveAmount);
                });

                boolean verticalVisible = vsb.isVisible();
                int widest = getWidestItem(list);
                int scrollbarWidth = verticalVisible ? vsb.getPreferredSize().width : 0;
                int finalWidth = widest + scrollbarWidth + 10;

                if (flexArrowButton != null) flexArrowButton.updateState(true);

                sp.setPreferredSize(new Dimension(finalWidth, sp.getPreferredSize().height));
                sp.revalidate();
                sp.repaint();
            }

            @Override
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {
                if (hasTooltip) tooltip.hideTooltip();
                if (flexArrowButton != null) flexArrowButton.updateState(false);
            }

            @Override
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {
                if (hasTooltip) tooltip.hideTooltip();
                if (flexArrowButton != null) flexArrowButton.updateState(false);
            }
        });
    }


    @Override
    protected JScrollPane createScroller() {
        JScrollPane sp = super.createScroller();
        sp.setOpaque(false);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return sp;
    }

    private <T> int getWidestItem(JList<T> list) {
        int max = 0;
        ListModel<T> model = list.getModel();
        ListCellRenderer<? super T> renderer = list.getCellRenderer();
        for (int i = 0; i < model.getSize(); i++) {
            T item = model.getElementAt(i);
            Component c = renderer.getListCellRendererComponent(list, item, i, false, false);
            if (c != null) max = Math.max(max, c.getPreferredSize().width);
        }
        return max;
    }

    public void updateTooltip(ListTooltip.ListTooltipAttribute attr, Object value) {
        if (tooltip != null) {
            tooltip.updateListTooltip(Objects.requireNonNull(attr, "Attribute cannot be null."),
                                      Objects.requireNonNull(value, "Value cannot be null."));
        }
    }

    public void setHasTooltip(boolean hasTooltip) {
        this.hasTooltip = hasTooltip;
        list.revalidate();
        list.repaint();
    }

    public void setPopupBackground(Color bg) {
        this.background = bg;
        list.setBackground(bg);

        revalidate();
        repaint();
    }

    public void setPopupBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        setBorder(BorderFactory.createLineBorder(borderColor, 1));
        revalidate();
        repaint();
    }

    public void setScrollTrackColor(Color color) {
        this.scrollTrackColor = color;
        sp.revalidate();
        sp.repaint();
    }

    public void setScrollThumbColor(Color color) {
        this.scrollThumbColor = color;
        sp.revalidate();
        sp.repaint();
    }

    public void setScrollThumbDragColor(Color color) {
        this.scrollThumbDragColor = color;
        sp.revalidate();
        sp.repaint();
    }

    public void setScrollThumbCornerRadius(int radius) {
        this.scrollThumbRadius = radius;
        sp.revalidate();
        sp.repaint();
    }
}