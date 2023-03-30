package com.project.races.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntitiyNotCreatedException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(EntitiyNotCreatedException.class);

    public EntitiyNotCreatedException(String msg) {
        super(msg);
        logger.error("Thrown EntitiyNotCreatedException with msg:" + msg);
    }
}
