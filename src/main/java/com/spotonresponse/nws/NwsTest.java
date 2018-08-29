package com.spotonresponse.nws;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;

import static java.time.Instant.now;

public class NwsTest {
    private static final Logger logger = LogManager.getLogger(NwsTest.class);

    public static void main(String args[]) {
//https://alerts.weather.gov/cap/al.php?x=0
        //https://alerts.weather.gov/cap/us.php?x=0
        //
        Instant t1 = now();
        GetCapEntries gce = new GetCapEntries("https://alerts.weather.gov/cap/us.php?x=0");
        Instant t2 = now();
        logger.debug("Fetch and update NWS CAP entries - Duration: " + Duration.between(t1, t2));

    }


}
