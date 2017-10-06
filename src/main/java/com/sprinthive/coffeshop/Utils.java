package com.sprinthive.coffeshop;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class Utils {

    public static void sleepRandomly() {
        try {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1) * 1000;
            log.debug("Randomly sleeping for " + randomNum + " secs");
            Thread.sleep(randomNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
