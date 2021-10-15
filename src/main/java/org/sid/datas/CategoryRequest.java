package org.sid.datas;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryRequest {

	private Long id;

	private String nom;

	private String photo;

	private String description;

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
