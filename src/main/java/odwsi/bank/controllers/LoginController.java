package odwsi.bank.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import odwsi.bank.dtos.PasswordDTO;
import odwsi.bank.dtos.PasswordLoginRequest;
import odwsi.bank.repositories.ClientRepository;
import odwsi.bank.repositories.PasswordLoginAttemptRepository;
import odwsi.bank.security.DataValidation;
import odwsi.bank.security.PasswordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.List;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final PasswordService passwordService;
    private final ClientRepository clientRepository;
    private final PasswordLoginAttemptRepository passwordLoginAttemptRepository;

    @GetMapping("/login")
    public ResponseEntity<?> getPasswordInstructions(@RequestParam String email){
        if(DataValidation.validateEmail(email) == false){
            return new ResponseEntity<>( "Invalid login", HttpStatus.BAD_REQUEST);
        }
        if(clientRepository.findByEmail(email)!=null) {
                        return new ResponseEntity<PasswordDTO>(
                    passwordService.getOneOfTheCombinations(clientRepository.findByEmail(email)), HttpStatus.OK);
        }else{
            return new ResponseEntity<>( "Invalid login", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> authenticateUser(@RequestBody PasswordLoginRequest loginRequest,
                                                   HttpSession session, HttpServletRequest request) {


        if(DataValidation.validateEmail(loginRequest.getEmail()) == false ||
                DataValidation.validate3LetterWord(loginRequest.getPassword()) == false){
            return new ResponseEntity<>( "Invalid login", HttpStatus.BAD_REQUEST);
        }

        if(clientRepository.findByEmail(loginRequest.getEmail()).getAttempts()>3){
            return new ResponseEntity<>( "Max attempts", HttpStatus.BAD_REQUEST);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clientRepository.findByEmail(loginRequest.getEmail()).setAttempts(0);
        var client = passwordService.getUserIfPasswordCorrect(
                loginAttempt.getPasswordId(), loginRequest.getEmail(), loginRequest.getPassword());

        if(client!=null) {
            var authentication =
                    UsernamePasswordAuthenticationToken.authenticated(
                            client.getEmail(), null, List.of(new SimpleGrantedAuthority("user")));

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, context);

            return new ResponseEntity<>(client, HttpStatus.OK);
        }
        clientRepository.findByEmail(loginRequest.getEmail()).setAttempts(clientRepository.findByEmail(loginRequest.getEmail()).getAttempts()+1);
        return new ResponseEntity<>( "Invalid login", HttpStatus.BAD_REQUEST);
    }
}
