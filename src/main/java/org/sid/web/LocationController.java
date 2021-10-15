package org.sid.web;

import org.sid.datas.*;
import org.sid.files.StorageException;
import org.sid.files.StorageService;
import org.sid.services.UtilisateurService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@Controller
public class LocationController {

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private StorageService storageService;


	@GetMapping("/")
	public String homePage(Model model) {
		List<VoitureResponse> listVoitures = utilisateurService.getVoitures();
		List<CategoryResponse> listCategory = utilisateurService.getCategories();
		model.addAttribute("listCars", listVoitures);
		model.addAttribute("listCateogries", listCategory);
		System.out.println("------->/");
		return "index";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		System.out.println("login " + model);
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		System.out.println("------->/inscription");
		return "registration";
	}

	@GetMapping("/voitures")
	public String voitures(Model model) {
		System.out.println("------->/voiture");
		List<VoitureResponse> listVoitures = utilisateurService.getVoitures();
		List<CategoryResponse> listCategory = utilisateurService.getCategories();
		model.addAttribute("listCars", listVoitures);
		model.addAttribute("listCateogries", listCategory);
		return "voiture";
	}

	@GetMapping("/voitures/{id}")
	public String voituresDetail(Model model, @PathVariable("id") long id) {
		System.out.println("------->/voiture.detail");
		VoitureResponse detailVoiture = utilisateurService.detailVoiture(id);
		List<CategoryResponse> listCategory = utilisateurService.getCategories();
		model.addAttribute("detailVoiture", detailVoiture);
		System.out.println("detailVoiture "+ detailVoiture);
		model.addAttribute("listCateogries", listCategory);
		return "voiture-detail";
	}

	@PostMapping("/signin")
	public String signin(RegistrationRequest client){
		System.out.println("LocationController signin" + client.toString());
		final boolean result = utilisateurService.saveClient(client);
		if(result){
			return "redirect:/registration?success";
		}
		return "redirect:/registration?errorUsername";
	}

	@GetMapping("/contact")
	public String contact(Model model) {
		List<CategoryResponse> listCategory = utilisateurService.getCategories();
		model.addAttribute("listCateogries", listCategory);
		return "contact";
	}

	@GetMapping("downloadfile/{fileOriginName:.+}")
	public ResponseEntity<Resource> downloadFile(Model model, @PathVariable String fileOriginName, HttpServletRequest request) {
		Resource resource = storageService.loadFileAsResource(fileOriginName);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex){
			throw new StorageException("Sorry! Filename not found", ex);
		}
		if (contentType == null) { contentType = "application/octet-tream"; }

		return ResponseEntity.ok()
			.contentType(MediaType.parseMediaType(contentType))
			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
			.body(resource);
	}

	@PostMapping("/reserver")
	public ResponseEntity<?> reserver(@RequestBody ReservationRequest reservationRequest) {
		System.out.println("addReservation------->/" + reservationRequest);
		final boolean result = utilisateurService.saveReservation(reservationRequest);
		if(result){
			return new ResponseEntity("La réservation a été faite avec succes", HttpStatus.OK);
		}
		return new ResponseEntity("Echec ajout réservation. utilisation non connecté", HttpStatus.BAD_REQUEST);

	}

	@PreAuthorize("hasRole('UTILISATEUR')")
	@GetMapping("/client")
	public String homeClient(Model model) {
		final List<ReservationResponse> listReservationsEncours = utilisateurService.listReservations();
		System.out.println("LocationController listReservations " + listReservationsEncours);
		model.addAttribute("listReservationsEncours", listReservationsEncours);
		return "client";
	}

	@PutMapping("/client/reservations/{id}/annuler")
	public ResponseEntity<?> reservationsAnnulerAction(@PathVariable("id") long id) {
		System.out.println("LocationController reservationsAnnulerAction id " + id);
		utilisateurService.annulerReservations(id);
		return new ResponseEntity("Reservation annulée avec succes", HttpStatus.OK);
	}



}
