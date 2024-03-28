package com._6core.platform.warehouse.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogger {
  private static final Logger logger = LoggerFactory.getLogger(TestLogger.class);

  public static void main(String[] args) {
    logger.debug("Debug message");
    logger.info("Info message");
    logger.warn("Warning message");
    logger.error("Error message");
  }
}
