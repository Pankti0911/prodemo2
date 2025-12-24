package com.example.prodemo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    
    @GetMapping("/")
    public String home() {
        return "redirect:/receipt-builder";
    }
    
    @GetMapping("/receipt-builder")
    public String receiptBuilder(Model model) {
        model.addAttribute("pageTitle", "Dynamic Receipt Builder");
        return "receipt-builder";
    }
    
    @GetMapping("/templates")
    public String templates(Model model) {
        model.addAttribute("pageTitle", "Receipt Templates");
        return "templates";
    }
    
    @GetMapping("/receipts")
    public String receipts(Model model) {
        model.addAttribute("pageTitle", "All Receipts");
        return "receipts";
    }
    
    @GetMapping("/hospitals")
    public String hospitals(Model model) {
        model.addAttribute("pageTitle", "Hospital Management");
        return "hospitals";
    }
    
    @GetMapping("/doctors")
    public String doctors(Model model) {
        model.addAttribute("pageTitle", "Doctor Management");
        return "doctors";
    }
    
    @GetMapping("/preview")
    public String previewReceipt(Model model) {
        model.addAttribute("pageTitle", "Receipt Preview");
        return "receipt-preview";
    }
}