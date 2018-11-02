package com.maxchen.libfilepiker;

import android.text.TextUtils;

public class SelectOptions {

    public static final String CHOOSE_TYPE_BROWSER = "choose_type_browser";
    public static final String CHOOSE_TYPE_SCAN = "choose_type_scan";
    public static final String CHOOSE_TYPE_MEDIA = "choose_type_media";

    public int request_code;
    public boolean isSingle = false;
    public int maxCount = 10;

    public String[] mFileTypes;
    public String mSortType;

    private SelectOptions() {
    }

    private static final class InstanceHolder {
        private static final SelectOptions INSTANCE = new SelectOptions();
    }

    public static SelectOptions getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public static SelectOptions getCleanInstance() {
        SelectOptions options = getInstance();
        options.reset();
        return options;
    }

    private void reset() {

    }

    public String[] getFileTypes() {
        if (mFileTypes == null || mFileTypes.length == 0) {
            return new String[]{};
        }
        return mFileTypes;
    }

    public int getSortType() {
        if (TextUtils.isEmpty(mSortType)) {
            return 0;
        }
        return Integer.valueOf(mSortType);
    }
}
