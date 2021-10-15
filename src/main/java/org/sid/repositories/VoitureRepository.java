package org.sid.repositories;

import org.sid.entities.Category;
import org.sid.entities.User;
import org.sid.entities.Voiture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long> {
	
	public Page<Voiture> findByMarqueContains(String mc, Pageable pageable);

	public int countByCategory(Category category);

	public List<Voiture> findByUserCreated(User userCreated);


}
