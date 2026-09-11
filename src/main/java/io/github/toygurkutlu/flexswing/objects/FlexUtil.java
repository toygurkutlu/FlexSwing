package io.github.toygurkutlu.flexswing.objects;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FlexUtil {

    /**
     * Converts a hexadecimal string representation back to a {@code Color} object.
     *
     * @param hex The hex code string of the color (e.g., "#FFFFFFFF")
     * @return The corresponding {@code Color} object
     */
    public static Color hexToColor(String hex) {
        if (hex.contains("#")) hex = hex.replace("#", "");
        if (hex.length() != 8) throw new IllegalArgumentException("Hex has to contain 8 characters (without #).");

        int r = Integer.parseInt(hex.substring(0, 2), 16);
        int g = Integer.parseInt(hex.substring(2, 4), 16);
        int b = Integer.parseInt(hex.substring(4, 6), 16);
        int a = Integer.parseInt(hex.substring(6, 8), 16);

        return new Color(r, g, b, a);
    }

    /**
     * Converts a {@code Color} object to its hexadecimal string representation.
     *
     * @param color The color to convert
     * @return The hex code string of the color (e.g., "#FFFFFFFF")
     */
    public static String colorToHex(Color color) {
        return String.format("%02X%02X%02X%02X",
                             color.getRed(),
                             color.getGreen(),
                             color.getBlue(),
                             color.getAlpha());
    }

    /**
     * Changes the color of the specified icon.
     *
     * @param icon  The icon whose color will be changed
     * @param color The new color to apply to the icon
     * @return The new {@code Icon} instance with the updated color
     */
    public static ImageIcon recolorIcon(Icon icon, Color color) {
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();

        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        icon.paintIcon(null, g2, 0, 0);
        g2.dispose();

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int rgba = img.getRGB(x, y);
                int alpha = (rgba >> 24) & 0xff;

                if (alpha > 0) {
                    img.setRGB(x, y,
                               (alpha << 24) |
                                       (color.getRed() << 16) |
                                       (color.getGreen() << 8) |
                                       color.getBlue()
                    );
                }
            }
        }

        return new ImageIcon(img);
    }

    /**
     * Calculates the percentage value of the color's real alpha value.
     *
     * @param value the alpha value of the color (0-255)
     * @return the percentage value of the color's alpha
     */
    public static int getAlphaPercentage(int value) {
        return Math.round(value / 255f * 100f);
    }

    /**
     * Calculates the color's real alpha value from its percentage value.
     *
     * @param percentage the percentage value of the color's alpha
     * @return the alpha value of the color (0-255)
     */
    public static int getAlphaValue(int percentage) {
        return Math.round(percentage / 100f * 255f);
    }

    public static Shape createRoundTopLeft(JComponent comp, int topLeft, int thickness) {
        double x = (double) thickness / 2.0;
        double y = (double) thickness / 2.0;
        double width = comp.getWidth() - (double) thickness;
        double height = comp.getHeight() - (double) thickness;

        int roundX = (int) Math.min(width, topLeft);
        int roundY = (int) Math.min(height, topLeft);

        Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(x + (double) roundX / 2, y, width - (double) roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(x, y + (double) roundY / 2, width, height - (double) roundY / 2)));
        return area;
    }

    public static Shape createRoundTopRight(JComponent comp, int topRight, int thickness) {
        double x = (double) thickness / 2.0;
        double y = (double) thickness / 2.0;
        double width = comp.getWidth() - (double) thickness;
        double height = comp.getHeight() - (double) thickness;

        int roundX = (int) Math.min(width, topRight);
        int roundY = (int) Math.min(height, topRight);

        Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(x, y, width - (double) roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(x, y + (double) roundY / 2, width, height - (double) roundY / 2)));
        return area;
    }

    public static Shape createRoundBottomLeft(JComponent comp, int bottomLeft, int thickness) {
        double x = (double) thickness / 2.0;
        double y = (double) thickness / 2.0;
        double width = comp.getWidth() - (double) thickness;
        double height = comp.getHeight() - (double) thickness;

        int roundX = (int) Math.min(width, bottomLeft);
        int roundY = (int) Math.min(height, bottomLeft);

        Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(x + (double) roundX / 2, y, width - (double) roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(x, y, width, height - (double) roundY / 2)));
        return area;
    }

    public static Shape createRoundBottomRight(JComponent comp, int bottomRight, int thickness) {
        double x = (double) thickness / 2.0;
        double y = (double) thickness / 2.0;
        double width = comp.getWidth() - (double) thickness;
        double height = comp.getHeight() - (double) thickness;

        int roundX = (int) Math.min(width, bottomRight);
        int roundY = (int) Math.min(height, bottomRight);

        Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(x, y, width - (double) roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(x, y, width, height - (double) roundY / 2)));
        return area;
    }

    public static List<Image> getColorPickerImageIcons() {
        java.util.List<Image> list = new ArrayList<>();

        ImageIcon img16 = new ImageIcon(Objects.requireNonNull(FlexUtil.class.getResource("/icons/color_picker/16.png"))),
                img24 = new ImageIcon(Objects.requireNonNull(FlexUtil.class.getResource("/icons/color_picker/24.png"))),
                img32 = new ImageIcon(Objects.requireNonNull(FlexUtil.class.getResource("/icons/color_picker/32.png"))),
                img64 = new ImageIcon(Objects.requireNonNull(FlexUtil.class.getResource("/icons/color_picker/64.png"))),
                img128 = new ImageIcon(Objects.requireNonNull(FlexUtil.class.getResource("/icons/color_picker/128.png"))),
                img256 = new ImageIcon(Objects.requireNonNull(FlexUtil.class.getResource("/icons/color_picker/256.png"))),
                img512 = new ImageIcon(Objects.requireNonNull(FlexUtil.class.getResource("/icons/color_picker/512.png")));

        list.add(img16.getImage());
        list.add(img24.getImage());
        list.add(img32.getImage());
        list.add(img64.getImage());
        list.add(img128.getImage());
        list.add(img256.getImage());
        list.add(img512.getImage());

        return list;
    }

    /**
     * Clears the global focus owner at both the Java and native levels. If there exists a focus owner,
     * that Component will receive a permanent FOCUS_LOST event. After this operation completes, the native
     * windowing system will discard all user-generated KeyEvents until the user selects a new Component to receive
     * focus, or a Component is given focus explicitly via a call to requestFocus(). This operation does not change the
     * focused or active Windows.
     * If a SecurityManager is installed, the calling thread must be granted the "replaceKeyboardFocusManager"
     * AWTPermission. If this permission is not granted, this method will throw a SecurityException, and the
     * current focus owner will not be cleared.
     * This method is intended to be used only by KeyboardFocusManager set as current KeyboardFocusManager for
     * the calling thread's context. It is not for general client use.
     * */
    public static void clearGlobalFocusOwner() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
    }

    /**
     * Gets the all available font families.
     *
     * @return the array of the available font families.
     */
    public static String[] fontFamilies() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    }
}