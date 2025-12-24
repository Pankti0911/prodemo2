package com.example.prodemo1.controller;

import com.example.prodemo1.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/canva-form-builder")
public class FormBuilderController {
    
    @GetMapping
    public String showCanvaBuilder(Model model) {
        // Text types
        model.addAttribute("textTypes", Arrays.asList(
            "heading", "subheading", "paragraph", "label", "quote", "warning", "info", "success"
        ));
        
        // Field types
        model.addAttribute("fieldTypes", Arrays.asList(
            "text", "email", "password", "number", "tel", "url", "date", "time",
            "textarea", "select", "checkbox", "radio", "file", "range", "color"
        ));
        
        // Fonts
        model.addAttribute("fonts", Arrays.asList(
            "Arial, sans-serif", "Verdana, sans-serif", "Tahoma, sans-serif",
            "'Trebuchet MS', sans-serif", "'Times New Roman', serif",
            "Georgia, serif", "Garamond, serif", "'Courier New', monospace"
        ));
        
        // Colors
        model.addAttribute("colors", Arrays.asList(
            "#000000", "#4a5568", "#718096", "#a0aec0", "#e2e8f0",
            "#667eea", "#764ba2", "#f56565", "#ed8936", "#48bb78",
            "#38b2ac", "#4299e1", "#9f7aea", "#ed64a6"
        ));
        
        // Icons
        model.addAttribute("icons", Arrays.asList(
            "fas fa-user", "fas fa-envelope", "fas fa-lock", "fas fa-phone",
            "fas fa-calendar", "fas fa-map-marker", "fas fa-building",
            "fas fa-globe", "fas fa-briefcase", "fas fa-graduation-cap",
            "fas fa-credit-card", "fas fa-heart", "fas fa-star", "fas fa-tag",
            "fas fa-link", "fas fa-image", "fas fa-file", "fas fa-upload"
        ));
        
        return "canva-form-builder";
    }
    
    @PostMapping("/save")
    @ResponseBody
    public Map<String, Object> saveForm(@RequestBody FormTemplate formTemplate) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Generate ID if not exists
            if (formTemplate.getId() == null) {
                formTemplate.setId("form_" + System.currentTimeMillis());
            }
            
            // Save to database logic would go here
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(formTemplate);
            
            System.out.println("Saved form: " + json);
            
            response.put("success", true);
            response.put("message", "Form saved successfully!");
            response.put("formId", formTemplate.getId());
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
        }
        
        return response;
    }
    
    @PostMapping("/generate-element")
    @ResponseBody
    public Map<String, Object> generateElement(@RequestBody Map<String, Object> elementData) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String elementType = (String) elementData.get("type");
            String html = "";
            
            switch(elementType) {
                case "heading":
                    TextElement heading = new TextElement("heading", (String) elementData.get("content"));
                    heading.setFontSize((String) elementData.getOrDefault("fontSize", "28px"));
                    heading.setFontWeight((String) elementData.getOrDefault("fontWeight", "bold"));
                    heading.setTextColor((String) elementData.getOrDefault("color", "#000000"));
                    html = heading.generateHtml();
                    break;
                    
                case "paragraph":
                    TextElement paragraph = new TextElement("paragraph", (String) elementData.get("content"));
                    paragraph.setFontSize((String) elementData.getOrDefault("fontSize", "16px"));
                    paragraph.setTextColor((String) elementData.getOrDefault("color", "#2d3748"));
                    html = paragraph.generateHtml();
                    break;
                    
                case "text":
                    FormField textField = new FormField("text", 
                        (String) elementData.get("label"), 
                        (String) elementData.get("placeholder"));
                    textField.setWidth((Integer) elementData.getOrDefault("width", 200));
                    textField.setHeight((Integer) elementData.getOrDefault("height", 40));
                    html = textField.generateHtml();
                    break;
                    
                case "email":
                    FormField emailField = new FormField("email",
                        (String) elementData.get("label"),
                        (String) elementData.get("placeholder"));
                    emailField.setWidth((Integer) elementData.getOrDefault("width", 200));
                    emailField.setHeight((Integer) elementData.getOrDefault("height", 40));
                    html = emailField.generateHtml();
                    break;
                    
                case "divider":
                    DividerElement divider = new DividerElement(
                        (String) elementData.getOrDefault("style", "solid"),
                        (Integer) elementData.getOrDefault("thickness", 1),
                        (String) elementData.getOrDefault("color", "#e2e8f0")
                    );
                    divider.setWidth((Integer) elementData.getOrDefault("width", 300));
                    html = divider.generateHtml();
                    break;
                    
                case "image":
                    ImageElement image = new ImageElement(
                        (String) elementData.get("url"),
                        (String) elementData.getOrDefault("alt", "Image")
                    );
                    image.setWidth((Integer) elementData.getOrDefault("width", 300));
                    image.setHeight((Integer) elementData.getOrDefault("height", 200));
                    html = image.generateHtml();
                    break;
            }
            
            response.put("success", true);
            response.put("html", html);
            response.put("elementId", elementData.get("id"));
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        
        return response;
    }
    
    @PostMapping("/validate-email")
    @ResponseBody
    public Map<String, Object> validateEmail(@RequestBody Map<String, String> data) {
        Map<String, Object> response = new HashMap<>();
        
        String email = data.get("email");
        boolean isValid = isValidEmail(email);
        
        response.put("valid", isValid);
        response.put("message", isValid ? "Valid email address" : "Please enter a valid email address");
        
        return response;
    }
    
    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) return false;
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }
}