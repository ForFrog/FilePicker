package com.maxchen.libfilepiker.loader;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.maxchen.libfilepiker.model.EssFile;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 按照文件类型查找
 */
public class EssMimeTypeCollection implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String ARGS_EXTENSION = "args_extension";
    private static final String ARGS_SORT_TYPE = "args_sort_type";
    public static final int LOADER_ID = 3;
    private WeakReference<Context> mContext;
    private LoaderManager mLoaderManager;
    private EssMimeTypeCallbacks mCallbacks;

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        Context context = mContext.get();
        if (context == null) {
            return null;
        } else {
            String extension = bundle.getString(ARGS_EXTENSION);
            int sortType = bundle.getInt(ARGS_SORT_TYPE);
            return EssMimeTypeLoader.newInstance(context, extension, sortType);
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        Context context = mContext.get();
        if (context == null) {
            return;
        }
        if (mCallbacks != null) {
            mCallbacks.onFileLoad(((EssMimeTypeLoader) loader).getEssFileList());
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        Context context = mContext.get();
        if (context == null) {
            return;
        }
        if (mCallbacks != null) {
            mCallbacks.onFileReset();
        }
    }

    public void onCreate(@NonNull FragmentActivity context, @NonNull EssMimeTypeCallbacks callbacks) {
        mContext = new WeakReference<Context>(context);
        mLoaderManager = context.getSupportLoaderManager();
        mCallbacks = callbacks;
    }

    public void onDestroy() {
        mLoaderManager.destroyLoader(LOADER_ID);
        mCallbacks = null;
    }

    public void load(String extension, int sortType, int loaderId) {
        Bundle args = new Bundle();
        args.putString(ARGS_EXTENSION, extension);
        args.putInt(ARGS_SORT_TYPE, sortType);
        if (mContext.get() == null) {
            mLoaderManager.initLoader(loaderId, args, this);
        } else {
            mLoaderManager.restartLoader(loaderId, args, this);
        }
    }

    public interface EssMimeTypeCallbacks {

        void onFileLoad(List<EssFile> essFileList);

        void onFileReset();
    }
}
