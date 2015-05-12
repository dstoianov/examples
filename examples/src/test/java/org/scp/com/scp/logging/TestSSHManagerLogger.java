package org.scp.com.scp.logging;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by User on 12/16/13.
 */
public class TestSSHManagerLogger {


    @Test
    public void testSendCommand() {
        System.out.println("sendCommand");

        /**
         * YOU MUST CHANGE THE FOLLOWING
         * FILE_NAME: A FILE IN THE DIRECTORY
         * USER: LOGIN USER NAME
         * PASSWORD: PASSWORD FOR THAT USER
         * HOST: IP ADDRESS OF THE SSH SERVER
         **/
        String command = "ls /tmp/local/root/www/RemoteTwo2.txt";
        String userName = "admin";
        String password = "IlK!M@";
        String connectionIP = "192.168.0.1";
        SSHManager instance = new SSHManager(userName, password, connectionIP, "");
        String errorMessage = instance.connect();

//        if (errorMessage != null) {
//            System.out.println(errorMessage);
//            fail();
//        }

        String expResult = "FILE_NAME\n";
        // call sendCommand for each command and the output
        //(without prompts) is returned
        String result = instance.sendCommand(command);
        // close only after all commands are sent
        instance.close();
        assertEquals(expResult, result);
    }
}
