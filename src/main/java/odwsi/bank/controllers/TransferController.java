package odwsi.bank.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import odwsi.bank.dtos.MakeTranfer;
import odwsi.bank.dtos.TransferDTO;
import odwsi.bank.models.Account;
import odwsi.bank.repositories.AccountRepository;
import odwsi.bank.services.ClientService;
import odwsi.bank.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Transfer Controller")
@RequestMapping("/api")
@RestController
public class TransferController {
    private final TransferService transferService;
    private final ClientService clientService;
    private final AccountRepository aRepository;

    @Autowired
    public TransferController(TransferService transferService,
                              ClientService clientService,
                              AccountRepository aRepository){
        this.transferService = transferService;
        this.clientService = clientService;
        this.aRepository = aRepository;
    }

    @GetMapping("/transferss")
    public List<TransferDTO> getAll(){
        List<TransferDTO> transfers = new ArrayList<>();

        transferService.getAllTransfers().forEach((x)-> transfers.add(TransferDTO.mapToDto(x)));
        return transfers;
    }

    @PostMapping("/transfers")
    public ResponseEntity<?> create(@RequestBody MakeTranfer transfer, Authentication authentication){
        if(transfer.getSum() == null){
            throw new IllegalArgumentException("Sum cannot be null");
        }
        Account fromAccount = clientService.getClient(authentication.getPrincipal().toString()).getAccount();
        Account toAccount = aRepository.findByAccountNumber(transfer.getToAccountNumber());

        fromAccount.setBalance(fromAccount.getBalance().add(transfer.getSum().negate()));
        toAccount.setBalance(toAccount.getBalance().add(transfer.getSum()));

        aRepository.save(fromAccount);
        aRepository.save(toAccount);
        return new ResponseEntity<>("Transfer was succesfull!", HttpStatus.OK);
    }

    @GetMapping("/transfers")
    public ResponseEntity<?> getAllForClient(Authentication authentication){
        List<TransferDTO> transfers = new ArrayList<>();
        transferService.getClientsTransfers(authentication.getPrincipal().toString()).forEach((x) -> transfers.add(TransferDTO.mapToDto(x)));
        return new ResponseEntity<>(transfers, HttpStatus.OK);
    }
}
