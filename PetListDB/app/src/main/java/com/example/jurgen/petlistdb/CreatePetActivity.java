package com.example.jurgen.petlistdb;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jurgen.petlistdb.db.PetManagementContract;
import com.example.jurgen.petlistdb.db.PetManagementDbHelper;
import com.example.jurgen.petlistdb.model.Pet;

import java.util.List;

public class CreatePetActivity extends AppCompatActivity {

    EditText petNameForm;
    EditText petDateOfBirth;
    EditText petGender;
    EditText petBreed;
    EditText petColour;
    EditText petDistiguishingMarks;
    EditText petChipId;
    EditText petOwnerName;
    EditText petOwnerAddress;
    EditText petOwnerPhone;
    EditText petVetName;
    EditText petVetAddress;
    EditText petVetPhone;
    EditText petComments;

    Button createPet;



    private static final String TAG = "CreatePetActivity";


    public static final String MyPREFERENCES = "UserPreferences" ;
    public SharedPreferences sharedpreferences;

    private PetManagementDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pet);

        dbHelper = new PetManagementDbHelper(this);

        petNameForm = (EditText) findViewById(R.id.petNameForm);
        petDateOfBirth = (EditText) findViewById(R.id.petDateOfBirth);
        petGender = (EditText) findViewById(R.id.petGenger);
        petBreed = (EditText) findViewById(R.id.petBreed);
        petColour = (EditText) findViewById(R.id.petColour);
        petDistiguishingMarks = (EditText) findViewById(R.id.petDistMarks);
        petChipId = (EditText) findViewById(R.id.petChipId);
        petOwnerName = (EditText) findViewById(R.id.petOwnerName);
        petOwnerAddress = (EditText) findViewById(R.id.petOwnerAddress);
        petOwnerPhone = (EditText) findViewById(R.id.petOwnPhone);
        petVetName = (EditText) findViewById(R.id.petVetName);
        petVetAddress = (EditText) findViewById(R.id.petVetAddress);
        petVetPhone = (EditText) findViewById(R.id.petVetPhone);
        petComments = (EditText) findViewById(R.id.petComment);
        createPet = (Button) findViewById(R.id.createPet);

        createPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pet pet = new Pet();
                pet.setName(petNameForm.getText().toString());
                pet.setDateOfBirth(petDateOfBirth.getText().toString());
                pet.setGender(petGender.getText().toString());
                pet.setBreed(petBreed.getText().toString());
                pet.setColour(petColour.getText().toString());
                pet.setDistiguishingMarks(petDistiguishingMarks.getText().toString());
                pet.setChipID(Integer.parseInt(petChipId.getText().toString()));
                pet.setOwnerName(petOwnerName.getText().toString());
                pet.setOwnerAddress(petOwnerAddress.getText().toString());
                pet.setVetName(petVetName.getText().toString());
                pet.setVetAddress(petVetAddress.getText().toString());
                pet.setVetPhone(petVetPhone.getText().toString());
                pet.setComments(petComments.getText().toString());
                if(petBreed.getText().toString().equals("Dog")){
                    pet.setImageUri(R.drawable.dogavatar);
                }else if(petBreed.getText().toString().equals("Cat")){
                    pet.setImageUri(R.drawable.catavatar);
                }else{
                    pet.setImageUri(R.drawable.rabbitavatar);
                }

                insertNewPet(pet);
                String messageStatus = "Record inserted";
                Toast.makeText(CreatePetActivity.this, messageStatus, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void insertNewPet(Pet pet){

        SQLiteDatabase db = dbHelper.getWritableDatabase();


        db.beginTransaction();
        try {

            ContentValues values = new ContentValues();
            values.put(PetManagementContract.Pet.COLUMN_NAME_PETNAME, pet.getName());
            values.put(PetManagementContract.Pet.COLUMN_NAME_DATE_OF_BIRTH, pet.getDateOfBirth());
            values.put(PetManagementContract.Pet.COLUMN_NAME_GENDER, pet.getGender());
            values.put(PetManagementContract.Pet.COLUMN_NAME_BREED, pet.getBreed());
            values.put(PetManagementContract.Pet.COLUMN_NAME_COLOR, pet.getColour());
            values.put(PetManagementContract.Pet.COLUMN_NAME_DISTINGUISHING_MARKS, pet.getDistiguishingMarks());
            values.put(PetManagementContract.Pet.COLUMN_NAME_CHIP_ID, pet.getChipID());
            values.put(PetManagementContract.Pet.COLUMN_NAME_OWNER_NAME, pet.getOwnerName());
            values.put(PetManagementContract.Pet.COLUMN_NAME_OWNER_ADDRESS, pet.getOwnerAddress());
            values.put(PetManagementContract.Pet.COLUMN_NAME_PHONE, pet.getOwnerPhone());
            values.put(PetManagementContract.Pet.COLUMN_NAME_VET_NAME, pet.getVetName());
            values.put(PetManagementContract.Pet.COLUMN_NAME_VET_ADDRESS, pet.getVetAddress());
            values.put(PetManagementContract.Pet.COLUMN_NAME_VET_PHONE, pet.getVetPhone());
            values.put(PetManagementContract.Pet.COLUMN_NAME_COMMENTS, pet.getComments());
            values.put(PetManagementContract.Pet.COLUMN_NAME_IMAGE_URI, pet.getImageUri());

            long newRowId;
            newRowId = db.insert(
                    PetManagementContract.Pet.TABLE_NAME, // the table to insert to
                    null, // nullColumnHack - if the values are empty you need this
                    values); // all the data to insert

            Log.d(TAG, "The id that was inserted is the following: ----> " + newRowId);

            db.setTransactionSuccessful();

        }finally {
            db.endTransaction();
        }

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
                Intent intent = new Intent(CreatePetActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;
            case R.id.Logout:
                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();

                intent = new Intent(CreatePetActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.AddPet:
                intent = new Intent(CreatePetActivity.this, CreatePetActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
