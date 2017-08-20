package com.example.jurgen.petlistdb;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.jurgen.petlistdb.db.PetManagementContract;
import com.example.jurgen.petlistdb.db.PetManagementDbHelper;
import com.example.jurgen.petlistdb.fragments.BrowsingListFragment;
import com.example.jurgen.petlistdb.fragments.MainFragment;
import com.example.jurgen.petlistdb.model.Pet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener {

    private List<Pet> petList;

    private static final String TAG = "MainActivity";

    public static final String MyPREFERENCES = "UserPreferences" ;
    public SharedPreferences sharedpreferences;

    private PetManagementDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("List of Pets");

        dbHelper = new PetManagementDbHelper(this);

        addRecordsToArrayList();
        invalidateOptionsMenu();
    }


    private void addRecordsToArrayList(){

         petList = new ArrayList<>();

        petList.add(new Pet(1, "Johny", "21-10-2017", "Male", "Dog", "White", "None", 21313141, "Takis Ioannou", "SDA 21", "213155525", "Norman White", "faf 121", "213414414", "Est id utroque salutandi periculis, ius dolore neglegentur ids", R.drawable.dog1));
        petList.add(new Pet(2, "Alex", "21-10-2008", "Male", "Dog", "White", "None", 21313142, "Takis Ioannou", "SDA 21", "213155525", "Norman White", "faf 121", "213414414", "Est id utroque salutandi periculis, ius dolore neglegentur ids", R.drawable.dog2));
        petList.add(new Pet(3, "Joey", "21-10-2010", "Male", "Dog", "White", "None", 21313143, "Takis Ioannou", "SDA 21", "213155525", "Norman White", "faf 121", "213414414", "Est id utroque salutandi periculis, ius dolore neglegentur ids", R.drawable.dog3));
        petList.add(new Pet(4, "Jake", "21-10-2011", "Male", "Dog", "White", "None", 21313144, "Takis Ioannou", "SDA 21", "213155525", "Norman White", "faf 121", "213414414", "Est id utroque salutandi periculis, ius dolore neglegentur ids", R.drawable.dog4));
        petList.add(new Pet(5, "Francois", "21-10-2012", "Male", "Dog", "White", "None", 21313145, "Takis Ioannou", "SDA 21", "213155525", "Norman White", "faf 121", "213414414", "Est id utroque salutandi periculis, ius dolore neglegentur ids", R.drawable.dog5));
        petList.add(new Pet(6, "Lilly", "21-10-2013", "Female", "Dog", "White", "None", 21313146, "Takis Ioannou", "SDA 21", "213155525", "Norman White", "faf 121", "213414414", "Est id utroque salutandi periculis, ius dolore neglegentur ids", R.drawable.dog6));
        petList.add(new Pet(7, "Dawg", "21-10-2015", "Male", "Dog", "White", "None", 21313147, "Takis Ioannou", "SDA 21", "213155525", "Norman White", "faf 121", "213414414", "Est id utroque salutandi periculis, ius dolore neglegentur ids", R.drawable.dog7));
        petList.add(new Pet(8, "Cathrine", "11-10-2011", "Female", "Cat", "Red-White", "None", 21310148, "Lena Arnolod", "SMA 22", "213153525", "Jake Marched", "KRA 111", "2134124414", "Est id utroque salutandi periculis, ius dolore neglegentur id", R.drawable.cat1));
        petList.add(new Pet(9, "Persi", "11-10-2009", "Female", "Cat", "Red-White", "None", 21310149, "Lena Arnolod", "SMA 22", "213153525", "Jake Marched", "KRA 111", "2134124414", "Est id utroque salutandi periculis, ius dolore neglegentur id", R.drawable.cat2));
        petList.add(new Pet(10, "Hulia", "11-10-2008", "Female", "Cat", "Red-White", "None", 213101410, "Lena Arnolod", "SMA 22", "213153525", "Jake Marched", "KRA 111", "2134124414", "Est id utroque salutandi periculis, ius dolore neglegentur id", R.drawable.cat3));
        petList.add(new Pet(11, "Cain", "11-10-2012", "Male", "Cat", "Red-White", "None", 213101411, "Lena Arnolod", "SMA 22", "213153525", "Jake Marched", "KRA 111", "2134124414", "Est id utroque salutandi periculis, ius dolore neglegentur id", R.drawable.cat4));
        petList.add(new Pet(12, "Rover", "11-10-2013", "Female", "Cat", "Red-White", "None", 213101412, "Lena Arnolod", "SMA 22", "213153525", "Jake Marched", "KRA 111", "2134124414", "Est id utroque salutandi periculis, ius dolore neglegentur id", R.drawable.cat5));
        petList.add(new Pet(13, "Cookie", "11-10-2014", "Male", "Other", "Red-White", "None", 213101413, "Lena Arnolod", "SMA 22", "213153525", "Jake Marched", "KRA 111", "2134124414", "Est id utroque salutandi periculis, ius dolore neglegentur id", R.drawable.horse));
        petList.add(new Pet(14, "Voukefalas", "11-10-2015", "Male", "Other", "Red-White", "None", 213101414, "Lena Arnolod", "SMA 22", "213153525", "Jake Marched", "KRA 111", "2134124414", "Est id utroque salutandi periculis, ius dolore neglegentur id", R.drawable.panther));
        petList.add(new Pet(15, "Bagera", "11-10-2016", "Male", "Other", "Red-White", "None", 213101415, "Lena Arnolod", "SMA 22", "213153525", "Jake Marched", "KRA 111", "2134124414", "Est id utroque salutandi periculis, ius dolore neglegentur id", R.drawable.parrot));
        petList.add(new Pet(16, "Wall Street", "11-10-2015", "Male", "Other", "Red-White", "None", 213101416, "Lena Arnolod", "SMA 22", "213153525", "Jake Marched", "KRA 111", "2134124414", "Est id utroque salutandi periculis, ius dolore neglegentur id", R.drawable.wolf));

        insertRecords(petList);
    }




    private void insertRecords(List<Pet> petList){

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.beginTransaction();
        try{

            ContentValues values = new ContentValues();
            for (Pet pet: petList){
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

                Log.d(TAG, "The id that was inserted is the following: ----> "+newRowId);
            }
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
        menu.findItem(R.id.DeletePet).setVisible(false);
        menu.findItem(R.id.EditPet).setVisible(false);

        return super.onPrepareOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Login:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;
            case R.id.Logout:
                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();

                intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.AddPet:
                intent = new Intent(MainActivity.this, CreatePetActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSpeciesSelected(String petSpecies) {

        View fragmentContainer = findViewById(R.id.fragment_container);
        boolean isDualPane = fragmentContainer != null && fragmentContainer.getVisibility() == View.VISIBLE;

        if (isDualPane) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, BrowsingListFragment.newInstance(petSpecies));
            fragmentTransaction.commit();
        } else {
            startActivity(BrowsingListActivity.getStartIntent(this, petSpecies));
        }

    }
}
