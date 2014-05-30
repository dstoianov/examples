package com.revimedia.testing.configuration.proxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by dstoianov on 5/30/2014, 12:15 PM.
 */
public class ProxyPorts {

    private static List<Integer> portsList = Collections.synchronizedList(new ArrayList<Integer>());

    public static Integer getProxyPort() {
        int newPort;
        int i = 0;
        synchronized (portsList) {
            do {
                newPort = generateNewPort();
                i++;
            } while (!(portsList.indexOf(newPort) == -1 || i > 5));
            if (i >= 5) {
                throw new Error("The is no any free port for Proxy Server");
            }
            portsList.add(newPort);
        }
        return newPort;
    }

    public static void dismissPort(Integer port) {
        synchronized (portsList) {
            boolean hasPortInList = portsList.indexOf(port) == -1;
            if (!hasPortInList) {
                int i = portsList.indexOf(port);
                portsList.remove(i);
            }
        }
    }

    private static int generateNewPort() {
        Random rand = new Random();
        int min = 8085;
        int max = 8086;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
