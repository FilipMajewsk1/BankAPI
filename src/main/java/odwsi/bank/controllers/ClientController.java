package odwsi.bank.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import odwsi.bank.dtos.ClientDTO;
import odwsi.bank.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Tag(name = "Client Controller")
@RestController
public class ClientController {
    private final ClientService service;
    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @Operation(
            summary = "Create client",
            description = "Create new Client object",
            tags = { "post" })
    @PostMapping("/clients")
    public ClientDTO create(@RequestBody ClientDTO clientDto) {
        return ClientDTO.mapToDto(service.createClient(service.mapFromDto(clientDto)));
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
    public ClientDTO get(@PathVariable int id) {
        return ClientDTO.mapToDto(service.getClient(id));
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
