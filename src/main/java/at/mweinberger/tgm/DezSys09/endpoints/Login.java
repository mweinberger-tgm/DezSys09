package at.mweinberger.tgm.DezSys09.endpoints;

import at.mweinberger.tgm.DezSys09.data.Account;
import at.mweinberger.tgm.DezSys09.repo.AccountRepository;
import at.mweinberger.tgm.DezSys09.repo.AccountAcceptance;
import at.mweinberger.tgm.DezSys09.data.Message;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *  Diese Klasse ist zustaendig fuer den Login.
 */
@Named
@Path("/login")
@Produces({MediaType.APPLICATION_JSON})
public class Login {
    @Autowired
    private AccountRepository repository;
    /**
     * Meldet den Benutzer an, insofern die Parameter richtig sind, und er in der Datenbank vorhanden ist.
     */
    @POST
    public Response login(Account loginAccount) {
        // Sind die notwenigen Parameter da?
        if (AccountAcceptance.hasLoginData(loginAccount)) {
            Account account = this.repository.findByEmail(loginAccount.getEmail());

            // Wurde der Account gefunden?
            if (account != null) {
                int status = Status.OK.getStatusCode(); // JA -> Angemeldet
                return Response.status(status).entity(new Message(status, "Benutzer '" +account.getUsername() + "' wurde erfolgreich angemeldet.")).build();
            } else {
                int status = Status.FORBIDDEN.getStatusCode(); // NEIN -> Forbidden, keinen User gefunden
                return Response.status(status).entity(new Message(status, "Anmeldung fehlgeschlagen. User nicht vorhanden.")).build();
            }
        } else {
            int status = Status.BAD_REQUEST.getStatusCode(); // NEIN -> Erneut eingeben lassen
            return Response.status(status).entity(new Message(status, "Anmeldeinformationen unvollstaendig.")).build();
        }
    }
}