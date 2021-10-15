package org.sid.datas;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@NoArgsConstructor
@Getter
@Setter
public class ReservationResponse {

	private Long id;

	private byte statusReservation; //1: Encours; 2: Valider; 3: Annuler

	private Date dateReservation;

	private String adresseDepart;
	private Date dateDepart;
	private Date heureDepart;

	private String adresseRetour;
	private Date dateRetour;
	private Date heureRetour;

	private byte nombrePersonne;
	private boolean avecChauffeur;

	private CategoryResponse category;

	private UserResponse userCreated;

	private UserResponse gererBy;


	public ReservationResponse(
			Long id, byte statusReservation, Date dateReservation,
			String adresseDepart, Date dateDepart, Date heureDepart,
			String adresseRetour, Date dateRetour, Date heureRetour,
			byte nombrePersonne, boolean avecChauffeur, CategoryResponse category
	) {
		this.id = id;
		this.statusReservation = statusReservation;
		this.dateReservation = dateReservation;
		this.adresseDepart = adresseDepart;
		this.dateDepart = dateDepart;
		this.heureDepart = heureDepart;
		this.adresseRetour = adresseRetour;
		this.dateRetour = dateRetour;
		this.heureRetour = heureRetour;
		this.nombrePersonne = nombrePersonne;
		this.avecChauffeur = avecChauffeur;
		this.category = category;
	}

	public ReservationResponse(
		Long id, byte statusReservation, Date dateReservation,
		String adresseDepart, Date dateDepart, Date heureDepart,
		String adresseRetour, Date dateRetour, Date heureRetour,
		byte nombrePersonne, boolean avecChauffeur,
		CategoryResponse category, UserResponse userCreated, UserResponse gererBy
	) {
		this.id = id;
		this.statusReservation = statusReservation;
		this.dateReservation = dateReservation;
		this.adresseDepart = adresseDepart;
		this.dateDepart = dateDepart;
		this.heureDepart = heureDepart;
		this.adresseRetour = adresseRetour;
		this.dateRetour = dateRetour;
		this.heureRetour = heureRetour;
		this.nombrePersonne = nombrePersonne;
		this.avecChauffeur = avecChauffeur;
		this.category = category;
		this.userCreated = userCreated;
		this.gererBy = gererBy;
	}

	@Override
	public String toString() {
		return "{\"id\":" + id + "}";
	}

}
