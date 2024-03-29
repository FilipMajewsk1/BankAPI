package odwsi.bank;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import odwsi.bank.models.Account;
import odwsi.bank.models.Client;
import odwsi.bank.models.Transfer;
import odwsi.bank.repositories.AccountRepository;
import odwsi.bank.security.DataValidation;
import odwsi.bank.security.PasswordService;
import odwsi.bank.services.AccountService;
import odwsi.bank.services.ClientService;
import odwsi.bank.services.TransferService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataFactory implements CommandLineRunner {
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final ClientService clientService;
    private final PasswordService passwordService;
    private final TransferService transferService;

    @Override
    @Transactional
    public void run(String... args) {
        createAccount(1, "11111111111111111111111111", "2500.12");
        createAccount(2, "11111111111111111111111112", "3500.12");
        createAccount(3, "11111111111111111111111113", "4500.12");

        createClient(1, "Adam", "Malysz", "12345678910", 1 ,"skaczedaleko@gmail.com", "Password123", "111222333");
        createClient(2, "Kamil", "Stoch", "22345678910", 2 ,"slaboskacze@gmail.com", "Zetis12345", "113222333");
        createClient(3, "Mariusz", "Pudzianowski", "17745678910", 3 ,"tobynicniedalo@gmail.com", "PolskaGora123", "111222343");

        createTransfer(1, "fromMariuszToKamil", "21.50","11111111111111111111111113",  "11111111111111111111111112");
        createTransfer(2, "fromMariuszToAdam", "204.50","11111111111111111111111113",  "11111111111111111111111111");
        createTransfer(3, "fromAdamToKamil", "12.50","11111111111111111111111111",  "11111111111111111111111112");
    }

    private void createAccount(int id, String accountNumber, String balance){
        Account account = new Account( id, accountNumber, new BigDecimal(balance));
        accountRepository.save(account);
    }

    private void createClient(int id,
                              String name,
                              String surName,
                              String pesel,
                              int accountId,
                              String email,
                              String password,
                              String phoneNum){
        if(DataValidation.validatePassword(password) == false){
            return;
        }
        Client client = Client.builder()
                .id(id)
                .name(name)
                .surname(surName)
                .pesel(pesel)
                .account(accountService.getAccount(accountId))
                .email(email)
                .phoneNum(phoneNum)
                .attempts(0)
                .attemptTime(LocalDateTime.now())
                .passwords(passwordService.createThreeCharCombinations(password)).build();

        client.getPasswords().forEach(p -> p.setClient(client));
        clientService.createClient(client);
    }

    private void createTransfer(int id,
                                String title,
                                String sum,
                                String fromAccountNumber,
                                String toAccountNumber){
        if(DataValidation.validateTransferSum(sum) == false ||
        DataValidation.validateTitle(title) == false ||
        DataValidation.validateAccountNumber(fromAccountNumber) == false ||
        DataValidation.validateAccountNumber(toAccountNumber) == false){
            return;
        }
        Transfer transfer= new Transfer(
                id,
                title,
                new BigDecimal(sum),
                accountRepository.findByAccountNumber(fromAccountNumber),
                accountRepository.findByAccountNumber(toAccountNumber));
        transferService.createTransfer(transfer);
    }

}
