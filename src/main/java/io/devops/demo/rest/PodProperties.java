package io.devops.demo.rest;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// tag::class[]
@Path("pod.properties")
public class PodProperties {
// end::class[]

    // tag::getProperties[]
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getProperties() {

        JsonObjectBuilder builder = Json.createObjectBuilder();
        
        builder.add("pod.name", System.getenv("POD_NAME") != null ? System.getenv("POD_NAME"): "unknown");
        builder.add("pod.namespace", System.getenv("POD_NAMESPACE") != null ? System.getenv("POD_NAMESPACE"): "unknown");
        builder.add("pod.ip", System.getenv("POD_IP") != null ? System.getenv("POD_IP") : "unknown" );
        builder.add("pod.version", System.getenv("POD_VERSION") != null ? System.getenv("POD_VERSION") : "unknown");
        builder.add("pod.environment", System.getenv("POD_ENVIRONMENT") != null ? System.getenv("POD_ENVIRONMENT") : "unknown");
       
       return builder.build();
    }
    // end::getProperties[]
}
