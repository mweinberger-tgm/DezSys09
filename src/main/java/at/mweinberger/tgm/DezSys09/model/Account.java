package at.mweinberger.tgm.DezSys09.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Die Modelklasse fuer die REST-API.
 */
@Entity
public class Account implements Serializable {
    @Id
    @Size(max = 50) // 50 Zeichen = ausreichend
    @NotEmpty
    private String email;
    @Size(max = 50) // 50 Zeichen = ausreichend
    private String username;
    private String password;
    public Account() { /* ... */ }

    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    /**
     * Aufbau eines Account-Objekts.
     */
    public Account(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
