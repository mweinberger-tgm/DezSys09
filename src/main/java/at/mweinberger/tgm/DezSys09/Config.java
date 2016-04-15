package at.mweinberger.tgm.DezSys09;

import at.mweinberger.tgm.DezSys09.endpoints.AccountEndpoint;
import at.mweinberger.tgm.DezSys09.endpoints.Login;
import at.mweinberger.tgm.DezSys09.endpoints.Registrieren;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Named;

/**
 * Definieren der RESTful Endpoints.
 */
@Named
public class Config extends ResourceConfig {

    public Config() {
        this.register(AccountEndpoint.class);
        this.register(Registrieren.class);
        this.register(Login.class);
        this.register(JacksonFeature.class);
    }
}