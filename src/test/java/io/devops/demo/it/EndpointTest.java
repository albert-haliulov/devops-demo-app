package io.devops.demo.it;

import static org.junit.Assert.assertEquals;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.provider.jsrjsonp.JsrJsonpProvider;
import org.junit.Test;

public class EndpointTest {

    @Test
    public void testGetProperties() {
        // tag::systemProperties[]
        String port = System.getProperty("liberty.test.port");
        String war = System.getProperty("war.name");
        String url = "http://localhost:" + port + "/" + war + "/";
        // end::systemProperties[]

        // tag::clientSetup[]
        Client client = ClientBuilder.newClient();
        client.register(JsrJsonpProvider.class);
        // end::clientSetup[]

        // tag::request[]
        WebTarget target = client.target(url + "api/node.properties");
        Response response = target.request().get();
        // end::request[]

        // tag::response[]
        assertEquals("Incorrect response code from " + url, 
                     Response.Status.OK.getStatusCode(), response.getStatus());
        // end::response[]

        // tag::body[]
        JsonObject obj = response.readEntity(JsonObject.class);

        assertEquals("The system property for the local and remote JVM should match",
                     System.getProperty("os.name"),
                     obj.getString("os.name"));
        // end::body[]
        response.close();
    }
}
