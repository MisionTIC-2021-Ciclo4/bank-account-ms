/*
	main/java/BackendAccountMsApplication.java is part of 
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

package com.misiontic.backend_account_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Class of the Project.
 * @author Carlos Andrés Sierra - casierrav@unal.edu.co
 */
@SpringBootApplication
public class BackendAccountMsApplication {

	/**
	 * This is the main function where all the application starts to run.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BackendAccountMsApplication.class, args);
	}
}