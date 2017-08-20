package com.example.jurgen.petlistdb.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jurgen on 4/17/2017.
 */

public class PetManagementDbHelper extends SQLiteOpenHelper {


    private static final String TAG = "HELPER ACTIVITY";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PetManagement.db";

    private static final String SQL_CREATE_PETS =
            "CREATE TABLE "+PetManagementContract.Pet.TABLE_NAME+ " ("+
                    PetManagementContract.Pet._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    PetManagementContract.Pet.COLUMN_NAME_PETNAME+" TEXT, "+
                    PetManagementContract.Pet.COLUMN_NAME_DATE_OF_BIRTH+" TEXT, "+
                    PetManagementContract.Pet.COLUMN_NAME_GENDER+" TEXT, "+
                    PetManagementContract.Pet.COLUMN_NAME_BREED+" TEXT, "+
                    PetManagementContract.Pet.COLUMN_NAME_COLOR+" TEXT, "+
                    PetManagementContract.Pet.COLUMN_NAME_DISTINGUISHING_MARKS+" TEXT, "+
                    PetManagementContract.Pet.COLUMN_NAME_CHIP_ID+" INTEGER UNIQUE, "+
                    PetManagementContract.Pet.COLUMN_NAME_OWNER_NAME+" TEXT, "+
                    PetManagementContract.Pet.COLUMN_NAME_OWNER_ADDRESS+" TEXT, "+
                    PetManagementContract.Pet.COLUMN_NAME_PHONE+" TEXT, "+
                    PetManagementContract.Pet.COLUMN_NAME_VET_NAME+" TEXT, "+
                    PetManagementContract.Pet.COLUMN_NAME_VET_ADDRESS+" TEXT, "+
                    PetManagementContract.Pet.COLUMN_NAME_VET_PHONE+" TEXT, "+
                    PetManagementContract.Pet.COLUMN_NAME_COMMENTS+" TEXT, "+
                    PetManagementContract.Pet.COLUMN_NAME_IMAGE_URI+" INTEGER"+
                    " )";

    private static final String SQL_DELETE_PETS =
            "DROP TABLE IF EXISTS " + PetManagementContract.Pet.TABLE_NAME;

    public PetManagementDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PETS);
        Log.d(TAG, "Populate view: The create function was passed");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PETS);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
