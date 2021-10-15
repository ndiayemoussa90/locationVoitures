package org.sid.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.sid.entities.Authenticate;
import org.sid.entities.User;


import java.util.List;
import java.util.Optional;

@Repository
public interface AuthenticateRepository extends JpaRepository<Authenticate, Long> {

    Optional<Authenticate> findByUsername(String username);

    @Query("SELECT auth FROM Authenticate auth WHERE auth.user IN(SELECT DISTINCT r.userCreated FROM Reservation r) ORDER BY auth.id DESC")
    List<Authenticate> getClients();

    @Query("SELECT auth FROM Authenticate auth WHERE auth.accountNonLocked = true ORDER BY auth.id DESC")
    List<Authenticate> allUsers();

    @Query("SELECT auth FROM Authenticate auth WHERE auth.accountNonLocked = true AND auth.user IN(SELECT u FROM User u WHERE u.userCreated = ?1) ORDER BY auth.id DESC")
    List<Authenticate> listUsers(User userCreated);

    @Query("SELECT auth FROM Authenticate auth WHERE auth.accountNonLocked = true AND auth.roles = ?1 ORDER BY auth.id DESC")
    List<Authenticate> listUsersByRoles(String roles);

    @Query("SELECT auth FROM Authenticate auth WHERE auth.accountNonLocked = true AND auth.roles = ?1 AND auth.user IN(SELECT u FROM User u WHERE u.userCreated = ?2) ORDER BY auth.id DESC")
    List<Authenticate> listUsersByRolesAndUserCreated(String roles, User userCreated);

    @Query("SELECT auth FROM Authenticate auth WHERE auth.accountNonLocked = true AND auth.id = ?1 AND auth.user IN(SELECT u FROM User u WHERE u.userCreated = ?2) ORDER BY auth.id DESC")
    Optional<Authenticate> getUserById(long id, User userCreated);

    Authenticate findFirstByUsername(String username);

}
