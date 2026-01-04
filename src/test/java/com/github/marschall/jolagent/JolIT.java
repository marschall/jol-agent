package com.github.marschall.jolagent;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

class JolIT {

  @Test
  void agent() {
    ClassLayout layout = ClassLayout.parseClass(ArrayList.class);
    assertNotNull(layout);
  }

}
