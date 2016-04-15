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
 */
@Named
@Path("/register")
@Produces({MediaType.APPLICATION_JSON})
public class Registrieren {

    @Inject
    private AccountRepository repository;
    /**
     * Der Benutzer wird angelegt, wenn er noch nicht vorhanden ist.
     */
    @POST
    public Response register(Account account) {
        // Stimmen die Parameter?
        if (AccountAcceptance.hasFilledOut(account)) {
            // Existiert der Benutzer bereits (Merkmal Email)?
            if (this.repository.findByEmail(account.getEmail()) == null) {
                Account newAccount = new Account(account.getEmail(), account.getUsername(), account.getPassword());
                this.repository.save(newAccount); // NEIN -> Benutzer wird erstellt
                int status = Response.Status.CREATED.getStatusCode();
                return Response.status(status).entity(new Message(status,
                        "Der Account '" +newAccount.getUsername() +"' mit der Email-Adresse '" +newAccount.getEmail() +"' wurde erfolgreich erstellt.")).build();
            } else {
                int status = Response.Status.FORBIDDEN.getStatusCode(); // JA -> Benutzer wird nicht erstellt, Fehlermeldung
                return Response.status(status).entity(new Message(status, "Benutzer bereits vorhanden.")).build();
            }
        } else {
            int status = Response.Status.BAD_REQUEST.getStatusCode(); // NEIN -> Erneut eingeben lassen
            return Response.status(status).entity(new Message(status, "Zu wenig erforderliche Felder angegeben.")).build();
        }
    }
}