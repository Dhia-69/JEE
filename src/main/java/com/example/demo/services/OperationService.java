package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Operation;

public interface OperationService {
	

	 Operation effectuerVersement(Long compteId, double montant);
		Operation effectuerRetrait(Long compteId, double montant);
	    void effectuerVirement(Long compteSourceId, Long compteDestinataireId, double montant);
	    public List<Operation> getAllOperations();
	    
}
