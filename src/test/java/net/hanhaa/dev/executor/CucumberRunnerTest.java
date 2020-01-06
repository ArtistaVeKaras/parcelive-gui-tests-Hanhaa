package net.hanhaa.dev.executor;

import cucumber.api.junit.Cucumber;
import net.hanhaa.dev.listeners.CustomListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import java.io.IOException;

public class CucumberRunnerTest extends Cucumber {
    public CucumberRunnerTest(Class clazz) throws InitializationError, IOException {
        super(clazz);
    }

    @Override
    public void run(RunNotifier notifier) {
        notifier.addFirstListener(new CustomListener());
        super.run(notifier);
    }
}

