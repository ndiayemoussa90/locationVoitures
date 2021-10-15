package org.sid.datas;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class VoitureResponse {

	private Long id;

	private String nom;
	private String marque;
	private String modele;
	private int annee;
	private double tarif;
	private String description;

	private String photoDefault;

	private List<String> photosList = new ArrayList<>();

	private Long idCategory;
	private String nomCategory;
	private String photoCategory;

	private UserResponse userCreated;


	public VoitureResponse(
		Long id, String nom, String marque, String modele, int annee, double tarif,
		String description, String photoDefault, List<String> photosList, Long idCategory,
		String nomCategory, String photoCategory, UserResponse userCreated
	) {
		this.id = id;
		this.nom = nom;
		this.marque = marque;
		this.modele = modele;
		this.annee = annee;
		this.tarif = tarif;
		this.description = description;
		this.photoDefault = photoDefault;
		this.photosList = photosList;
		this.idCategory = idCategory;
		this.nomCategory = nomCategory;
		this.photoCategory = photoCategory;
		this.userCreated = userCreated;
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
			"\"photosList\":\""+ photosList + "\"," +
			"\"idCategory\":"+ idCategory + "," +
			"\"nomCategory\":\""+ nomCategory + "\"," +
			"\"photoCategory\":\""+ photoCategory + "\"" +
		"}";
	}

}
