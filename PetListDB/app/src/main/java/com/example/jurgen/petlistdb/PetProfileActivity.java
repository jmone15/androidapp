package com.example.jurgen.petlistdb;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jurgen.petlistdb.db.PetManagementContract;
import com.example.jurgen.petlistdb.db.PetManagementDbHelper;

public class PetProfileActivity extends AppCompatActivity {

    ImageView petImage;
    TextView petName;
    TextView petSex;
    TextView dateOfBirth;
    TextView petBreed;
    TextView petColor;
    TextView petPedigree;
    TextView petOwner;
    TextView petInfo;
    TextView petComments;
    TextView petChip;
    TextView petOwnerAddress;
    TextView petOwnerPhone;
    TextView vetName;
    TextView vetAddress;
    TextView vetPhone;


    public static final String MyPREFERENCES = "UserPreferences" ;
    public SharedPreferences sharedpreferences;


    private static final String NAME_LABEL = "Name: ";
    private static final String SEX_LABEL = "Gender: ";
    private static final String BIRTH_LABEL = "Date Of Birth: ";
    private static final String BREED_LABEL = "Breed: ";
    private static final String COLOR_LABEL = "Colour: ";
    private static final String PEDIGREE_LABEL = "Pedigree: ";
    private static final String OWNER_LABEL = "Owner Name: ";
    private static final String OWNER_ADDRESS_LABEL = "Owner Address: ";
    private static final String OWNER_PHONE_LABEL = "Owner Phone: ";
    private static final String VET_NAME_LABEL = "Vet Name: ";
    private static final String VET_ADDRESS_LABEL = "Vet Address: ";
    private static final String VET_PHONE_LABEL = "Vet Phone: ";
    private static final String COMMENT_LABEL = "Comment: ";
    private static final String CHIP_LABEL = "Chip: ";


    private static final String SORT_ORDER = PetManagementContract.Pet.COLUMN_NAME_PETNAME + " ASC";
    //private PetManagementDbHelper dbHelper;
    private String petTagString;

    private PetManagementDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_profile);
        setTitle("List of Pets");

        Intent intent = getIntent();
        petTagString = intent.getStringExtra("tag");
        //int petTag = Integer.parseInt(petTagString);
        Log.i("RETRIEVED", "Value of INT "+petTagString);

        dbHelper = new PetManagementDbHelper(this);

        petImage = (ImageView) findViewById(R.id.petImageMain);
        petName = (TextView) findViewById(R.id.dogNameText);
        petSex = (TextView) findViewById(R.id.sex);
        dateOfBirth = (TextView) findViewById(R.id.dateOfBirth);
        petBreed = (TextView) findViewById(R.id.dist);
        petColor = (TextView) findViewById(R.id.color);
        petPedigree = (TextView) findViewById(R.id.pedigree);
        petOwner = (TextView) findViewById(R.id.owner);
        petInfo = (TextView) findViewById(R.id.mainTitle);
        petComments = (TextView) findViewById(R.id.desc);
        petChip = (TextView) findViewById(R.id.chipId);
        petOwnerAddress = (TextView) findViewById(R.id.ownerAddress);
        petOwnerPhone = (TextView) findViewById(R.id.ownerPhone);
        vetName = (TextView) findViewById(R.id.vetName);
        vetAddress = (TextView) findViewById(R.id.vetAddress);
        vetPhone = (TextView) findViewById(R.id.vetPhone);

        Log.d("INSIDE THE METHOD", "Populate view: Display data in list view");

        //SQLiteDatabase db = dbHelper.getReadableDatabase();

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

            petImage.setImageResource(cursor.getInt(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_IMAGE_URI)));
            petName.setText(NAME_LABEL+cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_PETNAME)));
            petSex.setText(SEX_LABEL+cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_GENDER)));
            dateOfBirth.setText(BIRTH_LABEL+cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_DATE_OF_BIRTH)));
            petBreed.setText(BREED_LABEL+cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_BREED)));
            petColor.setText(COLOR_LABEL+cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_COLOR)));
            petPedigree.setText(PEDIGREE_LABEL+cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_DISTINGUISHING_MARKS)));
            petOwner.setText(OWNER_LABEL+cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_OWNER_NAME)));
            petComments.setText(COMMENT_LABEL+cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_COMMENTS)));
            petChip.setText(CHIP_LABEL+String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_CHIP_ID))));
            petOwnerAddress.setText(OWNER_ADDRESS_LABEL+cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_OWNER_ADDRESS)));
            petOwnerPhone.setText(OWNER_PHONE_LABEL+cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_PHONE)));
            vetName.setText(VET_NAME_LABEL+cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_VET_NAME)));
            vetAddress.setText(VET_ADDRESS_LABEL+cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_VET_ADDRESS)));
            vetPhone.setText(VET_PHONE_LABEL+cursor.getString(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_VET_PHONE)));
        }
        cursor.close();
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
                Intent intent = new Intent(PetProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;
            case R.id.Logout:
                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();

                intent = new Intent(PetProfileActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.AddPet:
                intent = new Intent(PetProfileActivity.this, CreatePetActivity.class);
                startActivity(intent);
                return true;
            case R.id.DeletePet:
                deleteSelected(petTagString);
                intent = new Intent(PetProfileActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.EditPet:
                intent = new Intent(PetProfileActivity.this, EditPetActivity.class);
                intent.putExtra("petIdTag", petTagString);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteSelected(String petTag){

        Log.d("THE VALUE", "The record of the TAG is " + petTag);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.beginTransaction();
        try {
            String selection = PetManagementContract.Pet._ID+" = ?";
            String[] selectionArgs = { petTag };
            int rowCount = db.delete(
                    PetManagementContract.Pet.TABLE_NAME,
                    selection,
                    selectionArgs
            );

            Log.d("DELETED", "The record was delete from the database " + rowCount);
            db.setTransactionSuccessful();
        }finally{
            db.endTransaction();
            }
        }
}
