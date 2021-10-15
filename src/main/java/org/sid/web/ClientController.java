package org.sid.web;

import org.sid.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("client/")
@PreAuthorize("hasRole('UTILISATEUR')")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping("/index")
    public String homeClient(Model mode) {
        return "client/client.index";
    }

    @GetMapping("/reservations")
    public String reservationsClient(Model mode) {
        return "client/client.reservation";
    }

    @GetMapping("/reservations/en-cours")
    public String reservationsEncoursClient(Model mode) {
        return "client/client.reservation.encours";
    }

}
