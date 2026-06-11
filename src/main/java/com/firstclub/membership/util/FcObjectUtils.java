package com.firstclub.membership.util;

public class FcObjectUtils {

  public static void ensureNonNull(Object ob, Class clazz) {
    if (ob == null) throw new RuntimeException("Null object of type " + clazz.getName());
  }

  public static void ensureNonNull(Object ob, String message) {
    if (ob == null) throw new RuntimeException(message);
  }
}
