package odwsi.bank.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import odwsi.bank.dtos.MakeTranfer;
import odwsi.bank.dtos.TransferDTO;
import odwsi.bank.models.Account;
import odwsi.bank.models.Transfer;
import odwsi.bank.repositories.AccountRepository;
import odwsi.bank.repositories.TransferRepository;
import odwsi.bank.services.ClientService;
import odwsi.bank.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import odwsi.bank.security.DataValidation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Transfer Controller")
@RequestMapping("/api/norm")
@RestController
public class TransferController {
    private final TransferService transferService;
    private final TransferRepository transferRepository;
    private final ClientService clientService;
    private final AccountRepository aRepository;

    @Autowired
    public TransferController(TransferService transferService,
                              ClientService clientService,
                              AccountRepository aRepository,
                                TransferRepository transferRepository){
        this.transferService = transferService;
        this.transferRepository = transferRepository;
        this.clientService = clientService;
        this.aRepository = aRepository;
    }

    @PostMapping("/transfers")
    @Transactional
    public ResponseEntity<?> create(@RequestBody MakeTranfer transfer, Authentication authentication){

        if(authentication== null){
            return new ResponseEntity<String>("Can`t authenticate ", HttpStatus.UNAUTHORIZED);
        }
        if(DataValidation.validateTransferSum(transfer.getSum()) == false ||
        DataValidation.validateAccountNumber(transfer.getToAccountNumber()) == false ||
        DataValidation.validateTitle(transfer.getTitle())==false){
            return new ResponseEntity<String>("Can`t validate", HttpStatus.UNAUTHORIZED);
        }

        Account fromAccount = clientService.getClient(authentication.getPrincipal().toString()).getAccount();
        Account toAccount = aRepository.findByAccountNumber(transfer.getToAccountNumber());
        if(fromAccount.getAccountNumber() == toAccount.getAccountNumber()){
            return new ResponseEntity<String>("The same numbers", HttpStatus.UNAUTHORIZED);
        }
        fromAccount.setBalance(fromAccount.getBalance().add(new BigDecimal(transfer.getSum()).negate()));
        toAccount.setBalance(toAccount.getBalance().add(new BigDecimal(transfer.getSum())));

        aRepository.save(fromAccount);
        aRepository.save(toAccount);

        Transfer newTransfer = new Transfer(-1, transfer.getTitle(), new BigDecimal(transfer.getSum()), fromAccount,toAccount);

        transferRepository.save(newTransfer);

        return new ResponseEntity<MakeTranfer>(transfer, HttpStatus.OK);
    }

    @GetMapping("/transfers")
    public ResponseEntity<?> getAllForClient(Authentication authentication){
        if(authentication== null){
            return new ResponseEntity<String>("Can`t authenticate ", HttpStatus.UNAUTHORIZED);
        }
        List<TransferDTO> transfers = new ArrayList<>();
        transferService.getClientsTransfers(authentication.getPrincipal().toString()).forEach((x) -> transfers.add(TransferDTO.mapToDto(x)));
        return new ResponseEntity<List<TransferDTO>>(transfers, HttpStatus.OK);
    }

}
