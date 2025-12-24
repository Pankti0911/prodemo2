package com.example.prodemo1.repository;

import com.example.prodemo1.model.ReceiptTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReceiptTemplateRepository extends JpaRepository<ReceiptTemplate, Long> {
    
    List<ReceiptTemplate> findByIsActiveTrue();
    
    List<ReceiptTemplate> findByTemplateNameContainingIgnoreCase(String templateName);
    
    boolean existsByTemplateName(String templateName);
}