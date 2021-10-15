package org.sid.datas;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryResponse {

	private Long id;

	private String nom;

	private String photo;

	private String description;

	public CategoryResponse(Long id, String nom, String photo, String description) {
		this.id = id;
		this.nom = nom;
		this.photo = photo;
		this.description = description;
	}

	@Override
	public String toString() {
		return "{" +
			"\"id\":" + id + "," +
			"\"nom\":\"" + nom + "\"," +
			"\"photo\":\"" + photo + "\"," +
			"\"description\":\""+ description + "\"" +
		"}";
	}

}
