package com.petrichor.sincerity.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerUtils {
    public Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
