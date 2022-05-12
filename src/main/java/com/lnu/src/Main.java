package com.lnu.src;

import com.lnu.src.facade.Building;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
  private static final Logger LOGGER = LogManager.getLogger();

  public static void main(String[] args) {
    Building.createBuilding();
  }
}
