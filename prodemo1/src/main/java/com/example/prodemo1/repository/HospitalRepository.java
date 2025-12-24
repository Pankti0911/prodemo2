package com.example.prodemo1.repository;

import com.example.prodemo1.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    
    List<Hospital> findByIsActiveTrue();
    
    Hospital findByHospitalName(String hospitalName);
    
    List<Hospital> findByCity(String city);
}