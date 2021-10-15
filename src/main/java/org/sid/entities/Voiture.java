package org.sid.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "voitures")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Voiture {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String nom;
	private String marque;
	private String modele;
	private int annee;
	private double tarif;
	private String description;

	private String photoDefault;

	@Column(columnDefinition="text")
	private String photos;

	@ManyToOne
	private Category category;

	@ManyToOne
	private User userCreated;

	public Voiture(String nom, String marque, String modele, int annee, double tarif, String description, String photoDefault, Category category, User userCreated) {
		this.nom = nom;
		this.marque = marque;
		this.modele = modele;
		this.annee = annee;
		this.tarif = tarif;
		this.description = description;
		this.photoDefault = photoDefault;
		this.category = category;
		this.userCreated = userCreated;
	}

}
