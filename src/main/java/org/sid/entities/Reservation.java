package org.sid.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservations")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Reservation{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	//Info reservation
	private byte statusReservation = 1; //1: Encours; 2: Valider; 3: Annuler; 4: Supprimer

	private Date dateReservation = new Date();

	private String adresseDepart;
	private Date dateDepart;
	private Date heureDepart;

	private String adresseRetour;
	private Date dateRetour;
	private Date heureRetour;

	private byte nombrePersonne = 0;
	private boolean avecChauffeur = false;

	@ManyToOne
	private Category category;

	@ManyToOne
	private User userCreated;

	@ManyToOne
	private User gererBy;

	public Reservation(
		String adresseDepart, Date dateDepart, Date heureDepart,
		String adresseRetour, Date dateRetour, Date heureRetour,
		Category category, User userCreated
	) {
		this.adresseDepart = adresseDepart;
		this.dateDepart = dateDepart;
		this.heureDepart = heureDepart;
		this.adresseRetour = adresseRetour;
		this.dateRetour = dateRetour;
		this.heureRetour = heureRetour;
		this.category = category;
		this.userCreated = userCreated;
	}
}
