package org.sid.datas;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class VoitureRequest {

	private Long id;

	private String nom;
	private String marque;
	private String modele;
	private int annee;
	private double tarif;
	private String description;

	private String photoDefault;

	private Long idCategory;

	public VoitureRequest(String nom, String marque, String modele, int annee, double tarif, String description, String photoDefault, Long idCategory) {
		this.nom = nom;
		this.marque = marque;
		this.modele = modele;
		this.annee = annee;
		this.tarif = tarif;
		this.description = description;
		this.photoDefault = photoDefault;
		this.idCategory = idCategory;
	}

	@Override
	public String toString() {
		return "{" +
			"\"id\":" + id + "," +
			"\"nom\":\"" + nom + "\"," +
			"\"marque\":\"" + marque + "\"," +
			"\"modele\":\"" + modele + "\"," +
			"\"annee\":"+ annee + "," +
			"\"tarif\":"+ tarif + "," +
			"\"description\":\""+ description + "\"," +
			"\"photoDefault\":\""+ photoDefault + "\"," +
			"\"idCategory\":"+ idCategory +
		"}";
	}

}
