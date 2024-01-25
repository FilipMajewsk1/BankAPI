package odwsi.bank.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import odwsi.bank.dtos.AccountDTO;
import odwsi.bank.dtos.ClientDTO;
import odwsi.bank.models.Account;
import odwsi.bank.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Tag(name = "Account Controller")
@RequestMapping("/api/norm")
@RestController
public class AccountController {
    private final AccountService service;
    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @Operation(
            summary = "Create account",
            description = "Create new Account object",
            tags = { "post" })
    @PostMapping("/accounts")
    public AccountDTO create(@RequestBody AccountDTO accountDto) {
        return AccountDTO.mapToDto(service.createAccount(service.mapFromDto(accountDto)));
    }

    @Operation(
            summary = "Retrieve all accounts",
            description = "Get a list of all Account objects",
            tags = { "get" })
    @GetMapping("/all/accounts")
    public List<AccountDTO> getAll() {
        List<AccountDTO> accounts = new ArrayList<>();

        service.getAllAccounts().forEach((x) -> accounts.add(AccountDTO.mapToDto(x)));

        return accounts;
    }

    @Operation(
            summary = "Retrieve account",
            description = "Get Account object by specifying its id",
            tags = { "get" })
    @GetMapping("/accounts")
    public ResponseEntity<?> get(Authentication authentication) {
        return new ResponseEntity<>( AccountDTO.mapToDto(service.getAccount(authentication)), HttpStatus.OK);
    }
    @Operation(
            summary = "Update account",
            description = "Update Account object by specifying its id",
            tags = { "patch" })
    @PatchMapping("/accounts/{id}")
    public AccountDTO update(@PathVariable int id, @RequestBody AccountDTO accountDto) {
        return AccountDTO.mapToDto(service.updateAccount(id, service.mapFromDto(accountDto)));
    }

    @Operation(
            summary = "Delete account",
            description = "Delete Account object by specifying its id",
            tags = { "delete" })
    @DeleteMapping("/accounts/{id}")
    public void delete(@PathVariable int id) {
        service.deleteAccount(id);
    }

}
