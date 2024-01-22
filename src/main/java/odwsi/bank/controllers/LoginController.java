package odwsi.bank.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import odwsi.bank.dtos.LoginDTO;
import odwsi.bank.dtos.PasswordDTO;
import odwsi.bank.dtos.PasswordLoginRequest;
import odwsi.bank.models.Password;
import odwsi.bank.repositories.ClientRepository;
import odwsi.bank.security.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.List;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final PasswordService passwordService;
    private final ClientRepository clientRepository;
    @GetMapping("/login")
    public ResponseEntity<PasswordDTO> getPasswordInstructions(@RequestParam String email){
        return new ResponseEntity<PasswordDTO>(passwordService.getOneOfTheCombinations(clientRepository.findByEmail(email)), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody PasswordLoginRequest request,
                                                   HttpSession session) {
        //sprawdź hasło z bazy na podstawie id
        var user = passwordService.getUserIfPasswordCorrect(request.getId(), request.getEmail(), request.getPassword());

        if(user!=null) {
            var authentication = UsernamePasswordAuthenticationToken.authenticated(user.getEmail(), null, List.of(new SimpleGrantedAuthority("user")));

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, context);

            return new ResponseEntity<>("User login successfully!...", HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
