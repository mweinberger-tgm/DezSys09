package at.mweinberger.tgm.DezSys09.endpoints;

import at.mweinberger.tgm.DezSys09.repo.AccountRepository;
import at.mweinberger.tgm.DezSys09.data.Account;
import at.mweinberger.tgm.DezSys09.data.Message;
import at.mweinberger.tgm.DezSys09.repo.AccountAcceptance;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Diese Klasse ist für die Registrierung der Benutzer zustaendig
 *
 * @author mweinberger
 */
@Named
// URL PATH
@Path("/register")
@Produces({MediaType.APPLICATION_JSON})
public class Registrieren {

    @Inject
    private AccountRepository repository;

    /**
     * Erstellt den Account fuer den Benutzer soweit dieser noch nicht existiert.
     *
     * @param account Account
     * @return Response
     */
    @POST
    public Response register(Account account) {
        // Ueberprueft ob die angegeben Daten fuer eine Anmeldung passen.
        if (AccountAcceptance.hasFilledOut(account)) {
            // Ueberprueft ob der Benutzer schon existiert anhand der bereits gespeicherten Email Daten
            if (this.repository.findByEmail(account.getEmail()) == null) {
                Account newAccount = new Account(account.getEmail(), account.getUsername(), account.getPassword());
                this.repository.save(newAccount);
                int status = Response.Status.CREATED.getStatusCode();
                return Response.status(status).entity(new Message(status,
                        "Der Account wurde erfolgreich erstellt. " +
                                "Benutzername: " + newAccount.getUsername() + " Email: " + newAccount.getEmail())).build();
            } else {
                int status = Response.Status.FORBIDDEN.getStatusCode();
                return Response.status(status).entity(new Message(status, "UPS dich gibt es ja schon!")).build();
            }
        } else {
            int status = Response.Status.BAD_REQUEST.getStatusCode();
            return Response.status(status).entity(new Message(status, "Du musst ein bisschen mehr von dir preisgeben boy")).build();
        }
    }
}