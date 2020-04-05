
package com.dsproject;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

// This code is adapted from https://github.com/jmdns/jmdns
public class ExampleServiceRegistration {

    public static void main(String[] args) throws InterruptedException {

        try {
            // Create a JmDNS instance
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

            // Register a service
            ServiceInfo serviceInfo = ServiceInfo.create("_http._tcp.local.", "example", 8000, "path=index.html");
            jmdns.registerService(serviceInfo);

            Thread.sleep(5000);

            ServiceInfo serviceInfo2 = ServiceInfo.create("_grpc._tcp.local.", "node_helloWorld_service", 8001, "test_Service_text");

            HashMap<String, String> deviceInfoMap = new HashMap<String, String>();
            deviceInfoMap.put("testInfo", "testDetails");
            deviceInfoMap.put("testInfo2", "testDetails2");
            serviceInfo2.setText(deviceInfoMap);

            jmdns.registerService(serviceInfo2);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
