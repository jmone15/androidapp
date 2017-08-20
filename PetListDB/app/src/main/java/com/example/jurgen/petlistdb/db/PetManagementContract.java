package com.example.jurgen.petlistdb.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Jurgen on 4/17/2017.
 */

public final class PetManagementContract {

    public static final String AUTHORITY = "com.example.jurgen.app";

    private PetManagementContract(){

    }

    public static abstract class Pet implements BaseColumns{

        public static final String TABLE_NAME = "pets";
        public static final String COLUMN_NAME_PETNAME = "petName";
        public static final String COLUMN_NAME_DATE_OF_BIRTH = "dateOfBirth";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_BREED = "breed";
        public static final String COLUMN_NAME_COLOR = "colour";
        public static final String COLUMN_NAME_DISTINGUISHING_MARKS = "distinguishingMarks";
        public static final String COLUMN_NAME_CHIP_ID = "chipId";
        public static final String COLUMN_NAME_OWNER_NAME = "ownerName";
        public static final String COLUMN_NAME_OWNER_ADDRESS = "ownerAddress";
        public static final String COLUMN_NAME_PHONE = "ownerPhone";
        public static final String COLUMN_NAME_VET_NAME = "vetName";
        public static final String COLUMN_NAME_VET_ADDRESS = "vetAddress";
        public static final String COLUMN_NAME_VET_PHONE = "vetPhone";
        public static final String COLUMN_NAME_COMMENTS = "comments";
        public static final String COLUMN_NAME_IMAGE_URI = "imageUri";

        public static final Uri CONTENT_URI = Uri.parse("content://" +
                AUTHORITY + "/" + TABLE_NAME);

    }
}
