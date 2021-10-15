package org.sid.repositories;

import org.sid.entities.Reservation;
import org.sid.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    List<Reservation> findByStatusReservation(byte statusReservation);

    List<Reservation> findByUserCreated(User userCreated);

    @Query("SELECT DISTINCT r.userCreated FROM Reservation r WHERE r.gererBy = ?1 ORDER BY r.id DESC")
    List<User> getClients(User gererBy);


}
