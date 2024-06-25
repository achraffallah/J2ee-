package org.sid.oulaizebankingbackend;

import org.sid.oulaizebankingbackend.dtos.BankAccountDTO;
import org.sid.oulaizebankingbackend.dtos.CurrentBankAccountDTO;
import org.sid.oulaizebankingbackend.dtos.CustomerDTO;
import org.sid.oulaizebankingbackend.dtos.SavingBankAccountDTO;
import org.sid.oulaizebankingbackend.enums.AccountStatus;
import org.sid.oulaizebankingbackend.entities.CurrentAccount;
import org.sid.oulaizebankingbackend.entities.Customer;
import org.sid.oulaizebankingbackend.entities.SavingAccount;
import org.sid.oulaizebankingbackend.exceptions.CustomerNotFoundException;
import org.sid.oulaizebankingbackend.repositories.AccountOperationRepository;
import org.sid.oulaizebankingbackend.repositories.BankAccountRepository;
import org.sid.oulaizebankingbackend.repositories.CustomerRepository;
import org.sid.oulaizebankingbackend.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class OulaizEbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(OulaizEbankingBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
        return args -> {
            Stream.of("Hassan","Imane","Mohamed").forEach(name->{
                CustomerDTO customer=new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCustomers().forEach(customer->{
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*90000,9000,customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5,customer.getId());
                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });
            List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
            for (BankAccountDTO bankAccount:bankAccounts){
                for (int i = 0; i <10 ; i++) {
                    String accountId;
                    if(bankAccount instanceof SavingBankAccountDTO){
                        accountId=((SavingBankAccountDTO) bankAccount).getId();
                    } else{
                        accountId=((CurrentBankAccountDTO) bankAccount).getId();
                    }
                    bankAccountService.credit(accountId,10000+Math.random()*120000,"Credit");
                    bankAccountService.debit(accountId,1000+Math.random()*9000,"Debit");
                }
            }
        };
    }
}