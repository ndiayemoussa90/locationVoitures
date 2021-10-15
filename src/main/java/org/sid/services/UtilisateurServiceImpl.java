package org.sid.services;

import org.sid.auth.AuthenticateService;
import org.sid.datas.*;
import org.sid.entities.*;
import org.sid.repositories.CategoryRepository;
import org.sid.repositories.ReservationRepository;
import org.sid.repositories.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final AuthenticateService authenticateService;
    private final ReservationRepository reservationRepository;
    private final CategoryRepository categoryRepository;
    private final VoitureRepository voitureRepository;


    @Autowired
    public UtilisateurServiceImpl(
        AuthenticateService authenticateService,
        ReservationRepository reservationRepository,
        CategoryRepository categoryRepository,
        VoitureRepository voitureRepository
    ) {
        this.authenticateService = authenticateService;
        this.reservationRepository = reservationRepository;
        this.categoryRepository = categoryRepository;
        this.voitureRepository = voitureRepository;
    }


    /////////////////////////////////////////////////////////////////
    @Override
    public List<ReservationResponse> listReservations() {
        final Authenticate authenticate = authenticateService.getAuthenticate();
        //
        List<ReservationResponse> reservationResponses = new ArrayList<>();
        reservationRepository.findByUserCreated(authenticate.getUser()).forEach( reservation -> {
            reservationResponses.add(new ReservationResponse(
                    reservation.getId(), reservation.getStatusReservation(), reservation.getDateReservation(),
                    reservation.getAdresseDepart(), reservation.getDateDepart(), reservation.getHeureDepart(),
                    reservation.getAdresseRetour(), reservation.getDateRetour(), reservation.getHeureRetour(),
                    reservation.getNombrePersonne(), reservation.isAvecChauffeur(),
                    new CategoryResponse(
                            reservation.getCategory().getId(), reservation.getCategory().getNom(),
                            reservation.getCategory().getPhoto(), reservation.getCategory().getDescription()
                    )
            ));
        });
        return reservationResponses;
    }


    @Override
    public void annulerReservations(long id) {
        final Authenticate authenticate = authenticateService.getAuthenticate();
        //
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("La reservation id " + id + " n'existe pas.")
        );
        reservation.setStatusReservation((byte)3);
        reservation.setGererBy(authenticate.getUser());
        reservationRepository.save(reservation);
    }


    ///////////////////////////////////
    @Override
    public List<VoitureResponse> getVoitures() {
        List<VoitureResponse> voitureResponseList = new ArrayList<>();
        voitureRepository.findAll().forEach( voiture -> {
            voitureResponseList.add(new VoitureResponse(
                voiture.getId(), voiture.getNom(), voiture.getMarque(), voiture.getModele(),
                voiture.getAnnee(), voiture.getTarif(), voiture.getDescription(),
                voiture.getPhotoDefault(), new ArrayList<>(), voiture.getCategory().getId(),
                voiture.getCategory().getNom(), voiture.getCategory().getPhoto(), new UserResponse(
                    voiture.getUserCreated().getId(), voiture.getUserCreated().getFirstName(),
                    voiture.getUserCreated().getLastName(), voiture.getUserCreated().getAddress(),
                    voiture.getUserCreated().getPhoneNumber(), voiture.getUserCreated().getCni()
                )
            ));
        });
        return voitureResponseList;
    }

    @Override
    public VoitureResponse detailVoiture(Long id) {
        Voiture voiture = voitureRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("La voiture id " + id + " n'existe pas.")
        );
        List<String> photoList = new ArrayList<>();
        if((voiture.getPhotos() == null) || voiture.getPhotos().isEmpty()){
            photoList.add(voiture.getPhotoDefault());
        } else{
            photoList = Arrays.stream( voiture.getPhotos().split(",") ).map(String::new).collect(Collectors.toList());
        }
        return new VoitureResponse(
            voiture.getId(), voiture.getNom(), voiture.getMarque(), voiture.getModele(), voiture.getAnnee(),
            voiture.getTarif(), voiture.getDescription(), voiture.getPhotoDefault(), photoList,
            voiture.getCategory().getId(), voiture.getCategory().getNom(), voiture.getCategory().getPhoto(),
            new UserResponse(
                voiture.getUserCreated().getId(), voiture.getUserCreated().getFirstName(),
                voiture.getUserCreated().getLastName(), voiture.getUserCreated().getAddress(),
                voiture.getUserCreated().getPhoneNumber(), voiture.getUserCreated().getCni()
            )
        );
    }

    @Override
    public List<CategoryResponse> getCategories() {
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        categoryRepository.findAll().forEach( category -> {
            categoryResponseList.add(new CategoryResponse(
                    category.getId(), category.getNom(), category.getPhoto(), category.getDescription()
            ));
        });
        return categoryResponseList;
    }

    @Override
    public boolean saveReservation(ReservationRequest reservationRequest) {
        final Authenticate authenticate = authenticateService.getAuthenticate();
        if(authenticate == null){
            return  false;
        }
        System.out.println(authenticate.getUser().toString());
        //
        final Category category = categoryRepository.findById(reservationRequest.getIdCategory()).orElseThrow(
                () -> new IllegalStateException("La catégorie id " + reservationRequest.getIdCategory() + " n'existe pas.")
        );
        //
        reservationRepository.save(
            new Reservation(
                reservationRequest.getAdresseDepart(), reservationRequest.getDateDepart(),
                reservationRequest.getHeureDepart(), reservationRequest.getAdresseRetour(),
                reservationRequest.getDateRetour(), reservationRequest.getHeureRetour(),
                category, authenticate.getUser()
            )
        );
        //
        return true;
    }

    @Override
    public boolean saveClient(RegistrationRequest registrationRequest) {
        registrationRequest.setRoles("ROLE_UTILISATEUR");
        Authenticate result = authenticateService.save(registrationRequest);
        return result != null ? true : false;
    }



}
