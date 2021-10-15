package org.sid.web;

import org.sid.datas.*;
import org.sid.services.GerantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
@RequestMapping("gerant/")
@PreAuthorize("hasRole('GERANT')")
public class GerantController {

    @Autowired
    private GerantService gerantService;


    @GetMapping("/index")
    public String homeGerant(Model model) {
        final List<ReservationResponse> listReservationsEncours = gerantService.listReservationsEncours();
        System.out.println("GerantController homeGerant " + listReservationsEncours);
        model.addAttribute("listReservationsEncours", listReservationsEncours);
        return "gerant/gerant.index";
    }


    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////    RESERVATION   /////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @GetMapping("/reservations")
    public String reservationsAdmin(Model model) {
        final List<ReservationResponse> listReservations = gerantService.listReservations();
        System.out.println("GerantController listReservations " + listReservations);
        model.addAttribute("listReservations", listReservations);
        return "gerant/gerant.reservation";
    }

    @GetMapping("/reservations/en-cours")
    public String reservationsEncoursAdmin(Model model) {
        final List<ReservationResponse> listReservationsEncours = gerantService.listReservationsEncours();
        System.out.println("GerantController listReservationsEncours " + listReservationsEncours);
        model.addAttribute("listReservationsEncours", listReservationsEncours);
        return "gerant/gerant.reservation.encours";
    }

    @PutMapping("/reservations/en-cours/{id}/annuler")
    public ResponseEntity<?> reservationsEncoursAnnulerAction(@PathVariable("id") long id) {
        System.out.println("GerantController reservationsEncoursAnnulerAction id " + id);
        gerantService.annulerReservationsEncours(id);
        return new ResponseEntity("Reservation annulée avec succes", HttpStatus.OK);
    }


    @PutMapping("/reservations/en-cours/{id}/valider")
    public ResponseEntity<?> reservationsEncoursValiderAction(@PathVariable("id") long id) {
        System.out.println("GerantController reservationsEncoursValiderAction id " + id);
        gerantService.validerReservationsEncours(id);
        return new ResponseEntity("Reservation validée avec succes", HttpStatus.OK);
    }



    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////    CLIENT   //////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @GetMapping("/clients")
    public String clientsGerant(Model model) {
        final List<UserResponse> listClients = gerantService.listClients();
        System.out.println("GerantController listClients " + listClients);
        model.addAttribute("listClients", listClients);
        return "gerant/gerant.client";
    }



    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////    VOITURE   /////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @GetMapping("/voitures")
    public String voituresGerant(Model model) {
        System.out.println("GerantController voituresGerant ");
        List<CategoryResponse> listCategory = gerantService.getCategories();
        List<VoitureResponse> listVoiture = gerantService.getVoitures();
        model.addAttribute("listCateogries", listCategory);
        model.addAttribute("listVoitures", listVoiture);
        return "gerant/gerant.voiture";
    }

    @PostMapping("/voitures")
    public ResponseEntity<?> voituresAddAction(@RequestBody VoitureRequest voitureRequest) {
        System.out.println("GerantController voituresAddAction " + voitureRequest);
        gerantService.saveVoiture(voitureRequest);
        return new ResponseEntity("Voiture ajoutée avec succes", HttpStatus.OK);
    }

    @PutMapping("/voitures/{id}")
    public ResponseEntity<?> voituresEditAction(@PathVariable("id") long id, @RequestBody VoitureRequest voitureRequest) {
        System.out.println("GerantController voituresImageDeleteAction " + id + "; voitureRequest " + voitureRequest);
        voitureRequest.setId(id);
        System.out.println("GerantController voituresEditAction id " + id + "; voitureRequest " + voitureRequest);
        gerantService.updateVoiture(id, voitureRequest);
        return new ResponseEntity("Voiture modifiée avec succes", HttpStatus.OK);
    }

    @DeleteMapping("/voitures/{id}")
    public ResponseEntity<?> voituresDeleteAction(@PathVariable("id") long id) {
        System.out.println("GerantController voituresDeleteAction id " + id);
        gerantService.deleteVoiture(id);
        return new ResponseEntity("Voiture supprimée avec succes", HttpStatus.OK);
    }

    @GetMapping("/voitures/{id}")
    public String voituresDetailAction(Model model, @PathVariable("id") long id) {
        System.out.println("GerantController voituresAddAction " + id);
        VoitureResponse detailVoiture = gerantService.detailVoiture(id);
        System.out.println(detailVoiture);
        model.addAttribute("detailVoiture", detailVoiture);
        return "gerant/gerant.voiture.detail";
    }

    @PostMapping("/voitures/{id}")
    public String voituresAddPhotoAction(Model model, @PathVariable("id") long id, @RequestParam("file") MultipartFile file) {
        System.out.println("GerantController voituresAddAction " + id);
        gerantService.addPhotoVoiture(id, file);
        return "redirect:/gerant/voitures/" + id;
    }

    @PostMapping("/voitures/{id}/{photo}")
    public String voituresImageDeleteAction(@PathVariable("id") long id, @PathVariable("photo") String photo) {
        System.out.println("GerantController voituresImageDeleteAction " + id + "; photo " + photo);
        gerantService.deletePhotoVoiture(id, photo);
        return "redirect:/gerant/voitures/" + id;
    }



}
