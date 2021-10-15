package org.sid.services;



import org.sid.datas.*;

import java.util.List;



public interface UtilisateurService {

    /////////////////////////////////////////
    List<ReservationResponse> listReservations();

    void annulerReservations(long id);


    boolean saveReservation(ReservationRequest reservationRequest);


    /////////////////////////////////////////
    List<CategoryResponse> getCategories();

    List<VoitureResponse> getVoitures();

    VoitureResponse detailVoiture(Long id);

    boolean saveClient(RegistrationRequest registrationRequest);


}
