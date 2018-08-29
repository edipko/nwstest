package com.spotonresponse.nws;

import com.google.publicalerts.cap.feed.CapFeedParser;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class GetCapEntries {

    private static final Logger logger = LogManager.getLogger(GetCapEntries.class);

    GetCapEntries(String url) {

        try {
            CapFeedParser parser = new CapFeedParser(false);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(new URL(url)));
            List<SyndEntry> entries = feed.getEntries();

            for (SyndEntry e : entries) {
                logger.trace("Processing CAP entry: " + e.getLink());

                String alertRaw = "";
                URL link = new URL(e.getLink());
                BufferedReader in = new BufferedReader(new InputStreamReader(link.openStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.isEmpty())
                        continue;
                    alertRaw = alertRaw + line + "\n";
                }

                logger.debug("Got Alerts: " + alertRaw);
                logger.debug("****************");
            }

            /* Alert Output */
            /*
            for(Alert a : alerts){
                System.out.println("Identifier: " + a.getIdentifier());
                if (a.getStatus() == Alert.Status.ACTUAL
                        && (a.getMsgType() == Alert.MsgType.ALERT || a.getMsgType() == Alert.MsgType.UPDATE)) {

                    for (Info i : a.getInfoList()) {
                        if (i.getSeverity() == Info.Severity.SEVERE
                                || i.getSeverity() == Info.Severity.EXTREME
                                || i.getSeverity() == Info.Severity.MODERATE
                                || i.getSeverity() == Info.Severity.MINOR) {
                            System.out.println("HeadLine: " + i.getHeadline());
                            System.out.println("Description: " + i.getDescription());
                            System.out.println("Instruction: " + i.getInstruction());
                        }
                    }
                }
                System.out.println("******************");
            }
            */


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
