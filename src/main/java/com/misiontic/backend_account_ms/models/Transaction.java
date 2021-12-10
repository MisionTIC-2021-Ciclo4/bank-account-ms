/*
	main/java/models/Transaction.java is part of "Demo MisionTIC-2021 C4 - 
    BackendAccountMS", created by Carlos Andrés Sierra.

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


package com.misiontic.backend_account_ms.models;

import org.springframework.data.annotation.Id;
import java.util.Date;

/**
 * @author Carlos Andrés Sierra - casierrav@unal.edu.co
 */
public class Transaction {
    @Id
    private String  id;
    private String  usernameOrigin;
    private String  usernameDestiny;
    private Integer value;
    private String  note;
    private Date    date;

    public Transaction(String id, String usernameOrigin, String usernameDestiny, Integer value,
                       String note, Date date){
        this.id              = id;
        this.usernameOrigin  = usernameOrigin;
        this.usernameDestiny = usernameDestiny;
        this.value           = value;
        this.note            = note;
        this.date            = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsernameOrigin() {
        return usernameOrigin;
    }

    public void setUsernameOrigin(String usernameOrigin) {
        this.usernameOrigin = usernameOrigin;
    }

    public String getUsernameDestiny() {
        return usernameDestiny;
    }

    public void setUsernameDestiny(String usernameDestiny) {
        this.usernameDestiny = usernameDestiny;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
