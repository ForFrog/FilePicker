package com.maxchen.libfilepiker;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.maxchen.libfilepiker.activity.SelectFileByBrowserActivity;
import com.maxchen.libfilepiker.activity.SelectFileByScanActivity;
import com.maxchen.libfilepiker.activity.SelectPictureActivity;

/**
 * 选择器
 */
public final class SelectCreator {

    private final FilePicker filePicker;
    private final SelectOptions selectOptions;
    private String chooseType;

    public SelectCreator(FilePicker filePicker, String chooseType) {
        selectOptions = SelectOptions.getCleanInstance();
        this.chooseType = chooseType;
        this.filePicker = filePicker;
    }

    /**
     * 跳转到文件选择页面
     */
    public void start() {
        final Activity activity = filePicker.getActivity();
        if (activity == null) {
            return;
        }
        Intent intent = new Intent();
        if (SelectCreator.this.chooseType.equals(SelectOptions.CHOOSE_TYPE_BROWSER)) {
            intent.setClass(activity, SelectFileByBrowserActivity.class);
        } else if (SelectCreator.this.chooseType.equals(SelectOptions.CHOOSE_TYPE_SCAN)) {
            intent.setClass(activity, SelectFileByScanActivity.class);
        } else if (SelectCreator.this.chooseType.equals(SelectOptions.CHOOSE_TYPE_MEDIA)) {
            intent.setClass(activity, SelectPictureActivity.class);
        } else {
            return;
        }
        Fragment fragment = filePicker.getFragment();
        if (fragment != null) {
            fragment.startActivityForResult(intent, selectOptions.request_code);
        } else {
            activity.startActivityForResult(intent, selectOptions.request_code);
        }
    }

    public SelectCreator requestCode(int requestCode) {
        selectOptions.request_code = requestCode;
        return this;
    }

    public SelectCreator setMaxCount(int maxCount) {
        selectOptions.maxCount = maxCount;
        if (maxCount <= 1) {
            selectOptions.maxCount = 1;
            selectOptions.isSingle = true;
        } else {
            selectOptions.isSingle = false;
        }
        return this;
    }

    public SelectCreator setFileTypes(String... fileTypes) {
        selectOptions.mFileTypes = fileTypes;
        return this;
    }
}
