package org.learn.bookstore.commons.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.helpers.MessageFormatter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * @author abhinavagarwal
 * <p>
 * Created On: 01-Oct-2023
 */
public final class BookStoreUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public BookStoreUtils() {
    }

    public static LocalDateTime getCurrentUTCLocalDateTime() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }

    public static LocalDateTime getCurrentUserLocalDateTime(String userTimeZone) {
        return LocalDateTime.now(ZoneId.of(userTimeZone));
    }

    public static LocalDateTime epochMillisToLocalDateTime(Long epochMillis) {
        return epochMillisToLocalDateTime(epochMillis, ZoneOffset.UTC);
    }

    public static LocalDateTime epochMillisToLocalDateTime(Long epochMillis, ZoneOffset zoneOffset) {
        if (epochMillis == null) {
            return null;
        }
        return Instant.ofEpochMilli(epochMillis).atZone(zoneOffset).toLocalDateTime();
    }

    public static long localDateTimeToEpochMillis(LocalDateTime localDateTime) {
        return localDateTimeToEpochMillis(localDateTime, ZoneOffset.UTC);
    }

    public static long localDateTimeToEpochMillis(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        return localDateTime.toInstant(zoneOffset).toEpochMilli();
    }

    public static String msg(String message, Object... params) {
        return MessageFormatter.arrayFormat(message, params).getMessage();
    }

    public static String escapeSQLText(String text) {
        if (StringUtils.isBlank(text)) {
            return text;
        }
        return text.replaceAll("'", "''");
    }

    public static String wrapSingleQuotes(String text) {
        return wrap(text, "'");
    }

    public static String wrapParenthesis(String text) {
        return "(" + text + ")";
    }

    public static String wrap(String text, String wrapWith) {
        return wrapWith + text + wrapWith;
    }

    public static String getSqlQuotedStrOrNull(String str) {
        if (StringUtils.isBlank(str)) {
            return "NULL";
        } else {
            return wrapSingleQuotes(escapeSQLText(str));
        }
    }

    public static String getSqlQuotedDateOrNull(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return "NULL";
        }
        return wrapSingleQuotes(escapeSQLText(String.valueOf(localDateTime)));
    }
}
