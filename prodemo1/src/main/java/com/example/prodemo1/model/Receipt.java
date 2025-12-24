package com.example.prodemo1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receipts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String receiptNumber;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private ReceiptTemplate template;
    
    // Hospital Information
    private String hospitalName;
    private String hospitalAddress;
    private String hospitalContact;
    private String hospitalEmail;
    private String hospitalLogoUrl;
    
    // Doctor Information
    private String doctorName;
    private String doctorSpecialization;
    private String doctorContact;
    private String doctorSignature;
    
    // Patient Information
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String patientContact;
    private String patientAddress;
    private String patientId;
    
    // Medicine List
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "receipt_id")
    private List<MedicineItem> medicines = new ArrayList<>();
    
    // Payment Information
    private Double subTotal = 0.0;
    private Double discount = 0.0;
    private Double tax = 0.0;
    private Double totalAmount = 0.0;
    private String paymentMethod;
    private String paymentStatus = "PENDING";
    
    // Visit Information
    private LocalDateTime visitDate;
    private LocalDateTime generatedDate;
    
    private String status = "DRAFT"; // DRAFT, PUBLISHED, ARCHIVED
    
    @Lob
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        generatedDate = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}