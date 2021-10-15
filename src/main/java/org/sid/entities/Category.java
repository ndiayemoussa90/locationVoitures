package org.sid.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Category{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String nom;

	private String photo;

	private String description;

	@ManyToOne
	private User userCreated;

	public Category(String nom, String description, String photo, User userCreated) {
		this.nom = nom;
		this.description = description;
		this.photo = photo;
		this.userCreated = userCreated;
	}

}
