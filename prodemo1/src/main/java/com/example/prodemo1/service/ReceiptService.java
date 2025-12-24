package com.example.prodemo1.service;

import com.example.prodemo1.model.*;
import com.example.prodemo1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ReceiptService {
    
    @Autowired
    private ReceiptRepository receiptRepository;
    
    @Autowired
    private ReceiptTemplateRepository templateRepository;
    
    @Autowired
    private MedicineItemRepository medicineItemRepository;
    
    @Autowired
    private HospitalRepository hospitalRepository;
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    public Receipt createReceipt(Receipt receipt) {
        // Generate receipt number
        receipt.setReceiptNumber(generateReceiptNumber());
        receipt.setGeneratedDate(LocalDateTime.now());
        receipt.setStatus("DRAFT");
        
        // Calculate totals
        calculateReceiptTotals(receipt);
        
        // Save receipt first
        Receipt savedReceipt = receiptRepository.save(receipt);
        
        // Save medicine items with proper receipt reference
        if (receipt.getMedicines() != null && !receipt.getMedicines().isEmpty()) {
            for (MedicineItem medicine : receipt.getMedicines()) {
                medicine.setReceipt(savedReceipt); // Set the saved receipt reference
                medicineItemRepository.save(medicine);
            }
            // Update the saved receipt with medicine list
            savedReceipt.setMedicines(receipt.getMedicines());
        }
        
        return savedReceipt;
    }
    
    public Receipt updateReceipt(Long id, Receipt receiptDetails) {
        Receipt receipt = receiptRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Receipt not found with id: " + id));
        
        // Update basic information
        receipt.setHospitalName(receiptDetails.getHospitalName());
        receipt.setHospitalAddress(receiptDetails.getHospitalAddress());
        receipt.setHospitalContact(receiptDetails.getHospitalContact());
        receipt.setDoctorName(receiptDetails.getDoctorName());
        receipt.setDoctorSpecialization(receiptDetails.getDoctorSpecialization());
        receipt.setPatientName(receiptDetails.getPatientName());
        receipt.setPatientAge(receiptDetails.getPatientAge());
        receipt.setPatientGender(receiptDetails.getPatientGender());
        receipt.setPatientContact(receiptDetails.getPatientContact());
        receipt.setPatientAddress(receiptDetails.getPatientAddress());
        receipt.setNotes(receiptDetails.getNotes());
        receipt.setVisitDate(receiptDetails.getVisitDate());
        receipt.setPaymentMethod(receiptDetails.getPaymentMethod());
        
        // Update medicine list
        if (receiptDetails.getMedicines() != null) {
            // Clear existing medicines from database
            List<MedicineItem> existingMedicines = medicineItemRepository.findByReceiptId(id);
            for (MedicineItem medicine : existingMedicines) {
                medicineItemRepository.delete(medicine);
            }
            
            // Clear the list in the receipt entity
            receipt.getMedicines().clear();
            
            // Add new medicines
            for (MedicineItem medicine : receiptDetails.getMedicines()) {
                medicine.setId(null); // Ensure new ID for update
                medicine.setReceipt(receipt);
                receipt.getMedicines().add(medicine);
            }
        }
        
        // Recalculate totals
        calculateReceiptTotals(receipt);
        
        return receiptRepository.save(receipt);
    }
    
    public Receipt publishReceipt(Long id) {
        Receipt receipt = receiptRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Receipt not found with id: " + id));
        
        receipt.setStatus("PUBLISHED");
        receipt.setPaymentStatus("PAID");
        receipt.setUpdatedAt(LocalDateTime.now());
        
        return receiptRepository.save(receipt);
    }
    
    public Receipt getReceiptById(Long id) {
        return receiptRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Receipt not found with id: " + id));
    }
    
    public Receipt getReceiptByNumber(String receiptNumber) {
        Receipt receipt = receiptRepository.findByReceiptNumber(receiptNumber);
        if (receipt == null) {
            throw new RuntimeException("Receipt not found with number: " + receiptNumber);
        }
        return receipt;
    }
    
    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }
    
    public List<Receipt> getReceiptsByDoctor(String doctorName) {
        return receiptRepository.findByDoctorNameContainingIgnoreCase(doctorName);
    }
    
    public List<Receipt> getReceiptsByPatient(String patientName) {
        return receiptRepository.findByPatientNameContainingIgnoreCase(patientName);
    }
    
    public List<Receipt> getReceiptsByHospital(String hospitalName) {
        return receiptRepository.findByHospitalNameContainingIgnoreCase(hospitalName);
    }
    
    public List<Receipt> getReceiptsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return receiptRepository.findReceiptsBetweenDates(startDate, endDate);
    }
    
    public void deleteReceipt(Long id) {
        Receipt receipt = receiptRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Receipt not found with id: " + id));
        
        // First delete all medicine items
        List<MedicineItem> medicines = medicineItemRepository.findByReceiptId(id);
        for (MedicineItem medicine : medicines) {
            medicineItemRepository.delete(medicine);
        }
        
        // Then delete the receipt
        receiptRepository.delete(receipt);
    }
    
    private String generateReceiptNumber() {
        String timestamp = String.valueOf(System.currentTimeMillis()).substring(8);
        String random = UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        return "REC-" + timestamp + "-" + random;
    }
    
    private void calculateReceiptTotals(Receipt receipt) {
        double subTotal = 0.0;
        
        if (receipt.getMedicines() != null && !receipt.getMedicines().isEmpty()) {
            for (MedicineItem medicine : receipt.getMedicines()) {
                // Ensure total is calculated
                medicine.calculateTotal();
                subTotal += medicine.getTotal();
            }
        }
        
        receipt.setSubTotal(subTotal);
        
        // Calculate tax (5% default)
        double taxRate = 0.05;
        double tax = subTotal * taxRate;
        receipt.setTax(tax);
        
        // Calculate total
        double total = subTotal + tax - (receipt.getDiscount() != null ? receipt.getDiscount() : 0.0);
        receipt.setTotalAmount(total);
    }
    
    // Template Management
    public ReceiptTemplate createTemplate(ReceiptTemplate template) {
        template.setCreatedAt(LocalDateTime.now());
        template.setUpdatedAt(LocalDateTime.now());
        return templateRepository.save(template);
    }
    
    public List<ReceiptTemplate> getAllTemplates() {
        return templateRepository.findByIsActiveTrue();
    }
    
    public ReceiptTemplate getTemplateById(Long id) {
        return templateRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Template not found with id: " + id));
    }
    
    public ReceiptTemplate updateTemplate(Long id, ReceiptTemplate templateDetails) {
        ReceiptTemplate template = templateRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Template not found with id: " + id));
        
        template.setTemplateName(templateDetails.getTemplateName());
        template.setDescription(templateDetails.getDescription());
        template.setHtmlContent(templateDetails.getHtmlContent());
        template.setCssStyles(templateDetails.getCssStyles());
        template.setUpdatedAt(LocalDateTime.now());
        
        return templateRepository.save(template);
    }
    
    public void deleteTemplate(Long id) {
        ReceiptTemplate template = templateRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Template not found with id: " + id));
        
        template.setActive(false);
        template.setUpdatedAt(LocalDateTime.now());
        templateRepository.save(template);
    }
    
    // Hospital Management
    public Hospital createHospital(Hospital hospital) {
        hospital.setCreatedAt(LocalDateTime.now());
        hospital.setUpdatedAt(LocalDateTime.now());
        return hospitalRepository.save(hospital);
    }
    
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findByIsActiveTrue();
    }
    
    public Hospital updateHospital(Long id, Hospital hospitalDetails) {
        Hospital hospital = hospitalRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Hospital not found with id: " + id));
        
        hospital.setHospitalName(hospitalDetails.getHospitalName());
        hospital.setAddress(hospitalDetails.getAddress());
        hospital.setCity(hospitalDetails.getCity());
        hospital.setState(hospitalDetails.getState());
        hospital.setZipCode(hospitalDetails.getZipCode());
        hospital.setCountry(hospitalDetails.getCountry());
        hospital.setContactNumber(hospitalDetails.getContactNumber());
        hospital.setEmail(hospitalDetails.getEmail());
        hospital.setWebsite(hospitalDetails.getWebsite());
        hospital.setLogoUrl(hospitalDetails.getLogoUrl());
        hospital.setUpdatedAt(LocalDateTime.now());
        
        return hospitalRepository.save(hospital);
    }
    
    // Doctor Management
    public Doctor createDoctor(Doctor doctor) {
        doctor.setCreatedAt(LocalDateTime.now());
        doctor.setUpdatedAt(LocalDateTime.now());
        return doctorRepository.save(doctor);
    }
    
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findByIsActiveTrue();
    }
    
    public List<Doctor> getDoctorsByHospital(Long hospitalId) {
        return doctorRepository.findByHospitalId(hospitalId);
    }
    
    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        Doctor doctor = doctorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
        
        doctor.setDoctorName(doctorDetails.getDoctorName());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setQualification(doctorDetails.getQualification());
        doctor.setRegistrationNumber(doctorDetails.getRegistrationNumber());
        doctor.setContactNumber(doctorDetails.getContactNumber());
        doctor.setEmail(doctorDetails.getEmail());
        doctor.setSignatureUrl(doctorDetails.getSignatureUrl());
        doctor.setHospital(doctorDetails.getHospital());
        doctor.setUpdatedAt(LocalDateTime.now());
        
        return doctorRepository.save(doctor);
    }
    
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
        
        doctor.setActive(false);
        doctor.setUpdatedAt(LocalDateTime.now());
        doctorRepository.save(doctor);
    }
}