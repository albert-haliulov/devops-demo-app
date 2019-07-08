package io.devops.demo.rest;

import java.util.Properties;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("pod")
public class PodPropertiesService {

    @Inject
    APIManager manager;

    @GET
    @Path("properties")
    @Produces(MediaType.APPLICATION_JSON)
    public Properties getProperties(){
        return manager.getPodProperties();
    }

}
