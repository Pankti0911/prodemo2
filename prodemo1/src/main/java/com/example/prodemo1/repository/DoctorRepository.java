package com.example.prodemo1.repository;

import com.example.prodemo1.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    
    List<Doctor> findByIsActiveTrue();
    
    List<Doctor> findBySpecialization(String specialization);
    
    List<Doctor> findByHospitalId(Long hospitalId);
    
    Doctor findByDoctorNameAndHospitalId(String doctorName, Long hospitalId);
}