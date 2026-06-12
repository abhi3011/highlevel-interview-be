package com.highlevel.interview.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class FcTimeUtils {

  public static Timestamp getCurrentTimestamp() {
    return new Timestamp(System.currentTimeMillis());
  }

  public static Timestamp getCurrentTimestampWithOffsetDays(Integer offset) {
    FcObjectUtils.ensureNonNull(offset, "offset cannot be null");
    LocalDateTime updatedDateTime = getCurrentTimestamp().toLocalDateTime().plusDays(offset);
    return Timestamp.valueOf(updatedDateTime);
  }

  public static boolean isFutureTimeStamp(Timestamp timestamp) {
    FcObjectUtils.ensureNonNull(timestamp, "null timestamp");
    return timestamp.after(new Timestamp(System.currentTimeMillis()));
  }
}
