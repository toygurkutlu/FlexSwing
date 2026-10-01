package io.github.toygurkutlu.flexswing.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.Objects;

/**
 * Provides customizable and scrollable view of a lightweight component which extends from {@code JScrollPane}.
 * A JScrollPane manages a viewport, optional vertical and horizontal scroll bars, and optional row and
 * column heading viewports.
 *
 *
 * <p>Use the following methods to customize attributes:</p>
 * <ol>
 *     <li><strong>Track color:</strong>
 *         <ul>
 *             <li>{@link #setTrackColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Thumb color:</strong>
 *         <ul>
 *             <li>{@link #setThumbColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Thumb drag color:</strong>
 *         <ul>
 *             <li>{@link #setThumbDragColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Thumb radius:</strong>
 *         <ul>
 *              <li>{@link #setThumbRadius(int)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Corner color:</strong>
 *         <ul>
 *             <li>{@link #setCornerColor(Color)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Vertical knob height:</strong>
 *         <ul>
 *             <li>{@link #setVerticalKnobHeight(int)}</li>
 *         </ul>
 *     </li>
 *     <li><strong>Horizontal knob width:</strong>
 *         <ul>
 *             <li>{@link #setHorizontalKnobWidth(int)}</li>
 *         </ul>
 *     </li>
 * </ol>
 *
 * @see JScrollPane
 */
public class FlexScrollPane extends JScrollPane {

    private final boolean hasVerticalScrollBar;
    private final boolean hasHorizontalScrollBar;

    private Color TRACK_COLOR = new Color(25, 25, 25);
    private Color THUMB_COLOR = new Color(35, 35, 35);
    private Color THUMB_DRAG_COLOR = new Color(45, 45, 45);
    private Color CORNER_COLOR = THUMB_COLOR;
    private int THUMB_RADIUS = 10;
    private int VERTICAL_KNOB_HEIGHT = 40;
    private int HORIZONTAL_KNOB_WIDTH = 40;
    private JPanel cornerPanel;
    private JScrollBar vsb;
    private JScrollBar hsb;

    /**
     * Creates a {@code FlexScrollPane} that displays the view component in a viewport whose view position can be
     * controlled with a pair of scrollbars. The scrollbar policies specify when the scrollbars are displayed,
     * For example, if vsbPolicy is VERTICAL_SCROLLBAR_AS_NEEDED then the vertical scrollbar only appears if the
     * view doesn't fit vertically. The available policy settings are listed at setVerticalScrollBarPolicy and
     * setHorizontalScrollBarPolicy.
     *
     * @param view      the component to display in the scroll pane viewport
     * @param vsbPolicy an integer that specifies the vertical scrollbar policy (can be one of followings:
     *                  {@link #VERTICAL_SCROLLBAR_ALWAYS}, {@link #VERTICAL_SCROLLBAR_AS_NEEDED} or
     *                  {@link #VERTICAL_SCROLLBAR_NEVER})
     * @param hsbPolicy an integer that specifies the horizontal scrollbar policy (can be one of followings:
     *                  {@link #HORIZONTAL_SCROLLBAR_ALWAYS}, {@link #HORIZONTAL_SCROLLBAR_AS_NEEDED} or
     *                  {@link #HORIZONTAL_SCROLLBAR_NEVER})
     */
    public FlexScrollPane(Component view, int vsbPolicy, int hsbPolicy) {
        super(view, vsbPolicy, hsbPolicy);

        this.hasVerticalScrollBar = vsbPolicy == VERTICAL_SCROLLBAR_ALWAYS || vsbPolicy == VERTICAL_SCROLLBAR_AS_NEEDED;
        this.hasHorizontalScrollBar = hsbPolicy == HORIZONTAL_SCROLLBAR_ALWAYS || hsbPolicy == HORIZONTAL_SCROLLBAR_AS_NEEDED;

        updateScrollBar();
    }

    private BasicScrollBarUI createVerticalUI(int height) {
        return new BasicScrollBarUI() {
            final int FIXED_KNOB_HEIGHT = height;

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
                g.setColor(TRACK_COLOR);
                g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
            }

            @Override
            protected void setThumbBounds(int x, int y, int width, int height) {
                int trackHeight = scrollbar.getHeight();

                if (height > FIXED_KNOB_HEIGHT) {

                    int maxOriginalY = trackHeight - height;
                    int maxNewY = trackHeight - FIXED_KNOB_HEIGHT;

                    if (maxOriginalY > 0) {
                        double ratio = (double) y / maxOriginalY;
                        y = (int) (ratio * maxNewY);
                    }
                    height = FIXED_KNOB_HEIGHT;
                }
                if (scrollbar.getValue() + scrollbar.getModel().getExtent() >= scrollbar.getMaximum()) {
                    y = trackHeight - height;
                }

                super.setThumbBounds(x, y, width, height);
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (isDragging || scrollbar.getValueIsAdjusting())
                    g2.setColor(THUMB_DRAG_COLOR);
                else
                    g2.setColor(THUMB_COLOR);

                g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, THUMB_RADIUS, THUMB_RADIUS);
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
        };
    }

    private BasicScrollBarUI createHorizontalUI(int width) {
        return new BasicScrollBarUI() {
            final int FIXED_KNOB_WIDTH = width;

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
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                g.setColor(TRACK_COLOR);
                g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
            }

            @Override
            protected void setThumbBounds(int x, int y, int width, int height) {
                int trackWidth = scrollbar.getWidth();

                if (width > FIXED_KNOB_WIDTH) {
                    int maxOriginalX = trackWidth - width;
                    int maxNewX = trackWidth - FIXED_KNOB_WIDTH;

                    if (maxOriginalX > 0) {
                        double ratio = (double) x / maxOriginalX;
                        x = (int) (ratio * maxNewX);
                    }
                    width = FIXED_KNOB_WIDTH;
                }

                if (scrollbar.getValue() + scrollbar.getModel().getExtent() >= scrollbar.getMaximum()) {
                    x = trackWidth - width;
                }

                super.setThumbBounds(x, y, width, height);
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (isDragging || scrollbar.getValueIsAdjusting())
                    g2.setColor(THUMB_DRAG_COLOR);
                else
                    g2.setColor(THUMB_COLOR);

                g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, THUMB_RADIUS, THUMB_RADIUS);
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
        };
    }

    private void updateScrollBar() {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setOpaque(false);
        getViewport().setOpaque(false);

        if (hasVerticalScrollBar) {
            vsb = getVerticalScrollBar();
            vsb.setPreferredSize(new Dimension(10, 0));
            vsb.setOpaque(false);
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
            vsb.setUI(createVerticalUI(VERTICAL_KNOB_HEIGHT));

            setWheelScrollingEnabled(false);
            addMouseWheelListener(e -> {
                int scrollSpeedModifier = 5;
                int moveAmount = e.getWheelRotation() * scrollSpeedModifier;

                vsb.setValue(vsb.getValue() + moveAmount);
            });
        }

        if (hasHorizontalScrollBar) {
            hsb = getHorizontalScrollBar();
            hsb.setPreferredSize(new Dimension(0, 12));

            hsb.setOpaque(false);
            hsb.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    hsb.repaint();
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    hsb.repaint();
                }
            });
            hsb.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                @Override
                public void mouseMoved(java.awt.event.MouseEvent e) {
                    hsb.repaint();
                }
            });

            hsb.setUI(createHorizontalUI(HORIZONTAL_KNOB_WIDTH));
        }

        if (hasVerticalScrollBar && hasHorizontalScrollBar) {
            cornerPanel = new JPanel();
            cornerPanel.setBackground(CORNER_COLOR);
            setCorner(JScrollPane.LOWER_RIGHT_CORNER, cornerPanel);
        }

        if (hasVerticalScrollBar || hasHorizontalScrollBar) {
            revalidate();
            repaint();
        }
    }

    /**
     * Gets the color of the track.
     *
     * @return the track color
     */
    public Color getTrackColor() {
        return TRACK_COLOR;
    }

    /**
     * Sets the color of the track.
     *
     * @param trackColor the new track color, cannot be {@code null}
     * @throws NullPointerException if the provided {@code thumbDragColor} is {@code null}
     */
    public void setTrackColor(Color trackColor) {
        this.TRACK_COLOR = Objects.requireNonNull(trackColor, "TrackColor cannot be null.");
        revalidate();
        repaint();
    }

    /**
     * Gets the color of the thumb.
     *
     * @return the thumb color
     * @see #getThumbDragColor()
     */
    public Color getThumbColor() {
        return THUMB_COLOR;
    }

    /**
     * Sets the color of the thumb.
     *
     * @param thumbColor the new thumb color, cannot be {@code null}
     * @throws NullPointerException if the provided {@code thumbDragColor} is {@code null}
     * @see #setThumbDragColor(Color)
     */
    public void setThumbColor(Color thumbColor) {
        this.THUMB_COLOR = Objects.requireNonNull(thumbColor, "ThumbColor cannot be null.");
        revalidate();
        repaint();
    }

    /**
     * Gets the color of the thumb while dragging.
     *
     * @return the thumb drag color
     * @see #getThumbColor()
     */
    public Color getThumbDragColor() {
        return THUMB_DRAG_COLOR;
    }

    /**
     * Sets the color of the thumb while dragging.
     *
     * @param thumbDragColor the new thumb drag color, cannot be {@code null}
     * @throws NullPointerException if the provided {@code thumbDragColor} is {@code null}
     * @see #setThumbColor(Color)
     */
    public void setThumbDragColor(Color thumbDragColor) {
        this.THUMB_DRAG_COLOR = Objects.requireNonNull(thumbDragColor, "ThumbDragColor cannot be null.");
        revalidate();
        repaint();
    }

    /**
     * Gets the corner radius of the thumb.
     *
     * @return the corner radius of the thumb
     * @see #getVerticalKnobHeight()
     * @see #getHorizontalKnobWidth()
     */
    public int getThumbRadius() {
        return THUMB_RADIUS;
    }

    /**
     * Sets the corner radius of the thumb.
     *
     * @param thumbRadius the new corner radius, cannot be negative
     * @throws IllegalArgumentException if the provided {@code thumbRadius} is negative
     * @see #setVerticalKnobHeight(int)
     * @see #setHorizontalKnobWidth(int)
     */
    public void setThumbRadius(int thumbRadius) {
        if (thumbRadius < 0) throw new IllegalArgumentException("ThumbRadius cannot be negative.");
        this.THUMB_RADIUS = thumbRadius;
    }

    /**
     * Gets the background color of the lower-right (or trailing) corner
     * of the scroll pane.
     * <p>
     * This corner area becomes visible only when both the horizontal and
     * vertical scroll bars are actively displayed at the same time.
     * </p>
     *
     * @return the corner color of the scroll pane
     */
    public Color getCornerColor() {
        return CORNER_COLOR;
    }

    /**
     * Sets the background color of the lower-right (or trailing) corner
     * of the scroll pane.
     * <p>
     * This corner area becomes visible only when both the horizontal and
     * vertical scroll bars are actively displayed at the same time.
     * </p>
     *
     * @param cornerColor the new corner color of the scroll pane, cannot be {@code null}
     * @throws NullPointerException     if the provided {@code cornerColor} is {@code null}
     * @throws IllegalArgumentException if the policy of either scroll bar is configured as {@code NEVER}
     *                                  ({@link #VERTICAL_SCROLLBAR_NEVER} or {@link #HORIZONTAL_SCROLLBAR_NEVER})
     */
    public void setCornerColor(Color cornerColor) {
        this.CORNER_COLOR = Objects.requireNonNull(cornerColor, "CornerColor cannot be null.");
        if (!hasVerticalScrollBar || !hasHorizontalScrollBar)
            throw new IllegalArgumentException("Corner Panel is available when both scroll bars enabled. Check for the vsbPolicy and hsbPolicy.");
        cornerPanel.setBackground(cornerColor);
        revalidate();
        repaint();
    }

    /**
     * Gets the height of the vertical knob.
     *
     * @return the height of the vertical knob
     */
    public int getVerticalKnobHeight() {
        return VERTICAL_KNOB_HEIGHT;
    }

    /**
     * Sets the height of the vertical knob.
     *
     * @param verticalKnobHeight the new height of the vertical knob
     * @throws IllegalArgumentException if the VerticalScrollBar is null (if vsbPolicy configured as
     *                                  {@link #VERTICAL_SCROLLBAR_NEVER})
     */
    public void setVerticalKnobHeight(int verticalKnobHeight) {
        this.VERTICAL_KNOB_HEIGHT = verticalKnobHeight;
        if (!hasVerticalScrollBar)
            throw new IllegalArgumentException("VerticalScrollBar is null. Check for the vsbPolicy.");
        vsb.setUI(createVerticalUI(verticalKnobHeight));
        vsb.revalidate();
        vsb.repaint();
        revalidate();
        repaint();
    }

    /**
     * Gets the width of the horizontal knob.
     *
     * @return the width of the horizontal knob
     */
    public int getHorizontalKnobWidth() {
        return HORIZONTAL_KNOB_WIDTH;
    }

    /**
     * Sets the width of the horizontal knob.
     *
     * @param horizontalKnobWidth the new width of the horizontal knob
     * @throws IllegalArgumentException if the HorizontalScrollBar is null (if hsbPolicy configured as
     *                                  {@link #HORIZONTAL_SCROLLBAR_NEVER})
     */
    public void setHorizontalKnobWidth(int horizontalKnobWidth) {
        this.HORIZONTAL_KNOB_WIDTH = horizontalKnobWidth;
        if (!hasHorizontalScrollBar)
            throw new IllegalArgumentException("HorizontalScrollBar is null. Check for the hsbPolicy.");
        hsb.setUI(createHorizontalUI(horizontalKnobWidth));
        hsb.revalidate();
        hsb.repaint();
        revalidate();
        repaint();
    }
}