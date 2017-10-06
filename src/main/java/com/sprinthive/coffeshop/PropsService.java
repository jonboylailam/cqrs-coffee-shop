package com.sprinthive.coffeshop;

import com.sprinthive.coffeshop.heath.HeathCheckException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;

@Slf4j
public class PropsService {

    private final String testValue;
    private final Environment environment;

    public PropsService(Environment environment, String testValue) {
        this.environment = environment;
        this.testValue = testValue;
    }

    public String getTestValue() {
        return testValue;
    }

    public String heathCheck() {
        String status = "OK";
        String[] activeProfiles = environment.getActiveProfiles();
        String testValue = getTestValue();
        if (activeProfiles.length > 0) {
            String profile = activeProfiles[0];
            log.debug("Health check profile: {} testValue: {}", profile, testValue);
            if ("test".equals(profile) && !testValue.equals("test.value")) {
                throw new HeathCheckException("Expected test.value Actual " + testValue);
            }
            if ("preprod".equals(profile) && !testValue.equals("preprod.value")) {
                throw new HeathCheckException("Expected perprod.value Actual " + testValue);
            }
            if ("production".equals(profile) && !testValue.equals("prod.value")) {
                throw new HeathCheckException("Expected prod.value Actual " + testValue);
            }
        } else {
            if (!testValue.equals("dev.value")) {
                throw new HeathCheckException("Expected dev.value Actual " + testValue);
            }
        }
        return status;
    }

}
