package com.example.prodemo1.model;

import java.util.ArrayList;
import java.util.List;

public class FormField extends FormElement {
    private String fieldType = "text"; // text, email, password, number, tel, url, date, time, textarea, select, checkbox, radio, file
    private List<String> options = new ArrayList<>();
    private boolean multiple = false;
    private String accept = ""; // For file upload
    private int min;
    private int max;
    private String step = "1";
    private boolean autocomplete = true;
    private boolean showCharacterCounter = false;
    private boolean showStrengthMeter = false;
    private boolean showIcon = true;
    private String icon;
    private String prefix = "";
    private String suffix = "";
    private String pattern;
    private String patternMessage;
    private boolean showValidation = true;
    private String validationType = "default"; // default, inline, tooltip
    private String placeholderColor = "#999999";
    private String focusColor = "#4299e1";
    private String errorColor = "#f56565";
    private String successColor = "#48bb78";
    
    public FormField() {
        super("field");
        setPadding("8px 12px");
        setBorderRadius("4px");
        setBorderColor("#e2e8f0");
        setBackgroundColor("#ffffff");
    }
    
    public FormField(String fieldType, String label, String placeholder) {
        this();
        this.fieldType = fieldType;
        setLabel(label);
        setPlaceholder(placeholder);
        
        // Set default values based on field type
        switch(fieldType) {
            case "email":
                setPlaceholder("you@example.com");
                setPattern("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
                setPatternMessage("Please enter a valid email address");
                setIcon("fas fa-envelope");
                break;
            case "password":
                setPlaceholder("Enter password");
                setMinLength(6);
                setPatternMessage("Password must be at least 6 characters");
                setIcon("fas fa-lock");
                setShowStrengthMeter(true);
                break;
            case "number":
                setPlaceholder("Enter number");
                setIcon("fas fa-hashtag");
                break;
            case "tel":
                setPlaceholder("(123) 456-7890");
                setPattern("[0-9\\s\\-()]+");
                setPatternMessage("Please enter a valid phone number");
                setIcon("fas fa-phone");
                break;
            case "textarea":
                setPlaceholder("Enter your message...");
                setHeight(100);
                setIcon("fas fa-align-left");
                break;
            case "select":
                setPlaceholder("Select an option");
                options.add("Option 1");
                options.add("Option 2");
                options.add("Option 3");
                setIcon("fas fa-caret-down");
                break;
        }
    }
    
    // Getters and Setters
    public String getFieldType() { return fieldType; }
    public void setFieldType(String fieldType) { this.fieldType = fieldType; }
    
    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }
    
    public boolean isMultiple() { return multiple; }
    public void setMultiple(boolean multiple) { this.multiple = multiple; }
    
    public String getAccept() { return accept; }
    public void setAccept(String accept) { this.accept = accept; }
    
    public int getMin() { return min; }
    public void setMin(int min) { this.min = min; }
    
    public int getMax() { return max; }
    public void setMax(int max) { this.max = max; }
    
    public String getStep() { return step; }
    public void setStep(String step) { this.step = step; }
    
    public boolean isAutocomplete() { return autocomplete; }
    public void setAutocomplete(boolean autocomplete) { this.autocomplete = autocomplete; }
    
    public boolean isShowCharacterCounter() { return showCharacterCounter; }
    public void setShowCharacterCounter(boolean showCharacterCounter) { this.showCharacterCounter = showCharacterCounter; }
    
    public boolean isShowStrengthMeter() { return showStrengthMeter; }
    public void setShowStrengthMeter(boolean showStrengthMeter) { this.showStrengthMeter = showStrengthMeter; }
    
    public boolean isShowIcon() { return showIcon; }
    public void setShowIcon(boolean showIcon) { this.showIcon = showIcon; }
    
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    
    public String getPrefix() { return prefix; }
    public void setPrefix(String prefix) { this.prefix = prefix; }
    
    public String getSuffix() { return suffix; }
    public void setSuffix(String suffix) { this.suffix = suffix; }
    
    public String getPattern() { return pattern; }
    public void setPattern(String pattern) { this.pattern = pattern; }
    
    public String getPatternMessage() { return patternMessage; }
    public void setPatternMessage(String patternMessage) { this.patternMessage = patternMessage; }
    
    public boolean isShowValidation() { return showValidation; }
    public void setShowValidation(boolean showValidation) { this.showValidation = showValidation; }
    
    public String getValidationType() { return validationType; }
    public void setValidationType(String validationType) { this.validationType = validationType; }
    
    public String getPlaceholderColor() { return placeholderColor; }
    public void setPlaceholderColor(String placeholderColor) { this.placeholderColor = placeholderColor; }
    
    public String getFocusColor() { return focusColor; }
    public void setFocusColor(String focusColor) { this.focusColor = focusColor; }
    
    public String getErrorColor() { return errorColor; }
    public void setErrorColor(String errorColor) { this.errorColor = errorColor; }
    
    public String getSuccessColor() { return successColor; }
    public void setSuccessColor(String successColor) { this.successColor = successColor; }
    
    // Helper methods
    public String getCssClasses() {
        StringBuilder classes = new StringBuilder();
        classes.append("form-field ");
        classes.append(fieldType).append("-field ");
        
        if (isRequired()) {
            classes.append("required ");
        }
        
        if (isDisabled()) {
            classes.append("disabled ");
        }
        
        if (showIcon && icon != null) {
            classes.append("has-icon ");
        }
        
        if (getCssClass() != null) {
            classes.append(getCssClass());
        }
        
        return classes.toString();
    }
    
    public String generateHtml() {
        StringBuilder html = new StringBuilder();
        String classes = getCssClasses();
        
        // Field container
        html.append("<div id=\"").append(getId()).append("\"");
        html.append(" class=\"").append(classes).append("\"");
        html.append(" style=\"").append(getContainerStyle()).append("\"");
        html.append(">");
        
        // Label
        if (getLabel() != null && !getLabel().isEmpty()) {
            html.append("<label for=\"field_").append(getId()).append("\"");
            html.append(" style=\"").append(getLabelStyle()).append("\"");
            html.append(">");
            html.append(getLabel());
            if (isRequired()) {
                html.append(" <span style=\"color: ").append(errorColor).append(";\">*</span>");
            }
            html.append("</label>");
        }
        
        // Input wrapper with icon
        html.append("<div class=\"input-wrapper\" style=\"").append(getWrapperStyle()).append("\">");
        
        // Prefix
        if (prefix != null && !prefix.isEmpty()) {
            html.append("<span class=\"input-prefix\" style=\"").append(getPrefixSuffixStyle()).append("\">");
            html.append(prefix);
            html.append("</span>");
        }
        
        // Icon
        if (showIcon && icon != null) {
            html.append("<i class=\"").append(icon).append(" field-icon\"");
            html.append(" style=\"").append(getIconStyle()).append("\"></i>");
        }
        
        // Input element based on type
        html.append(generateInputElement());
        
        // Suffix
        if (suffix != null && !suffix.isEmpty()) {
            html.append("<span class=\"input-suffix\" style=\"").append(getPrefixSuffixStyle()).append("\">");
            html.append(suffix);
            html.append("</span>");
        }
        
        html.append("</div>");
        
        // Help text
        if (getHelpText() != null && !getHelpText().isEmpty()) {
            html.append("<div class=\"help-text\" style=\"").append(getHelpTextStyle()).append("\">");
            html.append(getHelpText());
            html.append("</div>");
        }
        
        // Validation message
        if (showValidation && patternMessage != null && !patternMessage.isEmpty()) {
            html.append("<div class=\"validation-message\" style=\"").append(getValidationStyle()).append("\">");
            html.append("<i class=\"fas fa-info-circle\"></i> ");
            html.append(patternMessage);
            html.append("</div>");
        }
        
        // Character counter
        if (showCharacterCounter) {
            html.append("<div class=\"character-counter\" style=\"").append(getCounterStyle()).append("\">");
            html.append("<span class=\"current\">0</span>/<span class=\"max\">").append(getMaxLength()).append("</span>");
            html.append("</div>");
        }
        
        // Strength meter (for passwords)
        if (showStrengthMeter && "password".equals(fieldType)) {
            html.append("<div class=\"strength-meter\" style=\"").append(getStrengthMeterStyle()).append("\">");
            html.append("<div class=\"strength-bar\"></div>");
            html.append("<div class=\"strength-labels\">");
            html.append("<span>Weak</span>");
            html.append("<span>Medium</span>");
            html.append("<span>Strong</span>");
            html.append("</div>");
            html.append("</div>");
        }
        
        html.append("</div>");
        
        return html.toString();
    }
    
    private String generateInputElement() {
        StringBuilder input = new StringBuilder();
        
        switch(fieldType) {
            case "textarea":
                input.append("<textarea");
                input.append(" id=\"field_").append(getId()).append("\"");
                input.append(" name=\"").append(getId()).append("\"");
                input.append(" placeholder=\"").append(getPlaceholder() != null ? getPlaceholder() : "").append("\"");
                input.append(" style=\"").append(getInputStyle()).append("\"");
                input.append(isRequired() ? " required" : "");
                input.append(isReadOnly() ? " readonly" : "");
                input.append(isDisabled() ? " disabled" : "");
                if (getMinLength() > 0) input.append(" minlength=\"").append(getMinLength()).append("\"");
                if (getMaxLength() > 0) input.append(" maxlength=\"").append(getMaxLength()).append("\"");
                input.append("></textarea>");
                break;
                
            case "select":
                input.append("<select");
                input.append(" id=\"field_").append(getId()).append("\"");
                input.append(" name=\"").append(getId()).append("\"");
                input.append(" style=\"").append(getInputStyle()).append("\"");
                input.append(isRequired() ? " required" : "");
                input.append(isMultiple() ? " multiple" : "");
                input.append(isDisabled() ? " disabled" : "");
                input.append(">");
                input.append("<option value=\"\">").append(getPlaceholder() != null ? getPlaceholder() : "Select...").append("</option>");
                for (String option : options) {
                    input.append("<option value=\"").append(option).append("\">").append(option).append("</option>");
                }
                input.append("</select>");
                break;
                
            case "checkbox":
            case "radio":
                input.append("<div class=\"option-group\" style=\"display: flex; flex-direction: column; gap: 8px;\">");
                for (int i = 0; i < options.size(); i++) {
                    String option = options.get(i);
                    input.append("<label style=\"display: flex; align-items: center; gap: 8px; cursor: pointer;\">");
                    input.append("<input type=\"").append(fieldType).append("\"");
                    input.append(" id=\"field_").append(getId()).append("_").append(i).append("\"");
                    input.append(" name=\"").append(getId()).append("\"");
                    input.append(" value=\"").append(option).append("\"");
                    input.append(" style=\"margin: 0;\"");
                    input.append(isRequired() ? " required" : "");
                    input.append(isDisabled() ? " disabled" : "");
                    input.append("> ");
                    input.append(option);
                    input.append("</label>");
                }
                input.append("</div>");
                break;
                
            default:
                input.append("<input");
                input.append(" type=\"").append(fieldType).append("\"");
                input.append(" id=\"field_").append(getId()).append("\"");
                input.append(" name=\"").append(getId()).append("\"");
                input.append(" placeholder=\"").append(getPlaceholder() != null ? getPlaceholder() : "").append("\"");
                input.append(" style=\"").append(getInputStyle()).append("\"");
                input.append(isRequired() ? " required" : "");
                input.append(isReadOnly() ? " readonly" : "");
                input.append(isDisabled() ? " disabled" : "");
                input.append(!autocomplete ? " autocomplete=\"off\"" : "");
                if (pattern != null) input.append(" pattern=\"").append(pattern).append("\"");
                if (getMinLength() > 0) input.append(" minlength=\"").append(getMinLength()).append("\"");
                if (getMaxLength() > 0) input.append(" maxlength=\"").append(getMaxLength()).append("\"");
                if (min != 0 || max != 0) {
                    if (min != 0) input.append(" min=\"").append(min).append("\"");
                    if (max != 0) input.append(" max=\"").append(max).append("\"");
                    if (step != null) input.append(" step=\"").append(step).append("\"");
                }
                input.append(">");
                break;
        }
        
        return input.toString();
    }
    
    // Style methods
    private String getContainerStyle() {
        StringBuilder style = new StringBuilder();
        style.append("position: absolute;");
        style.append("left: ").append(getX()).append("px;");
        style.append("top: ").append(getY()).append("px;");
        style.append("width: ").append(getWidth()).append("px;");
        style.append("z-index: ").append(getZIndex()).append(";");
        style.append("padding: ").append(getPadding()).append(";");
        style.append("margin: ").append(getMargin()).append(";");
        return style.toString();
    }
    
    private String getLabelStyle() {
        StringBuilder style = new StringBuilder();
        style.append("display: block;");
        style.append("font-size: ").append(getFontSize()).append(";");
        style.append("font-weight: ").append(getFontWeight()).append(";");
        style.append("color: ").append(getTextColor()).append(";");
        style.append("margin-bottom: 8px;");
        return style.toString();
    }
    
    private String getWrapperStyle() {
        StringBuilder style = new StringBuilder();
        style.append("position: relative;");
        style.append("display: flex;");
        style.append("align-items: center;");
        return style.toString();
    }
    
    private String getInputStyle() {
        StringBuilder style = new StringBuilder();
        style.append("width: 100%;");
        style.append("padding: ").append(getPadding()).append(";");
        style.append("font-size: ").append(getFontSize()).append(";");
        style.append("font-family: ").append(getFontFamily()).append(";");
        style.append("color: ").append(getTextColor()).append(";");
        style.append("background-color: ").append(getBackgroundColor()).append(";");
        style.append("border: ").append(getBorderWidth()).append("px ");
        style.append(getBorderStyle()).append(" ").append(getBorderColor()).append(";");
        style.append("border-radius: ").append(getBorderRadius()).append(";");
        style.append("box-shadow: ").append(getBoxShadow()).append(";");
        style.append("outline: none;");
        style.append("transition: all 0.3s ease;");
        
        // Placeholder color
        style.append("&::placeholder { color: ").append(placeholderColor).append("; }");
        
        // Focus state
        style.append("&:focus { border-color: ").append(focusColor).append("; box-shadow: 0 0 0 3px rgba(66, 153, 225, 0.1); }");
        
        return style.toString();
    }
    
    private String getIconStyle() {
        StringBuilder style = new StringBuilder();
        style.append("position: absolute;");
        style.append("left: 12px;");
        style.append("color: #a0aec0;");
        style.append("font-size: ").append(getFontSize()).append(";");
        return style.toString();
    }
    
    private String getPrefixSuffixStyle() {
        StringBuilder style = new StringBuilder();
        style.append("padding: 8px 12px;");
        style.append("background-color: #f7fafc;");
        style.append("border: 1px solid #e2e8f0;");
        style.append("font-size: ").append(getFontSize()).append(";");
        return style.toString();
    }
    
    private String getHelpTextStyle() {
        StringBuilder style = new StringBuilder();
        style.append("font-size: 12px;");
        style.append("color: #718096;");
        style.append("margin-top: 4px;");
        return style.toString();
    }
    
    private String getValidationStyle() {
        StringBuilder style = new StringBuilder();
        style.append("font-size: 12px;");
        style.append("color: #718096;");
        style.append("margin-top: 4px;");
        style.append("display: flex;");
        style.append("align-items: center;");
        style.append("gap: 4px;");
        return style.toString();
    }
    
    private String getCounterStyle() {
        StringBuilder style = new StringBuilder();
        style.append("font-size: 12px;");
        style.append("color: #a0aec0;");
        style.append("text-align: right;");
        style.append("margin-top: 4px;");
        return style.toString();
    }
    
    private String getStrengthMeterStyle() {
        StringBuilder style = new StringBuilder();
        style.append("margin-top: 8px;");
        style.append(".strength-bar { height: 4px; background: #e2e8f0; border-radius: 2px; margin-bottom: 4px; }");
        style.append(".strength-labels { display: flex; justify-content: space-between; font-size: 11px; color: #a0aec0; }");
        return style.toString();
    }
}