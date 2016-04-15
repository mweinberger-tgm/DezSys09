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
 * Auslesen der Accountdaten aus der DB.
 */
@Named
@Path("/account")
@Produces({MediaType.APPLICATION_JSON})
public class AccountEndpoint {
    @Inject
    private AccountRepository repository;
    /**
     * Returns the account with the given E-Mail.
     * If no E-Mail was specified, all accounts will be returned
     *
     * @param email String
     * @return Response
     */
    @GET
    public Response getAccount(@QueryParam("email") String email) {
        if (email != null) {
            Account account = this.repository.findByEmail(email);
            if (account != null) {
                return Response.status(Status.OK).entity(account).build(); // Wenn Email mit Account verknuepft -> OK
            } else {
                int status = Status.NOT_FOUND.getStatusCode(); // Ansonsten Fehlermeldung
                return Response.status(status).entity(new Message(status, "Kein User unter dieser Email-Adresse bekannt.")).build();
            }
        }
        int status = Status.NOT_FOUND.getStatusCode(); // Ansonsten Fehlermeldung
        return Response.status(status).entity(new Message(status, "Kein User unter dieser Email-Adresse bekannt.")).build();
    }
}
