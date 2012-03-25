package net.lampetty.samples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackSample1 {

    private static final Logger log =
        LoggerFactory.getLogger(LogbackSample1.class);

    public static void main(String[] args) {
        log.debug("debug message");
        log.info("info message");
    }

}
