package com.example.prodemo1.model;

import java.util.Map;

public class FormElement {
    private String id;
    private String type; // field, text, image, divider, button, spacer
    private String elementType; // For fields: text, email, password, etc.
    
    // Position and Size
    private int x = 0;
    private int y = 0;
    private int width = 200;
    private int height = 40;
    private String unit = "px";
    private int zIndex = 1;
    private boolean draggable = true;
    private boolean resizable = true;
    
    // Basic Properties
    private String label;
    private String placeholder;
    private String value;
    private String content;
    private String helpText;
    private String tooltip;
    
    // Styling
    private String cssClass;
    private Map<String, String> styles;
    private String hoverStyle;
    private String focusStyle;
    
    // Text Properties
    private String fontFamily = "Arial, sans-serif";
    private String fontSize = "16px";
    private String fontWeight = "normal";
    private String fontStyle = "normal";
    private String textColor = "#000000";
    private String textAlign = "left";
    private String textDecoration = "none";
    private double lineHeight = 1.5;
    private int letterSpacing = 0;
    
    // Border & Background
    private String borderColor = "#dddddd";
    private int borderWidth = 1;
    private String borderStyle = "solid";
    private String borderRadius = "4px";
    private String backgroundColor = "transparent";
    private String backgroundImage;
    private String boxShadow = "none";
    
    // Effects
    private double opacity = 1.0;
    private String transform;
    private String transition;
    private String animation;
    
    // Validation
    private boolean required = false;
    private String validationPattern;
    private String validationMessage;
    private int minLength = 0;
    private int maxLength = 255;
    private boolean readOnly = false;
    private boolean disabled = false;
    
    // Layout
    private String display = "block";
    private String flexDirection;
    private String justifyContent;
    private String alignItems;
    private String padding = "10px";
    private String margin = "0";
    
    // Constructor
    public FormElement() {
        this.id = "element_" + System.currentTimeMillis() + "_" + (int)(Math.random() * 1000);
    }
    
    public FormElement(String type) {
        this();
        this.type = type;
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getElementType() { return elementType; }
    public void setElementType(String elementType) { this.elementType = elementType; }
    
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    
    public int getZIndex() { return zIndex; }
    public void setZIndex(int zIndex) { this.zIndex = zIndex; }
    
    public boolean isDraggable() { return draggable; }
    public void setDraggable(boolean draggable) { this.draggable = draggable; }
    
    public boolean isResizable() { return resizable; }
    public void setResizable(boolean resizable) { this.resizable = resizable; }
    
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    
    public String getPlaceholder() { return placeholder; }
    public void setPlaceholder(String placeholder) { this.placeholder = placeholder; }
    
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public String getHelpText() { return helpText; }
    public void setHelpText(String helpText) { this.helpText = helpText; }
    
    public String getTooltip() { return tooltip; }
    public void setTooltip(String tooltip) { this.tooltip = tooltip; }
    
    public String getCssClass() { return cssClass; }
    public void setCssClass(String cssClass) { this.cssClass = cssClass; }
    
    public Map<String, String> getStyles() { return styles; }
    public void setStyles(Map<String, String> styles) { this.styles = styles; }
    
    public String getHoverStyle() { return hoverStyle; }
    public void setHoverStyle(String hoverStyle) { this.hoverStyle = hoverStyle; }
    
    public String getFocusStyle() { return focusStyle; }
    public void setFocusStyle(String focusStyle) { this.focusStyle = focusStyle; }
    
    public String getFontFamily() { return fontFamily; }
    public void setFontFamily(String fontFamily) { this.fontFamily = fontFamily; }
    
    public String getFontSize() { return fontSize; }
    public void setFontSize(String fontSize) { this.fontSize = fontSize; }
    
    public String getFontWeight() { return fontWeight; }
    public void setFontWeight(String fontWeight) { this.fontWeight = fontWeight; }
    
    public String getFontStyle() { return fontStyle; }
    public void setFontStyle(String fontStyle) { this.fontStyle = fontStyle; }
    
    public String getTextColor() { return textColor; }
    public void setTextColor(String textColor) { this.textColor = textColor; }
    
    public String getTextAlign() { return textAlign; }
    public void setTextAlign(String textAlign) { this.textAlign = textAlign; }
    
    public String getTextDecoration() { return textDecoration; }
    public void setTextDecoration(String textDecoration) { this.textDecoration = textDecoration; }
    
    public double getLineHeight() { return lineHeight; }
    public void setLineHeight(double lineHeight) { this.lineHeight = lineHeight; }
    
    public int getLetterSpacing() { return letterSpacing; }
    public void setLetterSpacing(int letterSpacing) { this.letterSpacing = letterSpacing; }
    
    public String getBorderColor() { return borderColor; }
    public void setBorderColor(String borderColor) { this.borderColor = borderColor; }
    
    public int getBorderWidth() { return borderWidth; }
    public void setBorderWidth(int borderWidth) { this.borderWidth = borderWidth; }
    
    public String getBorderStyle() { return borderStyle; }
    public void setBorderStyle(String borderStyle) { this.borderStyle = borderStyle; }
    
    public String getBorderRadius() { return borderRadius; }
    public void setBorderRadius(String borderRadius) { this.borderRadius = borderRadius; }
    
    public String getBackgroundColor() { return backgroundColor; }
    public void setBackgroundColor(String backgroundColor) { this.backgroundColor = backgroundColor; }
    
    public String getBackgroundImage() { return backgroundImage; }
    public void setBackgroundImage(String backgroundImage) { this.backgroundImage = backgroundImage; }
    
    public String getBoxShadow() { return boxShadow; }
    public void setBoxShadow(String boxShadow) { this.boxShadow = boxShadow; }
    
    public double getOpacity() { return opacity; }
    public void setOpacity(double opacity) { this.opacity = opacity; }
    
    public String getTransform() { return transform; }
    public void setTransform(String transform) { this.transform = transform; }
    
    public String getTransition() { return transition; }
    public void setTransition(String transition) { this.transition = transition; }
    
    public String getAnimation() { return animation; }
    public void setAnimation(String animation) { this.animation = animation; }
    
    public boolean isRequired() { return required; }
    public void setRequired(boolean required) { this.required = required; }
    
    public String getValidationPattern() { return validationPattern; }
    public void setValidationPattern(String validationPattern) { this.validationPattern = validationPattern; }
    
    public String getValidationMessage() { return validationMessage; }
    public void setValidationMessage(String validationMessage) { this.validationMessage = validationMessage; }
    
    public int getMinLength() { return minLength; }
    public void setMinLength(int minLength) { this.minLength = minLength; }
    
    public int getMaxLength() { return maxLength; }
    public void setMaxLength(int maxLength) { this.maxLength = maxLength; }
    
    public boolean isReadOnly() { return readOnly; }
    public void setReadOnly(boolean readOnly) { this.readOnly = readOnly; }
    
    public boolean isDisabled() { return disabled; }
    public void setDisabled(boolean disabled) { this.disabled = disabled; }
    
    public String getDisplay() { return display; }
    public void setDisplay(String display) { this.display = display; }
    
    public String getFlexDirection() { return flexDirection; }
    public void setFlexDirection(String flexDirection) { this.flexDirection = flexDirection; }
    
    public String getJustifyContent() { return justifyContent; }
    public void setJustifyContent(String justifyContent) { this.justifyContent = justifyContent; }
    
    public String getAlignItems() { return alignItems; }
    public void setAlignItems(String alignItems) { this.alignItems = alignItems; }
    
    public String getPadding() { return padding; }
    public void setPadding(String padding) { this.padding = padding; }
    
    public String getMargin() { return margin; }
    public void setMargin(String margin) { this.margin = margin; }
    
    // Helper method to get CSS styles
    public String getStyleString() {
        StringBuilder style = new StringBuilder();
        style.append("position: absolute;");
        style.append("left: ").append(x).append("px;");
        style.append("top: ").append(y).append("px;");
        style.append("width: ").append(width).append("px;");
        style.append("height: ").append(height).append("px;");
        style.append("z-index: ").append(zIndex).append(";");
        
        style.append("font-family: ").append(fontFamily).append(";");
        style.append("font-size: ").append(fontSize).append(";");
        style.append("font-weight: ").append(fontWeight).append(";");
        style.append("font-style: ").append(fontStyle).append(";");
        style.append("color: ").append(textColor).append(";");
        style.append("text-align: ").append(textAlign).append(";");
        style.append("text-decoration: ").append(textDecoration).append(";");
        style.append("line-height: ").append(lineHeight).append(";");
        style.append("letter-spacing: ").append(letterSpacing).append("px;");
        
        style.append("border-color: ").append(borderColor).append(";");
        style.append("border-width: ").append(borderWidth).append("px;");
        style.append("border-style: ").append(borderStyle).append(";");
        style.append("border-radius: ").append(borderRadius).append(";");
        style.append("background-color: ").append(backgroundColor).append(";");
        
        if (backgroundImage != null && !backgroundImage.isEmpty()) {
            style.append("background-image: url('").append(backgroundImage).append("');");
            style.append("background-size: cover;");
            style.append("background-position: center;");
        }
        
        style.append("box-shadow: ").append(boxShadow).append(";");
        style.append("opacity: ").append(opacity).append(";");
        
        if (transform != null && !transform.isEmpty()) {
            style.append("transform: ").append(transform).append(";");
        }
        
        if (transition != null && !transition.isEmpty()) {
            style.append("transition: ").append(transition).append(";");
        }
        
        style.append("display: ").append(display).append(";");
        style.append("padding: ").append(padding).append(";");
        style.append("margin: ").append(margin).append(";");
        
        if (flexDirection != null && !flexDirection.isEmpty()) {
            style.append("flex-direction: ").append(flexDirection).append(";");
        }
        if (justifyContent != null && !justifyContent.isEmpty()) {
            style.append("justify-content: ").append(justifyContent).append(";");
        }
        if (alignItems != null && !alignItems.isEmpty()) {
            style.append("align-items: ").append(alignItems).append(";");
        }
        
        return style.toString();
    }
}