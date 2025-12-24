package com.example.prodemo1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medicine_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String medicineName;
    
    private String dosage;
    private String frequency;
    private String duration;
    
    @Column(nullable = false)
    private Integer quantity = 1;
    
    @Column(nullable = false)
    private Double price = 0.0;
    
    @Column(nullable = false)
    private Double total = 0.0;
    
    private String instructions;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;
    
    @PrePersist
    @PreUpdate
    private void calculateTotal() {
        if (quantity != null && price != null) {
            total = quantity * price;
        }
    }
}