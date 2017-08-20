package com.example.jurgen.petlistdb;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jurgen.petlistdb.db.PetManagementContract;
import com.example.jurgen.petlistdb.db.PetManagementDbHelper;

public class EditPetActivity extends AppCompatActivity {

    EditText petNameFormEdit;
    EditText petDateOfBirthEdit;
    EditText petGenderEdit;
    EditText petBreedEdit;
    EditText petColourEdit;
    EditText petDistiguishingMarksEdit;
    EditText petChipIdEdit;
    EditText petOwnerNameEdit;
    EditText petOwnerAddressEdit;
    EditText petOwnerPhoneEdit;
    EditText petVetNameEdit;
    EditText petVetAddressEdit;
    EditText petVetPhoneEdit;
    EditText petCommentsEdit;

    Button createPetEdit;


    private String petTagString;

    public static final String MyPREFERENCES = "UserPreferences" ;
    public SharedPreferences sharedpreferences;

    private PetManagementDbHelper dbHelper;
    int imgUri;

    private static final String TAG = "EditPetActivity";

    private static final String SORT_ORDER = PetManagementContract.Pet.COLUMN_NAME_PETNAME + " ASC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pet);

        Intent intent = getIntent();
        petTagString = intent.getStringExtra("petIdTag");

        dbHelper = new PetManagementDbHelper(this);

        petNameFormEdit = (EditText) findViewById(R.id.petNameEdit);
        petDateOfBirthEdit = (EditText) findViewById(R.id.petDateOfBirthEdit);
        petGenderEdit = (EditText) findViewById(R.id.petGengerEdit);
        petBreedEdit = (EditText) findViewById(R.id.petBreedEdit);
        petColourEdit = (EditText) findViewById(R.id.petColourEdit);
        petDistiguishingMarksEdit = (EditText) findViewById(R.id.petDistMarksEdit);
        petChipIdEdit = (EditText) findViewById(R.id.petChipIdEdit);
        petOwnerNameEdit = (EditText) findViewById(R.id.petOwnerNameEdit);
        petOwnerAddressEdit = (EditText) findViewById(R.id.petOwnerAddressEdit);
        petOwnerPhoneEdit = (EditText) findViewById(R.id.petOwnPhoneEdit);
        petVetNameEdit = (EditText) findViewById(R.id.petVetNameEdit);
        petVetAddressEdit = (EditText) findViewById(R.id.petVetAddressEdit);
        petVetPhoneEdit = (EditText) findViewById(R.id.petVetPhoneEdit);
        petCommentsEdit = (EditText) findViewById(R.id.petCommentEdit);
        createPetEdit = (Button) findViewById(R.id.createPetEdit);

        String selection = PetManagementContract.Pet._ID+" = ?";
        String[] selectionArgs = { petTagString };

        Cursor cursor = getContentResolver().query(
                PetManagementContract.Pet.CONTENT_URI,
                null,
                selection,
                selectionArgs,
                SORT_ORDER
        );

        Log.d("INSIDE THE METHOD", "reached this location");

        while (cursor.moveToNext()) {
            petNameFormEdit.setText(cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_PETNAME)));
            petDateOfBirthEdit.setText(cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_DATE_OF_BIRTH)));
            petGenderEdit.setText(cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_GENDER)));
            petBreedEdit.setText(cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_BREED)));
            petColourEdit.setText(cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_COLOR)));
            petDistiguishingMarksEdit.setText(cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_DISTINGUISHING_MARKS)));
            petChipIdEdit.setText(cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_CHIP_ID)));
            petOwnerNameEdit.setText(cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_OWNER_NAME)));
            petOwnerAddressEdit.setText(cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_OWNER_ADDRESS)));
            petOwnerPhoneEdit.setText(cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_PHONE)));
            petVetNameEdit.setText(cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_VET_NAME)));
            petVetAddressEdit.setText(cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_VET_ADDRESS)));
            petVetPhoneEdit.setText(cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_VET_PHONE)));
            petCommentsEdit.setText(cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_BREED)));
            imgUri = cursor.getInt(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_IMAGE_URI));
        }
        cursor.close();

        createPetEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                db.beginTransaction();
                try{
                    ContentValues values = new ContentValues();
                    values.put(PetManagementContract.Pet.COLUMN_NAME_PETNAME, petNameFormEdit.getText().toString());
                    values.put(PetManagementContract.Pet.COLUMN_NAME_DATE_OF_BIRTH, petDateOfBirthEdit.getText().toString());
                    values.put(PetManagementContract.Pet.COLUMN_NAME_GENDER, petGenderEdit.getText().toString());
                    values.put(PetManagementContract.Pet.COLUMN_NAME_BREED, petBreedEdit.getText().toString());
                    values.put(PetManagementContract.Pet.COLUMN_NAME_COLOR, petColourEdit.getText().toString());
                    values.put(PetManagementContract.Pet.COLUMN_NAME_DISTINGUISHING_MARKS, petDistiguishingMarksEdit.getText().toString());
                    values.put(PetManagementContract.Pet.COLUMN_NAME_CHIP_ID, Integer.parseInt(petChipIdEdit.getText().toString()));
                    values.put(PetManagementContract.Pet.COLUMN_NAME_OWNER_NAME, petOwnerNameEdit.getText().toString());
                    values.put(PetManagementContract.Pet.COLUMN_NAME_OWNER_ADDRESS, petOwnerAddressEdit.getText().toString());
                    values.put(PetManagementContract.Pet.COLUMN_NAME_PHONE, petOwnerPhoneEdit.getText().toString());
                    values.put(PetManagementContract.Pet.COLUMN_NAME_VET_NAME, petVetNameEdit.getText().toString());
                    values.put(PetManagementContract.Pet.COLUMN_NAME_VET_ADDRESS, petVetAddressEdit.getText().toString());
                    values.put(PetManagementContract.Pet.COLUMN_NAME_VET_PHONE, petVetPhoneEdit.getText().toString());
                    values.put(PetManagementContract.Pet.COLUMN_NAME_COMMENTS, petCommentsEdit.getText().toString());
                    values.put(PetManagementContract.Pet.COLUMN_NAME_IMAGE_URI, imgUri);

                    String selection = PetManagementContract.Pet._ID+" = ?";
                    String[] selectionArgs = { petTagString};

                    int rowCount = db.update(
                            PetManagementContract.Pet.TABLE_NAME,
                            values,
                            selection,
                            selectionArgs
                    );

                    Log.d(TAG, "The id that was inserted is the following: ----> " + rowCount);
                    db.setTransactionSuccessful();

                }finally {
                    db.endTransaction();
                    Intent intent = new Intent(EditPetActivity.this, PetProfileActivity.class);
                    intent.putExtra("tag", petTagString);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //String statusValue = (sharedpreferences.getString("Status", ""));
        //Log.d("THE VALUE OF THE TAG", "The response is: " + statusValue);

        if(sharedpreferences.contains("Status")) {
            menu.findItem(R.id.Login).setVisible(false);

        }else{
            menu.findItem(R.id.Logout).setVisible(false);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Login:
                Intent intent = new Intent(EditPetActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;
            case R.id.Logout:
                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();

                intent = new Intent(EditPetActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.AddPet:
                intent = new Intent(EditPetActivity.this, CreatePetActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
