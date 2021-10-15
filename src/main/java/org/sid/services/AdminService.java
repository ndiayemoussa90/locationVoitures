package org.sid.services;

import org.sid.datas.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface AdminService {

    /////////////////////////////////////////
    List<UserResponse> listClients();


    /////////////////////////////////////////
    List<ReservationResponse> listReservations();

    List<ReservationResponse> listReservationsEncours();


    /////////////////////////////////////////
    boolean saveGerant(RegistrationRequest registrationRequest);

    List<UserResponse> listGerants();

    void deleteGerantById(long id);

    boolean updateGerantById(long id, RegistrationRequest registrationRequest);


    /////////////////////////////////////////
    void saveCategory(String nom, String description, MultipartFile file);

    List<CategoryResponse> getCategories();

    void deleteCategory(Long id);


    /////////////////////////////////////////
    void saveVoiture(VoitureRequest voitureRequest);

    void updateVoiture(long id, VoitureRequest voitureRequest);

    void deleteVoiture(long id);

    List<VoitureResponse> getVoitures();

    VoitureResponse detailVoiture(Long id);

    void addPhotoVoiture(Long id, MultipartFile file);

    void deletePhotoVoiture(Long id, String photo);



}
