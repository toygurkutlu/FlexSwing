package io.github.toygurkutlu.flexswing.components;

import io.github.toygurkutlu.flexswing.components.navigation_view.adapters.NavItemAdapter;
import io.github.toygurkutlu.flexswing.components.navigation_view.enums.TextPosition;
import io.github.toygurkutlu.flexswing.components.navigation_view.listeners.NavItemListener;
import io.github.toygurkutlu.flexswing.components.navigation_view.objects.NavItem;
import io.github.toygurkutlu.flexswing.components.navigation_view.records.NavAttributes;
import io.github.toygurkutlu.flexswing.components.navigation_view.records.NavItemAttributes;
import io.github.toygurkutlu.flexswing.objects.FlexUtil;
import io.github.toygurkutlu.flexswing.objects.Padding;
import io.github.toygurkutlu.flexswing.records.ScrollAttributes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Customizable {@code NavigationView} that using a varargs list of {@code NavItem} instances.
 * <p>
 * The provided {@code NavItem}s can contain either titles only or both titles and subtitles.
 * </p>
 * <ul>
 *   <li><b>If used with only titles:</b> The {@code NavigationView} appearance will be like a
 *   simple {@code ListView}.</li>
 *   <li><b>If used with titles and subtitles:</b> The {@code NavigationView} appearance will be
 *   like a {@code Tree}, and the subtitles will feature an expand/collapse mechanism.</li>
 * </ul>
 *
 * <p>For customizing the {@code NavigationView}, the developer should create custom {@code NavAttributes}.</p>
 *
 * @see NavItem
 * @see NavAttributes
 */
public class NavigationView extends JPanel {

    private JScrollPane sp;
    private final List<NavItem> items;
    private final NavAttributes attr;
    private final NavItemAttributes titleAttr;
    private final NavItemAttributes subtitleAttr;
    private JLabel iconLabel;
    private JPanel contentPanel;
    private Icon navLeft;
    private Icon navRight;
    private Icon downArrow;
    private Icon upArrow;
    private boolean isCollapsed = false;
    private int selectedTitlePosition = 0;
    private int selectedSubtitlePosition = -1;
    private List<JPanel> titlePanels;
    private List<JPanel> subtitlePanels;
    private NavItemListener listener;
    private int innerGap = 25;

    /**
     * Creates a {@code NavigationView} instance with the provided items and config.
     *
     * @param items the items to display on the {@code NavigationView}, cannot be null
     * @param attr  the style attributes of the {@code NavigationView}, cannot be null
     * @throws NullPointerException if any of the following parameters are {@code null}:
     *                              <ul>
     *                                <li>When the provided <code>items</code> is <code>null</code></li>
     *                                <li>When the provided <code>attr</code> is <code>null</code></li>
     *                              </ul>
     */
    public NavigationView(List<NavItem> items, NavAttributes attr) {
        super(new GridBagLayout());
        this.items = Objects.requireNonNull(items, "Items cannot be null.");
        this.attr = Objects.requireNonNull(attr, "NavAttributes cannot be null.");
        this.titleAttr = attr.titleAttributes();
        this.subtitleAttr = attr.subtitleAttributes();
        if (hasSubtitles(0)) selectedSubtitlePosition = 0;
        init();
    }

    /**
     * Sets the {@code NavItemListener} for tracking selected item.
     *
     * @param listener the listener for tracking selected item. Either use {@link NavItemListener} for tracking both
     *                 title and subtitle selection, or use {@link NavItemAdapter} for the specific
     *                 item selection, cannot be {@code null}
     * @throws NullPointerException if the provided {@code listener} is {@code null}
     * @see NavItemListener
     * @see NavItemAdapter
     */
    public void setOnNavItemListener(NavItemListener listener) {
        this.listener = Objects.requireNonNull(listener);
    }

    /**
     * Sets the selected title index.
     *
     * @param titlePosition the position of the title
     * @throws IndexOutOfBoundsException if any of the following conditions are met:
     *                                   <ul>
     *                                     <li>When the provided <code>titlePosition</code> is negative</li>
     *                                     <li>When the provided <code>titlePosition</code> equals or bigger than
     *                                     the list size</code></li>
     *                                   </ul>
     * @apiNote Used for if {@code NavigationView} contains only title. If {@code NavigationView} contains also
     * subtitles, the first subtitle (subtitlePosition = 0) of the title will be selected.
     * @see #setSelectedIndex(int, int)
     */
    public void setSelectedIndex(int titlePosition) {
        if (titlePosition < 0 || titlePosition >= items.size())
            throw new IndexOutOfBoundsException("Title position is out of bounds.");
        setTittleIsSelected(selectedTitlePosition, false);

        if (selectedSubtitlePosition > -1)
            setSubtitleIsSelected(selectedTitlePosition, selectedSubtitlePosition, false);

        String[] subtitles = items.get(titlePosition).getSubtitles();
        if (subtitles != null && subtitles.length > 0) {
            this.selectedSubtitlePosition = 0;
            setSubtitleIsSelected(titlePosition, 0, true);
        } else {
            selectedSubtitlePosition = -1;
        }

        selectedTitlePosition = titlePosition;
        setTittleIsSelected(titlePosition, true);

        revalidate();
        repaint();
    }

    /**
     * Sets the selected title and subtitle index.
     *
     * @param titlePosition    the position of the title
     * @param subtitlePosition the position of the subtitle
     * @throws IndexOutOfBoundsException if any of the following conditions are met:
     *                                   <ul>
     *                                     <li>When the provided <code>titlePosition</code> is negative</li>
     *                                     <li>When the provided <code>titlePosition</code> equals or bigger than
     *                                     the list size</code></li>
     *                                     <li>When the provided <code>subtitlePosition</code> is negative</li>
     *                                     <li>When the provided <code>subtitlePosition</code> equals or bigger than
     *                                     the subtitles length</code></li>
     *                                   </ul>
     * @throws IllegalStateException     if the title does not contain any subtitles
     * @apiNote Used for if {@code NavigationView} contains both title and subtitle.
     * @see #setSelectedIndex(int)
     */
    public void setSelectedIndex(int titlePosition, int subtitlePosition) {
        if (titlePosition < 0 || titlePosition >= items.size())
            throw new IndexOutOfBoundsException("Title position is out of bounds.");
        NavItem item = items.get(titlePosition);
        String[] subtitles = item.getSubtitles();
        if (subtitles == null) throw new IllegalStateException("This navigation item does not have any subtitles.");
        if (subtitlePosition < 0 || subtitlePosition >= item.getSubtitles().length)
            throw new IndexOutOfBoundsException("Subtitle position is out of bounds.");
        if (!subtitleIsSelected(titlePosition, subtitlePosition)) {
            if (selectedTitlePosition != titlePosition) {
                setTittleIsSelected(selectedTitlePosition, false);
                setSubtitleIsSelected(selectedTitlePosition, selectedSubtitlePosition, false);
                this.selectedTitlePosition = titlePosition;
                setTittleIsSelected(titlePosition, true);
            } else {
                setSubtitleIsSelected(selectedTitlePosition, selectedSubtitlePosition, false);
            }
            this.selectedSubtitlePosition = subtitlePosition;
            setSubtitleIsSelected(titlePosition, subtitlePosition, true);
        }
        revalidate();
        repaint();
    }

    /**
     * Sets the left arrow (hide) icon for the {@code NavigationView}.
     *
     * @param navLeft the new left arrow icon, cannot be null
     * @throws NullPointerException if the provided {@code navLeft} is {@code null}
     */
    public void setLeftNavigationIcon(Icon navLeft) {
        this.navLeft = Objects.requireNonNull(navLeft);
        if (iconLabel != null && !isCollapsed) iconLabel.setIcon(navLeft);
    }

    /**
     * Sets the right arrow (show) icon for the {@code NavigationView}.
     *
     * @param navRight the new right arrow icon, cannot be null
     * @throws NullPointerException if the provided {@code navRight} is {@code null}
     */
    public void setRightNavigationIcon(Icon navRight) {
        this.navRight = Objects.requireNonNull(navRight);
        if (iconLabel != null && isCollapsed) iconLabel.setIcon(navRight);
    }

    /**
     * Sets both navigation icons for the {@code NavigationView},
     *
     * @param navLeft  the new left arrow icon, cannot be null
     * @param navRight the new right arrow icon, cannot be null
     * @throws NullPointerException if any of the following parameters are {@code null}:
     *                              <ul>
     *                                <li>When the provided <code>navLeft</code> is <code>null</code></li>
     *                                <li>When the provided <code>navRight</code> is <code>null</code></li>
     *                              </ul>
     */
    public void setNavigationIcons(Icon navLeft, Icon navRight) {
        this.navLeft = Objects.requireNonNull(navLeft);
        this.navRight = Objects.requireNonNull(navRight);
        if (iconLabel != null) iconLabel.setIcon(isCollapsed ? navRight : navLeft);
    }

    private void init() {
        titlePanels = new ArrayList<>();
        subtitlePanels = new ArrayList<>();

        initIcons();

        createContentPanel();
        setupUI();
    }

    private void setupUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        add(iconLabel, gbc);

        gbc.insets.left = 0;
        gbc.insets.top = 5;
        gbc.gridx = 1;
        gbc.weighty = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(sp, gbc);

        setBackground(attr.background());
    }

    private void initIcons() {
        URL navLeftURL = Objects.requireNonNull(NavigationView.class.getResource("/icons/nav_left.png"));
        URL navRightURL = Objects.requireNonNull(NavigationView.class.getResource("/icons/nav_right.png"));
        URL downURL = Objects.requireNonNull(NavigationView.class.getResource("/icons/arrow_down_32.png"));
        URL upURL = Objects.requireNonNull(NavigationView.class.getResource("/icons/arrow_up_32.png"));
        navLeft = new ImageIcon(navLeftURL);
        navRight = new ImageIcon(navRightURL);
        downArrow = new ImageIcon(downURL);
        upArrow = new ImageIcon(upURL);

        iconLabel = new JLabel(navLeft);

        iconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isCollapsed = !isCollapsed;
                iconLabel.setIcon(isCollapsed ? navRight : navLeft);
                contentPanel.setVisible(!isCollapsed);
                setHasFixedWidth(!isCollapsed);
            }
        });
    }

    private void updateScrollBar() {
        ScrollAttributes scrollAttr = attr.scrollAttributes();
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
                g.setColor(scrollAttr.trackColor());
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

                int radius = scrollAttr.thumbRadius();

                if (isDragging || scrollbar.getValueIsAdjusting())
                    g2.setColor(scrollAttr.thumbDragColor());
                else
                    g2.setColor(scrollAttr.thumbColor());

                g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, radius, radius);
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
        sp.revalidate();
        sp.repaint();
    }

    private void createContentPanel() {
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 0, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        for (int i = 0; i < items.size(); i++) {
            NavItem titleItem = items.get(i);

            String text = titleItem.getTitle();
            Icon icon = titleItem.getTitleIcon();

            gbc.insets.top = titleAttr.verticalGap();
            gbc.insets.left = titleAttr.horizontalGap();
            contentPanel.add(createTitlePanel(i, text, icon), gbc);
            gbc.gridy++;

            boolean hasSubtitles = hasSubtitles(i);

            if (hasSubtitles) {
                String[] subtitles = titleItem.getSubtitles();
                Icon[] subtitleIcons = titleItem.getSubtitleIcons();
                JPanel panel = createSubtitlePanel(i, subtitles, subtitleIcons);

                gbc.insets.top = 0;
                gbc.insets.left = subtitleAttr.horizontalGap();
                contentPanel.add(panel, gbc);

                gbc.gridy++;
            }
        }

        gbc.weighty = 1;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        contentPanel.add(Box.createVerticalGlue(), gbc);

        sp = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setBorder(new EmptyBorder(1, 1, 1, 1));
        setHasFixedWidth(true);
        updateScrollBar();
    }

    private void setHasFixedWidth(boolean hasFixedWidth) {
        int baseWidth = contentPanel.getPreferredSize().width;

        if (hasFixedWidth) {
            int scrollBarWidth = sp.getVerticalScrollBar().getPreferredSize().width;
            Insets scrollInsets = sp.getInsets();
            int insetWidth = (scrollInsets != null) ? (scrollInsets.left + scrollInsets.right) : 0;

            int finalWidth = baseWidth + scrollBarWidth + insetWidth;
            sp.setMinimumSize(new Dimension(finalWidth, 0));
            sp.setPreferredSize(new Dimension(finalWidth, contentPanel.getPreferredSize().height));

            sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        } else {
            sp.setMinimumSize(new Dimension(0, 0));
            sp.setPreferredSize(new Dimension(0, 0));
            sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        }
        revalidate();
        repaint();
    }

    private void setTittleIsSelected(int position, boolean isSelected) {
        JPanel panel = titlePanels.get(position);
        JLabel label = (JLabel) panel.getComponent(0);

        Color background = isSelected ? titleAttr.selectedBackground() : titleAttr.background();
        Color foreground = isSelected ? titleAttr.selectedForeground() : titleAttr.foreground();

        if (hasSubtitles(position)) {
            JLabel iconLabel = (JLabel) panel.getComponent(1);
            Icon ic = iconLabel.getIcon();
            iconLabel.setIcon(FlexUtil.recolorIcon(ic, foreground));
        }
        panel.setBackground(background);
        label.setForeground(foreground);
    }

    private void setSubtitleIsSelected(int titlePosition, int subtitlePosition, boolean isSelected) {
        JPanel panel = subtitlePanels.get(titlePosition);
        JLabel label = (JLabel) panel.getComponent(subtitlePosition);

        Color background = isSelected ? subtitleAttr.selectedBackground() : subtitleAttr.background();
        Color foreground = isSelected ? subtitleAttr.selectedForeground() : subtitleAttr.foreground();
        label.setBackground(background);
        label.setForeground(foreground);
    }

    private JLabel createTitleView(JPanel panel, int position, String text, Icon ic) {
        JLabel label = new JLabel(text);
        if (ic != null) label.setIcon(ic);
        label.setHorizontalTextPosition(titleAttr.textPosition() == TextPosition.LEFT ? SwingConstants.LEFT :
                                                SwingConstants.RIGHT);
        label.setIconTextGap(titleAttr.iconTextGap());
        label.setForeground(selectedTitlePosition == position ? titleAttr.selectedForeground() :
                                    titleAttr.foreground());
        label.setFont(titleAttr.font());
        Padding padding = titleAttr.padding();
        label.setBorder(new EmptyBorder(padding.top(), padding.left(), padding.bottom(), padding.right()));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (selectedTitlePosition != position) {
                    setTittleIsSelected(selectedTitlePosition, false);
                    if (hasSubtitles(position)) {
                        setSubtitleIsSelected(selectedTitlePosition, selectedSubtitlePosition, false);
                        selectedSubtitlePosition = 0;
                        setSubtitleIsSelected(position, 0, true);
                    }
                    selectedTitlePosition = position;
                    setTittleIsSelected(position, true);
                    revalidate();
                    repaint();
                }
                if (listener != null) listener.onTitleSelected(position);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (selectedTitlePosition != position) {
                    panel.setBackground(titleAttr.hoverBackground());
                    label.setForeground(titleAttr.hoverForeground());
                    revalidate();
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (selectedTitlePosition != position) {
                    panel.setBackground(titleAttr.background());
                    label.setForeground(titleAttr.foreground());
                    revalidate();
                    repaint();
                }
            }
        });
        return label;
    }

    private JPanel createTitlePanel(int position, String text, Icon ic) {
        boolean isSelected = selectedTitlePosition == position;
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(isSelected ? titleAttr.selectedBackground() : titleAttr.background());
        titlePanels.add(panel);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = createTitleView(panel, position, text, ic);
        panel.add(label, gbc);
        final boolean[] isCollapsed = {false};

        if (hasSubtitles(position)) {
            Color color = selectedTitlePosition == position ? titleAttr.selectedForeground() :
                    titleAttr.foreground();
            JLabel collapseIcon = new JLabel(FlexUtil.recolorIcon(upArrow, color));
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 1;
            panel.add(collapseIcon, gbc);

            collapseIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    isCollapsed[0] = !isCollapsed[0];
                    subtitlePanels.get(position).setVisible(!isCollapsed[0]);
                    Color color = selectedTitlePosition == position ? subtitleAttr.selectedForeground() :
                            subtitleAttr.foreground();
                    Icon icon = isCollapsed[0] ? downArrow : upArrow;
                    collapseIcon.setIcon(FlexUtil.recolorIcon(icon, color));
                    revalidate();
                    repaint();
                }
            });
        }

        return panel;
    }

    private JLabel createSubtitleView(int titlePosition, int subtitlePosition, String text, Icon ic) {
        JLabel label = new JLabel();
        label.setOpaque(true);
        if (text != null) label.setText(text);
        if (ic != null) label.setIcon(ic);
        label.setHorizontalTextPosition(subtitleAttr.textPosition() == TextPosition.LEFT ? SwingConstants.LEFT :
                                                SwingConstants.RIGHT);
        label.setVerticalTextPosition(SwingConstants.CENTER);
        label.setIconTextGap(subtitleAttr.iconTextGap());
        label.setBackground(subtitleIsSelected(titlePosition, subtitlePosition) ? subtitleAttr.selectedBackground() :
                                    subtitleAttr.background());
        label.setForeground(subtitleIsSelected(titlePosition, subtitlePosition) ? subtitleAttr.selectedForeground() :
                                    subtitleAttr.foreground());
        label.setFont(subtitleAttr.font());
        Padding padding = subtitleAttr.padding();
        label.setBorder(new EmptyBorder(padding.top(), padding.left(), padding.bottom(), padding.right()));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!subtitleIsSelected(titlePosition, subtitlePosition)) {
                    setSubtitleIsSelected(selectedTitlePosition, selectedSubtitlePosition, false);
                    if (selectedTitlePosition != titlePosition) {
                        setTittleIsSelected(selectedTitlePosition, false);
                        selectedTitlePosition = titlePosition;
                        setTittleIsSelected(titlePosition, true);
                    }
                    selectedSubtitlePosition = subtitlePosition;
                    setSubtitleIsSelected(titlePosition, subtitlePosition, true);
                    revalidate();
                    repaint();
                }
                if (listener != null) listener.onSubtitleSelected(titlePosition, subtitlePosition);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!subtitleIsSelected(titlePosition, subtitlePosition)) {
                    label.setBackground(subtitleAttr.hoverBackground());
                    label.setForeground(subtitleAttr.hoverForeground());
                    revalidate();
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!subtitleIsSelected(titlePosition, subtitlePosition)) {
                    label.setBackground(subtitleAttr.background());
                    label.setForeground(subtitleAttr.foreground());
                    revalidate();
                    repaint();
                }
            }
        });
        return label;
    }

    private JPanel createSubtitlePanel(int titlePosition, String[] subtitles, Icon[] icons) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        subtitlePanels.add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(subtitleAttr.verticalGap(),0,0,0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int size = (subtitles != null) ? subtitles.length : 0;

        for (int i = 0; i < size; i++) {
            gbc.gridy = i;
            String subtitle = subtitles[i];
            Icon icon = (icons != null) ? icons[i] : null;
            JLabel label = createSubtitleView(titlePosition, i, subtitle, icon);
            panel.add(label, gbc);
        }

        return panel;
    }

    private boolean hasSubtitles(int position) {
        NavItem item = items.get(position);
        return item.getSubtitles() != null && item.getSubtitles().length > 0;

    }

    private boolean subtitleIsSelected(int titlePosition, int subtitlePosition) {
        return selectedTitlePosition == titlePosition && selectedSubtitlePosition == subtitlePosition;
    }
}