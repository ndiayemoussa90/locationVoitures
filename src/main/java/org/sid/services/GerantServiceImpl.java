package org.sid.services;

import org.sid.auth.AuthenticateService;
import org.sid.datas.*;
import org.sid.entities.*;
import org.sid.files.StorageException;
import org.sid.files.StorageService;
import org.sid.repositories.CategoryRepository;
import org.sid.repositories.ReservationRepository;
import org.sid.repositories.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.sid.auth.AuthenticateDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class GerantServiceImpl implements GerantService {

    private final AuthenticateService authenticateService;
    private final StorageService storageService;
    private final CategoryRepository categoryRepository;
    private final VoitureRepository voitureRepository;
    private final ReservationRepository reservationRepository;


    @Autowired
    public GerantServiceImpl(
            AuthenticateService authenticateService,
            StorageService storageService,
            CategoryRepository categoryRepository,
            ReservationRepository reservationRepository,
            VoitureRepository voitureRepository
    ) {
        this.authenticateService = authenticateService;
        this.storageService = storageService;
        this.categoryRepository = categoryRepository;
        this.voitureRepository = voitureRepository;
        this.reservationRepository = reservationRepository;
    }

    /////////////////////////////////////////////////////////////////
    @Override
    public List<ReservationResponse> listReservations() {

        List<ReservationResponse> reservationResponses = new ArrayList<>();
        reservationRepository.findAll().forEach( reservation -> {
            final UserResponse gerant = (reservation.getGererBy() == null) ? new UserResponse() : new UserResponse(
                    reservation.getGererBy().getId(),
                    reservation.getGererBy().getFirstName(),
                    reservation.getGererBy().getLastName(),
                    reservation.getGererBy().getAddress(),
                    reservation.getGererBy().getPhoneNumber(),
                    reservation.getGererBy().getCni()
            );

            reservationResponses.add(new ReservationResponse(
                    reservation.getId(), reservation.getStatusReservation(), reservation.getDateReservation(),
                    reservation.getAdresseDepart(), reservation.getDateDepart(), reservation.getHeureDepart(),
                    reservation.getAdresseRetour(), reservation.getDateRetour(), reservation.getHeureRetour(),
                    reservation.getNombrePersonne(), reservation.isAvecChauffeur(),
                    new CategoryResponse(
                            reservation.getCategory().getId(), reservation.getCategory().getNom(),
                            reservation.getCategory().getPhoto(), reservation.getCategory().getDescription()
                    ),
                    new UserResponse(
                            reservation.getUserCreated().getId(),
                            reservation.getUserCreated().getFirstName(),
                            reservation.getUserCreated().getLastName(),
                            reservation.getUserCreated().getAddress(),
                            reservation.getUserCreated().getPhoneNumber(),
                            reservation.getUserCreated().getCni()
                    ),
                    gerant
            ));
        });
        return reservationResponses;
    }

    @Override
    public List<ReservationResponse> listReservationsEncours() {
        List<ReservationResponse> reservationResponses = new ArrayList<>();
        reservationRepository.findByStatusReservation((byte) 1).forEach( reservation -> {

            final UserResponse gerant = (reservation.getGererBy() == null) ? new UserResponse() : new UserResponse(
                    reservation.getGererBy().getId(),
                    reservation.getGererBy().getFirstName(),
                    reservation.getGererBy().getLastName(),
                    reservation.getGererBy().getAddress(),
                    reservation.getGererBy().getPhoneNumber(),
                    reservation.getGererBy().getCni()
            );

            reservationResponses.add(new ReservationResponse(
                    reservation.getId(), reservation.getStatusReservation(), reservation.getDateReservation(),
                    reservation.getAdresseDepart(), reservation.getDateDepart(), reservation.getHeureDepart(),
                    reservation.getAdresseRetour(), reservation.getDateRetour(), reservation.getHeureRetour(),
                    reservation.getNombrePersonne(), reservation.isAvecChauffeur(),
                    new CategoryResponse(
                            reservation.getCategory().getId(), reservation.getCategory().getNom(),
                            reservation.getCategory().getPhoto(), reservation.getCategory().getDescription()
                    ),
                    new UserResponse(
                            reservation.getUserCreated().getId(),
                            reservation.getUserCreated().getFirstName(),
                            reservation.getUserCreated().getLastName(),
                            reservation.getUserCreated().getAddress(),
                            reservation.getUserCreated().getPhoneNumber(),
                            reservation.getUserCreated().getCni()
                    ),
                    gerant
            ));
        });
        return reservationResponses;
    }


    @Override
    public void annulerReservationsEncours(long id) {
        final Authenticate authenticate = authenticateService.getAuthenticate();
        //
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("La reservation id " + id + " n'existe pas.")
        );
        reservation.setStatusReservation((byte)3);
        reservation.setGererBy(authenticate.getUser());
        reservationRepository.save(reservation);
    }

    @Override
    public void validerReservationsEncours(long id) {
        final Authenticate authenticate = authenticateService.getAuthenticate();
        //
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("La reservation id " + id + " n'existe pas.")
        );
        reservation.setStatusReservation((byte)2);
        reservation.setGererBy(authenticate.getUser());
        reservationRepository.save(reservation);
    }


    /////////////////////////////////////////////////////////////////
    @Override
    public List<UserResponse> listClients() {
        final Authenticate authenticate = authenticateService.getAuthenticate();
        //
        List<UserResponse> userResponse = new ArrayList<>();
        //
        reservationRepository.getClients(authenticate.getUser()).forEach(auth ->
                userResponse.add(new UserResponse(
                        auth.getId(),  auth.getFirstName(),
                        auth.getLastName(), auth.getAddress(),
                        auth.getPhoneNumber(), auth.getCni()
                ))
        );

        //
        return userResponse;
    }


    //////////////////////////////////////////////////////////////////////
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
    public void saveVoiture(VoitureRequest voitureRequest) {
        final Authenticate authenticate = authenticateService.getAuthenticate();
        //
        final Category category = categoryRepository.findById(voitureRequest.getIdCategory()).orElseThrow(
                () -> new IllegalStateException("La catégorie id " + voitureRequest.getIdCategory() + " n'existe pas.")
        );
        voitureRepository.save(new Voiture(
                voitureRequest.getNom(), voitureRequest.getMarque(), voitureRequest.getModele(),
                voitureRequest.getAnnee(), voitureRequest.getTarif(), voitureRequest.getDescription(),
                category.getPhoto(), category, authenticate.getUser()
        ));
    }

    @Override
    public void updateVoiture(long id, VoitureRequest voitureRequest) {
        Voiture voiture = voitureRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("La voiture id " + id + " n'existe pas.")
        );
        System.out.println("updateVoiture 1 -> " + voiture);
        if(voiture.getCategory().getId() != voitureRequest.getIdCategory()){
            final Category category = categoryRepository.findById(voitureRequest.getIdCategory()).orElseThrow(
                    () -> new IllegalStateException("La catégorie id " + voitureRequest.getIdCategory() + " n'existe pas.")
            );
            voiture.setCategory(category);
            //
            if( (voiture.getPhotos() == null) || voiture.getPhotos().isEmpty()){
                voiture.setPhotoDefault(category.getPhoto());
            }
            else{
                List<String> photoList = Arrays.stream( voiture.getPhotos().split(",") ).map(String::new).collect(Collectors.toList());
                photoList.set(0, category.getPhoto());
                String str = String.join(",", photoList);
                voiture.setPhotos(str);
            }
        }

        voiture.setNom(voitureRequest.getNom());
        voiture.setMarque(voitureRequest.getMarque());
        voiture.setModele(voitureRequest.getModele());
        voiture.setAnnee(voitureRequest.getAnnee());
        voiture.setTarif(voitureRequest.getTarif());
        voiture.setDescription(voitureRequest.getDescription());
        System.out.println("updateVoiture 2 -> " + voiture);
        voitureRepository.save(voiture);
    }

    @Override
    public void deleteVoiture(long id) {
        Voiture voiture = voitureRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("La voiture id " + id + " n'existe pas.")
        );
        List<String> photoList = new ArrayList<>();
        if((voiture.getPhotos() == null) || voiture.getPhotos().isEmpty()){
            photoList.add(voiture.getPhotoDefault());
        } else{
            photoList = Arrays.stream( voiture.getPhotos().split(",") ).map(String::new).collect(Collectors.toList());
        }
        for (int i = 1; i < photoList.size(); i++){
            try {
                File resource = storageService.loadFileAsResource(photoList.get(i)).getFile();
                if(resource != null){
                    resource.delete();
                }
            } catch (IOException ex){
                throw new StorageException("Sorry! Could not store file " + photoList.get(i) + ". Please try again!", ex);
            }
        }
        voitureRepository.delete(voiture);
    }

    @Override
    public List<VoitureResponse> getVoitures() {
        final Authenticate authenticate = authenticateService.getAuthenticate();
        //
        List<VoitureResponse> voitureResponseList = new ArrayList<>();
        voitureRepository.findByUserCreated(authenticate.getUser()).forEach( voiture -> {
            voitureResponseList.add(new VoitureResponse(
                    voiture.getId(), voiture.getNom(), voiture.getMarque(), voiture.getModele(), voiture.getAnnee(),
                    voiture.getTarif(), voiture.getDescription(), voiture.getPhotoDefault(), new ArrayList<>(),
                    voiture.getCategory().getId(), voiture.getCategory().getNom(), voiture.getCategory().getPhoto(),
                    new UserResponse(
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
    public void addPhotoVoiture(Long id, MultipartFile file) {
        final String photo = storageService.storeFile(file);
        Voiture voiture = voitureRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("La voiture id " + id + " n'existe pas.")
        );
        if((voiture.getPhotos() == null) || voiture.getPhotos().isEmpty()){
            voiture.setPhotos(voiture.getPhotoDefault() + "," + photo);
        } else{
            voiture.setPhotos(voiture.getPhotos() + "," + photo);
        }
        voiture.setPhotoDefault(photo);
        voitureRepository.save(voiture);
    }

    @Override
    public void deletePhotoVoiture(Long id, String photo) {
        System.out.println("deletePhotoVoiture " + id + " " + photo);
        final Voiture voiture = voitureRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("La voiture id " + id + " n'existe pas.")
        );
        try {
            File resource = storageService.loadFileAsResource(photo).getFile();
            if(resource != null){
                resource.delete();
            }
        } catch (IOException ex){
            throw new StorageException("Sorry! Could not store file " + photo + ". Please try again!", ex);
        }
        String photoDefault = "";
        String photos = voiture.getPhotos().replaceFirst(photo, "")
                .replaceFirst("^,|,$", "");
        if(photos.isEmpty()){
            photoDefault = voiture.getCategory().getPhoto();
        } else {
            final String[] photoDefaultTab = photos.split(",");
            photoDefault = photoDefaultTab[photoDefaultTab.length - 1];
        }
        voiture.setPhotos(photos);
        voiture.setPhotoDefault(photoDefault);
        voitureRepository.save(voiture);
    }



}
