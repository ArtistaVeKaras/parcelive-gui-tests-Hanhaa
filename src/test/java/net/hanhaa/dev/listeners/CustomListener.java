package net.hanhaa.dev.listeners;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomListener extends RunListener {

    private Logger logger = LoggerFactory.getLogger(CustomListener.class);

    @Override
    public void testFinished(Description description) throws Exception {
        logger.debug("Listener.testStepFinished: executed " + description.getDisplayName());
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        logger.debug("Listener.testIgnored " + description.getDisplayName());
    }
}
