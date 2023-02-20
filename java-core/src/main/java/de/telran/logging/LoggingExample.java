package de.telran.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

/*
* Trace << Debug << Info << Warn << Error << Fatal
 */
public class LoggingExample {
//    private static final Logger log = LogManager.getLogger();
//    private static final Logger log = LogManager.getLogger("root");
    private static final Logger log = LogManager.getLogger(LoggingExample.class);

    public static void main(String[] args) {
//        atomics();
        try {
            loggingExample();
        } catch (Throwable e) {
            log.throwing(Level.ERROR, e);
            System.exit(-1);
        }
    }

    private static void loggingExample() {
        int a = 555;
        String s = "hello";
        log.info("Some objects: int - {}, String - {}", a, s);
        log.trace("Trace log");
        log.debug("Debug log");
        log.info("Info log");
        log.warn("Warn log");
        log.error("Error log");
        log.fatal("Fatal log");

        new Thread(() -> log.warn("From thread")).start();

        try {
            throw new RuntimeException("AAAAAAAAAAAAAAAAAA");
        } catch (RuntimeException e) {
            log.throwing(Level.ERROR, e);
        }

        throw new StackOverflowError();
    }

    private static void atomics() {
        AtomicInteger atomicInteger = new AtomicInteger(15);
        System.out.println(atomicInteger);
        System.out.println(atomicInteger.addAndGet(25));
    }
}
