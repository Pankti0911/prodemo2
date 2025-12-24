package com.example.prodemo1.model;

public class TextElement extends FormElement {
    private String textType = "paragraph"; // heading, subheading, paragraph, label, quote
    private int headingLevel = 2; // For headings: 1, 2, 3, 4, 5, 6
    private String headingSize = "auto"; // small, medium, large, xlarge
    private boolean showIcon = false;
    private String icon;
    private String iconPosition = "left"; // left, right, top, bottom
    private boolean enableAnimation = false;
    private String animationType = "fade"; // fade, slide, bounce, zoom
    
    public TextElement() {
        super("text");
        this.setTextAlign("left");
        this.setBackgroundColor("transparent");
        this.setBorderWidth(0);
    }
    
    // Specific constructors for different text types
    public TextElement(String textType, String content) {
        this();
        this.textType = textType;
        setContent(content);
        
        switch(textType) {
            case "heading":
                setFontSize("28px");
                setFontWeight("bold");
                setTextAlign("center");
                setMargin("0 0 10px 0");
                break;
            case "subheading":
                setFontSize("20px");
                setFontWeight("600");
                setTextColor("#4a5568");
                setMargin("0 0 8px 0");
                break;
            case "paragraph":
                setFontSize("16px");
                setLineHeight(1.6);
                setTextColor("#2d3748");
                setMargin("0 0 15px 0");
                break;
            case "label":
                setFontSize("14px");
                setFontWeight("500");
                setTextColor("#4a5568");
                setMargin("0 0 5px 0");
                break;
            case "quote":
                setFontStyle("italic");
                setFontSize("18px");
                setTextColor("#718096");
                setPadding("20px");
                setBorderLeft("4px solid #4299e1");
                setBackgroundColor("#f7fafc");
                break;
        }
    }
    
    public TextElement(String content, String textType, String fontSize, String fontWeight, String textColor) {
        this();
        setContent(content);
        this.textType = textType;
        setFontSize(fontSize);
        setFontWeight(fontWeight);
        setTextColor(textColor);
    }
    
    // Getters and Setters
    public String getTextType() { return textType; }
    public void setTextType(String textType) { this.textType = textType; }
    
    public int getHeadingLevel() { return headingLevel; }
    public void setHeadingLevel(int headingLevel) { this.headingLevel = headingLevel; }
    
    public String getHeadingSize() { return headingSize; }
    public void setHeadingSize(String headingSize) { this.headingSize = headingSize; }
    
    public boolean isShowIcon() { return showIcon; }
    public void setShowIcon(boolean showIcon) { this.showIcon = showIcon; }
    
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    
    public String getIconPosition() { return iconPosition; }
    public void setIconPosition(String iconPosition) { this.iconPosition = iconPosition; }
    
    public boolean isEnableAnimation() { return enableAnimation; }
    public void setEnableAnimation(boolean enableAnimation) { this.enableAnimation = enableAnimation; }
    
    public String getAnimationType() { return animationType; }
    public void setAnimationType(String animationType) { this.animationType = animationType; }
    
    // Helper methods
    public String getHtmlTag() {
        if ("heading".equals(textType)) {
            return "h" + headingLevel;
        } else if ("subheading".equals(textType)) {
            return "h3";
        } else if ("paragraph".equals(textType)) {
            return "p";
        } else if ("label".equals(textType)) {
            return "label";
        } else if ("quote".equals(textType)) {
            return "blockquote";
        }
        return "div";
    }
    
    public String getCssClasses() {
        StringBuilder classes = new StringBuilder();
        classes.append("text-element ");
        classes.append(textType).append("-element ");
        
        if (showIcon && icon != null) {
            classes.append("has-icon icon-").append(iconPosition).append(" ");
        }
        
        if (enableAnimation) {
            classes.append("animated ").append(animationType).append("-in ");
        }
        
        if (getCssClass() != null) {
            classes.append(getCssClass());
        }
        
        return classes.toString();
    }
    
    public String generateHtml() {
        StringBuilder html = new StringBuilder();
        String tag = getHtmlTag();
        String classes = getCssClasses();
        
        html.append("<").append(tag);
        html.append(" id=\"").append(getId()).append("\"");
        html.append(" class=\"").append(classes).append("\"");
        html.append(" style=\"").append(getStyleString()).append("\"");
        
        if (getTooltip() != null && !getTooltip().isEmpty()) {
            html.append(" title=\"").append(getTooltip()).append("\"");
        }
        
        html.append(">");
        
        // Add icon if enabled
        if (showIcon && icon != null) {
            if ("left".equals(iconPosition) || "top".equals(iconPosition)) {
                html.append("<i class=\"").append(icon).append("\"></i> ");
            }
        }
        
        // Add content
        html.append(getContent() != null ? getContent() : "");
        
        // Add icon if right or bottom position
        if (showIcon && icon != null) {
            if ("right".equals(iconPosition) || "bottom".equals(iconPosition)) {
                html.append(" <i class=\"").append(icon).append("\"></i>");
            }
        }
        
        html.append("</").append(tag).append(">");
        
        return html.toString();
    }
    
    // Style helper methods
    private void setBorderLeft(String borderLeft) {
        // Parse border string and set properties
        String[] parts = borderLeft.split(" ");
        if (parts.length >= 3) {
            setBorderWidth(Integer.parseInt(parts[0].replace("px", "")));
            setBorderStyle(parts[1]);
            setBorderColor(parts[2]);
        }
    }
}