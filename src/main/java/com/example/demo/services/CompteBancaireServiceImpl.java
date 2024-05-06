package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.CompteBancaire;
import com.example.demo.entities.CompteCourant;
import com.example.demo.entities.CompteEpargne;
import com.example.demo.entities.Operation;
import com.example.demo.repositories.CompteBancaireRepository;
import com.example.demo.repositories.OperationRepository;

@Service
public class CompteBancaireServiceImpl implements CompteBancaireService {
	  @Autowired
	    private CompteBancaireRepository compteBancaireRepository;
	  

	    @Override
	    public CompteBancaire createCompte(CompteBancaire compte) {
	        return compteBancaireRepository.save(compte);
	    }

	    @Override
	    public CompteBancaire getCompteById(Long id) {
	        return compteBancaireRepository.findById(id).orElse(null);
	    }

	    @Override
	    public List<CompteBancaire> getAllComptes() {
	        return compteBancaireRepository.findAll();
	    }

	    @Override
	    public void updateCompte(Long id, CompteBancaire newCompteInfo) {
	        CompteBancaire existingCompte = compteBancaireRepository.findById(id).orElse(null);
	        if (existingCompte != null) {
	            existingCompte.setDateCreation(newCompteInfo.getDateCreation());
	            existingCompte.setSolde(newCompteInfo.getSolde());
	            existingCompte.setEtat(newCompteInfo.getEtat());
	            compteBancaireRepository.save(existingCompte);
	        }
	    }

	    @Override
	    public void deleteCompte(Long id) {
	    	compteBancaireRepository.deleteById(id);
	    }
	    @Override
	    public void effectuerVersement(Long id, double montant) {
	        CompteBancaire compte = compteBancaireRepository.findById(id).orElse(null);
	        if (compte != null) {
	            compte.setSolde(compte.getSolde() + montant);
	            compteBancaireRepository.save(compte);
	            System.out.println("Versement effectué avec succès.");
	        } else {
	            System.out.println("Compte bancaire non trouvé.");
	        }
	    }

	    @Override
	    public void effectuerRetrait(Long id, double montant) {
	        CompteBancaire compte = compteBancaireRepository.findById(id).orElse(null);
	        if (compte != null) {
	            if (compte.getSolde() >= montant) {
	                compte.setSolde(compte.getSolde() - montant);
	                compteBancaireRepository.save(compte);
	                System.out.println("Retrait effectué avec succès.");
	            } else {
	                System.out.println("Solde insuffisant pour effectuer le retrait.");
	            }
	        } else {
	            System.out.println("Compte bancaire non trouvé.");
	        }
	    }

	    @Override
	    public void effectuerVirement(Long compteSourceId, Long compteDestinationId, double montant) {
	        effectuerRetrait(compteSourceId, montant);
	        effectuerVersement(compteDestinationId, montant);
	        System.out.println("Virement effectué avec succès.");
	    }
	    @Override
	    public List<Operation> getHistoriqueOperations(Long id) {
	        CompteBancaire compte = compteBancaireRepository.findById(id).orElse(null);
	        if (compte != null) {
	            return compte.getOperations();
	        }
	        return null;
	    }

		
		public String getClientNomPrenomByCompteId(Long compteId) {
			CompteBancaire compteBancaire = compteBancaireRepository.findById(compteId).orElse(null);
	        if (compteBancaire != null) {
	        	return compteBancaire.getClient().getNom()+" "+compteBancaire.getClient().getPrenom();
	        } else {
	            return null;
	        }
			
		}
		public List<CompteBancaire> getComptesByClientId(Long clientId) {
	        // Récupération de tous les comptes
	        List<CompteBancaire> comptes = compteBancaireRepository.findAll();
	        
	        // Filtrer les comptes par ID de client
	        return comptes.stream()
	                .filter(compte -> compte.getClient().getId().equals(clientId))
	                .collect(Collectors.toList());
	    }

		@Override
		
	    public CompteBancaire createCompte(CompteCourant compte) {
	    	return compteBancaireRepository.save(compte);
	         
	    }
	    @Override
	    public CompteBancaire createCompte(CompteEpargne compte) {
	    	return compteBancaireRepository.save(compte);
	    }
	    
	   
	}

