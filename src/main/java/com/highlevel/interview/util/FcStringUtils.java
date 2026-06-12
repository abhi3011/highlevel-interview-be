package com.highlevel.interview.util;

import java.util.UUID;

public class FcStringUtils {

  public static void ensureNonEmpty(String st, String message) {
    if (st == null || st.isEmpty()) throw new RuntimeException(message);
  }

  public static String getUuid(String prefix) {
    return prefix + getUuid();
  }

  public static String getUuid() {
    return UUID.randomUUID().toString();
  }
}
