package org.blottn.silk.core;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Nick on 27/11/2017.
 */

public class Provider extends ContentProvider {
    @Override
    public boolean onCreate() {
        //some init stuff..
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        //need to use a Uri matcher, see: https://developer.android.com/guide/topics/providers/content-provider-creating.html
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        // probably don't need this either
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //not allowed
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        //not allowed
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        //not allowed
        return 0;
    }
}
