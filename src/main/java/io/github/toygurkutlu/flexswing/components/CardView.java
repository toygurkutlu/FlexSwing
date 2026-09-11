package io.github.toygurkutlu.flexswing.components;


import io.github.toygurkutlu.flexswing.components.card_view.enums.CardSection;
import io.github.toygurkutlu.flexswing.enums.StateType;
import io.github.toygurkutlu.flexswing.components.card_view.enums.TextType;
import io.github.toygurkutlu.flexswing.components.card_view.listener.CardMechanismListener;
import io.github.toygurkutlu.flexswing.components.card_view.adapter.CardMechanismAdapter;
import io.github.toygurkutlu.flexswing.components.card_view.managers.CardComponentManager;
import io.github.toygurkutlu.flexswing.components.card_view.objects.CardMechanism;
import io.github.toygurkutlu.flexswing.components.card_view.enums.MechanismType;
import io.github.toygurkutlu.flexswing.components.card_view.records.*;
import io.github.toygurkutlu.flexswing.objects.FlexUtil;
import io.github.toygurkutlu.flexswing.objects.Padding;
import io.github.toygurkutlu.flexswing.objects.Radii;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Objects;

/**
 * A customizable and generic lightweight container that consists of a header and a content panel.
 * <p>
 * The {@code CardView} can be customized by providing custom {@code CardAttributes}.
 * </p>
 * <p>
 * It can support various {@code CardMechanism} types such as {@link MechanismType#HOVERABLE},
 * {@link MechanismType#SELECTABLE}, {@link MechanismType#COLLAPSIBLE}, and {@link MechanismType#FAVORITABLE} mechanics
 * if the {@code CardAttributes} are configured properly. Additionally, each mechanism features its own listener,
 * allowing developers to track and handle mechanism state changes. Besides each mechanism
 * (except {@link MechanismType#HOVERABLE} features its own tooltip, allowing developers to show tooltip according
 * to the its status.
 * </p>
 *
 * @see CardAttributes
 * @see CardMechanism
 * @see MechanismType
 * @see #setCardMechanismListener(CardMechanismListener)
 * @see CardMechanismListener
 * @see CardMechanismAdapter
 * @see #setCollapseTooltip(String, String, Tooltip.TooltipAlignment)
 * @see #setSelectTooltip(String, String, Tooltip.TooltipAlignment)
 * @see #setFavoriteTooltip(String, String, Tooltip.TooltipAlignment)
 */
public class CardView extends JPanel {

    private JLabel titleLabel;
    private JLabel collapseLabel;
    private JLabel favoriteLabel;
    private FlexPanel headerPanel;
    private FlexPanel contentPanel;
    private FlexPanel favPanel;
    private final CardAttributes attr;
    private final HeaderAttributes hAttr;
    private final ContentAttributes cAttr;
    private Radii radii;
    private StateType stateType = StateType.MAIN;
    private final CardComponentManager manager;
    private CardMechanism collapseMechanism;
    private CardMechanism selectMechanism;
    private CardMechanism favoriteMechanism;
    private CardMechanism hoverMechanism;
    private Tooltip collapseTooltip = null;
    private Tooltip favoriteTooltip = null;
    private Tooltip selectTooltip = null;
    private String selected;
    private String notSelected;
    private String notFavorited;
    private String favorited;
    private String collapsed;
    private String expanded;
    private Icon favIcon;
    private Icon nonfavIcon;
    private Icon downIcon;
    private Icon upIcon;
    private CardMechanismListener listener;
    private String title;

    /**
     * Constructs the {@code CardView} with displayable card title and customized {@code CardAttributes}.
     *
     * @param attr the configuration object of the {@code CardView}, cannot be {@code null}
     * @param title the title of the header, cannot be {@code null}
     * @apiNote {@code CardAttributes} cannot be change after the {@code CardView} is created.
     * @throws NullPointerException     if any of the following parameters are {@code null}:
     *                                  <ul>
     *                                    <li>When the provided <code>attr</code> is <code>null</code></li>
     *                                    <li>When the provided <code>title</code> is <code>null</code></li>
     *                                  </ul>
     * @see CardAttributes
     */
    public CardView(CardAttributes attr, String title) {
        super(new GridBagLayout());
        manager = new CardComponentManager();

        this.attr = Objects.requireNonNull(attr, "CardAttributes cannot be null.");
        this.title = Objects.requireNonNull(title, "Title cannot be null.");
        hAttr = attr.headerAttributes();
        cAttr = attr.contentAttributes();

        init();
    }

    /**
     * Sets the {@code CardStateListener} for this {@code CardView}.
     *
     * @param listener the listener to handle card mechanisms. Either use {@link CardMechanismListener} for the
     *                 all card mechanisms, or use {@link CardMechanismAdapter} for the specific card mechanism,
     *                 cannot be {@code null}
     * @throws NullPointerException if the provided {@code listener} is {@code null}
     * @see CardMechanismListener
     * @see CardMechanismAdapter
     */
    public void setCardMechanismListener(CardMechanismListener listener) {
        this.listener = Objects.requireNonNull(listener);
    }

    /**
     * Gets the {@code HeaderPanel} title.
     *
     * @return the text of the {@code HeaderPanel}
     */
    public String getHeaderText() {
        return title;
    }

    /**
     * Sets the {@code HeaderPanel} title.
     *
     * @param text the text of the {@code HeaderPanel}, cannot be {@code null}
     * @throws NullPointerException if the provided {@code text} is {@code null}
     */
    public void setHeaderText(String text) {
        this.title = Objects.requireNonNull(text," Text cannot be null.");
        titleLabel.setText(text);
        this.revalidate();
    }

    /**
     * Sets the {@code StateType} and updates the {@code CardView}.
     *
     * @param stateType the new {@code CardView} state, cannot be {@code null}
     * @throws NullPointerException if the provided {@code StateType} is {@code null}
     */
    public void setState(StateType stateType) {
        this.stateType = Objects.requireNonNull(stateType, "StateType cannot be null.");
        updateHeaderState(stateType);
        updateContentsState(stateType);
    }

    /**
     * Sets the {@code CardView}'s {@code Collapse} mechanism status and updates the {@code CardView}.
     *
     * @param isCollapsed the boolean value of the new {@code Collapse} status. {@code true} for the {@code collapsed}
     *                    status, {@code false} for the {@code expanded} status
     * @throws IllegalArgumentException if the {@code CardView} is not configured as {@code Collapsible}
     */
    public void setCollapsed(boolean isCollapsed) {
        if (!collapseMechanism.isEnabled()) throw new IllegalArgumentException("CardView is not Collapsible");
        collapseMechanism.setStatus(isCollapsed);

        Radii newRadii = isCollapsed ? attr.radii() :
                new Radii(radii.topLeft(), radii.topRight(), 0, 0);
        headerPanel.setRadii(newRadii);
        contentPanel.setVisible(!isCollapsed);
        updateArrowState(isCollapsed, stateType);
        if (favoriteMechanism.isEnabled() && attr.favIconLocation() == CardSection.CONTENT)
            favPanel.setVisible(!isCollapsed);
        this.revalidate();
        this.repaint();

    }

    /**
     * Sets the {@code CardView}'s {@code Favorite} mechanism status and updates the {@code favorite icon}.
     *
     * @param isFavorited the boolean value of the new {@code Favorite} status. {@code true} for the {@code favorited} status,
     *                    {@code false} for the {@code notFavorited} status
     * @throws IllegalArgumentException if the {@code CardView} is not configured as {@code Favoritable}
     */
    public void setFavorited(boolean isFavorited) {
        if (!favoriteMechanism.isEnabled()) throw new IllegalArgumentException("CardView is not Favoritable");
        favoriteMechanism.setStatus(isFavorited);
        favoriteLabel.setIcon(isFavorited ? favIcon : nonfavIcon);
    }

    /**
     * Sets the {@code CardView}'s {@code Select} mechanism status and updates the {@code CardView}.
     *
     * @param isSelected the boolean value of the new {@code Select} status. {@code true} for the {@code selected}
     *                   status, {@code false} for the {@code notSelected} status
     * @throws IllegalArgumentException if the {@code CardView} is not configured as {@code Selectable}
     */
    public void setSelected(boolean isSelected) {
        if (!selectMechanism.isEnabled()) throw new IllegalArgumentException("CardView is not Selectable.");
        selectMechanism.setStatus(isSelected);
        updateHeaderState(isSelected ? StateType.SELECTED : StateType.MAIN);
        updateContentsState(isSelected ? StateType.SELECTED : StateType.MAIN);
    }

    /**
     * Sets a tooltip for the {@code Collapse} mechanism.
     *
     * @param expanded  the tooltip text of the {@code CardView}'s {@code expanded} status, cannot be {@code null}
     * @param collapsed the tooltip text of the {@code CardView}'s {@code collapsed} status, cannot be {@code null}
     * @param alignment the location of the tooltip according to the {@code Collapse} icon,
     *                  default value is {@link Tooltip.TooltipAlignment#BOTTOM_RIGHT_TO_LEFT}
     * @throws IllegalArgumentException if {@code CardView} is not configured as {@code Collapsible}
     * @throws NullPointerException     if any of the following parameters are {@code null}:
     *                                  <ul>
     *                                    <li>When the provided <code>expanded</code> text is <code>null</code></li>
     *                                    <li>When the provided <code>collapsed</code> text is <code>null</code></li>
     *                                  </ul>
     * @see #updateCollapseTooltip(Tooltip.TooltipAttribute, Object)
     */
    public void setCollapseTooltip(String expanded, String collapsed, Tooltip.TooltipAlignment alignment) {
        if (!collapseMechanism.isEnabled())
            throw new IllegalArgumentException("CardView is not Collapsible.");

        this.expanded = Objects.requireNonNull(expanded, "The expanded text cannot be null.");
        this.collapsed = Objects.requireNonNull(collapsed, "The collapsed text cannot be null.");
        if (alignment == null) alignment = Tooltip.TooltipAlignment.BOTTOM_RIGHT_TO_LEFT;
        collapseTooltip = new Tooltip(collapseLabel, collapseMechanism.isStatus() ? collapsed : expanded);
        collapseTooltip.setTooltipAlignment(alignment);
    }

    /**
     * Updates a specific attribute of the tooltip for the {@code Collapse} mechanism.
     *
     * @param tooltipAttribute the attribute to be updated, cannot be {@code null}
     * @param value            the new value for the attribute, cannot be {@code null}
     * @throws IllegalArgumentException if any of the following validation conditions fail:
     *                                  <ul>
     *                                    <li>When the CardView is not configured as <code>Collapsible</code></li>
     *                                    <li>When the select tooltip has not been initialized yet</li>
     *                                  </ul>
     * @throws NullPointerException     if any of the following parameters are null:
     *                                  <ul>
     *                                    <li>When the provided <code>tooltipAttribute</code> is <code>null</code></li>
     *                                    <li>When the provided <code>value</code> object is <code>null</code></li>
     *                                  </ul>
     * @see #setCollapseTooltip(String, String, Tooltip.TooltipAlignment)
     */
    public void updateCollapseTooltip(Tooltip.TooltipAttribute tooltipAttribute, Object value) {
        if (!collapseMechanism.isEnabled())
            throw new IllegalArgumentException("CardView is not Collapsible.");

        if (collapseTooltip == null)
            throw new IllegalArgumentException("Tooltip is not set for Collapse mechanism. Use setCollapseTooltip(...) before updating.");

        collapseTooltip.updateTooltip(Objects.requireNonNull(tooltipAttribute, "TooltipAttribute cannot be null."),
                                      Objects.requireNonNull(value, "Value cannot be null."));
    }

    /**
     * Sets a tooltip for the {@code Favorite} mechanism.
     *
     * @param notFavorited the tooltip text of the {@code CardView}'s {@code notFavorited} status, cannot be
     *                     {@code null}
     * @param favorited    the tooltip text of the {@code CardView}'s {@code favorited} status, cannot be
     *                     {@code null}
     * @param alignment    the location of the tooltip according to the {@code Favorite} icon,
     *                     default value is {@link Tooltip.TooltipAlignment#BOTTOM_RIGHT_TO_LEFT}
     * @throws IllegalArgumentException if {@code CardView} is not configured as {@code Favoritable}
     * @throws NullPointerException     if any of the following parameters are {@code null}:
     *                                  <ul>
     *                                    <li>When the provided <code>notFavorited</code> text is <code>null</code></li>
     *                                    <li>When the provided <code>favorited</code> text is <code>null</code></li>
     *                                  </ul>
     * @see #updateFavoriteTooltip(Tooltip.TooltipAttribute, Object)
     */
    public void setFavoriteTooltip(String notFavorited, String favorited, Tooltip.TooltipAlignment alignment) {
        if (!favoriteMechanism.isEnabled())
            throw new IllegalArgumentException("CardView is not Favoritable.");

        this.notFavorited = Objects.requireNonNull(notFavorited, "The notFavorited text cannot be null.");
        this.favorited = Objects.requireNonNull(favorited, "The favorited Favorite text cannot be null.");
        if (alignment == null) alignment = Tooltip.TooltipAlignment.BOTTOM_RIGHT_TO_LEFT;
        favoriteTooltip = new Tooltip(favoriteLabel, favoriteMechanism.isStatus() ? favorited : notFavorited);
        favoriteTooltip.setTooltipAlignment(alignment);
    }

    /**
     * Updates a specific attribute of the tooltip for the Favorite mechanism.
     *
     * @param tooltipAttribute the attribute to be updated, cannot be {@code null}
     * @param value            the new value for the attribute, cannot be {@code null}
     * @throws IllegalArgumentException if any of the following validation conditions fail:
     *                                  <ul>
     *                                    <li>When the CardView is not configured as <code>Favoritable</code></li>
     *                                    <li>When the select tooltip has not been initialized yet</li>
     *                                  </ul>
     * @throws NullPointerException     if any of the following parameters are null:
     *                                  <ul>
     *                                    <li>When the provided <code>tooltipAttribute</code> is <code>null</code></li>
     *                                    <li>When the provided <code>value</code> object is <code>null</code></li>
     *                                  </ul>
     * @see #setFavoriteTooltip(String, String, Tooltip.TooltipAlignment)
     */
    public void updateFavoriteTooltip(Tooltip.TooltipAttribute tooltipAttribute, Object value) {
        if (!favoriteMechanism.isEnabled())
            throw new IllegalArgumentException("CardView is not Favoritable.");

        if (favoriteTooltip == null)
            throw new IllegalArgumentException("Tooltip is not set for Favorite mechanism. Use setFavoriteTooltip(...) before updating.");

        favoriteTooltip.updateTooltip(Objects.requireNonNull(tooltipAttribute, "TooltipAttribute cannot be null."),
                                      Objects.requireNonNull(value, "Value cannot be null."));
    }

    /**
     * Sets a tooltip for the {@code Select} mechanism.
     *
     * @param notSelected the tooltip text of the {@code CardView}'s {@code notSelected} status, cannot be
     *                    {@code null}
     * @param selected    the tooltip text of the {@code CardView}'s {@code selected} status, cannot be
     *                    {@code null}
     * @param alignment   the location of the tooltip according to the {@code CardView},
     *                    default value is {@link Tooltip.TooltipAlignment#BOTTOM_RIGHT_TO_LEFT}
     * @throws IllegalArgumentException if {@code CardView} is not configured as {@code Selectable}
     * @throws NullPointerException     if any of the following parameters are null:
     *                                  <ul>
     *                                    <li>When the provided <code>notSelected</code> text is <code>null</code></li>
     *                                    <li>When the provided <code>selected</code> text is <code>null</code></li>
     *                                  </ul>
     * @see #updateSelectTooltip(Tooltip.TooltipAttribute, Object)
     */
    public void setSelectTooltip(String notSelected, String selected, Tooltip.TooltipAlignment alignment) {
        if (!selectMechanism.isEnabled())
            throw new IllegalArgumentException("CardView is not Selectable.");

        this.notSelected = Objects.requireNonNull(notSelected, "The notSelected text cannot be null.");
        this.selected = Objects.requireNonNull(selected, "The selected text cannot be null.");
        if (alignment == null) alignment = Tooltip.TooltipAlignment.BOTTOM_RIGHT_TO_LEFT;
        selectTooltip = new Tooltip(this, selectMechanism.isStatus() ? selected : notSelected);
        selectTooltip.setTooltipAlignment(alignment);
    }

    /**
     * Updates a specific attribute of the tooltip for the Selected mechanism.
     *
     * @param tooltipAttribute the attribute to be updated, cannot be {@code null}
     * @param value            the new value for the attribute, cannot be {@code null}
     * @throws IllegalArgumentException if any of the following validation conditions fail:
     *                                  <ul>
     *                                    <li>When the CardView is not configured as <code>Selectable</code></li>
     *                                    <li>When the select tooltip has not been initialized yet</li>
     *                                  </ul>
     * @throws NullPointerException     if any of the following parameters are null:
     *                                  <ul>
     *                                    <li>When the provided <code>tooltipAttribute</code> is <code>null</code></li>
     *                                    <li>When the provided <code>value</code> object is <code>null</code></li>
     *                                  </ul>
     * @see #setSelectTooltip(String, String, Tooltip.TooltipAlignment)
     */
    public void updateSelectTooltip(Tooltip.TooltipAttribute tooltipAttribute, Object value) {
        if (!selectMechanism.isEnabled())
            throw new IllegalArgumentException("CardView is not Selectable.");

        if (selectTooltip == null)
            throw new IllegalArgumentException("Tooltip is not set for Select mechanism. Use setSelectTooltip(...) before updating.");

        selectTooltip.updateTooltip(Objects.requireNonNull(tooltipAttribute, "TooltipAttribute cannot be null."),
                                    Objects.requireNonNull(value, "Value cannot be null."));
    }

    /**
     * Adds the provided {@code component} to the {@code ContentPanel}.
     *
     * @param component        the component to be added, cannot be {@code null}
     * @param textType         the type of the text, cannot be {@code null}
     * @param gbc              the {@link GridBagConstraints} object, cannot be {@code null}
     * @param allowSelfPadding {@code true} to apply the internal padding specified in the component's
     *                         {@link TextAttributes#padding()}; {@code false} to bypass the style's padding
     *                         and use the custom {@link GridBagConstraints#insets} defined by the user
     * @throws NullPointerException     if any of the following parameters are null:
     *                                  <ul>
     *                                    <li>When the provided <code>component</code> is <code>null</code></li>
     *                                    <li>When the provided <code>textType</code> object is <code>null</code></li>
     *                                    <li>When the provided <code>gbc</code> object is <code>null</code></li>
     *                                  </ul>
     * @throws IllegalArgumentException if the {@code CardView} already contains the provided {@code component}
     * @apiNote This method is the recommended way to add new components to the {@code ContentPanel}.
     */
    public void addContent(JComponent component, TextType textType, GridBagConstraints gbc, boolean allowSelfPadding) {
        Objects.requireNonNull(component, "Component cannot be null.");
        Objects.requireNonNull(textType, "TextType cannot be null.");
        Objects.requireNonNull(gbc, "GridBagConstraints cannot be null.");

        component.setForeground(cAttr.foregroundByState(textType, stateType));
        component.setFont(cAttr.fontByTextType(textType));

        if (allowSelfPadding) {
            Padding padding = cAttr.paddingByTextType(textType);
            gbc.insets = new Insets(padding.top(), padding.left(), padding.bottom(), padding.right());
        }
        manager.addComponent(component, textType);
        contentPanel.add(component, gbc);
        this.revalidate();
        this.repaint();
    }

    /**
     * Adds the provided {@code component} to the {@code ContentPanel}.
     *
     * @param component the component to be added, cannot be {@code null}
     * @param textType  the type of the text, cannot be {@code null}
     * @throws NullPointerException     if any of the following parameters are {@code null}:
     *                                  <ul>
     *                                    <li>When the provided <code>JComponent</code> is <code>null</code></li>
     *                                    <li>When the provided <code>TextType</code> object is <code>null</code></li>
     *                                  </ul>
     * @throws IllegalArgumentException if the {@code CardView} already contains the provided {@code component}
     * @apiNote This method provides an alternative way to add components when you want to manage the layout manually.
     * Since the default layout is {@link GridBagLayout}, you can retrieve the panel via {@link #getContentPanel()}
     * and set a custom {@link LayoutManager}. However, you should still use this method to add components
     * so that the {@code CardView} can automatically manage and apply style and state changes. Using the standard
     * {@code add()} method of {@link JPanel} directly will bypass the card state management system.
     * @see #addContent(JComponent, TextType, GridBagConstraints, boolean)
     */
    public void addContent(JComponent component, TextType textType) {
        Objects.requireNonNull(component, "Component cannot be null.");
        Objects.requireNonNull(textType, "TextType cannot be null.");

        component.setForeground(cAttr.foregroundByState(textType, stateType));
        component.setFont(cAttr.fontByTextType(textType));

        manager.addComponent(component, textType);
        contentPanel.add(component);
        this.revalidate();
        this.repaint();
    }

    /**
     * Removes the component from the {@code ContentPanel}.
     *
     * @param component the component to be removed, cannot be {@code null}
     * @throws NullPointerException     if the provided {@code component} is {@code null}
     * @throws IllegalArgumentException if the {@code CardView} does not contain this {@code component}
     * @apiNote This method is the recommended way to remove a component from the {@code ContentPanel}.
     */
    public void removeContent(JComponent component) {
        Objects.requireNonNull(component, "Component cannot be null.");

        manager.removeComponent(component);
        contentPanel.remove(component);
        this.revalidate();
        this.repaint();
    }

    /**
     * Removes the component from the {@code ContentPanel}.
     *
     * @param position the position of the component
     * @throws IndexOutOfBoundsException if the position is not valid
     * @apiNote This method is the recommended way to remove a component from the {@code ContentPanel}.
     */
    public void removeContent(int position) {
        manager.removeComponent(position);
        contentPanel.remove(position);
        this.revalidate();
        this.repaint();
    }

    /**
     * Removes the all components from the {@code ContentPanel}.
     *
     * @apiNote This method is the recommended way to remove all components from the {@code ContentPanel}.
     */
    public void removeAllContents() {
        manager.clearComponents();
        contentPanel.removeAll();
        this.revalidate();
        this.repaint();
    }

    /**
     * Gets the counts of the components of the {@code ContentPanel}.
     *
     * @return the counts of the components
     */
    public int getContentCounts() {
        return contentPanel.getComponentCount();
    }

    /**
     * Gets the all components of the {@code ContentPanel}.
     *
     * @return an array of the {@link Component}
     */
    public Component[] getContents() {
        return contentPanel.getComponents();
    }

    /**
     * Gets the {@code TextType} of the provided component.
     *
     * @param component the owner component of the {@code TextType}, cannot be null
     * @return the {@code TextType} object for the provided component
     * @throws NullPointerException     if the provided {@code component} is {@code null}
     * @throws IllegalArgumentException if the {@code CardView} does not contain this {@code component}
     */
    public TextType getComponentTextType(JComponent component) {
        return manager.getTextType(Objects.requireNonNull(component, "Component cannot be null."));
    }

    /**
     * Gets the {@code TextType} of the provided component's position.
     *
     * @param position the position of the component
     * @return the {@code TextType} object for the provided component
     * @throws IndexOutOfBoundsException if the position is not valid
     */
    public TextType getComponentTextType(int position) {
        return manager.getTextType(position);
    }

    /**
     * Gets the {@code HeaderPanel}
     *
     * @return the {@code panel} of the header
     */
    public FlexPanel getHeaderPanel() {
        return headerPanel;
    }

    /**
     * Gets the {@code ContentPanel}
     *
     * @return the {@code panel} of the contents
     */
    public FlexPanel getContentPanel() {
        return contentPanel;
    }

    private void init() {
        radii = attr.radii();
        hoverMechanism = attr.hoverConfig();
        collapseMechanism = attr.collapseConfig();
        selectMechanism = attr.selectConfig();
        favoriteMechanism = attr.favoriteConfig();

        int size = 32;

        if (favoriteMechanism.isEnabled()) {
            URL favURL = Objects.requireNonNull(CardView.class.getResource("/icons/favorite_" + size + ".png"));
            URL nonfavURL = Objects.requireNonNull(CardView.class.getResource("/icons/nonfavorite_" + size + ".png"));
            favIcon = new ImageIcon(favURL);
            nonfavIcon = new ImageIcon(nonfavURL);
            favoriteLabel = new JLabel(nonfavIcon);
            favoriteLabel.addMouseListener(favoriteAdapter);
        }

        if (collapseMechanism.isEnabled()) {
            Color headerForeground = hAttr.textAttributes().foregroundByState(stateType);

            URL downURL = Objects.requireNonNull(CardView.class.getResource("/icons/arrow_down_" + size + ".png"));
            URL upURL = Objects.requireNonNull(CardView.class.getResource("/icons/arrow_up_" + size + ".png"));
            downIcon = FlexUtil.recolorIcon(new ImageIcon(downURL), headerForeground);
            upIcon = FlexUtil.recolorIcon(new ImageIcon(upURL), headerForeground);
            collapseLabel = new JLabel();
            updateArrowState(collapseMechanism.isStatus(), stateType);
            collapseLabel.addMouseListener(collapseAdapter);
        }

        setupParent();

        if (selectMechanism.isEnabled()) addMouseListener(selectAdapter);

        if (hoverMechanism.isEnabled()) addMouseListener(hoverAdapter);
    }

    private final MouseAdapter collapseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            boolean status = !collapseMechanism.isStatus();
            setCollapsed(status);
            if (listener != null) listener.onCollapseStateChanged(status);
            if (collapseTooltip != null) collapseTooltip.setTooltipText(status ? collapsed : expanded);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            updateForHover(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            updateForHover(false);
        }
    };

    private final MouseAdapter favoriteAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            boolean status = !favoriteMechanism.isStatus();
            setFavorited(status);
            if (listener != null) listener.onFavoriteStateChanged(status);
            if (favoriteTooltip != null) favoriteTooltip.setTooltipText(status ? favorited : notFavorited);
        }
    };

    private final MouseAdapter selectAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            boolean status = !selectMechanism.isStatus();
            setSelected(status);
            if (listener != null) listener.onFavoriteStateChanged(status);
            if (selectTooltip != null) selectTooltip.setTooltipText(status ? selected : notSelected);
        }
    };

    private final MouseAdapter hoverAdapter = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            updateForHover(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            updateForHover(false);
        }
    };

    private void updateForHover(boolean mouseEntered) {
        if (hoverMechanism.isEnabled()) {
            boolean status = selectMechanism.isEnabled() && selectMechanism.isStatus();
            StateType stateType;
            if (mouseEntered)
                stateType = status ? StateType.SELECTED_HOVER : StateType.MAIN_HOVER;
            else
                stateType = status ? StateType.SELECTED : StateType.MAIN;
            setState(stateType);
        }

    }

    private void createHeaderPanel() {
        TextAttributes tAttr = hAttr.textAttributes();
        Padding padding = tAttr.padding();

        titleLabel = new JLabel(title);
        titleLabel.setForeground(tAttr.foregroundByState(stateType));
        titleLabel.setFont(tAttr.font());

        headerPanel = new FlexPanel(new GridBagLayout());
        headerPanel.setHasBorder(false);
        headerPanel.setPadding(0);
        headerPanel.setBackgroundColor(hAttr.backgroundByState(stateType));
        headerPanel.setBorderThickness(0);
        headerPanel.setRadii(new Radii(radii.topLeft(), radii.topRight(), 0, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(padding.top(), padding.left(), padding.bottom(), padding.right());
        gbc.gridx = 0;
        gbc.gridy = 0;

        if (favoriteMechanism.isEnabled() && attr.favIconLocation() == CardSection.HEADER) {
            gbc.insets.left = 5;
            gbc.insets.right = 0;
            headerPanel.add(favoriteLabel, gbc);
            gbc.gridx++;
        }

        gbc.insets.right = padding.right();
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        headerPanel.add(titleLabel, gbc);

        if (collapseMechanism.isEnabled()) {
            gbc.gridx++;
            gbc.weightx = 0;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.EAST;
            gbc.insets.right = 0;
            headerPanel.add(collapseLabel, gbc);
        }
    }

    private void createContentPanel() {
        contentPanel = new FlexPanel(new GridBagLayout());
        contentPanel.setHasBorder(false);
        contentPanel.setPadding(0);
        contentPanel.setBackgroundColor(cAttr.backgroundByState(stateType));
        contentPanel.setRadii(getContentRadii());
        contentPanel.setBorderThickness(0);
    }

    private void createFavPanel() {
        favPanel = new FlexPanel(new GridBagLayout());
        favPanel.setBackgroundColor(cAttr.backgroundByState(stateType));
        favPanel.setHasBorder(false);
        favPanel.setBorderThickness(0);
        Radii favRadii = new Radii(0, 0, radii.bottomLeft(), 0);
        favPanel.setRadii(favRadii);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 0, 0, 0);
        gbc.weighty = 1;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;

        favPanel.add(favoriteLabel, gbc);
    }

    private Radii getContentRadii() {
        if (favoriteMechanism.isEnabled() && attr.favIconLocation() == CardSection.CONTENT)
            return new Radii(0, 0, 0, radii.bottomRight());
        else
            return new Radii(0, 0, radii.bottomLeft(), radii.bottomRight());
    }

    private void setupParent() {
        createHeaderPanel();
        createContentPanel();

        setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        add(headerPanel, gbc);
        gbc.gridy++;

        gbc.weighty = 1;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.VERTICAL;

        if (favoriteMechanism.isEnabled() && attr.favIconLocation() == CardSection.CONTENT) {
            createFavPanel();
            gbc.gridwidth = 1;
            gbc.weightx = 0;
            gbc.anchor = GridBagConstraints.WEST;
            add(favPanel, gbc);
            gbc.gridx++;
        }

        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(contentPanel, gbc);
    }

    private void updateHeaderState(StateType stateType) {
        titleLabel.setForeground(hAttr.textAttributes().foregroundByState(stateType));
        headerPanel.setBackgroundColor(hAttr.backgroundByState(stateType));
        updateArrowState(collapseMechanism.isStatus(), stateType);
    }

    private void updateContentsState(StateType stateType) {
        Color background = cAttr.backgroundByState(stateType);
        contentPanel.setBackgroundColor(background);
        for (ComponentBinder cc : manager.getCardComponents()) {
            JComponent comp = cc.component();
            TextType textType = cc.textType();

            comp.setForeground(cAttr.foregroundByState(textType, stateType));
        }
        if (favoriteMechanism.isEnabled() && attr.favIconLocation() == CardSection.CONTENT)
            favPanel.setBackgroundColor(background);
    }

    private void updateArrowState(boolean isCollapsed, StateType stateType) {
        if (collapseMechanism.isEnabled()) {
            Color color = hAttr.textAttributes().foregroundByState(stateType);
            Icon ic = isCollapsed ? downIcon : upIcon;
            collapseLabel.setIcon(FlexUtil.recolorIcon(ic, color));
        }
    }
}