package com.example.demo.services;

import com.example.demo.entities.CompteBancaire;
import com.example.demo.entities.Operation;
import com.example.demo.entities.TypeOperation;
import com.example.demo.repositories.OperationRepository;
import com.example.demo.repositories.CompteBancaireRepository;

import jakarta.transaction.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {
	@Autowired
    private  CompteBancaireRepository CompteBancaireRepository;
	@Autowired

    private  OperationRepository operationRepository;


    @Override
    @Transactional
    public Operation effectuerVersement(Long compteId, double montant) {
        Operation operation = new Operation(null, new Date(), montant, TypeOperation.CREDIT);
        
        CompteBancaire compte = CompteBancaireRepository.findById(compteId)
                .orElseThrow(() -> new IllegalArgumentException("Compte introuvable"));
        compte.setSolde(compte.getSolde() + montant);
        // Save Operation first to get an ID.
        operation = operationRepository.save(operation);
        List<Operation> s1 = compte.getOperations();
        s1.add(operation);
        compte.setOperations(s1);
        CompteBancaireRepository.save(compte);   
        return operation ;
    }

    @Override
    @Transactional
    public Operation effectuerRetrait(Long compteId, double montant) {
        Operation operation = new Operation(null, new Date(), montant, TypeOperation.DEBIT);
        CompteBancaire compte = CompteBancaireRepository.findById(compteId)
                .orElseThrow(() -> new IllegalArgumentException("Compte introuvable"));
        operationRepository.save(operation);
        if(compte.getSolde() >= montant) {
            compte.setSolde(compte.getSolde() - montant);
            List<Operation> s1 = compte.getOperations();
            s1.add(operation);
            compte.setOperations(s1);
            CompteBancaireRepository.save(compte);
            return operation;
        } else {
            throw new IllegalArgumentException("Solde insuffisant");
        }
    }

    @Override
    @Transactional
    public void effectuerVirement(Long compteSourceId, Long compteDestinataireId, double montant) {
        CompteBancaire compteSource = CompteBancaireRepository.findById(compteSourceId)
                .orElseThrow(() -> new IllegalArgumentException("Compte source introuvable"));
        CompteBancaire compteDestinataire = CompteBancaireRepository.findById(compteDestinataireId)
                .orElseThrow(() -> new IllegalArgumentException("Compte destinataire introuvable"));

        if(compteSource.getSolde() >= montant) {
            compteSource.setSolde(compteSource.getSolde() - montant);
            compteDestinataire.setSolde(compteDestinataire.getSolde() + montant);
            Operation debitOperation = new Operation(null, new Date(), montant, TypeOperation.DEBIT);
            Operation creditOperation = new Operation(null, new Date(), montant, TypeOperation.CREDIT);
            
            operationRepository.save(debitOperation);
            operationRepository.save(creditOperation);
            List<Operation> s1 = compteSource.getOperations();
            s1.add(debitOperation);
            compteSource.setOperations(s1);
            List<Operation> s2 = compteDestinataire.getOperations();
            s2.add(creditOperation);
            compteDestinataire.setOperations(s2);
            CompteBancaireRepository.save(compteSource);
            CompteBancaireRepository.save(compteDestinataire);
            
            
        } else {
            throw new IllegalArgumentException("Solde insuffisant pour le virement");
        }
    }
    @Override
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

	

  
}
