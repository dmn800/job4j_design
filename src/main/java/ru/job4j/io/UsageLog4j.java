package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        boolean student = true;
        char group = 'a';
        byte code = 7;
        double height = 1.9;
        float id = 3234f;
        long max = 525235423L;
        short sh = 23423;

        LOG.debug("\nUser info name - {}, age - {}, student - {}, group - {}, "
                        + "code - {}, height - {}, id - {}, max - {}, sh - {}",
                name, age, student, group, code, height, id, max, sh);
    }
}
