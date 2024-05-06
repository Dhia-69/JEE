package com.example.demo.controllers;

import com.example.demo.entities.Operation;
import com.example.demo.services.OperationService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operations")
@CrossOrigin(origins = "http://localhost:4200")
public class OperationController {

	 @Autowired
	    private OperationService operationService;
	    @GetMapping
	    @CrossOrigin(origins = "http://localhost:4200")
	    public List<Operation> getAllOperations() {
	        return operationService.getAllOperations();
	    }
	    @PostMapping("/versement")
	    @CrossOrigin(origins = "http://localhost:4200")
	    public Operation effectuerVersement(@RequestBody Map<String, Object> request) {
	        Long compteId = ((Number) request.get("compteId")).longValue();
	        double montant = ((Number) request.get("montant")).doubleValue();
	        return operationService.effectuerVersement(compteId, montant);
	    }

	    @PostMapping("/retrait")
	    @CrossOrigin(origins = "http://localhost:4200")
	    public Operation effectuerRetrait(@RequestBody Map<String, Object> request) {
	    	Long compteId = ((Number) request.get("compteId")).longValue();
	        double montant = ((Number) request.get("montant")).doubleValue();
	        return operationService.effectuerRetrait(compteId, montant);
	        
	    }

	    @PostMapping("/virement")
	    @CrossOrigin(origins = "http://localhost:4200")
	    public void effectuerVirement(@RequestBody Map<String, Object> request) {
	    	Long compteSourceId = ((Number) request.get("compteSourceId")).longValue();
	    	Long compteDestinataireId = ((Number) request.get("compteDestinataireId")).longValue();
	        double montant = ((Number) request.get("montant")).doubleValue();
	         operationService.effectuerVirement(compteSourceId, compteDestinataireId, montant);
	    }

}
