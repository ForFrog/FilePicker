package com.maxchen.filepicker;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.maxchen.libfilepiker.FilePicker;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static int REQUEST_CODE_CHOOSE = 1008;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_file_select).setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHOOSE) {
//            ArrayList<EssFile> essFileList = data.getParcelableArrayListExtra(Const.EXTRA_RESULT_SELECTION);
//            StringBuilder builder = new StringBuilder();
//            for (EssFile file : essFileList) {
//                builder.append(file.getMimeType()).append(" | ").append(file.getName()).append("\n\n");
//            }
//            textView.setText(builder.toString());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_file_select:

                FilePicker.from(this)
                        .chooseForMimeType()
                        .requestCode(REQUEST_CODE_CHOOSE)
                        .setMaxCount(10)
                        .setFileTypes("png", "doc", "apk", "mp3", "gif", "txt", "mp4", "zip")
                        .start();
                break;
        }
    }
}
