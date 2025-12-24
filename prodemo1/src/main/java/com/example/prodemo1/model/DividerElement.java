package com.example.prodemo1.model;

public class DividerElement extends FormElement {
    private String dividerStyle = "solid"; // solid, dashed, dotted, double, groove, ridge, inset, outset
    private String dividerColor = "#e2e8f0";
    private int dividerThickness = 1;
    private boolean showText = false;
    private String dividerText = "OR";
    private String textPosition = "center"; // left, center, right
    private String textColor = "#718096";
    private String textBackground = "#ffffff";
    private boolean shadow = false;
    private boolean gradient = false;
    private String gradientColors = "#667eea, #764ba2"; // comma separated colors
    
    public DividerElement() {
        super("divider");
        setHeight(20);
        setBackgroundColor("transparent");
        setBorderWidth(0);
    }
    
    public DividerElement(String style, int thickness, String color) {
        this();
        this.dividerStyle = style;
        this.dividerThickness = thickness;
        this.dividerColor = color;
    }
    
    // Getters and Setters
    public String getDividerStyle() { return dividerStyle; }
    public void setDividerStyle(String dividerStyle) { this.dividerStyle = dividerStyle; }
    
    public String getDividerColor() { return dividerColor; }
    public void setDividerColor(String dividerColor) { this.dividerColor = dividerColor; }
    
    public int getDividerThickness() { return dividerThickness; }
    public void setDividerThickness(int dividerThickness) { this.dividerThickness = dividerThickness; }
    
    public boolean isShowText() { return showText; }
    public void setShowText(boolean showText) { this.showText = showText; }
    
    public String getDividerText() { return dividerText; }
    public void setDividerText(String dividerText) { this.dividerText = dividerText; }
    
    public String getTextPosition() { return textPosition; }
    public void setTextPosition(String textPosition) { this.textPosition = textPosition; }
    
    public String getTextColor() { return textColor; }
    public void setTextColor(String textColor) { this.textColor = textColor; }
    
    public String getTextBackground() { return textBackground; }
    public void setTextBackground(String textBackground) { this.textBackground = textBackground; }
    
    public boolean isShadow() { return shadow; }
    public void setShadow(boolean shadow) { this.shadow = shadow; }
    
    public boolean isGradient() { return gradient; }
    public void setGradient(boolean gradient) { this.gradient = gradient; }
    
    public String getGradientColors() { return gradientColors; }
    public void setGradientColors(String gradientColors) { this.gradientColors = gradientColors; }
    
    // Helper methods
    public String getCssClasses() {
        StringBuilder classes = new StringBuilder();
        classes.append("divider-element ");
        
        if (showText) {
            classes.append("divider-with-text ");
            classes.append("text-").append(textPosition).append(" ");
        }
        
        if (shadow) {
            classes.append("divider-shadow ");
        }
        
        if (gradient) {
            classes.append("divider-gradient ");
        }
        
        if (getCssClass() != null) {
            classes.append(getCssClass());
        }
        
        return classes.toString();
    }
    
    public String generateHtml() {
        StringBuilder html = new StringBuilder();
        String classes = getCssClasses();
        
        html.append("<div id=\"").append(getId()).append("\"");
        html.append(" class=\"").append(classes).append("\"");
        html.append(" style=\"").append(getDividerStyleString()).append("\"");
        html.append(">");
        
        if (showText) {
            html.append("<span class=\"divider-text\" style=\"");
            html.append("color: ").append(textColor).append("; ");
            html.append("background-color: ").append(textBackground).append("; ");
            html.append("padding: 0 15px; ");
            html.append("font-size: 14px; ");
            html.append("font-weight: 500; ");
            html.append("position: relative; ");
            html.append("z-index: 1;");
            html.append("\">");
            html.append(dividerText);
            html.append("</span>");
        }
        
        html.append("</div>");
        
        return html.toString();
    }
    
    public String getDividerStyleString() {
        StringBuilder style = new StringBuilder();
        
        // Position and size
        style.append("position: absolute;");
        style.append("left: ").append(getX()).append("px;");
        style.append("top: ").append(getY()).append("px;");
        style.append("width: ").append(getWidth()).append("px;");
        style.append("height: ").append(getHeight()).append("px;");
        style.append("z-index: ").append(getZIndex()).append(";");
        
        // Divider styling
        if (showText) {
            style.append("display: flex;");
            style.append("align-items: center;");
            style.append("justify-content: ").append(getJustifyContentFromPosition()).append(";");
            style.append("border: none;");
            style.append("position: relative;");
            
            // Create divider line with text in middle
            style.append("&::before {");
            style.append("content: '';");
            style.append("position: absolute;");
            style.append("top: 50%;");
            style.append("left: 0;");
            style.append("right: 0;");
            style.append("height: ").append(dividerThickness).append("px;");
            
            if (gradient) {
                style.append("background: linear-gradient(90deg, ").append(gradientColors).append(");");
            } else {
                style.append("background-color: ").append(dividerColor).append(";");
            }
            
            style.append("border: none;");
            
            if (shadow) {
                style.append("box-shadow: 0 2px 5px rgba(0,0,0,0.1);");
            }
            
            style.append("}");
        } else {
            // Simple divider without text
            style.append("border-top: ").append(dividerThickness).append("px ");
            style.append(dividerStyle).append(" ");
            style.append(dividerColor).append(";");
            style.append("margin: ").append(getPadding()).append(" 0;");
            
            if (gradient) {
                style.append("background: linear-gradient(90deg, ").append(gradientColors).append(");");
                style.append("border: none;");
                style.append("height: ").append(dividerThickness).append("px;");
            }
            
            if (shadow) {
                style.append("box-shadow: 0 2px 5px rgba(0,0,0,0.1);");
            }
        }
        
        // Additional styles from parent
        style.append(getAdditionalStyles());
        
        return style.toString();
    }
    
    private String getJustifyContentFromPosition() {
        switch(textPosition) {
            case "left": return "flex-start";
            case "right": return "flex-end";
            default: return "center";
        }
    }
    
    private String getAdditionalStyles() {
        StringBuilder style = new StringBuilder();
        
        if (getBackgroundColor() != null && !"transparent".equals(getBackgroundColor())) {
            style.append("background-color: ").append(getBackgroundColor()).append(";");
        }
        
        if (getBorderRadius() != null && !getBorderRadius().isEmpty()) {
            style.append("border-radius: ").append(getBorderRadius()).append(";");
        }
        
        if (getOpacity() < 1.0) {
            style.append("opacity: ").append(getOpacity()).append(";");
        }
        
        return style.toString();
    }
}