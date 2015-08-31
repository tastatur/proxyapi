package de.unidue.proxyapi.util.impl;

import org.apache.commons.lang3.StringUtils;

public final class EntityUtils {

    private EntityUtils() {
    }

    public static String getNameFromLocalNameOrUri(final String localName, final String uri) {
        return StringUtils.defaultIfBlank(localName, StringUtils.substringAfterLast(uri, "/"));
    }
}
