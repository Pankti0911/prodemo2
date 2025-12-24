package com.example.prodemo1.controller;

import com.example.prodemo1.model.*;
import com.example.prodemo1.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/receipts")
@CrossOrigin(origins = "*")
public class ReceiptController {
    
    @Autowired
    private ReceiptService receiptService;
    
    // Receipt Operations
    @PostMapping
    public ResponseEntity<Map<String, Object>> createReceipt(@RequestBody Receipt receipt) {
        try {
            Receipt createdReceipt = receiptService.createReceipt(receipt);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Receipt created successfully");
            response.put("receipt", createdReceipt);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("success", false, "message", e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateReceipt(@PathVariable Long id, @RequestBody Receipt receipt) {
        try {
            Receipt updatedReceipt = receiptService.updateReceipt(id, receipt);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Receipt updated successfully");
            response.put("receipt", updatedReceipt);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("success", false, "message", e.getMessage()));
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getReceiptById(@PathVariable Long id) {
        try {
            Receipt receipt = receiptService.getReceiptById(id);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("receipt", receipt);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("success", false, "message", e.getMessage()));
        }
    }
    
    @GetMapping("/number/{receiptNumber}")
    public ResponseEntity<Map<String, Object>> getReceiptByNumber(@PathVariable String receiptNumber) {
        try {
            Receipt receipt = receiptService.getReceiptByNumber(receiptNumber);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("receipt", receipt);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("success", false, "message", "Receipt not found"));
        }
    }
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllReceipts() {
        try {
            List<Receipt> receipts = receiptService.getAllReceipts();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("receipts", receipts);
            response.put("count", receipts.size());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("success", false, "message", e.getMessage()));
        }
    }
    
    @GetMapping("/doctor/{doctorName}")
    public ResponseEntity<List<Receipt>> getReceiptsByDoctor(@PathVariable String doctorName) {
        return ResponseEntity.ok(receiptService.getReceiptsByDoctor(doctorName));
    }
    
    @GetMapping("/patient/{patientName}")
    public ResponseEntity<List<Receipt>> getReceiptsByPatient(@PathVariable String patientName) {
        return ResponseEntity.ok(receiptService.getReceiptsByPatient(patientName));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Receipt>> searchReceipts(
            @RequestParam(required = false) String doctorName,
            @RequestParam(required = false) String patientName,
            @RequestParam(required = false) String hospitalName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        
        // This is a simplified search - in production, you'd create a more sophisticated search method
        if (doctorName != null) {
            return ResponseEntity.ok(receiptService.getReceiptsByDoctor(doctorName));
        } else if (patientName != null) {
            return ResponseEntity.ok(receiptService.getReceiptsByPatient(patientName));
        } else if (hospitalName != null) {
            return ResponseEntity.ok(receiptService.getReceiptsByHospital(hospitalName));
        } else if (startDate != null && endDate != null) {
            return ResponseEntity.ok(receiptService.getReceiptsByDateRange(startDate, endDate));
        } else {
            return ResponseEntity.ok(receiptService.getAllReceipts());
        }
    }
    
    @PostMapping("/{id}/publish")
    public ResponseEntity<Map<String, Object>> publishReceipt(@PathVariable Long id) {
        try {
            Receipt publishedReceipt = receiptService.publishReceipt(id);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Receipt published successfully");
            response.put("receipt", publishedReceipt);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("success", false, "message", e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteReceipt(@PathVariable Long id) {
        try {
            receiptService.deleteReceipt(id);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Receipt deleted successfully");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("success", false, "message", e.getMessage()));
        }
    }
    
    // Template Operations
    @GetMapping("/templates")
    public ResponseEntity<List<ReceiptTemplate>> getAllTemplates() {
        return ResponseEntity.ok(receiptService.getAllTemplates());
    }
    
    @PostMapping("/templates")
    public ResponseEntity<ReceiptTemplate> createTemplate(@RequestBody ReceiptTemplate template) {
        return ResponseEntity.ok(receiptService.createTemplate(template));
    }
    
    // Hospital Operations
    @GetMapping("/hospitals")
    public ResponseEntity<List<Hospital>> getAllHospitals() {
        return ResponseEntity.ok(receiptService.getAllHospitals());
    }
    
    @PostMapping("/hospitals")
    public ResponseEntity<Hospital> createHospital(@RequestBody Hospital hospital) {
        return ResponseEntity.ok(receiptService.createHospital(hospital));
    }
    
    // Doctor Operations
    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(receiptService.getAllDoctors());
    }
    
    @GetMapping("/hospitals/{hospitalId}/doctors")
    public ResponseEntity<List<Doctor>> getDoctorsByHospital(@PathVariable Long hospitalId) {
        return ResponseEntity.ok(receiptService.getDoctorsByHospital(hospitalId));
    }
    
    @PostMapping("/doctors")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(receiptService.createDoctor(doctor));
    }
}