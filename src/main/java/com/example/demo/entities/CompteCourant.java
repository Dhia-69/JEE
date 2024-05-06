package com.example.demo.entities;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class CompteCourant extends CompteBancaire {
	private double decouvert;

}
