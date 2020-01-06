package net.hanhaa.dev.steps;

import net.hanhaa.dev.config.CucumberConfiguration;
import net.hanhaa.dev.pages.BaseAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = CucumberConfiguration.class)
@SpringBootTest
public class StepsContext {

    @Autowired
    BaseAPI baseAPI;
}
