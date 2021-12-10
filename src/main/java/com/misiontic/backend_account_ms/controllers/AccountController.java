/*
	main/java/controllers/AccountController.java is part of 
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

import com.misiontic.backend_account_ms.exceptions.TransactionNotFoundException;
import com.misiontic.backend_account_ms.models.Account;
import com.misiontic.backend_account_ms.repositories.AccountRepository;
import com.misiontic.backend_account_ms.exceptions.AccountNotFoundException;
import org.springframework.web.bind.annotation.*;

/**
 * @author Carlos Andrés Sierra - casierrav@unal.edu.co
 */
@RestController
public class AccountController {
    private final AccountRepository accountRepository;

    /**
     * 
     * @param accountRepository
     */
    public AccountController(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }


    /**
     * 
     */
    @GetMapping("/")
    String startMessage(){
        return "Welcome to AccountMS services";
    }


    /**
     * 
     * @param username
     * @return
     */
    @GetMapping("/accounts/{username}")
    Account getAccount(@PathVariable String username){
        return this.accountRepository.findById(username).
                orElseThrow(() -> new AccountNotFoundException("No se han encontrado " +
                        "cuentas asociadas al usuario " + username + " enviado."));
    }


    /**
     * 
     * @param account
     * @return
     */
    @PostMapping("/accounts")
    Account createAccount(@RequestBody Account account){
        return accountRepository.save(account);
    }

    
    /**
     * 
     * @param username
     * @return
     */
    @DeleteMapping("/accounts/delete/{username}")
    String deleteAccount(@PathVariable String username){
        Account account = accountRepository.findById(username).orElse(null);
        if(account == null) {
            throw new AccountNotFoundException("La cuenta a eliminar no se pudo encontrar.");
        }
        this.accountRepository.deleteById(username);
        return "Eliminacion completa";
    }
}