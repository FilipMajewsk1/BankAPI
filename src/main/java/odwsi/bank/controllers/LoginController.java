package odwsi.bank.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import odwsi.bank.dtos.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;

@RestController
public class LoginController {
    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

   //TODO @GetMapping("/login")
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO dto,
                                                   HttpServletRequest request,
                                                   HttpServletResponse response,
                                                   HttpSession session) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()));

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
        session.setAttribute(//TODO Klucz, context);

        return new ResponseEntity<>("User login successfully!...", HttpStatus.OK);
    }
}
