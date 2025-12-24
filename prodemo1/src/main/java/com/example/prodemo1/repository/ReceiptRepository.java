package com.example.prodemo1.repository;

import com.example.prodemo1.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    
    Receipt findByReceiptNumber(String receiptNumber);
    
    List<Receipt> findByDoctorNameContainingIgnoreCase(String doctorName);
    
    List<Receipt> findByPatientNameContainingIgnoreCase(String patientName);
    
    List<Receipt> findByHospitalNameContainingIgnoreCase(String hospitalName);
    
    List<Receipt> findByStatus(String status);
    
    @Query("SELECT r FROM Receipt r WHERE r.generatedDate BETWEEN :startDate AND :endDate")
    List<Receipt> findReceiptsBetweenDates(@Param("startDate") LocalDateTime startDate, 
                                          @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT r FROM Receipt r WHERE r.totalAmount > :amount")
    List<Receipt> findReceiptsWithAmountGreaterThan(@Param("amount") Double amount);
}