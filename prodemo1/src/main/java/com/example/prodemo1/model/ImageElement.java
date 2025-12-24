package com.example.prodemo1.model;

public class ImageElement extends FormElement {
    private String imageUrl;
    private String altText = "";
    private String caption;
    private String imageSize = "contain"; // contain, cover, auto
    private String imagePosition = "center"; // center, top, bottom, left, right
    private boolean showBorder = false;
    private boolean rounded = false;
    private boolean responsive = true;
    private String hoverEffect = "none"; // zoom, darken, lighten, grayscale
    private String clickAction = "none"; // none, lightbox, link, download
    
    public ImageElement() {
        super("image");
        setBackgroundColor("transparent");
        setBorderWidth(0);
    }
    
    public ImageElement(String imageUrl, String altText) {
        this();
        this.imageUrl = imageUrl;
        this.altText = altText;
        setWidth(300);
        setHeight(200);
    }
    
    // Getters and Setters
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public String getAltText() { return altText; }
    public void setAltText(String altText) { this.altText = altText; }
    
    public String getCaption() { return caption; }
    public void setCaption(String caption) { this.caption = caption; }
    
    public String getImageSize() { return imageSize; }
    public void setImageSize(String imageSize) { this.imageSize = imageSize; }
    
    public String getImagePosition() { return imagePosition; }
    public void setImagePosition(String imagePosition) { this.imagePosition = imagePosition; }
    
    public boolean isShowBorder() { return showBorder; }
    public void setShowBorder(boolean showBorder) { 
        this.showBorder = showBorder;
        if (showBorder) {
            setBorderWidth(2);
            setBorderColor("#e2e8f0");
        } else {
            setBorderWidth(0);
        }
    }
    
    public boolean isRounded() { return rounded; }
    public void setRounded(boolean rounded) { 
        this.rounded = rounded;
        if (rounded) {
            setBorderRadius("50%");
        } else {
            setBorderRadius("4px");
        }
    }
    
    public boolean isResponsive() { return responsive; }
    public void setResponsive(boolean responsive) { this.responsive = responsive; }
    
    public String getHoverEffect() { return hoverEffect; }
    public void setHoverEffect(String hoverEffect) { this.hoverEffect = hoverEffect; }
    
    public String getClickAction() { return clickAction; }
    public void setClickAction(String clickAction) { this.clickAction = clickAction; }
    
    // Helper methods
    public String getCssClasses() {
        StringBuilder classes = new StringBuilder();
        classes.append("image-element ");
        
        if (rounded) {
            classes.append("rounded ");
        }
        
        if (responsive) {
            classes.append("responsive ");
        }
        
        if (!"none".equals(hoverEffect)) {
            classes.append("hover-").append(hoverEffect).append(" ");
        }
        
        if (getCssClass() != null) {
            classes.append(getCssClass());
        }
        
        return classes.toString();
    }
    
    public String generateHtml() {
        StringBuilder html = new StringBuilder();
        String classes = getCssClasses();
        
        // Start container
        html.append("<div id=\"").append(getId()).append("\"");
        html.append(" class=\"").append(classes).append("\"");
        html.append(" style=\"").append(getStyleString()).append("\"");
        
        if ("lightbox".equals(clickAction)) {
            html.append(" onclick=\"openLightbox(this)\"");
        }
        
        html.append(">");
        
        // Image tag
        html.append("<img src=\"").append(imageUrl).append("\"");
        html.append(" alt=\"").append(altText).append("\"");
        html.append(" style=\"");
        html.append("width: 100%; height: 100%; ");
        html.append("object-fit: ").append(imageSize).append("; ");
        html.append("object-position: ").append(imagePosition).append("; ");
        
        if (rounded) {
            html.append("border-radius: ").append(getBorderRadius()).append("; ");
        }
        
        html.append("\"");
        
        if (!"none".equals(clickAction) && !"lightbox".equals(clickAction)) {
            if ("link".equals(clickAction)) {
                html.append(" onclick=\"window.open('").append(imageUrl).append("', '_blank')\"");
                html.append(" style=\"cursor: pointer;\"");
            } else if ("download".equals(clickAction)) {
                html.append(" onclick=\"downloadImage('").append(imageUrl).append("')\"");
                html.append(" style=\"cursor: pointer;\"");
            }
        }
        
        html.append(">");
        
        // Caption if exists
        if (caption != null && !caption.isEmpty()) {
            html.append("<div class=\"image-caption\" style=\"");
            html.append("text-align: center; ");
            html.append("font-size: 14px; ");
            html.append("color: #718096; ");
            html.append("margin-top: 8px; ");
            html.append("font-style: italic;");
            html.append("\">");
            html.append(caption);
            html.append("</div>");
        }
        
        html.append("</div>");
        
        return html.toString();
    }
    
    public String getImageStyle() {
        StringBuilder style = new StringBuilder();
        style.append("width: ").append(getWidth()).append("px; ");
        style.append("height: ").append(getHeight()).append("px; ");
        style.append("object-fit: ").append(imageSize).append("; ");
        style.append("object-position: ").append(imagePosition).append("; ");
        
        if (rounded) {
            style.append("border-radius: ").append(getBorderRadius()).append("; ");
        }
        
        return style.toString();
    }
}