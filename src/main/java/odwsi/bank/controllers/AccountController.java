package odwsi.bank.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import odwsi.bank.dtos.AccountDTO;
import odwsi.bank.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Account Controller")
@RequestMapping("/api/norm")
@RestController
public class AccountController {
    private final AccountService service;
    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }
    @GetMapping("/accounts")
    public ResponseEntity<?> get(Authentication authentication) {
        if(authentication== null){
            return new ResponseEntity<String>("Can`t authenticate ", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>( AccountDTO.mapToDto(service.getAccount(authentication)), HttpStatus.OK);
    }


}
