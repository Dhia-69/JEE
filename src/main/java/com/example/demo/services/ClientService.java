package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Client;
import com.example.demo.entities.CompteBancaire;
import com.example.demo.repositories.ClientRepository;

public interface ClientService {

	 void CreerClient(Client client);
	
	  Client getClientById(Long id);

	    Client ModifierClient(Long id, Client newClientInfo);
	 
	    List<Client> getAllClients();
	    
	    List<CompteBancaire> getComptesClient(Long id);
	    
	    void deleteClient(Long id); // Ajout de la méthode deleteClient

	
}
