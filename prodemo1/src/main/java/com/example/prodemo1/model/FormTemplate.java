package com.example.prodemo1.model;

import java.util.List;
import java.util.Map;

public class FormTemplate {
    private String id;
    private String name;
    private String title;
    private String description;
    
    // Layout
    private int width = 800;
    private int height = 600;
    private String alignment = "center";
    private String background = "#ffffff";
    private String backgroundImage;
    
    // Styling
    private String theme = "modern";
    private String fontFamily = "Arial, sans-serif";
    private int borderRadius = 8;
    private int shadow = 5;
    private boolean showGrid = true;
    
    // Elements
    private List<FormElement> elements;
    
    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    
    public String getAlignment() { return alignment; }
    public void setAlignment(String alignment) { this.alignment = alignment; }
    
    public String getBackground() { return background; }
    public void setBackground(String background) { this.background = background; }
    
    public String getBackgroundImage() { return backgroundImage; }
    public void setBackgroundImage(String backgroundImage) { this.backgroundImage = backgroundImage; }
    
    public String getTheme() { return theme; }
    public void setTheme(String theme) { this.theme = theme; }
    
    public String getFontFamily() { return fontFamily; }
    public void setFontFamily(String fontFamily) { this.fontFamily = fontFamily; }
    
    public int getBorderRadius() { return borderRadius; }
    public void setBorderRadius(int borderRadius) { this.borderRadius = borderRadius; }
    
    public int getShadow() { return shadow; }
    public void setShadow(int shadow) { this.shadow = shadow; }
    
    public boolean isShowGrid() { return showGrid; }
    public void setShowGrid(boolean showGrid) { this.showGrid = showGrid; }
    
    public List<FormElement> getElements() { return elements; }
    public void setElements(List<FormElement> elements) { this.elements = elements; }
}