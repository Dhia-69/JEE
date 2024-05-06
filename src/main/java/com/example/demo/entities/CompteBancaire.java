package com.example.demo.entities;
//Importez les classes nécessaires si elles ne sont pas déjà importées


//Ajoutez le champ type à votre classe CompteBancaire


import java.sql.Date;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class CompteBancaire {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Date dateCreation;
	private double solde;
	private CompteStatut etat;
	
	@JsonBackReference
	@ManyToOne
    private Client client;
	 @OneToMany
	 private List<Operation> operations;
}
