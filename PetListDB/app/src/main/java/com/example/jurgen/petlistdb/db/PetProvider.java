package com.example.jurgen.petlistdb.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by jmone on 5/8/17.
 */

public class PetProvider extends ContentProvider {

    // Creates a UriMatcher object.
    private static final UriMatcher uriMatcher;

    private static final int PETS = 1;
    private static final int PET_ITEM = 2;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PetManagementContract.AUTHORITY, PetManagementContract.Pet.TABLE_NAME, PETS);
        uriMatcher.addURI(PetManagementContract.AUTHORITY, PetManagementContract.Pet.TABLE_NAME + "/#", PET_ITEM);
    }

    private PetManagementDbHelper dbHelper;

    @Nullable
    @Override
    public boolean onCreate() {
        dbHelper = new PetManagementDbHelper(getContext());
        return true;
    }


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case PET_ITEM:
                selection = PetManagementContract.Pet._ID + "=" + uri.getLastPathSegment();
                break;
            case PETS:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        // Get the database and run the query
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(PetManagementContract.Pet.TABLE_NAME, projection, selection, selectionArgs,
                null, null, sortOrder);

        // Tell the cursor what uri to watch, so it knows when its source data changes
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }


    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (uriMatcher.match(uri) != PETS) {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        // Get the database and insert
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long rowId = db.insert(PetManagementContract.Pet.TABLE_NAME, null, values);
        if (rowId > 0) {
            Uri studentUri = ContentUris.withAppendedId(PetManagementContract.Pet.CONTENT_URI, rowId);

            // Signal all cursor which monitor this URI that there is new data and
            // they should re-query
            getContext().getContentResolver().notifyChange(studentUri, null);
            return studentUri;
        }

        throw new SQLException("Failed to insert row into " + uri);

    }

    @Nullable
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case PET_ITEM:
                selection = PetManagementContract.Pet._ID + "=" + uri.getLastPathSegment();
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        // Get the database and delete
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = db.delete(PetManagementContract.Pet.TABLE_NAME, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }

    @Nullable
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case PET_ITEM:
                selection = PetManagementContract.Pet._ID + "=" + uri.getLastPathSegment();
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        // Get the database and update
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = db.update(PetManagementContract.Pet.TABLE_NAME, values, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);

        return count;

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        String subType;
        switch (uriMatcher.match(uri)) {
            case PETS:
                subType = "vnd.android.cursor.dir/";
                break;
            case PET_ITEM:
                subType = "vnd.android.cursor.item/";
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        return subType += "vnd." + PetManagementContract.AUTHORITY + "." + PetManagementContract.Pet.TABLE_NAME;

    }

}
