/*
	main/java/controllers/TransactionController.java is part of 
	"Demo MisionTIC-2021 C4 - BackendAccountMS", created by Carlos Andrés Sierra.

    "Demo MisionTIC-2021 C4 - BackendAccountMS" is free software: 
    you can redistribute it and/or modify it under the terms of the 
    GNU General Public License as published by the Free Software Foundation, 
    either version 3 of the License, or (at your option) any later version.

    "Demo MisionTIC-2021 C4 - BackendAccountMS" is distributed in 
    the hope that it will be useful, but WITHOUT ANY WARRANTY; 
    without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
    PARTICULAR PURPOSE.  See the GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with "Demo MisionTIC-2021 C4 - BackendAccountMS". 
    If not, see <https://www.gnu.org/licenses/>.

    For contact, you can write to casierrav@unal.edu.co
*/


package com.misiontic.backend_account_ms.controllers;

import com.misiontic.backend_account_ms.models.Transaction;
import com.misiontic.backend_account_ms.repositories.TransactionRepository;
import com.misiontic.backend_account_ms.exceptions.TransactionNotFoundException;
import com.misiontic.backend_account_ms.exceptions.InsufficientBalanceException;

import com.misiontic.backend_account_ms.models.Account;
import com.misiontic.backend_account_ms.repositories.AccountRepository;
import com.misiontic.backend_account_ms.exceptions.AccountNotFoundException;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author Carlos Andrés Sierra - casierrav@unal.edu.co
 */
@RestController
public class TransactionController {
    private final TransactionRepository transactionRepository;
    private final AccountRepository     accountRepository;

    /**
     * 
     * @param transactionRepository
     * @param accountRepository
     */
    public TransactionController(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository     = accountRepository;
    }


    /**
     * 
     * @param transaction
     * @return
     */
    @PostMapping("/transactions")
    Transaction newTransaction(@RequestBody Transaction transaction){
        Account originAccount  = this.accountRepository.findById( transaction.getUsernameOrigin() ).orElse(null);
        if(originAccount == null){
            throw new AccountNotFoundException("La cuenta con usuario " + transaction.getUsernameOrigin()
                                                + " no se encuentra registrada en la base de datos.");
        }

        Account destinyAccount = this.accountRepository.findById( transaction.getUsernameDestiny() ).orElse(null);
        if(destinyAccount == null){
            throw new AccountNotFoundException("La cuenta con usuario " + transaction.getUsernameDestiny()
                    + " no se encuentra registrada en la base de datos.");
        }

        if(originAccount.getBalance() < transaction.getValue()){
            throw new InsufficientBalanceException("La cuenta no tiene saldo suficiente para la transferencia.");
        }

        originAccount.setBalance( originAccount.getBalance() - transaction.getValue() );
        originAccount.setLastChange( new Date() );
        accountRepository.save(originAccount);

        destinyAccount.setBalance( destinyAccount.getBalance() + transaction.getValue() );
        destinyAccount.setLastChange( new Date() );
        accountRepository.save(destinyAccount);

        transaction.setDate( new Date() );
        return transactionRepository.save(transaction);
    }


    /**
     * 
     * @param username
     * @return
     */
    @GetMapping("/transactions/{username}")
    List<Transaction> userTransactions(@PathVariable String username){
        Account userAccount = this.accountRepository.findById(username).orElse(null);
        if(userAccount == null){
            throw new AccountNotFoundException("La cuenta con usuario " + username
                    + " no se encuentra registrada en la base de datos.");
        }

        List<Transaction> transactionsOrigin  = this.transactionRepository.findByUsernameOrigin(username);
        List<Transaction> transactionsDestiny = this.transactionRepository.findByUsernameDestiny(username);

        List<Transaction> transactions = Stream.concat(transactionsOrigin.stream(),
                                                        transactionsDestiny.stream())
                                                .collect(Collectors.toList());
        return transactions;
    }


    /**
     * 
     * @param username
     * @return
     */
    @GetMapping("/transactions/recipients/{username}")
    List<String> userTransactionsRecipients(@PathVariable String username){
        Account userAccount = this.accountRepository.findById(username).orElse(null);
        if(userAccount == null){
            throw new AccountNotFoundException("La cuenta con usuario " + username
                    + " no se encuentra registrada en la base de datos.");
        }

        List<Transaction> transactionsOrigin  = this.transactionRepository.findByUsernameOrigin(username);
        List<Transaction> transactionsDestiny = this.transactionRepository.findByUsernameDestiny(username);
        List<String> recipients = new ArrayList<String>();
        for(Transaction transaction : transactionsOrigin) 
            if(!recipients.contains( transaction.getUsernameDestiny()))
                recipients.add(transaction.getUsernameDestiny());
        for(Transaction transaction : transactionsDestiny) 
            if(!recipients.contains( transaction.getUsernameDestiny()))
                recipients.add(transaction.getUsernameDestiny());
        return recipients;
    }


    /**
     * 
     * @param id
     * @return
     */
    @GetMapping("/transactions/get/{id}")
    Transaction getTransaction(@PathVariable String id){
        Transaction transaction = this.transactionRepository.findById(id).orElse(null);
        if(transaction == null){
            throw new TransactionNotFoundException("El código de la transacción no existe en la base de datos.");
        }
        return transaction;
    }


    /**
     * 
     * @param transactionUpdate
     * @return
     */
    @PutMapping("/transactions/update")
    Transaction updateTransaction(@RequestBody Transaction transactionUpdate){
        Transaction transaction = this.transactionRepository.findById( transactionUpdate.getId() ).orElse(null);
        if(transaction == null){
            throw new TransactionNotFoundException("El código de la transacción no existe en la base de datos.");
        }
        transaction.setNote( transactionUpdate.getNote() );
        transaction.setDate( new Date() );
        return transactionRepository.save(transaction);
    }

    /**
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/transactions/delete/{id}")
    String deleteTransaction(@PathVariable String id){
        Transaction transaction = this.transactionRepository.findById(id).orElse(null);
        if(transaction == null){
            throw new TransactionNotFoundException("El código de la transacción no existe en la base de datos.");
        }
        this.transactionRepository.deleteById(id);
        return "Eliminación exitosa";
    }
}