// src/main/java/com/example/prodemo1/model/ReceiptTemplate.java
package com.example.prodemo1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receipt_templates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptTemplate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String templateName;
    
    private String description;
    
    @Lob
    @Column(columnDefinition = "TEXT")
    private String htmlContent;
    
    @Lob
    @Column(columnDefinition = "TEXT")
    private String cssStyles;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "template_id")
    private List<TemplateElement> elements = new ArrayList<>();
    
    private boolean isActive = true;
    
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}