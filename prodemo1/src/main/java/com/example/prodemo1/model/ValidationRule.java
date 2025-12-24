package com.example.prodemo1.model;

public class ValidationRule {
    private String type; // required, email, pattern, min, max, minLength, maxLength
    private String pattern;
    private String message;
    private int minValue;
    private int maxValue;
    private int minLength;
    private int maxLength;
    
    // Getters and setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getPattern() { return pattern; }
    public void setPattern(String pattern) { this.pattern = pattern; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public int getMinValue() { return minValue; }
    public void setMinValue(int minValue) { this.minValue = minValue; }
    
    public int getMaxValue() { return maxValue; }
    public void setMaxValue(int maxValue) { this.maxValue = maxValue; }
    
    public int getMinLength() { return minLength; }
    public void setMinLength(int minLength) { this.minLength = minLength; }
    
    public int getMaxLength() { return maxLength; }
    public void setMaxLength(int maxLength) { this.maxLength = maxLength; }
}