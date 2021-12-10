/*
	main/java/repositories/AccountRepository.java is part of 
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

package com.misiontic.backend_account_ms.repositories;

import com.misiontic.backend_account_ms.models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interface to connect with Account collection in MongoDB
 * @author Carlos Andrés Sierra - casierrav@unal.edu.co
 */
public interface AccountRepository extends MongoRepository<Account, String> {}
