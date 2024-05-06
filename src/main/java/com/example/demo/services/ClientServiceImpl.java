package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Client;
import com.example.demo.entities.CompteBancaire;
import com.example.demo.repositories.ClientRepository;



@Service
public class ClientServiceImpl implements ClientService {
	// Injection de dépendance du repository
    @Autowired
    private ClientRepository clientRepository;

    // Implémentation de la méthode CreerClient
    @Override
    public void CreerClient(Client client) {
       clientRepository.save(client);
       System.out.println("Enregistrement client avec succès");
    }
 // Méthode pour récupérer un client par son identifiant
    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }
    
    @Override
    public Client ModifierClient(Long id, Client newClientInfo) {
        Client existingClient = clientRepository.findById(id).orElse(null);
        if (existingClient != null) {
            // Mettre à jour les informations du client existant avec les nouvelles informations
            existingClient.setNom(newClientInfo.getNom());
            existingClient.setPrenom(newClientInfo.getPrenom());
            existingClient.setEmail(newClientInfo.getEmail());
            // Enregistrer les modifications
            clientRepository.save(existingClient);
            System.out.println("Les informations du client ont été mises à jour avec succès");
            return existingClient; // Retourner le client mis à jour
        } else {
            System.out.println("Impossible de modifier le client : client introuvable");
            return null; // Retourner null si le client n'est pas trouvé
        }
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    // Méthode pour récupérer les comptes d'un client
    @Override
    public List<CompteBancaire> getComptesClient(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client != null) {
            return client.getComptes();
        }
        return null;
    }
	@Override
	public void deleteClient(Long id) {
		clientRepository.deleteById(id);
		
	}
}

