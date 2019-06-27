package io.devops.demo.rest;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// tag::class[]
@Path("node.properties")
public class NodeProperties {
// end::class[]

    // tag::getProperties[]
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getProperties() {

        JsonObjectBuilder builder = Json.createObjectBuilder();
        String hostname = "";
        try{
            hostname =  InetAddress.getLocalHost().getHostName();
        }catch(UnknownHostException e){
            e.printStackTrace();
            hostname = "unknown host";
        }
        builder.add("os.architecture", System.getProperty("os.arch"));
        builder.add("os.name", System.getProperty("os.name"));
        builder.add("os.version", System.getProperty("os.version"));
        builder.add("os.hostname", hostname);
        /*
        System.getProperties()
              .entrySet()
              .stream()
              .forEach(entry -> builder.add((String)entry.getKey(),
                                            (String)entry.getValue()));
        */
       return builder.build();
    }
    // end::getProperties[]
}
