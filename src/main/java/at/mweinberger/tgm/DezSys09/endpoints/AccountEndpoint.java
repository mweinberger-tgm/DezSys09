package at.mweinberger.tgm.DezSys09.endpoints;

import at.mweinberger.tgm.DezSys09.data.Account;
import at.mweinberger.tgm.DezSys09.repo.AccountRepository;
import at.mweinberger.tgm.DezSys09.data.Message;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Diese Klasse ist fuer das lesen der Accountdaten aus der Datenbank zustaendig
 *
 * @author mweinberger
 */
@Named
// URL PATH
@Path("/account")
@Produces({MediaType.APPLICATION_JSON})
public class AccountEndpoint {

    @Inject
    private AccountRepository repository;

    /**
     * Gibt den Account fuer die entsprechende E-Mail zurueck.
     * Wenn keine E-Mail angegeben wird, werden einfach alle Accounts zurueckgegeben
     * Returns the account with the given E-Mail.
     * If no E-Mail was specified, all accounts will be returned
     *
     * @param email String
     * @return Response
     */
    @GET
    public Response getAccount(@QueryParam("email") String email) {
        // Wenn die E-Mail nicht NULL ist dann wird nach der E-Mail gesucht.
        if (email != null) {
            Account account = this.repository.findByEmail(email);

            if (account != null) {
                return Response.status(Status.OK).entity(account).build();
            } else {
                int status = Status.NOT_FOUND.getStatusCode();
                return Response.status(status).entity(new Message(status, "Einen User zu dieser E-Mail gibt es nicht")).build();
            }
        }
        return Response.status(Status.OK).entity(this.repository.findAll()).build();
    }
}
