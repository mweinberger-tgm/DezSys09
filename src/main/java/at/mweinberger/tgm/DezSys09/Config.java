package at.mweinberger.tgm.DezSys09;

import at.mweinberger.tgm.DezSys09.controller.*;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Named;

/**
 * Definieren der RESTful Endpoints.
 */
@Named
public class Config extends ResourceConfig {

    public Config() {
        this.register(Lookup.class);
        this.register(Register.class);
        this.register(Login.class);
        this.register(JacksonFeature.class);
    }
}