package com.mprtcz.tictactoeproject.net;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by sergey on 03.07.17.
 */
@Test
public class ServerTest {


    @DataProvider(name = "serverDataProvider")
    public Object[][] serverProvider(){
        return new Object[][]{
                {1, 200, false},
                {2, 3000, true},

        };
    }


    @Test(dataProvider = "serverDataProvider")
    public void testServerCreation(int testId, int port, boolean result){




//        try {
//            server.getIp();
//            assertNotNull(server.getIpAddress());
//        } catch (UnknownHostException e) {
//            assertEquals(true, false, String.format("Exception %s", e.getLocalizedMessage()));
//        }
//        try {
//            assertEquals(true, server.connectToClient());
//        } catch (IOException | SecurityException e) {
//            assertEquals(true, false, String.format("Exception %s", e.getLocalizedMessage()));
//        }
    }



}
