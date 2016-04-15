package at.mweinberger.tgm.dezsys09.endpoints;

import at.mweinberger.tgm.dezsys09.data.Account;
import at.mweinberger.tgm.dezsys09.repo.AccountRepository;
import at.mweinberger.tgm.dezsys09.repo.AccountAcceptance;
import at.mweinberger.tgm.dezsys09.data.Message;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *  Diese Klasse ist für das Einloggen der Benutzer zustaendig.
 *
 * @author mweinberger
 */
@Named
// URL PATH
@Path("/login")
@Produces({MediaType.APPLICATION_JSON})
public class Login {

    @Autowired
    private AccountRepository repository;

    /**
     * Meldet den Benutzer an. Außer es gibt ihn noch nicht oder die Eingabe hat nicht gestimmt.
     *
     * @param loginAccount Account
     * @return Response
     */
    @POST
    public Response login(Account loginAccount) {
        // Required infos given?
        if (AccountAcceptance.hasLoginData(loginAccount)) {
            Account account = this.repository.findByEmail(loginAccount.getEmail());

            // Does the account exist?
            if (account != null) {
                int status = Status.OK.getStatusCode();
                return Response.status(status).entity(new Message(status, account.getUsername() + " du bist nun angemeldet")).build();
            } else {
                int status = Status.FORBIDDEN.getStatusCode();
                return Response.status(status).entity(new Message(status, "Diese Kombi gibts nicht!")).build();
            }
        } else {
            int status = Status.BAD_REQUEST.getStatusCode();
            return Response.status(status).entity(new Message(status, "Ein paar Zusatzinformationen fehlen")).build();
        }
    }
}