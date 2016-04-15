package at.mweinberger.tgm.dezsys09;

import at.mweinberger.tgm.dezsys09.endpoints.AccountEndpoint;
import at.mweinberger.tgm.dezsys09.endpoints.Login;
import at.mweinberger.tgm.dezsys09.endpoints.Registrieren;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Named;

/**
 * Hier werden die Endpunkte definiert
 *
 * @author Stefan Geyer
 * @version 20160218.1
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