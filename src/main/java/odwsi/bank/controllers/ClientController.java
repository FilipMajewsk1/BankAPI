package odwsi.bank.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.transaction.Transactional;
import odwsi.bank.dtos.ClientDTO;
import odwsi.bank.dtos.CreateClientRequest;
import odwsi.bank.models.Client;
import odwsi.bank.models.Password;
import odwsi.bank.security.PasswordService;
import odwsi.bank.services.AccountService;
import odwsi.bank.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Tag(name = "Client Controller")
@RestController
@RequestMapping("/api/norm")
public class ClientController {

    private final ClientService service;
    private final PasswordService pService;
    private final AccountService aService;
    @Autowired
    public ClientController(ClientService service, PasswordService pService, AccountService aService) {
        this.service = service;
        this.pService = pService;
        this.aService = aService;
    }
    @GetMapping("/clients")
    public ResponseEntity<?> get(Authentication authentication) {
        if(authentication == null){
            return new ResponseEntity<String>("Can`t authenticate ", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>( ClientDTO.mapToDto(service.getClient(authentication)), HttpStatus.OK);
    }

}
