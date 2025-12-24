package com.example.prodemo1.repository;

import com.example.prodemo1.model.MedicineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicineItemRepository extends JpaRepository<MedicineItem, Long> {
    
    List<MedicineItem> findByReceiptId(Long receiptId);
    
    List<MedicineItem> findByMedicineNameContainingIgnoreCase(String medicineName);
}