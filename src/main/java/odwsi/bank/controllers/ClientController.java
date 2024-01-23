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
@RequestMapping("/api")
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

    @Operation(
            summary = "Create client",
            description = "Create new Client object",
            tags = { "post" })
    @Transactional
    @PostMapping("/clients")
    public Client create(@RequestBody CreateClientRequest request) {
        Client client = Client.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .pesel(request.getPesel())
                .account(aService.getAccount(request.getAccount_id()))
                .email(request.getEmail())
                .phoneNum(request.getPhoneNum())
                .passwords(pService.createThreeCharCombinations(request.getPassword())).build();
        client.getPasswords().forEach(p -> p.setClient(client));
        return (service.createClient(client));
    }

    @Operation(
            summary = "Retrieve all clients",
            description = "Get a list of all Client objects",
            tags = { "get" })
    @GetMapping("/clients")
    public List<ClientDTO> getAll() {
        List<ClientDTO> clients = new ArrayList<>();

        service.getAllClients().forEach((x) -> clients.add(ClientDTO.mapToDto(x)));

        return clients;
    }

    @Operation(
            summary = "Retrieve client",
            description = "Get Client object by specifying its id",
            tags = { "get" })
    @GetMapping("/clients/{id}")
    public ResponseEntity<?> get(Authentication authentication) {
        return new ResponseEntity<>( ClientDTO.mapToDto(service.getClient(authentication)), HttpStatus.OK);
    }


    @Operation(
            summary = "Update client",
            description = "Update Client object by specifying its id",
            tags = { "patch" })
    @PatchMapping("/clients/{id}")
    public ClientDTO update(@PathVariable int id, @RequestBody ClientDTO clientDto) {
        return ClientDTO.mapToDto(service.updateClient(id, service.mapFromDto(clientDto)));
    }

    @Operation(
            summary = "Delete client",
            description = "Delete Client object by specifying its id",
            tags = { "delete" })
    @DeleteMapping("/clients/{id}")
    public void delete(@PathVariable int id) {
        service.deleteClient(id);
    }

}
