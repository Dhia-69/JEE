package com.example.demo.services;

import java.util.List;


import com.example.demo.entities.CompteBancaire;
import com.example.demo.entities.CompteCourant;
import com.example.demo.entities.CompteEpargne;
import com.example.demo.entities.Operation;

public interface CompteBancaireService {
	 CompteBancaire createCompte(CompteBancaire compte);
	 
		CompteBancaire createCompte(CompteCourant compte);
		CompteBancaire createCompte(CompteEpargne compte);
	    
	    CompteBancaire getCompteById(Long id);
	    
	    List<CompteBancaire> getAllComptes();
	    
	    void updateCompte(Long id, CompteBancaire newCompteInfo);
	    
	    void deleteCompte(Long id);
	    void effectuerVersement(Long id, double montant);
	    void effectuerRetrait(Long id, double montant);
	    void effectuerVirement(Long compteSourceId, Long compteDestinationId, double montant);
	    List<Operation> getHistoriqueOperations(Long id);
	    public String getClientNomPrenomByCompteId(Long compteId);
	    public List<CompteBancaire> getComptesByClientId(Long clientId);
	 
	    
}
