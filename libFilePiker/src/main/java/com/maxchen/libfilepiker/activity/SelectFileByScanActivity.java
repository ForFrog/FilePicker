package com.maxchen.libfilepiker.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.maxchen.libfilepiker.R;
import com.maxchen.libfilepiker.SelectOptions;
import com.maxchen.libfilepiker.adapter.FragmentPagerAdapter;
import com.maxchen.libfilepiker.loader.EssMimeTypeCollection;
import com.maxchen.libfilepiker.model.EssFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 通过文件类型来选择文件
 */
public class SelectFileByScanActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Toolbar mToolBar;
    private MenuItem mCountMenuItem;

    private ArrayList<EssFile> mSelectedFileList = new ArrayList<>();//选中的文件

    /*当前选中排序方式的位置*/
    private int mSelectSortTypeIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_file_by_scan);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.browse_menu, menu);
        mCountMenuItem = menu.findItem(R.id.browser_select_count);
        mCountMenuItem.setTitle(String.format(getString(R.string.selected_file_count), String.valueOf(mSelectedFileList.size()), String.valueOf(SelectOptions.getInstance().maxCount)));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.browser_select_count) {
            //选中
            if (mSelectedFileList.isEmpty()) {
                return true;
            }
            //不为空
//            Intent result = new Intent();
//            result.putParcelableArrayListExtra(Const.EXTRA_RESULT_SELECTION, mSelectedFileList);
//            setResult(RESULT_OK, result);
            super.onBackPressed();
        } else if (i == R.id.browser_sort) {
            //排序
            showDialog();

        }
        return true;
    }

    private void showDialog() {
        new AlertDialog
                .Builder(this)
                .setSingleChoiceItems(R.array.sort_list_scan, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSelectSortTypeIndex = which;
                    }
                })
                .setNegativeButton("降序", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        switch (mSelectSortTypeIndex) {
//                            case 0:
//                                SelectOptions.getInstance().setSortType(FileUtils.BY_NAME_DESC);
//                                break;
//                            case 1:
//                                SelectOptions.getInstance().setSortType(FileUtils.BY_TIME_ASC);
//                                break;
//                            case 2:
//                                SelectOptions.getInstance().setSortType(FileUtils.BY_SIZE_DESC);
//                                break;
//                        }
//                        EventBus.getDefault().post(new FileScanSortChangedEvent(SelectOptions.getInstance().getSortType(),mViewPager.getCurrentItem()));
                    }
                })
                .setPositiveButton("升序", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        switch (mSelectSortTypeIndex) {
//                            case 0:
//                                SelectOptions.getInstance().setSortType(FileUtils.BY_NAME_ASC);
//                                break;
//                            case 1:
//                                SelectOptions.getInstance().setSortType(FileUtils.BY_TIME_DESC);
//                                break;
//                            case 2:
//                                SelectOptions.getInstance().setSortType(FileUtils.BY_SIZE_ASC);
//                                break;
//                        }
//                        EventBus.getDefault().post(new FileScanSortChangedEvent(SelectOptions.getInstance().getSortType(),mViewPager.getCurrentItem()));
                    }
                })
                .setTitle("请选择")
                .show();
    }


    private void initView() {
        mViewPager = findViewById(R.id.vp_select_file_scan);
        mTabLayout = findViewById(R.id.tabl_select_file_scan);
        mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("文件选择");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        String[] fileTypes = SelectOptions.getInstance().getFileTypes();
        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < SelectOptions.getInstance().getFileTypes().length; i++) {
            FileTypeListFragment f = FileTypeListFragment.newInstance(SelectOptions.getInstance().getFileTypes()[i], SelectOptions.getInstance().isSingle, SelectOptions.getInstance().maxCount, SelectOptions.getInstance().getSortType(), EssMimeTypeCollection.LOADER_ID + i);
            fragmentList.add(f);
        }
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager(), fragmentList, Arrays.asList(fileTypes));
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(fragmentList.size() - 1);
        mViewPager.addOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
