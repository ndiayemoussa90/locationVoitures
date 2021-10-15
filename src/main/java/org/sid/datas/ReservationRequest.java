package org.sid.datas;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@NoArgsConstructor
@Getter
@Setter
public class ReservationRequest {

	private Long id;

	private Long idCategory;

	private String adresseDepart;
	private Date dateDepart;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private Date heureDepart;

	private String adresseRetour;
	private Date dateRetour;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private Date heureRetour;

	private byte nombrePersonne;
	private boolean avecChauffeur;

	@Override
	public String toString() {
		return "{" +
			"\"id\":" + id + "," +
			"\"idCategory\":" + idCategory + "," +
			"\"adresseDepart\":\"" + adresseDepart + "\"," +
			"\"dateDepart\":\"" + dateDepart + "\"," +
			"\"heureDepart\":\""+ heureDepart + "\"" +
			"\"adresseRetour\":\"" + adresseRetour + "\"," +
			"\"dateRetour\":\"" + dateRetour + "\"," +
			"\"heureRetour\":\"" + heureRetour + "\"," +
			"\"nombrePersonne\":" + nombrePersonne + "," +
			"\"avecChauffeur\":"+ avecChauffeur +
		"}";
	}

}
