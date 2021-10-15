package org.sid.web;

import org.sid.datas.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.sid.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("admin/")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {


    @Autowired
    private AdminService adminService;


    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////    HOME   ////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @GetMapping("/index")
    public String homeAdmin(Model model) {
        final List<ReservationResponse> listReservations = adminService.listReservations();
        System.out.println("AdminController listReservations " + listReservations);
        model.addAttribute("listReservations", listReservations);
        return "admin/admin.index";
    }



    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////    GERANT   //////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @GetMapping("/users")
    public String listGerants(Model model) {
        final List<UserResponse> listGerants = adminService.listGerants();
        System.out.println("AdminController listGerants " + listGerants);
        model.addAttribute("listGerants", listGerants);
        return "admin/admin.user";
    }

    @PostMapping("/users")
    public ResponseEntity<?> usersAddAction(@RequestBody RegistrationRequest registrationRequest) {
        System.out.println("AdminController usersAddAction " + registrationRequest);
        final boolean result = adminService.saveGerant(registrationRequest);
        if(result){
            return new ResponseEntity("Gerant ajouté avec succes", HttpStatus.OK);
        }
        return new ResponseEntity("Echec ajout gerant", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> usersDeleteAction(@PathVariable("id") long id) {
        System.out.println("AdminController usersDeleteAction " + id);
        adminService.deleteGerantById(id);
        return new ResponseEntity("Gerant supprimé avec succes", HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> usersEditAction(@PathVariable("id") long id, @RequestBody RegistrationRequest registrationRequest) {
        registrationRequest.setId(id);
        System.out.println("AdminController usersEditAction " + id + "; " + registrationRequest);
        boolean result = adminService.updateGerantById(id, registrationRequest);
        if(result){
            return new ResponseEntity("Gerant modifié avec succes", HttpStatus.OK);
        }
        return new ResponseEntity("Echec modification gerant", HttpStatus.BAD_REQUEST);
    }



    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////    RESERVATION   /////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @GetMapping("/reservations")
    public String reservationsAdmin(Model model) {
        final List<ReservationResponse> listReservations = adminService.listReservations();
        System.out.println("AdminController listReservations " + listReservations);
        model.addAttribute("listReservations", listReservations);
        return "admin/admin.reservation";
    }




    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////    CLIENT   //////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @GetMapping("/clients")
    public String clientsAdmin(Model model) {
        final List<UserResponse> listClients = adminService.listClients();
        System.out.println("AdminController listClients " + listClients);
        model.addAttribute("listClients", listClients);
        return "admin/admin.client";
    }



    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////    VOITURE   /////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @GetMapping("/voitures")
    public String voituresAdmin(Model model) {
        List<CategoryResponse> listCategory = adminService.getCategories();
        List<VoitureResponse> listVoiture = adminService.getVoitures();
        model.addAttribute("listCateogries", listCategory);
        model.addAttribute("listVoitures", listVoiture);
        return "admin/admin.voiture";
    }

    @PostMapping("/voitures")
    public ResponseEntity<?> voituresAddAction(@RequestBody VoitureRequest voitureRequest) {
        System.out.println("AdminController voituresAddAction " + voitureRequest);
        adminService.saveVoiture(voitureRequest);
        return new ResponseEntity("Voiture ajoutée avec succes", HttpStatus.OK);
    }

    @PutMapping("/voitures/{id}")
    public ResponseEntity<?> voituresEditAction(@PathVariable("id") long id, @RequestBody VoitureRequest voitureRequest) {
        voitureRequest.setId(id);
        System.out.println("AdminController voituresEditAction id " + id + "; voitureRequest " + voitureRequest);
        adminService.updateVoiture(id, voitureRequest);
        return new ResponseEntity("Voiture modifiée avec succes", HttpStatus.OK);
    }

    @DeleteMapping("/voitures/{id}")
    public ResponseEntity<?> voituresDeleteAction(@PathVariable("id") long id) {
        System.out.println("AdminController voituresDeleteAction id " + id);
        adminService.deleteVoiture(id);
        return new ResponseEntity("Voiture supprimée avec succes", HttpStatus.OK);
    }

    @GetMapping("/voitures/{id}")
    public String voituresDetailAction(Model model, @PathVariable("id") long id) {
        System.out.println("AdminController voituresAddAction " + id);
        VoitureResponse detailVoiture = adminService.detailVoiture(id);
        System.out.println(detailVoiture);
        model.addAttribute("detailVoiture", detailVoiture);
        return "admin/admin.voiture.detail";
    }

    @PostMapping("/voitures/{id}")
    public String voituresAddPhotoAction(Model model, @PathVariable("id") long id, @RequestParam("file") MultipartFile file) {
        System.out.println("AdminController voituresAddAction " + id);
        adminService.addPhotoVoiture(id, file);
        return "redirect:/admin/voitures/" + id;
    }

    @PostMapping("/voitures/{id}/{photo}")
    public String voituresImageDeleteAction(@PathVariable("id") long id, @PathVariable("photo") String photo) {
        System.out.println("AdminController voituresImageDeleteAction " + id + "; photo" + photo);
        adminService.deletePhotoVoiture(id, photo);
        return "redirect:/admin/voitures/" + id;
    }



    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////    CATEGORIE   ///////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    @GetMapping("/categories")
    public String categoriesAdmin(Model model) {
        List<CategoryResponse> categoryResponseList = adminService.getCategories();
        model.addAttribute("listCategories", categoryResponseList);
        return "admin/admin.categorie";
    }

    @PostMapping("/categories")
    public String categoriesAddAction(@RequestParam("nom") String nom, @RequestParam("description") String description, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        System.out.println("AdminController categoriesAddAction: nom " + nom + "; file " + file.getOriginalFilename());
        adminService.saveCategory(nom, description, file);
        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/{id}")
    public String categoriesDeleteAction(@PathVariable("id") long id) {
        System.out.println("AdminController categoriesDeleteAction " + id);
        adminService.deleteCategory(id);
        return "redirect:/admin/categories";
    }




}
