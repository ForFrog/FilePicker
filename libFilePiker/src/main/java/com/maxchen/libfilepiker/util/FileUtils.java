package com.maxchen.libfilepiker.util;

import android.webkit.MimeTypeMap;

public final class FileUtils {

    public static final int BY_NAME_ASC = 0;
    public static final int BY_NAME_DESC = 1;
    public static final int BY_TIME_ASC = 2;
    public static final int BY_TIME_DESC = 3;
    public static final int BY_SIZE_ASC = 4;
    public static final int BY_SIZE_DESC = 5;
    public static final int BY_EXTENSION_ASC = 6;
    public static final int BY_EXTENSION_DESC = 7;

    /**
     * 获取文件的MIME类型
     */
    public static String getMimeType(String pathOrUrl) {
        String ext = getExtension(pathOrUrl);
        MimeTypeMap map = MimeTypeMap.getSingleton();
        String mimeType;
        if (map.hasExtension(ext)) {
            mimeType = map.getMimeTypeFromExtension(ext);
        } else {
            mimeType = "*/*";
        }
        return mimeType;
    }

    /**
     * 获取文件后缀,不包括“.”
     */
    public static String getExtension(String pathOrUrl) {
        int dotPos = pathOrUrl.lastIndexOf('.');
        if (0 <= dotPos) {
            return pathOrUrl.substring(dotPos + 1);
        } else {
            return "ext";
        }
    }
}
