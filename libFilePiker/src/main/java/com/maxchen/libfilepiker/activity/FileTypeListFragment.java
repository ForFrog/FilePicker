package com.maxchen.libfilepiker.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.maxchen.libfilepiker.BaseFileFragment;
import com.maxchen.libfilepiker.R;

public class FileTypeListFragment extends BaseFileFragment {

    private static final String ARG_FileType = "ARG_FileType";
    private static final String ARG_IsSingle = "mIsSingle";
    private static final String ARG_MaxCount = "mMaxCount";
    private static final String ARG_SortType = "mSortType";
    private static final String ARG_Loader_Id = "ARG_Loader_Id";

    private String mFileType;
    private boolean mIsSingle;
    private int mMaxCount;
    private int mSortType;
    private int mLoaderId;


    public FileTypeListFragment() {
    }

    public static FileTypeListFragment newInstance(String fileType, boolean IsSingle, int mMaxCount, int mSortType, int loaderId) {
        FileTypeListFragment fragment = new FileTypeListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FileType, fileType);
        args.putBoolean(ARG_IsSingle, IsSingle);
        args.putInt(ARG_MaxCount, mMaxCount);
        args.putInt(ARG_SortType, mSortType);
        args.putInt(ARG_Loader_Id, loaderId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mFileType = getArguments().getString(ARG_FileType);
            mIsSingle = getArguments().getBoolean(ARG_IsSingle);
            mMaxCount = getArguments().getInt(ARG_MaxCount);
            mSortType = getArguments().getInt(ARG_SortType);
            mLoaderId = getArguments().getInt(ARG_Loader_Id);
        }
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_file_type_list;
    }
}
