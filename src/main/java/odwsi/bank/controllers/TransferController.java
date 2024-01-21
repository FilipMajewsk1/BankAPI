package odwsi.bank.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import odwsi.bank.dtos.ClientDTO;
import odwsi.bank.dtos.TransferDTO;
import odwsi.bank.models.Client;
import odwsi.bank.models.Transfer;
import odwsi.bank.services.ClientService;
import odwsi.bank.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Transfer Controller")
@RestController
public class TransferController {
    private final TransferService transferService;
    private final ClientService clientService;

    @Autowired
    public TransferController(TransferService transferService,
                              ClientService clientService){
        this.transferService = transferService;
        this.clientService = clientService;
    }

    @PostMapping("/transfers")
    public TransferDTO create(@RequestBody TransferDTO dto){
        if(dto.getSum() == null){
            throw new IllegalArgumentException("Sum cannot be null");
        }
        return TransferDTO.mapToDto(transferService.createTransfer(transferService.mapFromDto(dto)));
    }

    @GetMapping("/transfers/{email}")
    public List<TransferDTO> getAllForClient(@PathVariable String email){
        List<TransferDTO> transfers = new ArrayList<>();
        transferService.getClientsTransfers(email).forEach((x) -> transfers.add(TransferDTO.mapToDto(x)));
        return transfers;
    }
}
