package com.us.urlshortener.util;

import java.util.UUID;

public class UrlUtil {
    public static String generateRandomCode() {
        return UUID.randomUUID().toString().substring(0, 6);
    }
}