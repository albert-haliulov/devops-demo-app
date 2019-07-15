package io.devops.demo.rest;

import java.util.Properties;

import javax.inject.Inject;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@RequestScoped
@Path("/user")
public class UserCheckService {

    @Inject
    APIManager manager;

    @GET
    @Path("/v2/check")
    @Produces(MediaType.APPLICATION_JSON)
    public Properties checkUser(@QueryParam("username") String userName, @QueryParam("password") String password) {
        return manager.checkUser(userName, password);
    }

    @GET
    @Path("/v3/check")
    @Produces(MediaType.APPLICATION_JSON)
    public Properties checkUserFast(@QueryParam("username") String userName, @QueryParam("password") String password) {
        return manager.checkUserFast(userName, password);
    }

}
