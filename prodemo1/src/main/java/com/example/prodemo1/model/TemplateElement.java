package com.example.prodemo1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "template_elements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateElement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String elementType; // HOSPITAL_INFO, DOCTOR_INFO, MEDICINE_LIST, PAYMENT, etc.
    
    @Column(nullable = false)
    private String elementName;
    
    private String displayName;
    
    @Lob
    @Column(columnDefinition = "TEXT")
    private String htmlContent;
    
    private String cssClass;
    private Integer sortOrder = 0;
    private boolean draggable = true;
    private boolean editable = true;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private ReceiptTemplate template;
}