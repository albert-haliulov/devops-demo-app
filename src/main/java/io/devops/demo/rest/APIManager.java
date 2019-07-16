package io.devops.demo.rest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Random;

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
        description = "Number of times the checkUser is requested.")
    @Timed(name = "userCheckRequestTime", 
        absolute = true,
        description = "Time needed to operate checkUser.")
    public Properties checkUser(String userName, String password) {
        Properties props = new Properties();
        props.setProperty("status", checkUserByExternal(userName, password) ? "valid" : "invalid");
        return props;
    }
    
    
    @Counted(name = "userCheckFastAccessCount", 
        absolute = true, 
        monotonic = true,
        description = "Number of times the useCheckFast is requested.")
    @Timed(name = "userCheckFastRequestTime", 
        absolute = true,
        description = "Time needed to operate userCheckFast.")
    public Properties checkUserFast(String userName, String password) {
        Properties props = new Properties();
        props.setProperty("status", checkUserByCache(userName, password) ? "valid" : "invalid");
        return props;
    } 

    private boolean checkUserByExternal(String userName, String password){
        boolean check = false;
        try {
            if(userName != null && !userName.trim().isEmpty() &&
                password !=null && !password.trim().isEmpty()){
                    check = true;
            }else{
                // this artificial delay in order to demosntrate slow response from enterprise system
                int delay = (int)(3 * Math.random() + 1); 
                Thread.sleep((long)(delay * 1000));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return check;
    }

    private boolean checkUserByCache(String userName, String password){
        boolean check = false; 
        // here need to put some caching functionality
        if(userName != null && !userName.trim().isEmpty() &&
           password !=null && !password.trim().isEmpty()){
                check = true;
        }
        return check;
    }


}