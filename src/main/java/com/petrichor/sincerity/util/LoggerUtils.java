package com.petrichor.sincerity.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerUtils {
    public Logger getLogger() {
        return LoggerFactory.getLogger(getClass());
    }
}
