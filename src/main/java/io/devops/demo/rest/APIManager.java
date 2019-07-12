package io.devops.demo.rest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class APIManager {

    @Counted(name = "nodePropertiesAccessCount", 
        absolute = true, 
        monotonic = true,
        description = "Number of times the node.properties is requested.")
    @Timed(name = "nodePropertiesRequestTime", 
        absolute = true,
        description = "Time needed to get the node.properties.")
    public Properties getNodeProperties() {
        Properties props = new Properties();

        String hostname = "";
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            hostname = "unknown host";
        }
        props.setProperty("os.architecture", System.getProperty("os.arch"));
        props.setProperty("os.name", System.getProperty("os.name"));
        props.setProperty("os.version", System.getProperty("os.version"));
        props.setProperty("os.hostname", hostname);

        return props;
    }

    @Counted(name = "podPropertiesAccessCount", 
        absolute = true, 
        monotonic = true,
        description = "Number of times the pod.properties is requested.")
    @Timed(name = "podPropertiesRequestTime", 
        absolute = true,
        description = "Time needed to get the pod.properties.")
    public Properties getPodProperties() {
        Properties props = new Properties();
        
        props.setProperty("pod.name", System.getenv("POD_NAME") != null ? System.getenv("POD_NAME"): "unknown");
        props.setProperty("pod.namespace", System.getenv("POD_NAMESPACE") != null ? System.getenv("POD_NAMESPACE"): "unknown");
        props.setProperty("pod.ip", System.getenv("POD_IP") != null ? System.getenv("POD_IP") : "unknown" );
        props.setProperty("pod.version", System.getenv("POD_VERSION") != null ? System.getenv("POD_VERSION") : "unknown");
        props.setProperty("pod.environment", System.getenv("POD_ENVIRONMENT") != null ? System.getenv("POD_ENVIRONMENT") : "unknown");
       
       return props;
    }

    @Counted(name = "userCheckAccessCount", 
        absolute = true, 
        monotonic = true,
        description = "Number of times the user.check is requested.")
    @Timed(name = "userCheckRequestTime", 
        absolute = true,
        description = "Time needed to check the username.")
    public Properties checkUser(String userName, String password) {
        Properties props = new Properties();
        props.setProperty("username", userName);
        props.setProperty("password", "not4all");

        String status = "invalid";
        if (userName != null && !userName.trim().isEmpty()) {
            status = "valid";
        } else {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        props.setProperty("status", status);

        return props;
    }    


}