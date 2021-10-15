package org.sid.services;


import org.sid.datas.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface GerantService {


    /////////////////////////////////////////
    List<UserResponse> listClients();


    /////////////////////////////////////////
    List<ReservationResponse> listReservations();

    List<ReservationResponse> listReservationsEncours();

    void annulerReservationsEncours(long id);

    void validerReservationsEncours(long id);


    /////////////////////////////////////////
    List<CategoryResponse> getCategories();

    void saveVoiture(VoitureRequest voitureRequest);

    void updateVoiture(long id, VoitureRequest voitureRequest);

    void deleteVoiture(long id);

    List<VoitureResponse> getVoitures();

    VoitureResponse detailVoiture(Long id);

    void addPhotoVoiture(Long id, MultipartFile file);

    void deletePhotoVoiture(Long id, String photo);


}
