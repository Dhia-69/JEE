package com.example.demo.controllers;

import com.example.demo.entities.CompteBancaire;
import com.example.demo.entities.CompteCourant;
import com.example.demo.entities.CompteEpargne;
import com.example.demo.entities.Operation;
import com.example.demo.services.CompteBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
public class CompteBancaireController {

    @Autowired
    private CompteBancaireService compteBancaireService;

    @PostMapping("/creer")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<CompteBancaire> creerCompte(@RequestBody CompteBancaire compte) {
        CompteBancaire createdCompte = compteBancaireService.createCompte(compte);
        return new ResponseEntity<>(createdCompte, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<CompteBancaire> getCompteById(@PathVariable Long id) {
        CompteBancaire compte = compteBancaireService.getCompteById(id);
        return ResponseEntity.ok().body(compte);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<CompteBancaire>> getAllComptes() {
        List<CompteBancaire> comptes = compteBancaireService.getAllComptes();
        return ResponseEntity.ok().body(comptes);
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> updateCompte(@PathVariable Long id, @RequestBody CompteBancaire newCompteInfo) {
        compteBancaireService.updateCompte(id, newCompteInfo);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteCompte(@PathVariable Long id) {
        compteBancaireService.deleteCompte(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/versement")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> effectuerVersement(@PathVariable Long id, @RequestParam double montant) {
        compteBancaireService.effectuerVersement(id, montant);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/retrait")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> effectuerRetrait(@PathVariable Long id, @RequestParam double montant) {
        compteBancaireService.effectuerRetrait(id, montant);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/virement")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> effectuerVirement(@RequestParam Long compteSourceId, @RequestParam Long compteDestinationId, @RequestParam double montant) {
        compteBancaireService.effectuerVirement(compteSourceId, compteDestinationId, montant);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/operations")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Operation>> getHistoriqueOperations(@PathVariable Long id) {
        List<Operation> operations = compteBancaireService.getHistoriqueOperations(id);
        return ResponseEntity.ok().body(operations);
    }
    @GetMapping("/{id}/client-nom-prenom")
    @CrossOrigin(origins = "http://localhost:4200")
    public  ResponseEntity<String> getClientNomPrenomByCompteId(@PathVariable Long id) {
        String nomPrenom = compteBancaireService.getClientNomPrenomByCompteId(id);
        if (nomPrenom != null) {
            return ResponseEntity.ok(nomPrenom);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/by-client/{clientId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<CompteBancaire>> getComptesByClientId(@PathVariable Long clientId) {
        // Récupération des comptes par ID de client
        List<CompteBancaire> comptes = compteBancaireService.getComptesByClientId(clientId);
        return new ResponseEntity<>(comptes, HttpStatus.OK);
    }
    @PostMapping("/sauvegarder-compte-courant")
    @CrossOrigin(origins = "http://localhost:4200")
    public CompteBancaire saveCompteCourant(@RequestBody CompteCourant compteCourant) {
    	return compteBancaireService.createCompte(compteCourant);
    	}
    @PostMapping("/sauvegarder-compte-epargne")
    @CrossOrigin(origins = "http://localhost:4200")
    public CompteBancaire saveCompteEpargne(@RequestBody CompteEpargne compteEpargne) {
        return compteBancaireService.createCompte(compteEpargne);
    }
}
