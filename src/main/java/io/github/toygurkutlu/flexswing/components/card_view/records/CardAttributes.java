package io.github.toygurkutlu.flexswing.components.card_view.records;


import io.github.toygurkutlu.flexswing.components.card_view.enums.CardSection;
import io.github.toygurkutlu.flexswing.enums.StateType;
import io.github.toygurkutlu.flexswing.components.card_view.enums.TextType;
import io.github.toygurkutlu.flexswing.components.card_view.objects.CardMechanism;
import io.github.toygurkutlu.flexswing.components.card_view.enums.MechanismType;
import io.github.toygurkutlu.flexswing.objects.StateColors;
import io.github.toygurkutlu.flexswing.objects.Padding;
import io.github.toygurkutlu.flexswing.objects.Radii;

import java.awt.*;
import java.util.Objects;

/**
 * Style and mechanism attributes for the {@code CardView}.
 * <ul>
 *     <li>Users can create custom styles or use the provided default attributes.</li>
 *     <li>When using default attributes, any specific property can be modified using the fluent {@code with...()} methods.</li>
 * </ul>
 *
 * @param hoverConfig       the configuration settings for the {@link MechanismType#HOVERABLE} mechanism
 * @param selectConfig      the configuration settings for the {@link MechanismType#SELECTABLE} mechanism
 * @param collapseConfig    the configuration settings for the {@link MechanismType#COLLAPSIBLE} mechanism
 * @param favoriteConfig    the configuration settings for the {@link MechanismType#FAVORITABLE} mechanism
 * @param favIconLocation   the visual section placement of the favorite icon within the card
 * @param radii             the corner radii configuration of the card
 * @param headerAttributes  the style attributes of the card header panel
 * @param contentAttributes the style attributes of the card content panel
 * @apiNote The {@code favIconLocation} attribute only affects the UI layout if the {@link MechanismType#FAVORITABLE} mechanism is enabled.
 * @see #defaultCardAttributes()
 * @see #withHoverConfig(CardMechanism)
 * @see #withSelectConfig(CardMechanism)
 * @see #withCollapseConfig(CardMechanism)
 * @see #withFavoriteConfig(CardMechanism)
 * @see #withFavoriteIconLocation(CardSection)
 * @see #withRadii(Radii)
 * @see #withRadii(int)
 * @see #withHeaderAttributes(HeaderAttributes)
 * @see #withContentAttributes(ContentAttributes)
 * @see #backgroundByState(CardSection, StateType)
 * @see #foregroundByState(TextType, StateType)
 * @see #fontByTextType(TextType)
 * @see #paddingByTextType(TextType)
 * @see CardMechanism
 * @see MechanismType
 * @see CardSection
 * @see StateColors
 * @see StateType
 * @see Radii
 * @see HeaderAttributes
 * @see ContentAttributes
 */
public record CardAttributes(CardMechanism hoverConfig,
                             CardMechanism selectConfig,
                             CardMechanism collapseConfig,
                             CardMechanism favoriteConfig,
                             CardSection favIconLocation,
                             Radii radii,
                             HeaderAttributes headerAttributes,
                             ContentAttributes contentAttributes) {

    /**
     * Creates the default style and mechanism attributes for the {@code CardView}.
     *
     * @return the {@code CardAttributes} of the {@code CardView}
     */
    public static CardAttributes defaultCardAttributes() {
        return new CardAttributes(new CardMechanism(MechanismType.HOVERABLE, true, false),
                                  new CardMechanism(MechanismType.SELECTABLE, true, false),
                                  new CardMechanism(MechanismType.COLLAPSIBLE, true, false),
                                  new CardMechanism(MechanismType.FAVORITABLE, true, false),
                                  CardSection.HEADER,
                                  new Radii(15, 15, 15, 15),
                                  HeaderAttributes.defaultHeaderAttributes(),
                                  ContentAttributes.defaultContentAttributes());
    }

    /**
     * Creates a copy of this {@code CardAttributes} with the specified {@link MechanismType#HOVERABLE} mechanism
     * configuration.
     *
     * @param config the new configuration for the {@link MechanismType#HOVERABLE} mechanism, cannot be {@code null}
     * @return a new {@code CardAttributes} instance with the updated {@link MechanismType#HOVERABLE} mechanism
     * configuration
     * @throws NullPointerException     if the provided {@code config} is {@code null}
     * @throws IllegalArgumentException if the config parameter {@code initialStatus} is {@code true}
     * @apiNote The configuration for the {@link MechanismType#HOVERABLE} mechanism cannot be initialized with
     * a {@code true} status. Therefore, the {@code initialStatus} argument provided to the
     * {@link CardMechanism#CardMechanism(MechanismType, boolean, boolean)} constructor must be {@code false}.
     * @see CardMechanism#CardMechanism(MechanismType, boolean, boolean)
     */
    public CardAttributes withHoverConfig(CardMechanism config) {
        return new CardAttributes(Objects.requireNonNull(config, "Config cannot be null"),
                                  this.selectConfig,
                                  this.collapseConfig,
                                  this.favoriteConfig,
                                  this.favIconLocation,
                                  this.radii,
                                  this.headerAttributes,
                                  this.contentAttributes);
    }

    /**
     * Creates a copy of this {@code CardAttributes} with the specified {@link MechanismType#SELECTABLE} mechanism
     * configuration.
     *
     * @param config the new configuration for the {@link MechanismType#SELECTABLE} mechanism, cannot be {@code null}
     * @return a new {@code CardAttributes} instance with the updated {@link MechanismType#SELECTABLE} mechanism
     * configuration
     * @throws NullPointerException if the provided {@code config} is {@code null}
     * @see CardMechanism#CardMechanism(MechanismType, boolean, boolean)
     */
    public CardAttributes withSelectConfig(CardMechanism config) {
        return new CardAttributes(this.hoverConfig,
                                  Objects.requireNonNull(config, "Config cannot be null"),
                                  this.collapseConfig,
                                  this.favoriteConfig,
                                  this.favIconLocation,
                                  this.radii,
                                  this.headerAttributes,
                                  this.contentAttributes);
    }

    /**
     * Creates a copy of this {@code CardAttributes} with the specified {@link MechanismType#COLLAPSIBLE} mechanism
     * configuration.
     *
     * @param config the new configuration for the {@link MechanismType#COLLAPSIBLE} mechanism, cannot be {@code null}
     * @return a new {@code CardAttributes} instance with the updated {@link MechanismType#COLLAPSIBLE} mechanism
     * configuration
     * @throws NullPointerException if the provided {@code config} is {@code null}
     * @see CardMechanism#CardMechanism(MechanismType, boolean, boolean)
     */
    public CardAttributes withCollapseConfig(CardMechanism config) {
        return new CardAttributes(this.hoverConfig,
                                  this.selectConfig,
                                  Objects.requireNonNull(config, "Config cannot be null"),
                                  this.favoriteConfig,
                                  this.favIconLocation,
                                  this.radii,
                                  this.headerAttributes,
                                  this.contentAttributes);
    }

    /**
     * Creates a copy of this {@code CardAttributes} with the specified {@link MechanismType#FAVORITABLE} mechanism
     * configuration.
     *
     * @param config the new configuration for the {@link MechanismType#FAVORITABLE} mechanism, cannot be {@code null}
     * @return a new {@code CardAttributes} instance with the updated {@link MechanismType#FAVORITABLE} mechanism
     * configuration
     * @throws NullPointerException if the provided {@code config} is {@code null}
     * @see CardMechanism#CardMechanism(MechanismType, boolean, boolean)
     */
    public CardAttributes withFavoriteConfig(CardMechanism config) {
        return new CardAttributes(this.hoverConfig,
                                  this.selectConfig,
                                  this.collapseConfig,
                                  Objects.requireNonNull(config, "Config cannot be null"),
                                  this.favIconLocation,
                                  this.radii,
                                  this.headerAttributes,
                                  this.contentAttributes);
    }

    /**
     * Creates a copy of this {@code CardAttributes} with the specified {@link CardSection} icon placement.
     *
     * @param section the card section where the favorite icon will be placed, cannot be {@code null}
     * @return a new {@code CardAttributes} instance with the updated favorite icon placement
     * @throws NullPointerException if the provided {@code section} is {@code null}
     */
    public CardAttributes withFavoriteIconLocation(CardSection section) {
        return new CardAttributes(this.hoverConfig,
                                  this.selectConfig,
                                  this.collapseConfig,
                                  this.favoriteConfig,
                                  Objects.requireNonNull(section, "Section cannot be null"),
                                  this.radii,
                                  this.headerAttributes,
                                  this.contentAttributes);
    }

    /**
     * Creates a copy of this {@code CardAttributes} with the specified radii.
     *
     * @param radii the new radii for the card corners, cannot be {@code null}
     * @return a new {@code CardAttributes} instance with the updated card corner radii
     * @throws NullPointerException if the provided {@code radii} is {@code null}
     * @see #withRadii(int)
     */
    public CardAttributes withRadii(Radii radii) {
        return new CardAttributes(this.hoverConfig,
                                  this.selectConfig,
                                  this.collapseConfig,
                                  this.favoriteConfig,
                                  this.favIconLocation,
                                  Objects.requireNonNull(radii, "Radii cannot be null"),
                                  this.headerAttributes,
                                  this.contentAttributes);
    }

    /**
     * Creates a copy of this {@code CardAttributes} with the specified card corner radius.
     *
     * @param radius the new radius for the card corners, cannot be {@code null}
     * @return a new {@code CardAttributes} instance with the updated card corner radii
     * @throws NullPointerException if the provided {@code radii} is {@code null}
     * @see #withRadii(Radii)
     */
    public CardAttributes withRadii(int radius) {
        if(radius < 0) throw new IllegalArgumentException("Radius cannot be negative.");
        return new CardAttributes(this.hoverConfig,
                                  this.selectConfig,
                                  this.collapseConfig,
                                  this.favoriteConfig,
                                  this.favIconLocation,
                                  new Radii(radius),
                                  this.headerAttributes,
                                  this.contentAttributes);
    }

    /**
     * Creates a copy of this {@code CardAttributes} with the specified {@code HeaderPanel} style.
     *
     * @param headerAttributes the new style for the {@code HeaderPanel}
     * @return a new {@code CardAttributes} instance with the updated header style
     * @throws NullPointerException if the provided {@code headerAttributes} is {@code null}
     * @see HeaderAttributes#HeaderAttributes(StateColors, TextAttributes)
     */
    public CardAttributes withHeaderAttributes(HeaderAttributes headerAttributes) {
        return new CardAttributes(this.hoverConfig,
                                  this.selectConfig,
                                  this.collapseConfig,
                                  this.favoriteConfig,
                                  this.favIconLocation,
                                  this.radii,
                                  Objects.requireNonNull(headerAttributes, "HeaderAttributes cannot be null"),
                                  this.contentAttributes);
    }

    /**
     * Creates a copy of this {@code CardAttributes} with the specified {@code ContentPanel} style.
     *
     * @param contentAttributes the new style for the {@code ContentPanel}, cannot be {@code null}
     * @return a new {@code CardAttributes} instance with the updated  {@code ContentPanel} style
     * @throws NullPointerException if the provided {@code contentAttributes} is {@code null}
     * @see ContentAttributes#ContentAttributes(StateColors, TextAttributes, TextAttributes, TextAttributes, TextAttributes)
     */
    public CardAttributes withContentAttributes(ContentAttributes contentAttributes) {
        return new CardAttributes(this.hoverConfig,
                                  this.selectConfig,
                                  this.collapseConfig,
                                  this.favoriteConfig,
                                  this.favIconLocation,
                                  this.radii,
                                  this.headerAttributes,
                                  Objects.requireNonNull(contentAttributes, "ContentAttributes cannot be null"));
    }

    /**
     * Gets the background color associated with the specified {@code CardSection} and {@code StateType}.
     *
     * @param cardSection the section of the card
     * @param stateType   the type of the card state, cannot be {@code null}
     * @return the background color that matches the specified {@code StateType}
     * @throws NullPointerException     if any of the following parameters are {@code null}:
     *                                  <ul>
     *                                    <li>When the provided <code>cardSection</code> is <code>null</code></li>
     *                                    <li>When the provided <code>stateType</code> object is <code>null</code></li>
     *                                  </ul>
     */
    public Color backgroundByState(CardSection cardSection, StateType stateType) {
        Objects.requireNonNull(cardSection, "CardSection cannot be null.");
        Objects.requireNonNull(stateType, "StateType cannot be null");
        if (cardSection == CardSection.CONTENT)
            return contentAttributes.backgroundByState(stateType);
        else
            return headerAttributes.backgroundByState(stateType);
    }

    /**
     * Gets the foreground color of the {@code ContentPanel} associated with the specified {@code TextType} and
     * {@code StateType}.
     *
     * @param textType  the type of the text, cannot be {@code null}
     * @param stateType the type of the card state, cannot be {@code null}
     * @return the foreground color that matches the specified {@code TextType} and {@code StateType}
     * @throws NullPointerException     if any of the following parameters are {@code null}:
     *                                  <ul>
     *                                    <li>When the provided <code>textType</code> is <code>null</code></li>
     *                                    <li>When the provided <code>stateType</code> object is <code>null</code></li>
     *                                  </ul>
     * @apiNote For header text foreground color, access the {@link TextAttributes#foregroundByState(StateType)}
     * method through the header configuration via {@link #headerAttributes()}.
     */
    public Color foregroundByState(TextType textType, StateType stateType) {
        return this.contentAttributes.foregroundByState(textType, stateType);
    }

    /**
     * Gets the text font of the {@code ContentPanel} associated with the specified {@code TextType}.
     *
     * @param textType the type of the text, cannot be {@code null}
     * @return the text font that matches the specified {@code TextType}
     * @throws NullPointerException if the provided {@code textType} is {@code null}
     * @apiNote For header text font, access the {@link TextAttributes#font()}
     * method through the header configuration via {@link #headerAttributes()}.
     */
    public Font fontByTextType(TextType textType) {
        return this.contentAttributes.fontByTextType(Objects.requireNonNull(textType, "TextType cannot be null"));
    }

    /**
     * Gets the internal gaps between texts of the {@code ContentPanel} associated with the specified {@code TextType}.
     *
     * @param textType the type of the text, cannot be {@code null}
     * @return the internal that matches the specified {@code TextType}
     * @throws NullPointerException if the provided {@code textType} is {@code null}
     * @apiNote For header text internal padding, access the {@link TextAttributes#padding()}
     * method through the header configuration via {@link #headerAttributes()}.
     */
    public Padding paddingByTextType(TextType textType) {
        return this.contentAttributes.paddingByTextType(Objects.requireNonNull(textType, "TextType cannot be null"));
    }
}